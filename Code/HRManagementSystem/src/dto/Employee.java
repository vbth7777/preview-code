/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author ADMIN
 */
public abstract class Employee {
    private String id;
    private String name;
    private double basicSalary;
    private String email;
    private String pwd;

    public Employee() {
    }

    public Employee(String id, String name, double basicSalary, String email, String pwd) {
        this.id = id;
        this.name = name;
        this.basicSalary = basicSalary;
        this.email = email;
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }
    @Override
    public String toString(){
        return id.toUpperCase()+","+name.toUpperCase()+","+email.toUpperCase();
    }
    public boolean login(String e, String p){
        return email.equalsIgnoreCase(e) && pwd.equals(p);
    }
    
    public void logout(){
        System.out.println("Chuc ban suc khoe");
    }
    public abstract double calculateSalary();
}
