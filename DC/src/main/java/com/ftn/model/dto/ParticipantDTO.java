package com.ftn.model.dto;


import com.ftn.model.Participant;
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
public class ParticipantDTO extends BaseDTO {

    @NotNull
    private boolean carrier;

    private String email;

    @NotNull
    private CustomerDTO customer;

    //@NotNull
    //private InsurancePolicyDTO insuredByPolicy;

    public ParticipantDTO(Participant participant){
        super(participant);
        this.carrier = participant.isCarrier();
        this.email = participant.getEmail();
        this.customer = new CustomerDTO(participant.getCustomer());
    }

    public Participant construct(){
        final Participant participant = new Participant(this);
        participant.setCarrier(carrier);
        participant.setEmail(email);
        participant.setCustomer(customer != null ? customer.construct() : null);
        //insured.setInsuredByPolicy(insuredByPolicy != null ? insuredByPolicy.construct() : null);
        return participant;
    }
}
