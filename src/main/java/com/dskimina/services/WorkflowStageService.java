package com.dskimina.services;

import com.dskimina.data.ServiceRequest;
import com.dskimina.data.User;
import com.dskimina.data.WorkflowStage;
import com.dskimina.enums.WorkflowStep;
import com.dskimina.repositories.ServiceRequestRepository;
import com.dskimina.repositories.WorkflowStageRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WorkflowStageService {

    private static final Log LOG = LogFactory.getLog(WorkflowStageService.class);

    @Autowired
    private WorkflowStageRepository workflowStageRepository;

    @Autowired
    private ServiceRequestRepository serviceRequestRepository;

    public void createInitialWorkflowStage(User owner, ServiceRequest serviceRequest){
        WorkflowStage workflowStage = new WorkflowStage();
        workflowStage.setServiceRequest(serviceRequest);
        workflowStage.setWorkflowStep(WorkflowStep.CREATED);
        workflowStage.setDate(new Date());
        workflowStage.setOwner(owner);
        workflowStage.setDone(true);
        workflowStageRepository.save(workflowStage);

        workflowStage = new WorkflowStage();
        workflowStage.setServiceRequest(serviceRequest);
        workflowStage.setWorkflowStep(WorkflowStep.WAITING_FOR_FIRST_APPROVE);
        workflowStage.setDone(false);
        workflowStageRepository.save(workflowStage);
    }

    public void moveServiceRequestToNextWorkflowStage(ServiceRequest serviceRequest, User owner){
        WorkflowStage workflowStage = workflowStageRepository.getByServiceRequestAndDone(serviceRequest, false);
        if (workflowStage != null) {
            workflowStage.setDate(new Date());
            workflowStage.setOwner(owner);
            workflowStage.setDone(true);
            workflowStage = workflowStageRepository.save(workflowStage);

            WorkflowStep workflowStep = workflowStage.getWorkflowStep();
            workflowStep = getNextWorkflowStep(workflowStep);

            if (workflowStep != null) {
                workflowStage = new WorkflowStage();
                workflowStage.setServiceRequest(serviceRequest);
                workflowStage.setWorkflowStep(workflowStep);
                workflowStage.setDone(false);
                workflowStageRepository.save(workflowStage);

                serviceRequest.setWorkflowStep(workflowStep);
                serviceRequestRepository.save(serviceRequest);
            }else{
                LOG.debug("Maximum stage reached. End of workflow");
            }
        }else{
            LOG.debug("Workflow for this service request has ended.");
        }
    }

    public List<WorkflowStage> getWorkflowStagesForServiceRequest(ServiceRequest serviceRequest){
        return workflowStageRepository.findByServiceRequest(serviceRequest);
    }

    public WorkflowStep getNextWorkflowStep(WorkflowStep workflowStep){

        if(WorkflowStep.CREATED.equals(workflowStep)){
            return WorkflowStep.WAITING_FOR_FIRST_APPROVE;
        }
        if(WorkflowStep.WAITING_FOR_FIRST_APPROVE.equals(workflowStep)){
            return WorkflowStep.WAITING_FOR_ORDER_SERVICE_FROM_CONTRACTOR;
        }
        if(WorkflowStep.WAITING_FOR_ORDER_SERVICE_FROM_CONTRACTOR.equals(workflowStep)){
            return WorkflowStep.WAITING_FOR_UPLOAD_INVOICE_BY_CONTRACTOR;
        }
        if(WorkflowStep.WAITING_FOR_UPLOAD_INVOICE_BY_CONTRACTOR.equals(workflowStep)){
            return WorkflowStep.WAITING_FOR_SERVICE_ACCEPTANCE_BY_EMPLOYEE;
        }
        if(WorkflowStep.WAITING_FOR_SERVICE_ACCEPTANCE_BY_EMPLOYEE.equals(workflowStep)){
            return WorkflowStep.WAITING_FOR_SERVICE_ACCEPTANCE_BY_SUPERVISOR;
        }
        if(WorkflowStep.WAITING_FOR_SERVICE_ACCEPTANCE_BY_SUPERVISOR.equals(workflowStep)){
            return WorkflowStep.WAITING_FOR_SERVICE_ACCOUNTING;
        }
        if(WorkflowStep.WAITING_FOR_SERVICE_ACCOUNTING.equals(workflowStep)){
            return WorkflowStep.WAITING_FOR_SERVICE_ACCOUNTING_ACCEPTANCE;
        }
        if(WorkflowStep.WAITING_FOR_SERVICE_ACCOUNTING_ACCEPTANCE.equals(workflowStep)){
            return WorkflowStep.SERVICE_WORKFLOW_FINISHED;
        }

        return null;
    }


}
