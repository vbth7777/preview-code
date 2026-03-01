/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import controller.IMeeting;
import controller.IRequest;
import dto.Employee;
import dto.Fresher;
import dto.Manager;
import dto.Senior;
import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class Main {

    public static void main(String[] args) {
        // //Employee e = new Employee();
        // IMeeting tmp;
        // IRequest tmp2;
        // Employee e = new Fresher(true, "C#", "SE123", "thi no", 123,
        // "thino@gmail.com", "djdksk");
        // System.out.println(e);
        // tmp = (Fresher)e;
        // tmp.createMeeting();
        // e = new Manager(12, "se234", "trong thuy", 2, "trongthuy@gmail.com", "sdfa");
        // System.out.println(e);
        // tmp = (Manager)e;
        // tmp.createMeeting();
        // tmp2 = (Manager)e;
        // tmp2.sendRequestDayOff();
        // e = new Senior(1.3, "Python", "se234", "chi pheo", 3, "chipheo@gmail.com",
        // "sdfff");
        // System.out.println(e);
        // //tmp = (Senior)e; Khong viet duoc vi senior va Imeeting khong lien quan
        // tmp2=(Senior)e;
        // tmp2.sendRequestDayOff();
        int choice = 0;
        service.EmployeeService employeeService = new service.EmployeeService();
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("\n========== QUAN LY NHAN VIEN ============");
            System.out.println("1. Them nhan vien");
            System.out.println("2. Xuat danh sach nhan vien");
            System.out.println("3. Tim kiem nhan vien bang Email");
            System.out.println("4. Tim kiem nhan vien bang ID");
            System.out.println("5. Tim kiem nhan vien bang Ten");
            System.out.println("6. Dem nhan vien co luong tren muc N");
            System.out.println("7. Tim nhan vien co luong thap nhat");
            System.out.println("8. Tim nhan vien co luong cao nhat");
            System.out.println("9. Ghi danh sach vao File TXT");
            System.out.println("10. Thoat");
            System.out.print(">>> Chon chuc nang: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    employeeService.themNV();
                    break;
                case 2:
                    employeeService.xuatDSNV();
                    break;
                case 3:
                    System.out.print("Nhap Email can tim: ");
                    String email = sc.nextLine();
                    Employee empByEmail = employeeService.timKiemNVBangEmail(email);
                    if (empByEmail != null)
                        System.out.println(empByEmail);
                    else
                        System.out.println("Khong tim thay nhan vien nao co email nay.");
                    break;
                case 4:
                    System.out.print("Nhap ID can tim: ");
                    String id = sc.nextLine();
                    Employee empById = employeeService.timKiemNVBangID(id);
                    if (empById != null)
                        System.out.println(empById);
                    else
                        System.out.println("Khong tim thay nhan vien nao co ID nay.");
                    break;
                case 5:
                    System.out.print("Nhap Ten can tim: ");
                    String name = sc.nextLine();
                    Employee empByName = employeeService.timKiemNVBangTen(name);
                    if (empByName != null)
                        System.out.println(empByName);
                    else
                        System.out.println("Khong tim thay nhan vien nao co Ten nay.");
                    break;
                case 6:
                    System.out.print("Nhap muc luong can tim (Vi du 2000): ");
                    double mucLuong = sc.nextDouble();
                    sc.nextLine();
                    int count = employeeService.demSoNVCoLuongTrenMuc(mucLuong);
                    System.out.println("Co " + count + " nhan vien co luong > " + mucLuong);
                    break;
                case 7:
                    Employee minEmp = employeeService.timKiemNVCoLuongThapNhat();
                    if (minEmp != null)
                        System.out.println("Nhan vien luong thap nhat: " + minEmp);
                    else
                        System.out.println("Danh sach trong!");
                    break;
                case 8:
                    Employee maxEmp = employeeService.timKiemNVCoLuongCaoNhat();
                    if (maxEmp != null)
                        System.out.println("Nhan vien luong cao nhat: " + maxEmp);
                    else
                        System.out.println("Danh sach trong!");
                    break;
                case 9:
                    System.out.print("Nhap ten file muon mo (Vi du: nhanvien.txt): ");
                    String fileName = sc.nextLine();
                    employeeService.ghiDSNV(fileName);
                    break;
                case 10:
                    System.out.println("Thoat chuong trinh.");
                    return;
                default:
                    System.out.println("Lua chon khong hop le!");
                    break;
            }
        } while (true);
    }
}
