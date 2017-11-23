package com.ftn.model;

import com.ftn.model.dto.BaseDTO;
import com.ftn.model.dto.InsuredDTO;
import com.ftn.util.SqlConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by Jasmina on 21/11/2017.
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SQLDelete(sql = SqlConstants.UPDATE + "insured" + SqlConstants.SOFT_DELETE)
@Where(clause = SqlConstants.ACTIVE)
public class Insured extends Base{

    @Column(nullable = false)
    private boolean carrier;

    @Column
    private String email;

    @ManyToOne(optional = false)
    private Customer insuredCustomer;

    @ManyToOne
    private InsurancePolicy insuredByPolicy;

    public Insured(BaseDTO baseDTO){
        super(baseDTO);
    }

    public void merge(InsuredDTO insuredDTO){
        this.carrier = insuredDTO.isCarrier();
        this.email = insuredDTO.getEmail();
        this.insuredCustomer = insuredDTO.getInsuredCustomer().construct();
        if(insuredDTO.getInsuredByPolicy() != null){
            this.insuredByPolicy = insuredDTO.getInsuredByPolicy().construct();
        }
    }
}
