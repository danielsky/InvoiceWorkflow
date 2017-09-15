package com.dskimina.model;

import java.util.Date;

/**
 * Created by Daniel on 14.09.2017.
 */
public class CommentDTO {
    private String identifier;
    private String content;
    private String author;
    private Date date;

    public CommentDTO(String identifier, String content, String author, Date date) {
        this.identifier = identifier;
        this.content = content;
        this.author = author;
        this.date = date;
    }

    public CommentDTO() {
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

