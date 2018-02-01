package com.ftn.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.model.dto.HomeInsuranceDTO;
import com.ftn.model.dto.InsurancePolicyDTO;
import com.ftn.model.dto.InternationalTravelInsuranceDTO;
import com.ftn.model.dto.RoadsideAssistanceInsuranceDTO;
import com.ftn.model.dto.*;
import com.ftn.service.PriceService;
import com.ftn.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zlatan on 1/17/18.
 */
@Service
public class PriceServiceImpl implements PriceService {

    @Value("${dc.home}")
    private String dc_home;

    @Value("${dc.international.travel.insurance}")
    private String dc_international_travel_insurance;

    @Value("${dc.home.insurance}")
    private String dc_home_insurance;

    @Value("${dc.roadside.assistance.insurance}")
    private String dc_roadside_assistance_insurance;

    @Value("${dc.risk}")
    private String dc_risk;

    @Value("${dc.pricelist}")
    private String dc_pricelist;

    private final RuleService ruleService;

    @Autowired
    public PriceServiceImpl(RuleService ruleService) {
        this.ruleService = ruleService;
    }

    public enum InsuranceType{
        TRAVEL,
        HOME,
        CAR
    }

    @Override
    public List<Double> getPrice(InsurancePolicyDTO insurancePolicyDTO) {
        ArrayList<Double> priceList = new ArrayList<>();

        double internationalTravelInsurancePrice, homeInsurancePrice, roadsideAssistancePrice;

        InternationalTravelInsuranceDTO internationalTravelInsuranceDTO = insurancePolicyDTO.getInternationalTravelInsurance();
        HomeInsuranceDTO homeInsuranceDTO = insurancePolicyDTO.getHomeInsurance();
        RoadsideAssistanceInsuranceDTO roadsideAssistanceInsuranceDTO = insurancePolicyDTO.getRoadsideAssistanceInsurance();

        if(internationalTravelInsuranceDTO == null){
            internationalTravelInsurancePrice = 0.0;
            priceList.add(internationalTravelInsurancePrice);
        }else{
            List<RiskDTO> riskDTOS = getRiskForInsurance(insurancePolicyDTO, InsuranceType.TRAVEL);
            internationalTravelInsurancePrice = calculatePrice(riskDTOS);
            priceList.add(internationalTravelInsurancePrice);
        }

        if(homeInsuranceDTO == null){
            homeInsurancePrice = 0.0;
            priceList.add(homeInsurancePrice);
        }else{
            List<RiskDTO> riskDTOS = getRiskForInsurance(insurancePolicyDTO, InsuranceType.HOME);
            homeInsurancePrice = calculatePrice(riskDTOS);
            priceList.add(homeInsurancePrice);
        }

        if(roadsideAssistanceInsuranceDTO == null){
            roadsideAssistancePrice = 0.0;
            priceList.add(roadsideAssistancePrice);
        }else{
            List<RiskDTO> riskDTOS = getRiskForInsurance(insurancePolicyDTO, InsuranceType.CAR);
            roadsideAssistancePrice = calculatePrice(riskDTOS);
            priceList.add(roadsideAssistancePrice);
        }

        insurancePolicyDTO.setTotalPrice(internationalTravelInsurancePrice + homeInsurancePrice + roadsideAssistancePrice);
        InsurancePolicyDTO insurancePolicyDTO1 = ruleService.getInsurancePolicy(insurancePolicyDTO);

        priceList.add(insurancePolicyDTO1.getTotalPrice());

        return priceList;
    }

    private List<RiskDTO> getRiskForInsurance(InsurancePolicyDTO insurancePolicyDTO, InsuranceType type){
        List<RiskDTO> riskDTOS = new ArrayList<>();

        if(type.equals(InsuranceType.TRAVEL)){
            riskDTOS = insurancePolicyDTO.getInternationalTravelInsurance().getRisks();
        }else if(type.equals(InsuranceType.HOME)){
            riskDTOS = insurancePolicyDTO.getHomeInsurance().getRisks();
        }else if(type.equals(InsuranceType.CAR)){
            riskDTOS = insurancePolicyDTO.getRoadsideAssistanceInsurance().getRisks();
        }
        return riskDTOS;
    }

    private double calculatePrice(List<RiskDTO> riskDTOS){
        double totalPrice = 0.0;
        RestTemplate restTemplate = new RestTemplate();

        for (RiskDTO risk: riskDTOS) {
            long riskId = risk.getId();
            String URIPriceList = dc_home + dc_pricelist + "/currentlyActive";
            String URIRisks = dc_home + dc_risk + "/" + riskId;
            ResponseEntity<RiskDTO> riskDTOResponseEntity = restTemplate.getForEntity(URIRisks, RiskDTO.class);
            ResponseEntity<PricelistDTO> activePriceListResponse = restTemplate.getForEntity(URIPriceList, PricelistDTO.class);
            RiskDTO riskDTOFull = riskDTOResponseEntity.getBody();
            PricelistDTO activePriceList = activePriceListResponse.getBody();

            if(riskDTOFull != null && activePriceList != null){
                for (PricelistItemDTO riskPrice : riskDTOFull.getPricelistItem()) {
                   List<PricelistItemDTO> activePrices = activePriceList.getPricelistItems();
                    for (PricelistItemDTO activePrice : activePrices) {
                        if(activePrice.getId() == riskPrice.getId()){
                            totalPrice += riskPrice.getPrice()* riskPrice.getCoefficient();
                            break;
                        }
                    }
                }
            }
         }
        return totalPrice;
    }
}
