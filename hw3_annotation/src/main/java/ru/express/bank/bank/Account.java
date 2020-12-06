package ru.express.bank.bank;

import java.util.Date;

public class Account {
    private String name;
    private String currency;
    private Double amount;
    private Date dateBegin;

    public Account(String name, String currency, Double amount, Date dateBegin)
    {
        this.name = name;
        this.currency = currency;
        this.amount = amount;
        this.dateBegin = dateBegin;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setDateBegin(Date dateBegin) {
        this.dateBegin = dateBegin;
    }

    public String getName() {
        return name;
    }

    public String getCurrency() {
        return currency;
    }

    public Double getAmount() {
        return amount;
    }

    public Date getDateBegin() {
        return dateBegin;
    }
}
