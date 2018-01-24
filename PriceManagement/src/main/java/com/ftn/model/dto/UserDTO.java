package com.ftn.model.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Jasmina on 16/11/2017.
 */
@Data
@NoArgsConstructor
public class UserDTO {

    private Long id;

    @NotNull
    private String username;

    @NotNull
    private String password;
}
