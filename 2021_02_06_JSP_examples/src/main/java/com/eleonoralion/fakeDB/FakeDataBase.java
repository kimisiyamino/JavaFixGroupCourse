package com.eleonoralion.fakeDB;

import com.eleonoralion.models.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FakeDataBase {
    private final static FakeDataBase fakeDataBase;

    static {
        fakeDataBase = new FakeDataBase();
    }

    private static List<User> users;

    private FakeDataBase(){
        users = new ArrayList<>();

        users.add(new User("Leo", "qwertry4434", LocalDate.parse("2020-01-30")));
        users.add(new User("Eleonora", "qwertry314", LocalDate.parse("2007-12-05")));
        users.add(new User("Geo", "qwertry5634", LocalDate.parse("2005-03-11")));
    }

    public static FakeDataBase getFakeDataBase(){
        return fakeDataBase;
    }

    public static List<User> getUsers(){
        return users;
    }
}
