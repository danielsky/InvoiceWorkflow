package com.dskimina.data;


import javax.persistence.*;
import java.util.Date;

@Entity
public class SecurityCode {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String code;

    @ManyToOne
    private User acceptor;

    private Date creationDate;

    @ManyToOne
    private ServiceRequest serviceRequest;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public User getAcceptor() {
        return acceptor;
    }

    public void setAcceptor(User acceptor) {
        this.acceptor = acceptor;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public ServiceRequest getServiceRequest() {
        return serviceRequest;
    }

    public void setServiceRequest(ServiceRequest serviceRequest) {
        this.serviceRequest = serviceRequest;
    }
}
