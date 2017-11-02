package com.dskimina.repositories;

import com.dskimina.data.Contractor;
import com.dskimina.data.ContractorServiceData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractorServiceDataRepository extends CrudRepository<ContractorServiceData, Long> {

    ContractorServiceData getByIdentifier(String id);
    List<ContractorServiceData> findByContractor(Contractor contractor);
}
