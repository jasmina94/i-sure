package com.ftn.service;


import com.ftn.model.dto.warrant.Warrant;

/**
 * Created by zlatan on 6/24/17.
 */
public interface PaymentService {

    void process(Warrant warrant);

}
