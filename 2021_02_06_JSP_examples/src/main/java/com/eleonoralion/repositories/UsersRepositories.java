package com.eleonoralion.repositories;

import com.eleonoralion.models.User;

import java.util.List;

public interface UsersRepositories {
    List<User> findAll();
    boolean saveUser(User user);
    // Существует ли ...
    boolean isExist(String name, String password);

    boolean save(User user);
}
