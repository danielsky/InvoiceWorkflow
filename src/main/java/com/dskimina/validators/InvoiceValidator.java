package com.dskimina.validators;

import com.dskimina.forms.ServiceRequestForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class InvoiceValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(ServiceRequestForm.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ServiceRequestForm invoice = (ServiceRequestForm) o;
    }
}
