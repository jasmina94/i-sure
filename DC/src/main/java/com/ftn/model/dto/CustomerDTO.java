package com.ftn.model.dto;

import com.ftn.model.Customer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Jasmina on 21/11/2017.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class CustomerDTO extends BaseDTO{

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
    
    @NotNull
    private boolean carrier;

    private String email;
    
    public CustomerDTO(Customer customer){
        super(customer);
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.ucn = customer.getUcn();
        this.passport = customer.getPassport();
        this.address = customer.getAddress();
        this.telephoneNumber = customer.getTelephoneNumber();
    }

    public Customer construct(){
        final Customer customer = new Customer(this);
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setUcn(ucn);
        customer.setPassport(passport);
        customer.setAddress(address);
        customer.setTelephoneNumber(telephoneNumber);
        return customer;
    }
}
