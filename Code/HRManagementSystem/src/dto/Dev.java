/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author ADMIN
 */
public abstract class Dev extends Employee{
    private String proLan;

    public Dev() {
        super();
        proLan="Java";
    }

    public Dev(String proLan) {
        this.proLan = proLan;
    }

    public Dev(String proLan, String id, String name, double basicSalary, String email, String pwd) {
        super(id, name, basicSalary, email, pwd);
        this.proLan = proLan;
    }

    public String getProLan() {
        return proLan;
    }

    public void setProLan(String proLan) {
        this.proLan = proLan;
    }
    public void pushCodeToGitHub(){
        System.out.println("Comming soon");
    }
    @Override
    public String toString(){
        return super.toString()+","+proLan;
    }
    
}
