/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mytool;

import java.util.Scanner;

/**
 *
 * @author user
 */
// lop nay dung de chua cac ham hay su dung trong nhieu project
public class Inputer {
    private static Scanner sc = new Scanner(System.in);

    // ham nay tra ve chuoi nhap tu ban phi,m
    // chuoi nhap khop voi chuoi mau(pattern)
    public static String inputString(String pattern) {
        String s = sc.nextLine();
        if (s.matches(pattern))
            return s;
        return "";
    }

    public static int inputNumber(int min, int max) {
        try {
            int number = Integer.parseInt(sc.nextLine());
            if (number >= min && number <= max)
                return number;
        } catch (Exception e) {
            // handle parse exception
        }
        return Integer.MAX_VALUE;
    }
}
