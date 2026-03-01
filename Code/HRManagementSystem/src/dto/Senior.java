/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import controller.IRequest;

/**
 *
 * @author ADMIN
 */
public class Senior extends Dev implements IRequest{
    private double bonusSalary;

    public Senior() {
        super();
        bonusSalary = 0;
    }

    public Senior(double bonusSalary) {
        this.bonusSalary = bonusSalary;
    }

    public Senior(double bonusSalary, String proLan) {
        super(proLan);
        this.bonusSalary = bonusSalary;
    }

    public Senior(double bonusSalary, String proLan, String id, String name, double basicSalary, String email, String pwd) {
        super(proLan, id, name, basicSalary, email, pwd);
        this.bonusSalary = bonusSalary;
    }

    @Override
    public double calculateSalary() {
        return getBasicSalary()*bonusSalary;
    }
    @Override
    public String toString(){
        return super.toString()+","+String.format("%.1f", bonusSalary);
    }
    @Override
    public void sendRequestDayOff(){
        System.out.println("Senior send a request: "+IRequest.DayOff1);
    }
}
