package com.ftn.controller;

import com.ftn.service.IssuerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jasmina on 04/12/2017.
 */
@RestController
@RequestMapping("/issuer")
public class IssuerController {

    private final IssuerService issuerService;

    @Autowired
    public IssuerController(IssuerService issuerService){
        this.issuerService = issuerService;
    }
}
