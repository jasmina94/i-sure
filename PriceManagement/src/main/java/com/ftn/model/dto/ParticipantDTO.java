package com.ftn.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ParticipantDTO {
	private Long id;
	private boolean carrier;
	private String email;
	private CustomerDTO customer;
	//private PolicyDTO policy;
}
