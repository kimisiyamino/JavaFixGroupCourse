package com.eleonoralion.classes;

public class Human {

    private int age;
    private String name;

    public Human(){
        age = 999;
        name = "DEFAULT NAME";
    }

    public Human(String name) {
        this.name = name;
    }

    public Human(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return age + " " + name;
    }
}
