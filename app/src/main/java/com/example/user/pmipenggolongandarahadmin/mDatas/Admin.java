package com.example.user.pmipenggolongandarahadmin.mDatas;

/**
 * Created by USER on 15/08/2017.
 */

public class Admin {
    private String username;
    private String password;
    private String email;
    private String password_email;

    public Admin() {
    }

    public Admin(String username, String password, String email, String password_email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.password_email = password_email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword_email() {
        return password_email;
    }
}
