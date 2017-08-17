package com.dskimina.repositories;

import com.dskimina.enums.Role;
import com.dskimina.data.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    User getByEmail(String email);
    List<User> findByRole(Role role);
}
