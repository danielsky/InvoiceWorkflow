package com.dskimina.model;


import com.dskimina.enums.WorkflowStep;

import java.util.Date;

public class WorkflowStageDTO {

    private WorkflowStep workflowStep;
    private Date date;
    private String owner;
    private boolean done;

    public WorkflowStep getWorkflowStep() {
        return workflowStep;
    }

    public void setWorkflowStep(WorkflowStep workflowStep) {
        this.workflowStep = workflowStep;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
