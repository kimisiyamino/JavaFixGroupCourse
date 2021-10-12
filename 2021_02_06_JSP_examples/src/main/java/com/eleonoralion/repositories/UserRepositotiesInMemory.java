package com.eleonoralion.repositories;

import com.eleonoralion.fakeDB.FakeDataBase;
import com.eleonoralion.models.User;

import javax.servlet.http.HttpSession;
import com.eleonoralion.fakeDB.FakeDataBase;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserRepositotiesInMemory implements UsersRepositories {
    private List<User> users;

    public UserRepositotiesInMemory() {
        // Первая лекция: Создаём фейковых пользователей для примера

        users = new ArrayList<>(FakeDataBase.getFakeDataBase().getUsers());


        //users.add(new User("Leo", "qwertry34", LocalDate.parse("2020-01-30")));
        //users.add(new User("Eleonora", "qwertry34", LocalDate.parse("2007-12-05")));
        //users.add(new User("Geo", "qwertry34", LocalDate.parse("2005-03-11")));
    }

    // Вторая лекция (Обращаемся к фейковой статической базе данных)

    public List<User> getFakeDataBase() {
        return FakeDataBase.getFakeDataBase().getUsers();
    }

    public boolean save(User user) {
        FakeDataBase.getFakeDataBase().getUsers().add(user);
        saveUser(user);
        return true;
    }

    // Проверяем, есть ли в фейковой базе данных с текущим именем и паролем
    @Override
    public boolean isExist(String name, String password){
        for(User user : FakeDataBase.getFakeDataBase().getUsers()){
            if(user.getName().equals(name) && user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    // Первая лекция

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public boolean saveUser(User user) {
        users.add(user);
        return true;
    }
}
