package com.dskimina.repositories;

import com.dskimina.data.Contractor;
import com.dskimina.data.ContractorServiceData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContractorServiceDataRepository extends CrudRepository<ContractorServiceData, Long> {

    ContractorServiceData getByIdentifier(String id);
    List<ContractorServiceData> findByContractor(Contractor contractor);
}
