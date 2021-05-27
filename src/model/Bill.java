/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author pc
 */
public class Bill implements Serializable{
    private int id;
    private Date createDate;
    private int statusBill;
    private String note;
    private int amount;
    private User user;
    private Client client;
    private ReturnedCar rc;
    
    public Bill() {
        super();
    }

    public Bill(int id, Date createDate, int statusBill, String note, int amount, User user, Client client, ReturnedCar rc) {
        super();
        this.id = id;
        this.createDate = createDate;
        this.statusBill = statusBill;
        this.note = note;
        this.amount = amount;
        this.user = user;
        this.client = client;
        this.rc = rc;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ReturnedCar getRc() {
        return rc;
    }

    public void setRc(ReturnedCar rc) {
        this.rc = rc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getStatusBill() {
        return statusBill;
    }

    public void setStatusBill(int statusBill) {
        this.statusBill = statusBill;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
