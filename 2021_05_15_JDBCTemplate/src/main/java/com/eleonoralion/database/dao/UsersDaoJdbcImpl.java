package com.eleonoralion.database.dao;

import com.eleonoralion.database.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersDaoJdbcImpl implements UserDao {

    private DataSource dataSource;
    private Connection connection;

    public UsersDaoJdbcImpl(DataSource dataSource){
        this.dataSource = dataSource;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> findAllByFirstName(String firstName) {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL.FIND_BY_FIRSTNAME_USER);
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
    public Optional<User> findWithCars(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> find(Integer id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL.FIND_USER);
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
            PreparedStatement preparedStatement = connection.prepareStatement(SQL.INSERT_USER);
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
            PreparedStatement preparedStatement = connection.prepareStatement(SQL.UPDATE_USER);
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
            PreparedStatement preparedStatement = connection.prepareStatement(SQL.DELETE_USER);
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
            ResultSet resultSet = statement.executeQuery(SQL.SELECT_ALL_USERS);

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
