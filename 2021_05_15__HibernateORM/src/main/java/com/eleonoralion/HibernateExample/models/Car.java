package com.eleonoralion.HibernateExample.models;

import javax.persistence.*;

@Entity
@Table(name = "fix_car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "model")
    private String model;

    @ManyToOne // Много машин у одного пользователя
    @JoinColumn(name = "ownerid")
    private User owner;

    public Car() {
    }

    public Car(Integer id, String model) {
        this.id = id;
        this.model = model;
    }

    public Car(Integer id, String model, User owner) {
        this.id = id;
        this.model = model;
        this.owner = owner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        String own;
        if(owner == null){
            own = "NULL";
        }else {
            own = owner.getFirstName();
        }

        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", owner=" + own +
                '}';
    }
}
