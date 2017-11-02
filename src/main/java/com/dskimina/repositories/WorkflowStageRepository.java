package com.dskimina.repositories;

import com.dskimina.data.ServiceRequest;
import com.dskimina.data.WorkflowStage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkflowStageRepository extends CrudRepository<WorkflowStage, Long> {

    List<WorkflowStage> findByServiceRequest(ServiceRequest serviceRequest);
    WorkflowStage getByServiceRequestAndDone(ServiceRequest serviceRequest, boolean done);
}
