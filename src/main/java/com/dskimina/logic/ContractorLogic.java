package com.dskimina.logic;

import com.dskimina.model.ServiceRequestDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class ContractorLogic {
    public List<ServiceRequestDTO> getServiceRequestForContractor(String name) {
        return Collections.emptyList();
    }
}
