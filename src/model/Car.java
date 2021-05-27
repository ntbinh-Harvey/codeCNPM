/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author pc
 */
public class Car implements Serializable{
    private int id;
    private String name;
    private String model;
    private String carLicensePlate;

    public Car() {
        super();
    }

    public Car(String name, String model, String carLicensePlate) {
        super();
        this.name = name;
        this.model = model;
        this.carLicensePlate = carLicensePlate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCarLicensePlate() {
        return carLicensePlate;
    }

    public void setCarLicensePlate(String carLicensePlate) {
        this.carLicensePlate = carLicensePlate;
    }
}
