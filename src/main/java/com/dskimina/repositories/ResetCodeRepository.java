package com.dskimina.repositories;

import com.dskimina.data.ResetCode;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResetCodeRepository extends CrudRepository<ResetCode, Long> {

    ResetCode getByCode(String code);
}
