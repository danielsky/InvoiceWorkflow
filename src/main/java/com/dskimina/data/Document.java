package com.dskimina.data;

import javax.persistence.*;

@Entity
public class Document {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String identifier;

    private String name;

    @ManyToOne
    private File file;

    @ManyToOne
    private ServiceRequest serviceRequest;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public ServiceRequest getServiceRequest() {
        return serviceRequest;
    }

    public void setServiceRequest(ServiceRequest serviceRequest) {
        this.serviceRequest = serviceRequest;
    }
}
