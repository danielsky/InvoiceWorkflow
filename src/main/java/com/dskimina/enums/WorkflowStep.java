package com.dskimina.enums;

public enum WorkflowStep {
    CREATED("workflow.step.created"),
    WAITING_FOR_FIRST_APPROVE("workflow.step.waiting.for.first.approve"),
    WAITING_FOR_ORDER_SERVICE_FROM_CONTRACTOR("workflow.step.waiting.for.order.service"),
    WAITING_FOR_UPLOAD_INVOICE_BY_CONTRACTOR("workflow.step.waiting.for.upload.invoice"),
    WAITING_FOR_SERVICE_ACCEPTANCE_BY_EMPLOYEE("workflow.step.waiting.for.service.acceptance.employee"),
    WAITING_FOR_SERVICE_ACCEPTANCE_BY_SUPERVISOR("workflow.step.waiting.for.service.acceptance.supervisor"),
    WAITING_FOR_SERVICE_ACCOUNTING("workflow.step.waiting.for.service.accounting"),
    WAITING_FOR_SERVICE_ACCOUNTING_ACCEPTANCE("workflow.step.waiting.for.service.accounting.acceptance"),
    SERVICE_WORKFLOW_FINISHED("workflow.step.finished");

    private String translationKey;

    WorkflowStep(String translationKey) {
        this.translationKey = translationKey;
    }

    public String getTranslationKey() {
        return translationKey;
    }
}
