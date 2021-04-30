package com.app.rewardscenterbackend.entity;

import com.google.cloud.firestore.annotation.DocumentId;

import java.util.Date;
import java.util.Objects;

public class Transactions {
    @DocumentId
    private String id;
    private Date date;
    private String category;
    private Double amount;

    public Transactions(String id, Date date, String category, Double amount) {
        this.id = id;
        this.date = date;
        this.category = category;
        this.amount = amount;
    }

    public Transactions() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transactions that = (Transactions) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(date, that.date) &&
                Objects.equals(category, that.category) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, category, amount);
    }

}
