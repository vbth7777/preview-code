/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sms;

import mytool.Inputer;

/**
 *
 * @author ADMIN
 */
public class User {
    private String username;
    private String password;

    public User() {
        this.username = "None";
        this.password = "None";
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public void displayInfo(){
        System.out.println("USERNAME: " + username + " - PASSWORD: " + password);
    }
    public void inputUser(){
        System.out.println("Enter username: ");
        username = Inputer.inputString("^[a-zA-Z0-9]+$");
        System.out.println("Enter password: ");
        password = Inputer.inputString("^[a-zA-Z0-9]+$");
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
