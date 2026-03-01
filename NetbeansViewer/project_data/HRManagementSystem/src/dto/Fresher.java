/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import controller.IMeeting;

/**
 *
 * @author ADMIN
 */
public class Fresher extends Dev implements IMeeting{
    private boolean isFullstack;

    public Fresher() {
        super();
        isFullstack=true;
    }

    public Fresher(boolean isFullstack) {
        this.isFullstack = isFullstack;
    }

    public Fresher(boolean isFullstack, String proLan) {
        super(proLan);
        this.isFullstack = isFullstack;
    }

    public Fresher(boolean isFullstack, String proLan, String id, String name, double basicSalary, String email, String pwd) {
        super(proLan, id, name, basicSalary, email, pwd);
        this.isFullstack = isFullstack;
    }

    public boolean isIsFullstack() {
        return isFullstack;
    }

    public void setIsFullstack(boolean isFullstack) {
        this.isFullstack = isFullstack;
    }
    
    @Override
    public double calculateSalary() {
        if(isFullstack) return getBasicSalary()*1.1;
        return getBasicSalary();
    }

    @Override
    public void createMeeting() {
        System.out.println("offline meeting at the offcie");
    }

    @Override
    public String toString() {
        return super.toString() + "," + isFullstack;
    }
    
}
