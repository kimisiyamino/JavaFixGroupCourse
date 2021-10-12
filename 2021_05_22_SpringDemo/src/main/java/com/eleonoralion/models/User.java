package com.eleonoralion.models;

import com.eleonoralion.forms.UserForm;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "fix_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "age")
    private Integer age;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Car> cars;

    public static User empty(){
        return new User();
    }

    public static User from(UserForm userForm){
        return User.builder().firstName(userForm.getFirstName()).lastName(userForm.getLastName()).build();
    }
}
