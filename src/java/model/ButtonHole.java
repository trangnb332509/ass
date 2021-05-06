/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Dell Inc
 */
public class ButtonHole {
    private int id;
    private String name;
    private int price;
    private int bhquantity;
    private int sum;
    private int money;

    public int getBhquantity() {
        return bhquantity;
    }

    public void setBhquantity(int bhquantity) {
        this.bhquantity = bhquantity;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney() {
        this.money = this.sum*this.bhquantity*this.price;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
