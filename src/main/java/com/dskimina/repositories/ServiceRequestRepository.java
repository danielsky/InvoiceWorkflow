package com.dskimina.repositories;

import com.dskimina.data.ServiceRequest;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ServiceRequestRepository extends CrudRepository<ServiceRequest, Long> {

    List<ServiceRequest> findByName(String lastName);
    ServiceRequest getByIdentifier(String identifier);
}
