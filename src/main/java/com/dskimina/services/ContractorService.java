package com.dskimina.services;

import com.dskimina.data.Contractor;
import com.dskimina.data.ContractorServiceData;
import com.dskimina.data.User;
import com.dskimina.exceptions.ObjectNotFoundException;
import com.dskimina.forms.ContractorForm;
import com.dskimina.model.ContractorDTO;
import com.dskimina.model.ContractorServiceDTO;
import com.dskimina.repositories.ContractorRepository;
import com.dskimina.repositories.ContractorServiceDataRepository;
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

    @Autowired
    private ContractorServiceDataRepository contractorServiceDataRepository;

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

    public void updateContractor(String id, ContractorDTO contractorDTO) throws ObjectNotFoundException {
        Contractor contractor = contractorRepository.getByIdentifier(id);
        if(contractor == null){
            throw new ObjectNotFoundException("Cannot find contractor with id: "+id);
        }
        contractor.setName(contractorDTO.getName());
        contractor.setEmail(contractorDTO.getEmail());
        contractor.setTelephone(contractorDTO.getTelephone());
        contractor.setAddress(contractorDTO.getAddress());
        contractorRepository.save(contractor);
    }

    public Contractor getContractorByIdentifier(String identifier) throws ObjectNotFoundException{
        Contractor contractor = contractorRepository.getByIdentifier(identifier);
        if(contractor == null){
            throw new ObjectNotFoundException("Cannot find contractor with id: "+identifier);
        }
        return contractor;
    }

    public List<ContractorServiceData> getContractorServicesByContractorIdentifier(String identifier) throws ObjectNotFoundException{
        Contractor contractor = getContractorByIdentifier(identifier);
        return contractorServiceDataRepository.findByContractor(contractor);
    }

    public ContractorServiceData getContractorServiceByIdentifier(String identifier) throws ObjectNotFoundException{
        return contractorServiceDataRepository.getByIdentifier(identifier);
    }

    public String createContractorService(String serviceName, Contractor contractor, User creator){
        ContractorServiceData contractorServiceData = new ContractorServiceData();
        contractorServiceData.setName(serviceName);
        contractorServiceData.setContractor(contractor);
        contractorServiceData.setCreationTime(new Date());
        contractorServiceData.setCreator(creator);
        contractorServiceData.setIdentifier(UUID.randomUUID().toString());
        contractorServiceDataRepository.save(contractorServiceData);
        return contractorServiceData.getIdentifier();
    }

    public void updateContractorService(String id, ContractorServiceDTO dto) throws ObjectNotFoundException{
        ContractorServiceData contractorServiceData = contractorServiceDataRepository.getByIdentifier(id);
        if(contractorServiceData == null){
            throw new ObjectNotFoundException("Cannot find contractor with id: "+id);
        }
        contractorServiceData.setName(dto.getName());
        contractorServiceDataRepository.save(contractorServiceData);
    }

    public void removeContractorService(String id) throws ObjectNotFoundException{
        ContractorServiceData contractorServiceData = contractorServiceDataRepository.getByIdentifier(id);
        if(contractorServiceData == null){
            throw new ObjectNotFoundException("Cannot find contractor with id: "+id);
        }

        contractorServiceDataRepository.delete(contractorServiceData);
    }

}
