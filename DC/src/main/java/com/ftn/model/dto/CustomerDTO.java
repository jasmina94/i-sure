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
    private String firstname;

    @NotNull
    private String lastname;

    @NotNull
    @Size(min = 13, max = 13)
    @Pattern(regexp = "[0-9]*")
    private String birthId;

    @NotNull
    @Size(min = 9, max = 9)
    @Pattern(regexp = "[0-9]*")
    private String passport;

    @NotNull
    private String address;

    private String phone;

    private List<InsuredDTO> insureds = new ArrayList<>();

    public CustomerDTO(Customer customer){
        this(customer, true);
    }

    public CustomerDTO(Customer customer, boolean casscade){
        super(customer);
        this.firstname = customer.getFirstname();
        this.lastname = customer.getLastname();
        this.birthId = customer.getBirthId();
        this.passport = customer.getPassport();
        this.address = customer.getAddress();
        this.phone = customer.getPhone();
        if(casscade){
            this.insureds = customer.getInsureds().stream().map(insured -> new InsuredDTO(insured, false)).collect(Collectors.toList());
        }
    }

    public Customer construct(){
        final Customer customer = new Customer(this);
        customer.setFirstname(firstname);
        customer.setLastname(lastname);
        customer.setBirthId(birthId);
        customer.setPassport(passport);
        customer.setAddress(address);
        customer.setPhone(phone);
        if(insureds != null && insureds.size() != 0){
            insureds.forEach(insuredDTO -> customer.getInsureds().add(insuredDTO.construct()));
        }
        return customer;
    }
}
