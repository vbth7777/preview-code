/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import dto.Car;
import service.CarService;
import mytool.Inputer;

/**
 *
 * @author user
 */
public class Program {
    public static void main(String[] args) {
        CarService service = new CarService();
        int choice;

        // Tao du lieu gia (Dummy data)
        service.addCar(new Car("MEC", "E300", 2025, "electric", 3));
        service.addCar(new Car("MEC", "C250", 2025, "hyreid", 2));
        service.addCar(new Car("MEC", "E400", 2026, "electric", 3));
        service.addCar(new Car("MEC", "C250", 2026, "electric", 2));
        service.addCar(new Car("LX", "LS500", 2024, "gas", 4));
        service.addCar(new Car("LX", "RX350", 2025, "hyreid", 3));
        service.addCar(new Car("Honda", "Civic", 2024, "gas", 2));

        do {
            System.out.println("\n====== CAR DEALER SYSTEM ======");
            System.out.println("1. Add cars");
            System.out.println("2. Display all cars");
            System.out.println("3. Count cars by brand");
            System.out.println("4. Count cars by year");
            System.out.println("5. Display cars by brand");
            System.out.println("6. Display cars by type");
            System.out.println("7. Get cars by brand");
            System.out.println("8. Get cars by year");
            System.out.println("9. Get cars by power range");
            System.out.println("10. Statistic by brand");
            System.out.println("11. Statistic cars by brand");
            System.out.println("0. Exit");
            System.out.println("===============================");
            System.out.println("Enter your choice:");
            choice = Inputer.inputNumber(0, 11);

            switch (choice) {
                case 1:
                    service.inputCars();
                    break;
                case 2:
                    System.out.println("--- All Cars ---");
                    service.displayCars();
                    break;
                case 3:
                    System.out.println("Enter brand to count:");
                    String b1 = Inputer.inputString(".+");
                    System.out.println("=> Number of cars: " + service.countCarsByBrand(b1));
                    break;
                case 4:
                    System.out.println("Enter year to count:");
                    int y1 = Inputer.inputNumber(1900, 2100);
                    System.out.println("=> Number of cars: " + service.countCarsByYear(y1));
                    break;
                case 5:
                    System.out.println("Enter brand to display:");
                    String b2 = Inputer.inputString(".+");
                    service.displayCarsByBrand(b2);
                    break;
                case 6:
                    System.out.println("Enter engine type:");
                    String type = Inputer.inputString(".+");
                    service.displayCarsByType(type);
                    break;
                case 7:
                    System.out.println("Enter brand to get:");
                    String b3 = Inputer.inputString(".+");
                    Car[] cars1 = service.getCarsByBrand(b3);
                    for (Car c : cars1) {
                        if (c != null) {
                            c.outputCar();
                            System.out.println("-----");
                        }
                    }
                    break;
                case 8:
                    System.out.println("Enter year to get:");
                    int y2 = Inputer.inputNumber(1900, 2100);
                    Car[] cars2 = service.getCarsByYear(y2);
                    for (Car c : cars2) {
                        if (c != null) {
                            c.outputCar();
                            System.out.println("-----");
                        }
                    }
                    break;
                case 9:
                    System.out.println("Enter MIN power:");
                    int p1 = Inputer.inputNumber(0, 100);
                    System.out.println("Enter MAX power:");
                    int p2 = Inputer.inputNumber(0, 100);
                    Car[] cars3 = service.getCarsByPower(p1, p2);
                    for (Car c : cars3) {
                        if (c != null) {
                            c.outputCar();
                            System.out.println("-----");
                        }
                    }
                    break;
                case 10:
                    System.out.println("Enter brand for detail statistic:");
                    String b4 = Inputer.inputString(".+");
                    service.statistic(b4);
                    break;
                case 11:
                    System.out.println("--- Statistic Summary ---");
                    service.statictisCarsByBrand();
                    break;
                case 0:
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        } while (choice != 0);
    }
}
