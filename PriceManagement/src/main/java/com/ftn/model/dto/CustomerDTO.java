package com.ftn.model.dto;

import java.util.Collection;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerDTO{
	private Long id;
	private String firstName;
	private String lastName;
	private String ucn;
	private String passport;
	private String address;
	private String telephoneNumber;
	private Collection<ParticipantDTO> participants;
}
