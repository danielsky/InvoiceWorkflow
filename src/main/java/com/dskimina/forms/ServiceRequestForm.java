package com.dskimina.forms;

import com.dskimina.enums.Currency;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;

public class ServiceRequestForm {

    @NotEmpty(message = "newServiceRequest.validation.emptyName")
    private String name;

    private String contractor;
    private String contractorService;
    private String location;

    @Min(value = 0, message = "newServiceRequest.validation.negativePrice")
    private double price;
    private Currency currency;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContractor() {
        return contractor;
    }

    public void setContractor(String contractor) {
        this.contractor = contractor;
    }

    public String getContractorService() {
        return contractorService;
    }

    public void setContractorService(String contractorService) {
        this.contractorService = contractorService;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
