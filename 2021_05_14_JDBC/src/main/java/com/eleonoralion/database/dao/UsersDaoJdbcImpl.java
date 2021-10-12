package com.eleonoralion.database.dao;

import com.eleonoralion.database.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersDaoJdbcImpl implements UserDao {

    private Connection connection;

    //language=sql
    private String SQL_findAll = "SELECT * FROM fix_user";
    //language=sql
    private String SQL_save = "INSERT INTO fix_user(firstname, lastname) VALUES (?, ?)";
    //language=sql
    private String SQL_find = "SELECT * FROM fix_user WHERE id=?";
    //language=sql
    private String SQL_delete = "DELETE FROM fix_user WHERE id=?";
    //language=sql
    private String SQL_update = "UPDATE fix_user SET firstname=?, lastname=? WHERE id=?";

    //language=sql
    private String SQL_findAllByFirstName = "SELECT * FROM fix_user WHERE firstname=?";

    public UsersDaoJdbcImpl(Connection connection){
        this.connection = connection;
    }

    @Override
    public List<User> findAllByFirstName(String firstName) {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_findAllByFirstName);
            preparedStatement.setString(1, firstName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                User user = new User(resultSet.getInt("id"), resultSet.getString("firstName"), resultSet.getString("lastName"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public Optional<User> find(Integer id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_find);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                User user = new User(id, resultSet.getString("firstName"), resultSet.getString("lastName"));
                return Optional.of(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public void save(User model) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_save);
            preparedStatement.setString(1, model.getFirstName());
            preparedStatement.setString(2, model.getLastName());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User model) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_update);
            preparedStatement.setString(1, model.getFirstName());
            preparedStatement.setString(2, model.getLastName());
            preparedStatement.setInt(3, model.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User model) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_delete);
            preparedStatement.setInt(1, model.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> findAll() {

        List<User> users = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_findAll);

            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");

                User user = new User(id, firstName, lastName);

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}
