package com.eleonoralion.services;

import cn.apiclub.captcha.Captcha;
import com.eleonoralion.forms.UserLoginForm;
import com.eleonoralion.forms.UserRegistrationForm;
import com.eleonoralion.models.Gender;
import com.eleonoralion.models.Role;
import com.eleonoralion.models.State;
import com.eleonoralion.models.User;
import com.eleonoralion.repositories.UserRepository;
import com.eleonoralion.util.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @Override
    public void addUser(UserRegistrationForm userRegistrationForm) {

        String hashPassword = passwordEncoder.encode(userRegistrationForm.getPassword());

        userRepository.save(User.builder()
                      .firstName(userRegistrationForm.getFirstName())
                      .lastName(userRegistrationForm.getLastName())
                      .nickName(userRegistrationForm.getNickName())
                      .age(userRegistrationForm.getAge())
                      .email(userRegistrationForm.getEmail())
                      .gender(Gender.valueOf(userRegistrationForm.getGender()))
                      .login(userRegistrationForm.getLogin())
                      .hashPassword(hashPassword)
                      .imgFileName("")
                      .role(Role.USER)
                      .state(State.ACTIVE)
                      .build());
    }

    @Override
    public Optional<User> findUsersByLogin(String login) {
        return Optional.of(userRepository.findUsersByLogin(login));
    }

    @Override
    public UserLoginForm getUserFormWithCaptcha(UserLoginForm userLoginForm) {
        return getCaptcha(userLoginForm);
    }

    private UserLoginForm getCaptcha(UserLoginForm userLoginForm) {
        Captcha captcha = CaptchaUtil.createCaptcha(240, 70);
        userLoginForm.setHiddenCaptcha( captcha.getAnswer());
        userLoginForm.setCaptcha("");
        userLoginForm.setRealCaptcha(CaptchaUtil.encodeCaptcha(captcha));



        return userLoginForm;
    }

}
