package com.ftn.model.dto;


import com.ftn.model.Insured;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Created by Jasmina on 21/11/2017.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class InsuredDTO extends BaseDTO {

    @NotNull
    private boolean carrier;

    private String email;

    @NotNull
    private CustomerDTO insuredCustomer;

    @NotNull
    private InsurancePolicyDTO insuredByPolicy;

    public InsuredDTO(Insured insured){
        this(insured, true);
    }

    public InsuredDTO(Insured insured, boolean casscade){
        super(insured);
        this.carrier = insured.isCarrier();
        this.email = insured.getEmail();
    }

    public Insured construct(){
        final Insured insured = new Insured(this);
        insured.setCarrier(carrier);
        insured.setEmail(email);
        insured.setInsuredCustomer(insuredCustomer != null ? insuredCustomer.construct() : null);
        insured.setInsuredByPolicy(insuredByPolicy != null ? insuredByPolicy.construct() : null);
        return insured;
    }
}
