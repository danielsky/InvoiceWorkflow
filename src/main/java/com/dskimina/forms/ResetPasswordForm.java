package com.dskimina.forms;

import org.hibernate.validator.constraints.NotEmpty;

public class ResetPasswordForm {

    @NotEmpty(message = "resetPassword.validation.emptyPass")
    private String newPass;

    @NotEmpty(message = "resetPassword.validation.emptyPass")
    private String retypedPass;

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getRetypedPass() {
        return retypedPass;
    }

    public void setRetypedPass(String retypedPass) {
        this.retypedPass = retypedPass;
    }
}
