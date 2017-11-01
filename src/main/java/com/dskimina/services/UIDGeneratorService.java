package com.dskimina.services;

import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class UIDGeneratorService {

    private SecureRandom secureRandom = new SecureRandom();

    public String generateCode(){
        return generateCode(16);
    }

    public String generateCode(int length){
        byte[] bytes = new byte[length];
        secureRandom.nextBytes(bytes);
        return new String(Hex.encode(bytes));
    }
}
