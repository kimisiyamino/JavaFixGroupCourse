package com.eleonoralion.database.dao;

import com.eleonoralion.database.models.User;

import java.util.List;

public interface UserDao extends CrudDao<User> {
    List<User> findAllByFirstName(String firstName);
}
