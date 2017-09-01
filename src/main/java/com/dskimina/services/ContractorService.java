package com.dskimina.services;

import com.dskimina.data.Contractor;
import com.dskimina.data.User;
import com.dskimina.model.ContractorDTO;
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

    public void createContractor(ContractorDTO contractorDTO, User creator){
        Contractor contractor = new Contractor();
        contractor.setName(contractorDTO.getName());
        contractor.setIdentifier(UUID.randomUUID().toString());
        contractor.setCreator(creator);
        contractor.setCreationTime(new Date());
        contractor.setAddress(contractor.getAddress());
        contractor.setEmail(contractor.getEmail());
        contractor.setTelephone(contractor.getTelephone());
        contractorRepository.save(contractor);
    }
}
