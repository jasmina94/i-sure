package com.ftn.model.dto;

import com.ftn.model.Risk;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Created by zlatan on 25/11/2017.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class RiskDTO extends BaseDTO {

    @NotNull
    private String riskName;

    @NotNull
    private RiskTypeDTO riskType;

    public RiskDTO(Risk risk){
        this(risk, true);
    }

    public RiskDTO(Risk risk, boolean cascade){
        super(risk);
        this.riskName = risk.getRiskName();
        if(cascade){
            this.riskType = new RiskTypeDTO(risk.getRiskType(), false);
        }
    }

    public Risk construct() {
        final Risk risk = new Risk(this);
        risk.setRiskName(riskName);
        if(riskType != null){
            risk.setRiskType(riskType.construct());
        }
        return risk;
    }
}
