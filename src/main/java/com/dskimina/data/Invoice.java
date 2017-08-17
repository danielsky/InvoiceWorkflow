package com.dskimina.data;

import com.dskimina.enums.WorkflowStep;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String identifier;

    private String name;

    @Enumerated(EnumType.STRING)
    private WorkflowStep workflowStep;

    private Date creationTime;

    @ManyToOne
    private User creator;

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
}
