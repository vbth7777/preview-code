/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mytool;

import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class Inputer {
    public static String inputString(String pattern) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        if(s.matches(pattern)) return s;
        return "";
    }
    public static int inputNumber(int min, int max){
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        if(number >= min && number <= max) return number;
        return Integer.MAX_VALUE;
    }
}
