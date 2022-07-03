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
}
