package com.eleonoralion.database.dao;

public interface SQL {
    //language=sql
    String SELECT_ALL_USERS = "SELECT * FROM fix_user";
    //language=sql
    String INSERT_USER = "INSERT INTO fix_user(firstname, lastname) VALUES (?, ?)";
    //language=sql
    String FIND_USER = "SELECT * FROM fix_user WHERE id=?";
    //language=sql
    String DELETE_USER = "DELETE FROM fix_user WHERE id=?";
    //language=sql
    String UPDATE_USER = "UPDATE fix_user SET firstname=?, lastname=? WHERE id=?";
    //language=sql
    String FIND_BY_FIRSTNAME_USER = "SELECT * FROM fix_user WHERE firstname=?";
}
