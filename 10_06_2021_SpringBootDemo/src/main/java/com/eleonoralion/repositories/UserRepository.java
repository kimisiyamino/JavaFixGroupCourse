package com.eleonoralion.repositories;

import com.eleonoralion.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUsersByLogin(String login);
}
