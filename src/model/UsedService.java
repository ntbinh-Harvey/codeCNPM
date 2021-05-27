/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author pc
 */
public class UsedService {
    private int id;
    private int quantity;
    private int price;
    private Service sv;

    public UsedService() {
        super();
    }

    public UsedService(int id, int quantity, int price, Service sv) {
        super();
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.sv = sv;
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

    public Service getSv() {
        return sv;
    }

    public void setSv(Service sv) {
        this.sv = sv;
    }
    
}
