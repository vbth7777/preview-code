/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sms;

import mytool.Inputer;
import service.IConstants;

/**
 *
 * @author ADMIN
 */
public class Student extends User {

    private String studentID;
    private Course[] courses = new Course[IConstants.MAX];
    private int courseCount = 0;

    public Student() {
        this.studentID = "None";
    }

    public Student(String studentID) {
        this.studentID = studentID;
    }

    public Student(String studentID, String username, String password) {
        super(username, password);
        this.studentID = studentID;
    }

    public void displayInfoStudent() {
        System.out.println("--------- STUDENT ID: " + studentID + " ------");
        displayInfo();
        if (courseCount > 0) {
            System.out.println("COURSE LIST: ");
            for (int i = 0; i < courseCount; i++) {
                courses[i].displayCourse();
            }
        }
    }

    public void enrollCourse(Course c) {
        if (courseCount >= IConstants.MAX) {
            System.out.println("Filled all courses!!!");

        } else {
            courses[courseCount] = c;
            courseCount++;
        }
    }

    public void inputStudent() {
        inputUser();
        System.out.println("Enter student ID: ");
        studentID = Inputer.inputString("^[a-zA-Z0-9]+$");
    }

    public void inputNewCourse() {
        Course c = new Course();
        c.inputCourse();
        enrollCourse(c);
    }

    public String getStudentID() {
        return studentID;
    }

    public int getCourseCount() {
        return courseCount;
    }

    public void setCourseCount(int courseCount) {
        this.courseCount = courseCount;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public Course[] getCourses() {
        return courses;
    }

    public void setCourses(Course[] courses) {
        this.courses = courses;
    }

}
