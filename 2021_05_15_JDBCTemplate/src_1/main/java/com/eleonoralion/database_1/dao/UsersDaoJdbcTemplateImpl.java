package com.eleonoralion.database.dao;

import com.eleonoralion.database.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class UsersDaoJdbcTemplateImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;

    public UsersDaoJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<User> findAllByFirstName(String firstName) {
        return jdbcTemplate.query(SQL.FIND_BY_FIRSTNAME_USER, userRowMappper, firstName);
    }

    @Override
    public Optional<User> find(Integer id) {
        return jdbcTemplate.query(SQL.INSERT_USER, userRowMappper, id).stream().findAny();
    }

    @Override
    public void save(User model) {
        jdbcTemplate.update(SQL.INSERT_USER, model.getFirstName(), model.getLastName());
    }

    @Override
    public void update(User model) {
        jdbcTemplate.update(SQL.UPDATE_USER, model.getFirstName(), model.getLastName(), model.getId());
    }

    @Override
    public void delete(User model) {
        jdbcTemplate.update(SQL.DELETE_USER, model.getId());
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL.SELECT_ALL_USERS, userRowMappper);
    }


    private RowMapper<User> userRowMappper = (resultSet, i) -> new User(resultSet.getInt("id"),
                    resultSet.getString("firstName"),
                    resultSet.getString("lastName"));
}