package com.example.myapplication;

public class Post {

    private String id;

    private String email;

    private String password;

    private String name;

    private String School;

    public Post(String id, String email, String password, String name, String school)
    {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.School = school;

    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSchool() {
        return School;
    }
}