/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dto.Dev;
import dto.Employee;
import dto.Fresher;
import dto.Manager;
import dto.Senior;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class EmployeeService {

    private Employee[] arr = new Employee[100];
    private int count = 0;

    //cai dat cac chuc nang: 
    //x them nv, xuat ds nv, tim kiem nv dua vao email
    //x tim kiem nv dua vao id
    //x tim ds nv dua vao 1 phan cua name
    //x dem so nv co luong (khong phai basicSalary) tren muc can tim , user nhap muc can tim
    //x tim thong tin nv co luong thap nhat
    //x tim thong tin nv co luong cao nhat
    //x ghi ds nv vao file text: doc document de lam
    public void themNV() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap id: ");
        String id = sc.nextLine();
        System.out.println("Nhap ten: ");
        sc = new Scanner(System.in);
        String name = sc.nextLine();
        System.out.println("Nhap luong co ban: ");
        sc = new Scanner(System.in);
        double basicSalary = sc.nextDouble();
        System.out.println("Nhap email: ");
        sc = new Scanner(System.in);
        String email = sc.nextLine();
        System.out.println("Nhap password: ");
        sc = new Scanner(System.in);
        String pwd = sc.nextLine();
        int type = 0;
        Employee e = null;
        do {
            System.out.println("Nhap loai nhan vien: 1. Fresher | 2. Senior | 3. Manager ");
            sc = new Scanner(System.in);
            type = sc.nextInt();
            if (type == 1) {
                System.out.println("Nhap ngon ngu lap trinh: ");
                sc = new Scanner(System.in);
                String proLan = sc.nextLine();
                System.out.println("Ban co phai la fullstack khong (y/n): ");
                sc = new Scanner(System.in);
                String choice = sc.nextLine();
                boolean isFullstack = false;
                if (choice.equalsIgnoreCase("y")) {
                    isFullstack = true;
                }
                e = new Fresher(isFullstack, proLan, id, name, basicSalary, email, pwd);
            } else if (type == 2) {
                System.out.println("Nhap ngon ngu lap trinh: ");
                sc = new Scanner(System.in);
                String proLan = sc.nextLine();
                System.out.println("Nhap luong thuong: ");
                sc = new Scanner(System.in);
                double bonusSalary = sc.nextDouble();
                e = new Senior(bonusSalary, proLan, id, name, basicSalary, email, pwd);
            } else if (type == 3) {
                System.out.println("Nhap nam kinh nghiem: ");
                sc = new Scanner(System.in);
                int expYear = sc.nextInt();
                e = new Manager(expYear, id, name, basicSalary, email, pwd);
            } else {
                System.out.println("Loai khong hop le. Vui long nhap lai!");
            }
        } while (type < 1 || type > 3);
        arr[count] = e;
        count++;

    }

    public void xuatDSNV() {
        System.out.println("-----DANH SACH NHAN VIEN------");
        for (int i = 0; i < count; i++) {
            System.out.println(arr[i]);
        }
    }

    public int demSoNVCoLuongTrenMuc(double muc) {
        int c = 0;
        for (int i = 0; i < count; i++) {
            if (arr[i].calculateSalary() >= muc) {
                c++;
            }
        }
        return c;
    }

    public Employee timKiemNVBangEmail(String email) {
        for (int i = 0; i < count; i++) {
            Employee e = arr[i];
            if (e.getEmail().equalsIgnoreCase(email)) {
                return e;
            }
        }
        return null;
    }

    public Employee timKiemNVBangID(String id) {
        for (int i = 0; i < count; i++) {
            Employee e = arr[i];
            if (e.getId().equalsIgnoreCase(id)) {
                return e;
            }
        }
        return null;
    }

    public Employee timKiemNVBangTen(String name) {
        for (int i = 0; i < count; i++) {
            Employee e = arr[i];
            if (e.getName().contains(name)) {
                return e;
            }
        }
        return null;
    }

    public Employee timKiemNVCoLuongThapNhat() {
        Employee min = arr[0];
        for (int i = 0; i < count; i++) {
            Employee e = arr[i];
            if (e.calculateSalary() < min.calculateSalary()) {
                min = e;
            }
        }
        return min;
    }

    public Employee timKiemNVCoLuongCaoNhat() {
        Employee max = arr[0];
        for (int i = 0; i < count; i++) {
            Employee e = arr[i];
            if (e.calculateSalary() > max.calculateSalary()) {
                max = e;
            }
        }
        return max;
    }

    public void ghiDSNV(String tenFile) {
        try {
            File file = new File(tenFile);
            PrintWriter pw = new PrintWriter(file);
            for (int i = 0; i < count; i++) {
                String thongTin = arr[i].toString() + ',' + arr[i].calculateSalary();
                pw.println(thongTin);
            }
            pw.close();
            System.out.println("Ghi danh sach nhan vien thanh cong!!");
        } catch (FileNotFoundException ex) {
            System.out.println("Ghi danh sach nhan vien bi loi!!");
        }

    }
}
