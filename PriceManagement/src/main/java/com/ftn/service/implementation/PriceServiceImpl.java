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
import java.util.Date;
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

//      priceList.add(internationalTravelInsuranceDTO.getPrice());
//      priceList.add(homeInsuranceDTO.getPrice());
//      priceList.add(roadsideAssistanceInsuranceDTO.getPrice());

        priceList.add(1.0);
        priceList.add(50.0);
        priceList.add(100.0);

        return priceList;
    }
}

//    InternationalTravelInsuranceDTO iti = new InternationalTravelInsuranceDTO();
//    iti.setIssueDate(new Date());
//    iti.setDurationInDays(7);
//    iti.setNumberOfPersons(3);
//    iti.setPrice(1200);
//    System.out.println(iti);
//    InternationalTravelInsuranceDTO iti2 = ruleService.getInternationalTravelInsurance(iti);
//    System.out.println(iti2);
//
//    HomeInsuranceDTO hi = new HomeInsuranceDTO();
//    hi.setOwnerFirstName("Zl");
//    hi.setOwnerLastName("Pr");
//    hi.setAddress("Cmelik");
//    hi.setPersonalId("1111111111111");
//    hi.setPrice(1200);
//    System.out.println(hi);
//    HomeInsuranceDTO hi2 = ruleService.getHomeInsurance(hi);
//    System.out.println(hi2);
//
//    RoadsideAssistanceInsuranceDTO rai = new RoadsideAssistanceInsuranceDTO();
//    rai.setOwnerFirstName("Zl");
//    rai.setOwnerLastName("Pr");
//    rai.setPersonalId("2222222222222");
//    rai.setCarBrand("Yugo");
//    rai.setCarType("Limuzina");
//    rai.setYearOfManufacture("1999");
//    rai.setLicencePlateNumber("11111111");
//    rai.setUndercarriageNumber("22");
//    rai.setPrice(101);
//    System.out.println(rai);
//    RoadsideAssistanceInsuranceDTO rai2 = ruleService.getRoadsideAssistanceInsurance(rai);
//    System.out.println(rai2);
