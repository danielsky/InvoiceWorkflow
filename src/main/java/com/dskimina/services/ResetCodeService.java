package com.dskimina.services;

import com.dskimina.data.ResetCode;
import com.dskimina.data.User;
import com.dskimina.repositories.ResetCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ResetCodeService {

    @Autowired
    private UIDGeneratorService uidGeneratorService;

    @Autowired
    private ResetCodeRepository resetCodeService;

    public ResetCode createResetCode(User user){
        ResetCode resetCode = new ResetCode();
        resetCode.setUser(user);
        resetCode.setCreationDate(new Date());
        resetCode.setCode(uidGeneratorService.generateCode());
        resetCode = resetCodeService.save(resetCode);
        return resetCode;
    }

    public ResetCode getResetCodeByCode(String code){
        return resetCodeService.getByCode(code);
    }

    public void removeResetCode(ResetCode resetCode){
        resetCodeService.delete(resetCode);
    }
}
