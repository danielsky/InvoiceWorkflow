package com.dskimina.repositories;

import com.dskimina.data.Contractor;
import org.springframework.data.repository.CrudRepository;

public interface ContractorRepository extends CrudRepository<Contractor, Long> {

    Contractor getByIdentifier(String id);
}
