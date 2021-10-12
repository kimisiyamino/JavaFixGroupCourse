package com.eleonoralion.forms;

import lombok.Data;

import javax.persistence.Transient;

@Data
public class UserRegistrationForm {

    private String firstName;
    private String lastName;
    private String nickName;
    private String age;
    private String gender;
    private String email;
    private String login;
    private String password;


}
