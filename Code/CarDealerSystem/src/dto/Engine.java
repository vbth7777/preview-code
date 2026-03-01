/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import mytool.Inputer;

/**
 *
 * @author user
 */
public class Engine {
    private String type;
    private int power;

    public Engine() {
        type="electric";
        power=1;
    }

    public Engine(String type, int power) {
        this.type = type;
        this.power = power;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
    public void input(){
        System.out.println("enter type:");
        this.type=Inputer.inputString("^[a-zA-Z]+$");
        System.out.println("enter power:");
        this.power=Inputer.inputNumber(1, 5);
    }
    public void output(){
        System.out.println("type:"+ this.type);
        System.out.println("power:"+this.power);
    }
}
