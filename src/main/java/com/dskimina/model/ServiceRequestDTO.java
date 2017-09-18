package com.dskimina.model;

import com.dskimina.enums.Currency;
import com.dskimina.enums.WorkflowStep;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

public class ServiceRequestDTO {

    @NotEmpty
    private String name;

    private String identifier;

    private Integer number;

    private WorkflowStep workflowStep;

    private String contractorName;

    private String contractorService;

    private String location;

    private Date creationTime;

    private double price;

    private Currency currency;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public WorkflowStep getWorkflowStep() {
        return workflowStep;
    }

    public void setWorkflowStep(WorkflowStep workflowStep) {
        this.workflowStep = workflowStep;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public String getContractorName() {
        return contractorName;
    }

    public void setContractorName(String contractorName) {
        this.contractorName = contractorName;
    }

    public String getContractorService() {
        return contractorService;
    }

    public void setContractorService(String contractorService) {
        this.contractorService = contractorService;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
