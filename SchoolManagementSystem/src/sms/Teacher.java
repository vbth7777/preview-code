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
public class Teacher extends User{
    private String teacherID;
    private String subject;

    public Teacher() {
        this.teacherID = "None";
        this.subject = "None";
    }

    public Teacher(String teacherID, String subject) {
        this.teacherID = teacherID;
        this.subject = subject;
    }

    public Teacher(String teacherID, String subject, String username, String password) {
        super(username, password);
        this.teacherID = teacherID;
        this.subject = subject;
    }
    
    public void displayInforTeacher(){
        System.out.println("--------- TEACHER ID: " + teacherID + " ---------");
        System.out.println("SUBJECT: " + subject);
        displayInfo();
    }
    public void inputTeacher(){
        inputUser();
        System.out.println("Enter teacher ID: ");
        teacherID = Inputer.inputString("^[a-zA-Z0-9]+$");
        System.out.println("Enter subject: ");
        subject = Inputer.inputString("^[a-zA-Z0-9]+$");
    }

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    
}
