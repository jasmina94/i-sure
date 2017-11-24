package com.ftn.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.ftn.model.dto.BaseDTO;
import com.ftn.model.dto.ParticipantDTO;
import com.ftn.util.SqlConstants;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by Jasmina on 21/11/2017.
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SQLDelete(sql = SqlConstants.UPDATE + "participant" + SqlConstants.SOFT_DELETE)
@Where(clause = SqlConstants.ACTIVE)
public class Participant extends Base{

    @Column(nullable = false)
    private boolean carrier;

    @Column(nullable = false)
    private String email;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    private InsurancePolicy insuredByPolicy;

    public Participant(BaseDTO baseDTO){
        super(baseDTO);
    }

    public void merge(ParticipantDTO participantDTO){
        this.carrier = participantDTO.isCarrier();
        this.email = participantDTO.getEmail();
        this.customer = participantDTO.getCustomer().construct();
//        if(insuredDTO.getInsuredByPolicy() != null){
//            this.insuredByPolicy = insuredDTO.getInsuredByPolicy().construct();
//        }
    }
}
