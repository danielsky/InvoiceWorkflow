package com.dskimina.data;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ResetCode {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String code;

    @ManyToOne
    private User user;

    private Date creationDate;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
