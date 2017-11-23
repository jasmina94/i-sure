package com.ftn.model;

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
@SQLDelete(sql = SqlConstants.UPDATE + "insuredCustomer" + SqlConstants.SOFT_DELETE)
@Where(clause = SqlConstants.ACTIVE)
public class Customer extends Base{

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false, length = 13)
    private String birthId;

    @Column(nullable = false, length = 9)
    private String passport;

    @Column(nullable = false)
    private String address;

    @Column
    private String phone;

    @OneToMany(mappedBy = "insuredCustomer", cascade = CascadeType.ALL)
    private List<Insured> insureds = new ArrayList<>();

    public Customer(BaseDTO baseDTO){
        super(baseDTO);
    }

    public void merge(CustomerDTO customerDTO){
        this.firstname = customerDTO.getFirstname();
        this.lastname = customerDTO.getLastname();
        this.birthId = customerDTO.getBirthId();
        this.passport = customerDTO.getPassport();
        this.address = customerDTO.getAddress();
        this.phone = customerDTO.getPhone();
    }
}

