package com.example.budgetmanagement.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Todo {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nameBudget;

    private String priceBudget;

    private String descriptionBudget;

    public Todo(String nameBudget, String priceBudget, String descriptionBudget) {
        this.nameBudget = nameBudget;
        this.priceBudget = priceBudget;
        this.descriptionBudget = descriptionBudget;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameBudget() {
        return nameBudget;
    }

    public void setNameBudget(String nameBudget) {
        this.nameBudget = nameBudget;
    }

    public String getPriceBudget() {
        return priceBudget;
    }

    public void setPriceBudget(String priceBudget) {
        this.priceBudget = priceBudget;
    }

    public String getDescriptionBudget() {
        return descriptionBudget;
    }

    public void setDescriptionBudget(String descriptionBudget) {
        this.descriptionBudget = descriptionBudget;
    }
}
