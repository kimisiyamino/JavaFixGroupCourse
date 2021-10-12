package com.eleonoralion.forms;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UserLoginForm {

    @NotEmpty(message = "Поле не должно быть пустым!")
    @Size(min = 4, max = 20, message = "Имя должно содержать от 4 до 20 символов!")
    private String login;
    @NotEmpty(message = "Поле не должно быть пустым!")
    @Size(min = 4, max = 20, message = "Пароль должен содержать от 4 до 20 символов!")
    private String password;
    private Boolean rememberMe;

    @Transient
    private String captcha;

    @Transient
    private String hiddenCaptcha;

    @Transient
    private String realCaptcha;
}
