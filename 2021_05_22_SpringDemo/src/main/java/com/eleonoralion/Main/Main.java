package com.eleonoralion.Main;

import com.eleonoralion.dao.UsersDaoJdbcTemplateImpl;
import com.eleonoralion.models.User;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.persistence.EntityManager;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Properties properties = new Properties();

        final String dir = System.getProperty("user.dir") + "\\src\\main\\resources\\";

        try {
            properties.load(new FileInputStream(dir + "db.properties"));

            String dbUrl = properties.getProperty("db.url");
            String dbUsername = properties.getProperty("db.username");
            String dbPassword = properties.getProperty("db.password");
            String dbClass = properties.getProperty("db.driverClassName");

            DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();

            driverManagerDataSource.setDriverClassName(dbClass);
            driverManagerDataSource.setUrl(dbUrl);
            driverManagerDataSource.setUsername(dbUsername);
            driverManagerDataSource.setPassword(dbPassword);

            // DAO
            UsersDaoJdbcTemplateImpl usersDao = new UsersDaoJdbcTemplateImpl(driverManagerDataSource);

            Scanner scanner = new Scanner(System.in);

            List<User> users = usersDao.findAll();
            users.sort(Comparator.comparingLong(User::getId));
            for(User user : users){
                System.out.println(user);
            }

            while (true) {
                Long id = scanner.nextLong();
                User user = usersDao.findWithCars(id).orElse(User.empty());
                System.out.println(user.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
