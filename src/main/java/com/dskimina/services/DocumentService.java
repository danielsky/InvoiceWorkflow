package com.dskimina.services;

import com.dskimina.data.Document;
import com.dskimina.data.File;
import com.dskimina.data.ServiceRequest;
import com.dskimina.exceptions.ObjectNotFoundException;
import com.dskimina.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private FileService fileService;

    @Autowired
    private UIDGeneratorService uidGeneratorService;

    public Document createDocumentForServiceRequest(String name, ServiceRequest serviceRequest, byte[] content){
        File file = fileService.createFile(name, content);
        Document document = new Document();
        document.setIdentifier(uidGeneratorService.generateCode());
        document.setName(name);
        document.setFile(file);
        document.setServiceRequest(serviceRequest);
        return documentRepository.save(document);
    }

    public Document getDocumentForServiceRequestAndDocumentId(ServiceRequest serviceRequest, String documentId) throws ObjectNotFoundException{
        Document document = documentRepository.getByIdentifierAndServiceRequest(documentId, serviceRequest);
        if(document == null){
            throw new ObjectNotFoundException("Document not found for id: "+documentId);
        }
        return document;
    }

    public List<Document> getDocumentsForServiceRequest(ServiceRequest serviceRequest) {
        return documentRepository.findByServiceRequest(serviceRequest);
    }
}
