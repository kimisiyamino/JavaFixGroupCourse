package com.eleonoralion.database.models;

public class Car {
    private Integer id;
    private String model;
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
}
