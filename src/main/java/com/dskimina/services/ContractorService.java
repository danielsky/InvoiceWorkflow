package com.dskimina.services;

import com.dskimina.data.Contractor;
import com.dskimina.data.User;
import com.dskimina.forms.ContractorForm;
import com.dskimina.repositories.ContractorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ContractorService {

    @Autowired
    private ContractorRepository contractorRepository;

    public List<Contractor> getAllContractors(){
        List<Contractor> contractorsList = new ArrayList<>();
        for(Contractor contractor : contractorRepository.findAll()){
            contractorsList.add(contractor);
        }
        return contractorsList;
    }

    public String createContractor(ContractorForm contractorForm, User creator){
        Contractor contractor = new Contractor();
        contractor.setName(contractorForm.getName());
        contractor.setIdentifier(UUID.randomUUID().toString());
        contractor.setCreator(creator);
        contractor.setCreationTime(new Date());
        contractor.setAddress(contractorForm.getAddress());
        contractor.setEmail(contractorForm.getEmail());
        contractor.setTelephone(contractorForm.getTelephone());
        contractorRepository.save(contractor);
        return contractor.getIdentifier();
    }
}
