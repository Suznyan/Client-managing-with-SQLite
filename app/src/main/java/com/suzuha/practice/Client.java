package com.suzuha.practice;

public class Client {
    protected String url;
    protected String name;
    protected String email;
    protected String password;
    protected String gender;
    protected String nationality;

    public Client(String url, String name, String email, String password, String gender, String nationality) {
        this.url = url;
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.nationality = nationality;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
