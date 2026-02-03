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
public class Course {
    private String courseName;
    private String courseCode;

    public Course() {
        this.courseCode = "None";
        this.courseName = "None";
    }

    public Course(String courseName, String courseCode) {
        this.courseName = courseName;
        this.courseCode = courseCode;
    }
    public void inputCourse(){
        System.out.println("Enter course code: ");
        courseCode = Inputer.inputString("^[a-zA-Z0-9]+$");
        System.out.println("Enter course name: ");
        courseName = Inputer.inputString("^[a-zA-Z0-9]+$");
    }
    public void displayCourse(){
        System.out.println("COURSE: " + courseCode + " - " + courseName);
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    
}
