package com.ftn.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Jasmina on 21/11/2017.
 */

@Data
@NoArgsConstructor
public abstract class BaseDTO {

    private long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date created;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date updated;
    
    private boolean active = true;

	public BaseDTO(BaseDTO baseDTO) {
		super();
		this.id = baseDTO.getId();
		this.created = baseDTO.getCreated();
		this.updated = baseDTO.getUpdated();
		this.active = baseDTO.isActive();
	}
    
    


}
