package com.ftn.controller;

import com.ftn.service.AcquirerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jasmina on 04/12/2017.
 */
@RestController
@RequestMapping("/acquirer")
public class AcquirerController {

    private final AcquirerService acquirerService;

    @Autowired
    public AcquirerController(AcquirerService acquirerService){
        this.acquirerService = acquirerService;
    }
}
