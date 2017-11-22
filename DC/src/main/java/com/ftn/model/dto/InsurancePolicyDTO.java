package com.ftn.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ftn.model.InsurancePolicy;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jasmina on 21/11/2017.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class InsurancePolicyDTO extends BaseDTO {

    @NotNull
    private double totalValue;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date dateOfIssue;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date dateBecomeEffective;

    @NotNull
    private double totalPrice;

    private List<InsuredDTO> insureds = new ArrayList<>();

    public InsurancePolicyDTO(InsurancePolicy insurancePolicy){
        this(insurancePolicy, true);
    }

    public InsurancePolicyDTO(InsurancePolicy insurancePolicy, boolean casscade){
        super(insurancePolicy);
        this.totalValue = insurancePolicy.getTotalValue();
        this.dateOfIssue = insurancePolicy.getDateOfIssue();
        this.dateBecomeEffective = insurancePolicy.getDateBecomeEffective();
        this.totalPrice = insurancePolicy.getTotalPrice();
        if(casscade){
            this.insureds = insurancePolicy.getInsureds().stream().map(insured -> new InsuredDTO(insured, false)).collect(Collectors.toList());
        }
    }

    public InsurancePolicy construct(){
        final InsurancePolicy insurancePolicy = new InsurancePolicy(this);
        insurancePolicy.setTotalValue(totalValue);
        insurancePolicy.setDateOfIssue(dateOfIssue);
        insurancePolicy.setDateBecomeEffective(dateBecomeEffective);
        insurancePolicy.setTotalPrice(totalPrice);
        return insurancePolicy;
    }
}
