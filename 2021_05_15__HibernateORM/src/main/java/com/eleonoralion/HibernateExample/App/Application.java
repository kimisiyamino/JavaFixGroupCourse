package com.eleonoralion.HibernateExample.App;

import com.eleonoralion.HibernateExample.models.Car;
import com.eleonoralion.HibernateExample.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class Application {
    public static void main(String[] args) {
        // == Hibernate properties ==
        //hibernate.connection.url
        //hibernate.connection.username
        //hibernate.connection.password
        //hibernate.connection.driver_class
        //hibernate.dialect

//        configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/fixgroup_db");
//        configuration.setProperty("hibernate.connection.username", "postgres");
//        configuration.setProperty("hibernate.connection.password", "postgres");
//        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
//        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");

//configuration.setProperty("hibernate.hbm2ddl.auto", "update");

//        Properties properties = new Properties();
//        try {
//            properties.load(new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\resources\\hibernate.properties"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        configuration.setProperties(properties);

        // config
        Configuration configuration = new Configuration().configure();
        // add model mapping
        configuration.addResource("user.hbm.xml");

        //
        configuration.addAnnotatedClass(Car.class);
        //configuration.addAnnotatedClass(User.class);


        // Create session, start
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        // Open Session
        Session session = sessionFactory.openSession();

        // SELECT USER
        // select user0_.id as id1_0_, user0_.firstname as firstnam2_0_, user0_.lastname as lastname3_0_, user0_.age as age4_0_ from fix_user user0_ where user0_.id=2

        // HQL - hibernate query language
        User user = session.createQuery("FROM User user WHERE user.id = 1", User.class).getSingleResult();

        System.out.println("\n\n" + user + "\n\n");

        List<User> users = session.createQuery("FROM User user", User.class).getResultList();

        for(User user1 : users){
            System.out.println(user1);
        }

        // INSERT
        // insert into fix_user (firstname, lastname, age) values (?, ?, ?)

        session.beginTransaction();
        session.save(new User("TOM", "JERRY", 999));
        session.getTransaction().commit();


        // SELECT CAR (Annotated)
        // select car0_.id as id1_0_, car0_.model as model2_0_, car0_.owner_id as owner_id3_0_ from fix_car car0_

        List<Car> cars = session.createQuery("from Car car", Car.class).getResultList();

        for(Car car : cars){
            System.out.println(car.toString());
        }

        session.close();
    }
}
