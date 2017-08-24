package com.dskimina.model;

import com.dskimina.enums.WorkflowStep;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

public class InvoiceDTO {

    @NotEmpty
    private String name;

    private String identifier;

    private WorkflowStep workflowStep;

    private Date creationTime;

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


}
