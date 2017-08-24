package com.dskimina.forms;

import org.hibernate.validator.constraints.NotEmpty;

public class InvoiceForm {

    @NotEmpty
    private String name;

    private boolean sendNow = Boolean.FALSE;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSendNow() {
        return sendNow;
    }

    public void setSendNow(boolean sendNow) {
        this.sendNow = sendNow;
    }
}
