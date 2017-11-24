package com.ftn.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ftn.model.dto.BaseDTO;
import com.ftn.model.dto.CustomerDTO;
import com.ftn.util.SqlConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jasmina on 21/11/2017.
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SQLDelete(sql = SqlConstants.UPDATE + "customer" + SqlConstants.SOFT_DELETE)
@Where(clause = SqlConstants.ACTIVE)
public class Customer extends Base{

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, length = 13)
    private String ucn;

    @Column(nullable = false, length = 9)
    private String passport;

    @Column(nullable = false)
    private String address;

    @Column
    private String telephoneNumber;

    @Column(nullable = false)
    private boolean carrier;

    @Column(nullable = false)
    private String email;
    
    
    
    public Customer(BaseDTO baseDTO){
        super(baseDTO);
    }

    public void merge(CustomerDTO customerDTO){
        this.firstName = customerDTO.getFirstName();
        this.lastName = customerDTO.getLastName();
        this.ucn = customerDTO.getUcn();
        this.passport = customerDTO.getPassport();
        this.address = customerDTO.getAddress();
        this.telephoneNumber = customerDTO.getTelephoneNumber();
    }
}

