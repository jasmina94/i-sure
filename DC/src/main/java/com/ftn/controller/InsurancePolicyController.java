package com.ftn.controller;


import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ftn.exception.BadRequestException;
import com.ftn.model.dto.InsurancePolicyDTO;
import com.ftn.service.InsurancePolicyService;
import com.ftn.service.implementation.InsurancePolicyServiceImpl;


/**
 * Created by Jasmina on 22/11/2017.
 */

@Controller
@RequestMapping("/insurancePolicy")

public class InsurancePolicyController {

    private final InsurancePolicyService insurancePolicyService;

    @Autowired
    public InsurancePolicyController(InsurancePolicyServiceImpl insurancePolicyService){

        this.insurancePolicyService = insurancePolicyService;
    }

    @Transactional
    @GetMapping
    public ResponseEntity read() {
        return new ResponseEntity<>(insurancePolicyService.readAll(), HttpStatus.OK);
    }

    @Transactional
    @PostMapping
    public ResponseEntity create(@Valid @RequestBody InsurancePolicyDTO insurancePolicyDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new BadRequestException();
        return new ResponseEntity<>(insurancePolicyService.create(insurancePolicyDTO), HttpStatus.OK);
    }

    @Transactional
    @PatchMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable Long id, @Valid @RequestBody InsurancePolicyDTO insurancePolicyDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException();
        }

        
        InsurancePolicyDTO ipDTO;
//        if(insurancePolicyDTO.getCreated() == null){
//    		insurancePolicyDTO.setCreated(insurancePolicyService.findById(id).getCreated());
//    	}
        try {
        	
			ipDTO = insurancePolicyService.update(id, insurancePolicyDTO);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
        
        return new ResponseEntity<>(ipDTO, HttpStatus.OK);

    }

    @Transactional
    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {

        try {
			insurancePolicyService.delete(id);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Transactional
    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable Long id){

    	
    	InsurancePolicyDTO ipDTO;
    	
    	try {
			ipDTO = insurancePolicyService.findById(id);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
    	
        return new ResponseEntity<>(ipDTO, HttpStatus.OK);
    }

    @Transactional
    @GetMapping(value = "/byDateOfIssue/{date}")
    public ResponseEntity findByDateOfIssue(@PathVariable Date date){
    	
    	return new ResponseEntity<>(insurancePolicyService.findByDateOfIssue(date), HttpStatus.OK);
			

    }
}
