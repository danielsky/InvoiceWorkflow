package com.dskimina.model;

import org.hibernate.validator.constraints.NotEmpty;

public class InvoiceDTO {

    @NotEmpty
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
