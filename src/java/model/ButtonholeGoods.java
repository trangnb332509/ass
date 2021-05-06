/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author Dell Inc
 */
public class ButtonholeGoods {
    private int id;
    private Date date;
    private int quantity;
    private int bhquantity;
    private ButtonHole buttonhole;
    private Employee emp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getBhquantity() {
        return bhquantity;
    }

    public void setBhquantity(int bhquantity) {
        this.bhquantity = bhquantity;
    }

    public ButtonHole getButtonhole() {
        return buttonhole;
    }

    public void setButtonhole(ButtonHole buttonhole) {
        this.buttonhole = buttonhole;
    }

    public Employee getEmp() {
        return emp;
    }

    public void setEmp(Employee emp) {
        this.emp = emp;
    }
}
