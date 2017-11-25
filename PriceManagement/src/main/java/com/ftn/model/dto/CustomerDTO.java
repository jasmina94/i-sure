package com.ftn.model.dto;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerDTO{

	private Long id;

	@NotNull
	private String firstName;

	@NotNull
	private String lastName;

	@NotNull
    @Size(min = 13, max = 13)
    @Pattern(regexp = "[0-9]*")
	private String ucn;

	@NotNull
    @Size(min = 9, max = 9)
    @Pattern(regexp = "[0-9]*")
	private String passport;

	@NotNull
	private String address;

	private String telephoneNumber;

	private boolean carrier;

	private String email;

}
