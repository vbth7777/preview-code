/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.util.Date;
import mytool.Inputer;

/**
 *
 * @author user
 */
// has-a
public class Car {
    private String brand, model;
    private int year;
    private Engine usedEngine; // 1 Car has 1 Engine

    public Car() {
        this.brand = "MEC";
        this.model = "MayBach";
        this.year = 2025;
        this.usedEngine = new Engine();
    }

    public Car(String brand, String model, int year, Engine usedEngine) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.usedEngine = usedEngine;
    }

    public Car(String brand, String model, int year, String type, int power) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.usedEngine = new Engine(type, power);
    }

    public void inputCar() {
        this.brand = Inputer.inputString("[^[M][E][C]$|^[L][x]$");
        this.model = Inputer.inputString("(.)+");
        Date currentDate = new Date();
        this.year = Inputer.inputNumber(2000, currentDate.getYear() + 1900);
        this.usedEngine.input();
    }

    public void outputCar() {
        System.out.println("brand:" + brand);
        System.out.println("model:" + model);
        System.out.println("year:" + year);
        usedEngine.output();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Engine getUsedEngine() {
        return usedEngine;
    }

    public void setUsedEngine(Engine usedEngine) {
        this.usedEngine = usedEngine;
    }

    public static void main(String[] args) {
        Car A = new Car();
        A.outputCar();
        // target:sua year cua A la 2024
        A.setYear(2024);

        // xuat year cua A
        System.out.println(A.getYear());

        Engine eng = new Engine("Hyrid", 2);
        Car B = new Car("MEC", "C300", 2025, eng);
        B.outputCar();
        // thay brand cua B thanh "LX"
        B.setBrand("LX");

        // xuat engine cua B
        B.getUsedEngine().output();

        Engine eng_new = new Engine("MEC", 4);
        String model_new = "E300";
        // cap nhat model, engine cua B theo 2 bien tren
        B.setModel(model_new);
        B.setUsedEngine(eng_new);
        // xuat day du thong tin cua B
        B.outputCar();
    }
}
