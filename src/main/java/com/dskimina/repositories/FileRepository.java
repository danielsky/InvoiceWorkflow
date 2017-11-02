package com.dskimina.repositories;

import com.dskimina.data.File;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends CrudRepository<File, Long> {

    File getByIdentifier(String identifier);
}
