package com.dskimina.forms;

import org.hibernate.validator.constraints.NotEmpty;

public class CommentForm {

    @NotEmpty(message = "newComment.validation.emptyContent")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
