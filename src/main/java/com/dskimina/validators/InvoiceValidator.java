package com.dskimina.validators;

import com.dskimina.forms.InvoiceForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class InvoiceValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(InvoiceForm.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        InvoiceForm invoice = (InvoiceForm) o;
    }
}
