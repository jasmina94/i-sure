package com.ftn.service.implementation;

import com.ftn.model.dto.HomeInsuranceDTO;
import com.ftn.model.dto.InsurancePolicyDTO;
import com.ftn.model.dto.InternationalTravelInsuranceDTO;
import com.ftn.model.dto.RoadsideAssistanceInsuranceDTO;
import com.ftn.service.PriceService;
import com.ftn.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zlatan on 1/17/18.
 */
@Service
public class PriceServiceImpl implements PriceService {

    private final RuleService ruleService;

    @Autowired
    public PriceServiceImpl(RuleService ruleService) {
        this.ruleService = ruleService;
    }

    @Override
    public List<Double> getPrice(InsurancePolicyDTO insurancePolicyDTO) {
        ArrayList<Double> priceList = new ArrayList<>();

        InternationalTravelInsuranceDTO internationalTravelInsuranceDTO = ruleService.getInternationalTravelInsurance(insurancePolicyDTO.getInternationalTravelInsurance());
        HomeInsuranceDTO homeInsuranceDTO = ruleService.getHomeInsurance(insurancePolicyDTO.getHomeInsurance());
        RoadsideAssistanceInsuranceDTO roadsideAssistanceInsuranceDTO = ruleService.getRoadsideAssistanceInsurance(insurancePolicyDTO.getRoadsideAssistanceInsurance());

//        priceList.add(internationalTravelInsuranceDTO.getPrice());
//        priceList.add(homeInsuranceDTO.getPrice());
//        priceList.add(roadsideAssistanceInsuranceDTO.getPrice());

        priceList.add(1.0);
        priceList.add(50.0);
        priceList.add(100.0);

        return priceList;
    }
}
