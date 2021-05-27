/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author pc
 */
public class ReturnedCar implements Serializable{
    private int id;
    private Date checkOutDate;
    private String note;
    private Car car;
    private User user;
    private ArrayList<UsedComponent> luc;
    private ArrayList<UsedService> lus;

    public ReturnedCar() {
        super();
    }

    public ReturnedCar(int id, Date checkOutDate, String note, Car car, User user, ArrayList<UsedComponent> luc, ArrayList<UsedService> lus) {
        this.id = id;
        this.checkOutDate = checkOutDate;
        this.note = note;
        this.car = car;
        this.user = user;
        this.luc = luc;
        this.lus = lus;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public ArrayList<UsedComponent> getLuc() {
        return luc;
    }

    public void setLuc(ArrayList<UsedComponent> luc) {
        this.luc = luc;
    }

    public ArrayList<UsedService> getLus() {
        return lus;
    }

    public void setLus(ArrayList<UsedService> lus) {
        this.lus = lus;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}
