package com.ftn.model.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by zlatan on 25/11/2017.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class InsuranceCategoryDTO extends BaseDTO {

    @NotNull
    private String categoryName;

    private List<RiskTypeDTO> riskTypes = new ArrayList<>();

}
