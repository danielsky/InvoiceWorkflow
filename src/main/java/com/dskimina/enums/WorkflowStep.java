package com.dskimina.enums;

public enum WorkflowStep {
    CREATED("workflow.step.created"),
    WAITING_FOR_FIRST_APPROVE("workflow.step.waiting.for.first.approve"),
    FIRST_APPROVE("workflow.step.first.approve"),
    FIRST_REJECT("workflow.step.first.reject");

    private String translationKey;

    WorkflowStep(String translationKey) {
        this.translationKey = translationKey;
    }

    public String getTranslationKey() {
        return translationKey;
    }
}
