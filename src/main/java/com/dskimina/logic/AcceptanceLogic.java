package com.dskimina.logic;

import com.dskimina.data.SecurityCode;
import com.dskimina.data.ServiceRequest;
import com.dskimina.data.User;
import com.dskimina.exceptions.ObjectNotFoundException;
import com.dskimina.services.SecurityCodeService;
import com.dskimina.services.WorkflowStageService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AcceptanceLogic {

    private static final Log LOG = LogFactory.getLog(AcceptanceLogic.class);

    @Autowired
    private WorkflowStageService workflowStageService;

    @Autowired
    private SecurityCodeService securityCodeService;

    public void moveServiceRequestToNextWorkflowStage(String serviceRequestId, String securityCodeId) throws ObjectNotFoundException {

        SecurityCode securityCode = securityCodeService.getSecurityCodeByCode(securityCodeId);
        if(securityCode == null){
            throw new ObjectNotFoundException("Cannot find security code: "+ securityCodeId);
        }
        User user = securityCode.getAcceptor();
        ServiceRequest serviceRequest = securityCode.getServiceRequest();

        if(!serviceRequest.getIdentifier().equals(serviceRequestId)){
            throw new SecurityException("Service request for security code: "+ securityCodeId+ "doesn't belong to service request: "+securityCodeId);
        }

        workflowStageService.moveServiceRequestToNextWorkflowStage(serviceRequest, user);

        securityCodeService.removeSecurityCode(securityCode);

        LOG.info("Service request "+ serviceRequestId + "is scheduled to contractor.");
    }

}
