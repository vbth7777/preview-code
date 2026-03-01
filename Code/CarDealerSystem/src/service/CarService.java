/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dto.Car;

import mytool.Inputer;

/**
 *
 * @author user
 */
public class CarService {
    private Car[] arr = new Car[IConstant.MAX];
    private int count = 0;

    public boolean addCar(Car c) {
        if (count < IConstant.MAX) {
            arr[count] = c;
            count++;
            return true;
        }
        return false;
    }

    // ham nay de nhap nhieu xe vao arr
    // sau khi tao 1 xe. Hoi add nua ko?
    public void inputCars() {
        int answer = 0;
        do {
            Car A = new Car();
            A.inputCar();// nhap tu ban phim cho 1 xe
            arr[count] = A;
            count++;
            System.out.println("add more(1|0)?:");
            answer = Inputer.inputNumber(0, 1);
        } while (answer == 1);
    }

    public void displayCars() {
        for (Car c : arr) {
            if (c != null) {
                c.outputCar();
            }
        }
    }

    // ham nay de dem so car trong arr dua vao ten brand
    // input: ten brand can thong ke
    // output: so xe trung brand do
    public int countCarsByBrand(String brand) {
        int result = 0;
        for (Car c : arr) {
            if (c != null) {
                if (c.getBrand().equalsIgnoreCase(brand)) {
                    result++;
                }
            }
        }
        return result;
    }

    // hay dem so xe cung nam san xuat
    // input: year can dem
    // out: so xe theo input
    public int countCarsByYear(int year) {
        int result = 0;
        for (Car c : arr) {
            if (c != null) {
                if (c.getYear() == year) {
                    result++;
                }
            }
        }
        return result;
    }

    // ham nay de xuat car xe theo brand
    public void displayCarsByBrand(String brand) {
        for (Car c : arr) {
            if (c == null)
                break;
            else {
                if (c.getBrand().equalsIgnoreCase(brand)) {
                    c.outputCar();
                }
            }
        }
    }

    // ham nay de xuat cac xe theo engine's type
    public void displayCarsByType(String type) {
        for (Car c : arr) {
            if (c != null) {
                if (c.getUsedEngine().getType().equalsIgnoreCase(type)) {
                    c.outputCar();
                }
            }
        }
    }

    // ham nay de tra ve array chua cac xe co brand can tim
    // input: brand
    // output: array of cars
    public Car[] getCarsByBrand(String brand) {
        Car[] result = new Car[count];
        int n = 0;
        for (Car c : arr) {
            if (c != null) {
                if (c.getBrand().equalsIgnoreCase(brand)) {
                    result[n] = c;
                    n++;
                }
            }
        }
        return result;
    }

    // ham nay de tim cac xe theo nam san xuat
    public Car[] getCarsByYear(int year) {
        Car[] result = new Car[count];
        int n = 0;
        for (Car c : arr) {
            if (c != null) {
                if (c.getYear() == year) {
                    result[n] = c;
                    n++;
                }
            }
        }
        return result;
    }

    // ham nay de lay cac xe co power between low and high
    public Car[] getCarsByPower(int low, int high) {
        Car[] result = new Car[count];
        int n = 0;
        for (Car c : arr) {
            if (c != null) {
                int power = c.getUsedEngine().getPower();
                if (power >= low && power <= high) {
                    result[n] = c;
                    n++;
                }
            }
        }
        return result;
    }

    // ham nay de in ra cac xe theo format sau:
    /*
     * BAND: MEC
     * YEAR: 2025
     * MODEL TYPE POWER
     * E300 electric 3
     * C250 hyreid 2
     * YEAR:2026
     * MODEL TYPE POWER
     * E400 electric 3
     * C250 electric 2
     * 
     */
    public void statistic(String brand) {
        System.out.println("BRAND:  " + brand);

        // Tim cac nam san xuat khac nhau cua brand nay
        int[] cacNam = new int[count];
        int soNam = 0;

        for (Car c : arr) {
            if (c != null) {
                if (c.getBrand().equalsIgnoreCase(brand)) {
                    boolean daCo = false;
                    for (int i = 0; i < soNam; i++) {
                        if (cacNam[i] == c.getYear()) {
                            daCo = true;
                            break;
                        }
                    }
                    if (daCo == false) {
                        cacNam[soNam] = c.getYear();
                        soNam++;
                    }
                }
            }
        }

        // Voi moi nam, in ra cac xe
        for (int i = 0; i < soNam; i++) {
            System.out.println("   YEAR: " + cacNam[i]);
            System.out.println("      MODEL    TYPE      POWER");
            for (Car c : arr) {
                if (c != null) {
                    if (c.getBrand().equalsIgnoreCase(brand) && c.getYear() == cacNam[i]) {
                        System.out.println("       " + c.getModel() + "    " + c.getUsedEngine().getType() + "    "
                                + c.getUsedEngine().getPower());
                    }
                }
            }
        }
    }

    // ham nay dem so xe tren each brand
    /*
     * BRAND NUMBER of CARS
     * MEC: 3
     * LX: 2
     * Honda: 12
     */
    public void statictisCarsByBrand() {
        String[] brands = new String[count];
        int[] brandCounts = new int[count];
        int n = 0;

        for (Car c : arr) {
            if (c == null) {
                break;
            } else {
                String brand = c.getBrand();
                // tim thu xem brand nay da co trong mang brands chua
                int foundIndex = -1;
                for (int i = 0; i < n; i++) {
                    if (brands[i] != null && brands[i].equalsIgnoreCase(brand)) {
                        foundIndex = i;
                        break;
                    }
                }

                if (foundIndex == -1) {
                    // Chua co thi them moi
                    brands[n] = brand;
                    brandCounts[n] = 1;
                    n++;
                } else {
                    // Co roi thi tang so luong len
                    brandCounts[foundIndex]++;
                }
            }
        }

        // xuat ket qua ra man hinh
        for (int i = 0; i < n; i++) {
            System.out.println(brands[i] + ":\t" + brandCounts[i]);
        }
    }

    public static void main(String[] args) {
        CarService service = new CarService();

        // Tao du lieu gia
        service.arr[service.count++] = new Car("MEC", "E300", 2025, "electric", 3);
        service.arr[service.count++] = new Car("MEC", "C250", 2025, "hyreid", 2);
        service.arr[service.count++] = new Car("MEC", "E400", 2026, "electric", 3);
        service.arr[service.count++] = new Car("MEC", "C250", 2026, "electric", 2);
        service.arr[service.count++] = new Car("LX", "LS500", 2024, "gas", 4);
        service.arr[service.count++] = new Car("LX", "RX350", 2025, "hyreid", 3);
        service.arr[service.count++] = new Car("Honda", "Civic", 2024, "gas", 2);

        System.out.println("--- 1. TESTING displayCars() ---");
        service.displayCars();

        System.out.println("\n--- 2. TESTING countCarsByBrand('MEC') ---");
        System.out.println("Result: " + service.countCarsByBrand("MEC") + " xe");

        System.out.println("\n--- 3. TESTING countCarsByYear(2025) ---");
        System.out.println("Result: " + service.countCarsByYear(2025) + " xe");

        System.out.println("\n--- 4. TESTING displayCarsByBrand('LX') ---");
        service.displayCarsByBrand("LX");

        System.out.println("\n--- 5. TESTING displayCarsByType('electric') ---");
        service.displayCarsByType("electric");

        System.out.println("\n--- 6. TESTING getCarsByBrand('Honda') ---");
        Car[] hondas = service.getCarsByBrand("Honda");
        for (Car c : hondas) {
            if (c != null) {
                System.out.println(c.getBrand() + " " + c.getModel());
            }
        }

        System.out.println("\n--- 7. TESTING getCarsByYear(2026) ---");
        Car[] cars2026 = service.getCarsByYear(2026);
        for (Car c : cars2026) {
            if (c != null) {
                System.out.println(c.getBrand() + " " + c.getModel() + " - " + c.getYear());
            }
        }

        System.out.println("\n--- 8. TESTING getCarsByPower(3, 4) ---");
        Car[] powerfulCars = service.getCarsByPower(3, 4);
        for (Car c : powerfulCars) {
            if (c != null) {
                System.out.println(c.getBrand() + " " + c.getModel() + " - Power: " + c.getUsedEngine().getPower());
            }
        }

        System.out.println("\n--- 9. TESTING statistic('MEC') ---");
        service.statistic("MEC");

        System.out.println("\n--- 10. TESTING statictisCarsByBrand() ---");
        service.statictisCarsByBrand();
    }
}
