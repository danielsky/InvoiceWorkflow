package com.dskimina.data;

import com.dskimina.enums.Currency;
import com.dskimina.enums.WorkflowStep;

import javax.persistence.*;
import java.util.Date;

@Entity(name = ServiceRequest.TABLE_NAME)
public class ServiceRequest {

    public static final String TABLE_NAME = "service_request";

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String identifier;

    private Integer number;

    private String name;

    @Enumerated(EnumType.STRING)
    private WorkflowStep workflowStep;

    private Date creationTime;

    @ManyToOne
    private User creator;

    @ManyToOne
    private Contractor contractor;

    @ManyToOne
    private ContractorServiceData serviceData;

    private double price;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private String location;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public WorkflowStep getWorkflowStep() {
        return workflowStep;
    }

    public void setWorkflowStep(WorkflowStep workflowStep) {
        this.workflowStep = workflowStep;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

    public ContractorServiceData getServiceData() {
        return serviceData;
    }

    public void setServiceData(ContractorServiceData serviceData) {
        this.serviceData = serviceData;
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
