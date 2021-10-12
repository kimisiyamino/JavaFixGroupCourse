package com.eleonoralion.dao;

import com.eleonoralion.models.Car;
import com.eleonoralion.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.*;

@Component
public class UsersDaoJdbcTemplateImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private String FIND_USER = "SELECT * FROM fix_user WHERE id=?";
    private String INSERT_USER = "INSERT INTO fix_user(firstname, lastname, age) VALUES(?, ?, ?)";
    private String UPDATE_USER = "UPDATE fix_user SET firstname=?, lastname=?, age=? WHERE id=?";
    private String DELETE_USER = "DELETE FROM fix_user WHERE id=?";

    private String SELECT_ALL_USERS = "SELECT * FROM fix_user";
    private String FIND_BY_FIRSTNAME_USER = "SELECT * FROM fix_user WHERE firstname=?";
    //language=sql
    private String FIND_USER_WITH_CARS = "SELECT fix_user.*, fix_car.id AS car_id, fix_car.model, fix_car.ownerid FROM fix_user LEFT JOIN fix_car ON fix_user.id = fix_car.ownerid WHERE fix_user.id = ?";

    @Autowired
    public UsersDaoJdbcTemplateImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    @Override
    public Optional<User> find(Long id) {
        return jdbcTemplate.query(FIND_USER, userRowMappper, id).stream().findAny();
    }

    @Override
    public void save(User model) {
        //jdbcTemplate.update(INSERT_USER, model.getFirstName(), model.getLastName(), model.getAge());

        Map<String, Object> params = new HashMap<>();
        params.put("firstName", model.getFirstName());
        params.put("lastName", model.getLastName());

        //language=sql
        namedParameterJdbcTemplate.update("INSERT INTO fix_user(firstname, lastname) VALUES (:firstName, :lastName)", params);

    }

    @Override
    public void update(User model) {
        jdbcTemplate.update(UPDATE_USER, model.getFirstName(), model.getLastName(), model.getAge(), model.getId());
    }

    @Override
    public void delete(User model) {
        jdbcTemplate.update(DELETE_USER, model.getId());
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SELECT_ALL_USERS, userRowMappper);
    }

    @Override
    public List<User> findAllByFirstName(String firstName) {
        return jdbcTemplate.query(FIND_BY_FIRSTNAME_USER, userRowMappper, firstName);
    }

    public Optional<User> findWithCars(Long id) {
        usersMap = new HashMap<>();
        jdbcTemplate.query(FIND_USER_WITH_CARS, userWithCarsRowMappper, id);

        if(usersMap.containsKey(id)){
            return Optional.of(usersMap.get(id));
        }

        return Optional.empty();
    }


    private RowMapper<User> userRowMappper = (resultSet, i) ->
            (  User.builder()
                    .id(resultSet.getLong("id"))
                    .firstName(resultSet.getString("firstName"))
                    .lastName(resultSet.getString("lastName")).age(resultSet.getInt("age"))
                    .cars(findWithCars(resultSet.getLong("id")).get().getCars()).build()
    );


    private Map<Long, User> usersMap = new HashMap<>();

    private RowMapper<User> userWithCarsRowMappper = (resultSet, i) -> {
        Long userId = resultSet.getLong("id");

        if (!usersMap.containsKey(userId)) {

            String userFirstName = resultSet.getString("firstname");
            String userLastName = resultSet.getString("lastname");
            int userAge = resultSet.getInt("age");
            List<Car> userCars = new ArrayList<>();

            User user = new User(userId, userFirstName, userLastName, userAge, userCars);
            usersMap.put(userId, user);
        }

        Long carId = resultSet.getLong("car_id");
        String carModel = resultSet.getString("model");
        //Long carOwnerId = resultSet.getLong("ownerid");
        User user = usersMap.get(userId);

        Car car = new Car(carId, carModel, user);
        user.getCars().add(car);

        return user;
    };
}
