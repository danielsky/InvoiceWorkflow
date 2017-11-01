package com.dskimina.services;

import com.dskimina.data.SecurityCode;
import com.dskimina.data.ServiceRequest;
import com.dskimina.data.User;
import com.dskimina.repositories.SecurityCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SecurityCodeService {

    @Autowired
    private UIDGeneratorService uidGeneratorService;

    @Autowired
    private SecurityCodeRepository securityCodeRepository;

    public SecurityCode createSecurityCode(User user, ServiceRequest serviceRequest){
        SecurityCode securityCode = new SecurityCode();
        securityCode.setAcceptor(user);
        securityCode.setCreationDate(new Date());
        securityCode.setServiceRequest(serviceRequest);
        securityCode.setCode(uidGeneratorService.generateCode());
        securityCode = securityCodeRepository.save(securityCode);
        return securityCode;
    }

    public SecurityCode getSecurityCodeByCode(String code){
        return securityCodeRepository.getByCode(code);
    }

    public void removeSecurityCode(SecurityCode securityCode){
        securityCodeRepository.delete(securityCode);
    }
}
