package com.dskimina.services;

import com.dskimina.data.ResetCode;
import com.dskimina.data.User;
import com.dskimina.repositories.ResetCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class ResetCodeService {

    @Autowired
    private UIDGeneratorService uidGeneratorService;

    @Autowired
    private ResetCodeRepository resetCodeRepository;

    public ResetCode createResetCode(User user){
        ResetCode resetCode = new ResetCode();
        resetCode.setUser(user);
        resetCode.setCreationDate(new Date());
        resetCode.setCode(uidGeneratorService.generateCode());
        resetCode = resetCodeRepository.save(resetCode);
        return resetCode;
    }

    public ResetCode getResetCodeByCode(String code){
        return resetCodeRepository.getByCode(code);
    }

    public void removeResetCode(ResetCode resetCode){
        resetCodeRepository.delete(resetCode);
    }

    public boolean isResetCodeInvalid(String id) {
        ResetCode resetCode = resetCodeRepository.getByCode(id);
        if(resetCode == null){
            return true;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(resetCode.getCreationDate());
        cal.add(Calendar.HOUR, 1);
        return cal.getTime().before(new Date());
    }
}
