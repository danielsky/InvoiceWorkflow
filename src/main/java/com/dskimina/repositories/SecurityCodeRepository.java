package com.dskimina.repositories;

import com.dskimina.data.SecurityCode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityCodeRepository extends CrudRepository<SecurityCode, Long> {

    SecurityCode getByCode(String code);
}
