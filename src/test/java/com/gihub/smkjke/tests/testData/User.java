package com.gihub.smkjke.tests.testData;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    private String name;
    private String job;
    private String email;
    private String password;

    public User(String name, String job, String email, String password) {
        this.name = name;
        this.job = job;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
