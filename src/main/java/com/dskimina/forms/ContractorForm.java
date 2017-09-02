package com.dskimina.forms;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class ContractorForm{

    @NotEmpty(message = "newContractor.validation.emptyName")
    private String name;

    @Email(message = "newContractor.validation.badEmailFormat")
    @Size(min = 5, message = "newContractor.validation.badEmailSize")
    private String email;

    private String telephone;

    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
