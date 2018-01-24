package com.ftn.service;

import java.util.List;

import com.ftn.model.dto.InsurancePolicyDTO;

/**
 * Created by zlatan on 1/17/18.
 */
public interface PriceService {

    List<Double> getPrice(InsurancePolicyDTO insurancePolicyDTO);

}
