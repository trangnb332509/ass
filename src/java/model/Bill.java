/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Dell Inc
 */
public class Bill {
    private int bid;
    private Date date;
    private int total;
    private int paid;
    private Customer customer;
    private ArrayList<BillDetail> billDetails = new ArrayList<>();

    public ArrayList<BillDetail> getBillDetails() {
        return billDetails;
    }

    public void setBillDetails(ArrayList<BillDetail> billDetails) {
        this.billDetails = billDetails;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
}
