package com.eleonoralion.services;

import com.eleonoralion.forms.UserLoginForm;
import com.eleonoralion.forms.UserRegistrationForm;
import com.eleonoralion.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getUsers();
    void addUser(UserRegistrationForm userRegistrationForm);
    Optional<User> findUsersByLogin(String login);
    UserLoginForm getUserFormWithCaptcha(UserLoginForm userLoginForm);
}
