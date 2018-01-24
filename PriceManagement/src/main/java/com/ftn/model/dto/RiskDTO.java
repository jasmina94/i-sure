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
public class RiskDTO extends BaseDTO {

    @NotNull
    private String riskName;

    @NotNull
    private RiskTypeDTO riskType;

    private List<PricelistItemDTO> pricelistItem = new ArrayList<>();
}
