package com.ftn.model;

import com.ftn.model.dto.BaseDTO;
import com.ftn.model.dto.InsurancePolicyDTO;
import com.ftn.util.SqlConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by Jasmina on 21/11/2017.
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SQLDelete(sql = SqlConstants.UPDATE + "insurance_policy" + SqlConstants.SOFT_DELETE)
@Where(clause = SqlConstants.ACTIVE)
public class InsurancePolicy extends Base {

    @Column(nullable = false)
    private double totalValue;

    @Column(nullable = false)
    private Date dateOfIssue;

    @Column(nullable = false)
    private Date dateBecomeEffective;

    @Column(nullable = false)
    private double totalPrice;

    @OneToMany(mappedBy = "insuredByPolicy", cascade = CascadeType.ALL)
    private List<Insured> insureds = new ArrayList<>();

    public InsurancePolicy(BaseDTO baseDTO){
        super(baseDTO);
    }

    public void merge(InsurancePolicyDTO insurancePolicyDTO){
        this.totalValue = insurancePolicyDTO.getTotalValue();
        this.dateOfIssue = insurancePolicyDTO.getDateOfIssue();
        this.dateBecomeEffective = insurancePolicyDTO.getDateBecomeEffective();
        this.totalPrice = insurancePolicyDTO.getTotalPrice();
    }
}
