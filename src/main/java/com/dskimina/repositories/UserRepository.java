package com.dskimina.repositories;

import com.dskimina.data.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User getByEmail(String email);
    List<User> findByRole(String role);
}
