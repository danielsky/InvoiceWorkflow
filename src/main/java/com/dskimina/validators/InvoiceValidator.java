package com.dskimina.validators;

import com.dskimina.model.InvoiceDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class InvoiceValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(InvoiceDTO.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        InvoiceDTO invoice = (InvoiceDTO) o;
    }
}
