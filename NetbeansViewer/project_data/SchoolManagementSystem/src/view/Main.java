package view;


import java.util.Scanner;
import sms.Student;
import sms.Teacher;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ADMIN
 */
public class Main {
    public static void main(String[] args) {
        int choice = 0;
        Student st = new Student();
        Teacher t = new Teacher(); 
        
        do {            
            System.out.println("========MENU=======");
            System.out.println("1. Create student");
            System.out.println("2. Add a new Course for the student");
            System.out.println("3. Create teacher");
            System.out.println("4. Display student");
            System.out.println("5. Display teacher");
            System.out.println("Enter choice: ");
            Scanner sc = new Scanner(System.in);
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    st.inputStudent();
                    break;
                case 2:
                    st.inputNewCourse();
                    break;
                case 3:
                    t.inputTeacher();
                    break;
                case 4:
                    st.displayInfoStudent();
                    break;
                case 5:
                    t.displayInforTeacher();
                    break;
                default:
                    throw new AssertionError();
            }
        } while (choice <= 5);
    }
}
