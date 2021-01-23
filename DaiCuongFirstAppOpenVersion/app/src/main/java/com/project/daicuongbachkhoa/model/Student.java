package com.project.daicuongbachkhoa.model;

//model for student
public class Student {
    private String name,id,email,pass;

    public Student() {
    }

    public Student(String name, String id, String email, String pass) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPass() {
        return pass;
    }
}
