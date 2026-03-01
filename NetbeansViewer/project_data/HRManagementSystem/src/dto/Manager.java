/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import controller.IMeeting;
import controller.IRequest;

/**
 *
 * @author ADMIN
 */
public class Manager extends Employee implements IRequest, IMeeting {

    private int expYear;


    public Manager() {
    }

    public Manager(int expYear) {
        this.expYear = expYear;
    }

    public Manager(int expYear, String id, String name, double basicSalary, String email, String pwd) {
        super(id, name, basicSalary, email, pwd);
        this.expYear = expYear;
    }

    @Override
    public double calculateSalary() {
        double heso = 1;
        if (expYear <= 5) {
            heso = 1;
        } else if (expYear <= 10 && expYear > 5) {
            heso = 2;
        } else if (expYear > 10) {
            heso = 3;
        }
        double salary = getBasicSalary() * (heso*expYear);
        return salary;
    }
    @Override
    public void sendRequestDayOff() {
        System.out.println("Don xin nghi phep: " + IRequest.DayOff2);
    }

    @Override
    public void createMeeting() {
        System.out.println("Meeting at the link: ahihi.gmail.com");
    }
    @Override
    public String toString(){
        return super.toString()+","+String.format("%.2f",calculateSalary());
    }
}
