package com.eleonoralion.app;

import cn.apiclub.captcha.servlet.StickyCaptchaServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.unit.DataSize;
import sun.security.util.Password;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@ComponentScan("com.eleonoralion")
@EnableJpaRepositories(basePackages = "com.eleonoralion.repositories")
@EnableTransactionManagement
@EntityScan(basePackages = "com.eleonoralion.models")
public class Application {

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.parse("128KB"));
        factory.setMaxRequestSize(DataSize.parse("128KB"));
        return factory.createMultipartConfig();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
