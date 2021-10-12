package com.eleonoralion.dao;

import com.eleonoralion.models.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends CrudDAO<User> {
    List<User> findAllByFirstName(String firstName);
}
