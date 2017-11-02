package com.dskimina.repositories;

import com.dskimina.data.Document;
import com.dskimina.data.ServiceRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends CrudRepository<Document, Long> {

    Document getByIdentifierAndServiceRequest(String identifier, ServiceRequest serviceRequest);
    List<Document> findByServiceRequest(ServiceRequest serviceRequest);
}
