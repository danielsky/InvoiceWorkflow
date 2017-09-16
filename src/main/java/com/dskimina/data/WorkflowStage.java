package com.dskimina.data;

import com.dskimina.enums.WorkflowStep;

import javax.persistence.*;
import java.util.Date;

@Entity
public class WorkflowStage {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Enumerated(EnumType.STRING)
    private WorkflowStep workflowStep;

    private Date date;

    @ManyToOne
    private User owner;

    @ManyToOne
    private ServiceRequest serviceRequest;

    private boolean done;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public ServiceRequest getServiceRequest() {
        return serviceRequest;
    }

    public void setServiceRequest(ServiceRequest serviceRequest) {
        this.serviceRequest = serviceRequest;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
