package com.ftn.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.exception.BadRequestException;
import com.ftn.model.dto.RiskDTO;
import com.ftn.service.RiskService;

/**
 * Created by zlatan on 25/11/2017.
 */
@RestController
@RequestMapping("/risks")
//@PreAuthorize("authenticated")
public class RiskController {

    private final RiskService riskService;

    @Autowired
    public RiskController(RiskService riskService){
        this.riskService = riskService;
    }

    @GetMapping
    public ResponseEntity read() {
        return new ResponseEntity<>(riskService.readAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody RiskDTO riskDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new BadRequestException();
        return new ResponseEntity<>(riskService.create(riskDTO), HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable Long id, @Valid @RequestBody RiskDTO riskDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException();
        }
        return new ResponseEntity<>(riskService.update(id, riskDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        riskService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        return new ResponseEntity<>(riskService.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity findByBirthId(@PathVariable String name){
        return new ResponseEntity<>(riskService.findByName(name), HttpStatus.OK);
    }
    
    @GetMapping(value = "/byRiskType/{id}")
    public ResponseEntity findByRiskType(@PathVariable Long id){
        return new ResponseEntity<>(riskService.findByRiskType(id), HttpStatus.OK);
    }
}
