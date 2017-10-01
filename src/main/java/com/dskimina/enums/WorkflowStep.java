package com.dskimina.enums;

public enum WorkflowStep {
    CREATED(
            "workflow.step.created",
            "workflow.step.mail.created"
            ),
    WAITING_FOR_FIRST_APPROVE(
            "workflow.step.waiting.for.first.approve",
            "workflow.step.mail.waiting.for.first.approve"
    ),
    WAITING_FOR_ORDER_SERVICE_FROM_CONTRACTOR(
            "workflow.step.waiting.for.order.service",
            "workflow.step.waiting.for.order.service"
    ),
    WAITING_FOR_UPLOAD_INVOICE_BY_CONTRACTOR(
            "workflow.step.waiting.for.upload.invoice",
            "workflow.step.waiting.for.upload.invoice"
    ),
    WAITING_FOR_SERVICE_ACCEPTANCE_BY_EMPLOYEE(
            "workflow.step.waiting.for.service.acceptance.employee",
            "workflow.step.waiting.for.service.acceptance.employee"
    ),
    WAITING_FOR_SERVICE_ACCEPTANCE_BY_SUPERVISOR(
            "workflow.step.waiting.for.service.acceptance.supervisor",
            "workflow.step.waiting.for.service.acceptance.supervisor"
    ),
    WAITING_FOR_SERVICE_ACCOUNTING(
            "workflow.step.waiting.for.service.accounting",
            "workflow.step.waiting.for.service.accounting"
    ),
    WAITING_FOR_SERVICE_ACCOUNTING_ACCEPTANCE(
            "workflow.step.waiting.for.service.accounting.acceptance",
            "workflow.step.waiting.for.service.accounting.acceptance"
    ),
    SERVICE_WORKFLOW_FINISHED(
            "workflow.step.finished",
            "workflow.step.finished"
    );

    private String statusKey;
    private String mailSubjectKey;

    WorkflowStep(String statusKey, String mailSubjectKey) {
        this.statusKey = statusKey;
        this.mailSubjectKey = mailSubjectKey;
    }

    public String getStatusKey() {
        return statusKey;
    }

    public String getMailSubjectKey() {
        return mailSubjectKey;
    }
}
