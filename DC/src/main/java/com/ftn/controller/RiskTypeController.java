package com.ftn.controller;

import com.ftn.exception.BadRequestException;
import com.ftn.intercepting.CustomAnnotation;
import com.ftn.model.dto.RiskTypeDTO;
import com.ftn.service.RiskTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by zlatan on 25/11/2017.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/riskTypes")
public class RiskTypeController {

    private final RiskTypeService riskTypeService;

    @Autowired
    public RiskTypeController(RiskTypeService riskTypeService){
        this.riskTypeService = riskTypeService;
    }

    @CustomAnnotation(value = "READ_ALL_RISK_TYPES")
    @Transactional
    @GetMapping
    public ResponseEntity read() {
        return new ResponseEntity<>(riskTypeService.readAll(), HttpStatus.OK);
    }

    @CustomAnnotation(value = "CREATE_RISK_TYPE")
    @Transactional
    @PostMapping
    public ResponseEntity create(@Valid @RequestBody RiskTypeDTO riskTypeDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new BadRequestException();
        return new ResponseEntity<>(riskTypeService.create(riskTypeDTO), HttpStatus.OK);
    }

    @CustomAnnotation(value = "UPDATE_RISK_TYPE")
    @Transactional
    @PatchMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable Long id, @Valid @RequestBody RiskTypeDTO riskTypeDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException();
        }
        return new ResponseEntity<>(riskTypeService.update(id, riskTypeDTO), HttpStatus.OK);
    }

    @CustomAnnotation(value = "DELETE_RISK_TYPE")
    @Transactional
    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        riskTypeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @CustomAnnotation(value = "FIND_RISK_TYPE_BY_ID")
    @Transactional
    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        return new ResponseEntity<>(riskTypeService.findById(id), HttpStatus.OK);
    }

    @CustomAnnotation(value = "FIND_RISK_TYPE_BY_NAME")
    @Transactional
    @GetMapping(value = "/name/{name}")
    public ResponseEntity findByName(@PathVariable String name){
        return new ResponseEntity<>(riskTypeService.findByName(name), HttpStatus.OK);
    }

    //@CustomAnnotation(value = "FIND_RISK_TYPE_BY_CATEGORY")
    @Transactional
    @GetMapping(value = "/insuranceCategory/{name}")
    public ResponseEntity findRisksByCategory(@PathVariable String name){
        return new ResponseEntity<>(riskTypeService.findByCategory(name), HttpStatus.OK);
    }

    //@CustomAnnotation(value = "FIND_RISK_TYPE_BY_CATEGORY")
    @Transactional
    @GetMapping(value = "/insuranceCategoryRiskTypes/{name}")
    public ResponseEntity findRiskTypesByCategory(@PathVariable String name){
        return new ResponseEntity<>(riskTypeService.findRiskTypesByCategory(name), HttpStatus.OK);
    }
}
