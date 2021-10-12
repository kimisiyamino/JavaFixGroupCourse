package com.eleonoralion.repositories;

import com.eleonoralion.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<User, Long> {
    List<User> findAllByAge(Integer age);
    List<User> findAllByFirstName(String firstName);
    List<User> findAllByFirstNameAndLastName(String firstName, String lastName);
}
