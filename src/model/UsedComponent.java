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
public class UsedComponent implements Serializable{
    private int id;
    private int quantity;
    private int price;
    private CarComponent cpn;

    public UsedComponent() {
        super();
    }

    public UsedComponent(int id, int quantity, int price, CarComponent cpn) {
        super();
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.cpn = cpn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public CarComponent getCpn() {
        return cpn;
    }

    public void setCpn(CarComponent cpn) {
        this.cpn = cpn;
    }
    
}
