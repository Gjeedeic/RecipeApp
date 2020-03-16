package com.example.huang.cookhelper;

import java.io.Serializable;


public class Ingredient implements Serializable {
    private String name;
    private float quantity;
    private String mestype;

    public Ingredient(String name, float quantity, String mestype){
        this.mestype=mestype;
        this.name=name;
        this.quantity=quantity;

    }
    public String getname(){
        return name;
    }
    public double getQuantity(){
        return quantity;
    }
    public String getMestype(){
        return mestype;
    }
    public void setAll(String name, float quantity, String mestype){
        this.mestype=mestype;
        this.name=name;
        this.quantity=quantity;
    }

    public String toString(){
        return name + " " + quantity + " " + mestype;
    }


}

