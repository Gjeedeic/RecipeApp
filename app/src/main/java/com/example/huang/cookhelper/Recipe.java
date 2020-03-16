package com.example.huang.cookhelper;

import java.io.Serializable;
import java.util.ArrayList;

public class Recipe implements Serializable {
    private String name;
    private String classr;
    private String type;
    private String category;
    private ArrayList<Ingredient> ing;
    private String cal;
    private String cook;
    private String prep;
    private String steps;

    //init blanks
    Recipe(){
    }

    Recipe(String name, String classr, String type, String category,ArrayList<Ingredient> ing, String cook,String prep, String cal, String steps){
        this.name=name;
        this.classr=classr;
        this.type=type;
        this.category=category;
        this.ing=ing;
        this.steps=steps;
        this.cal=cal;
        this.cook=cook;
        this.prep=prep;

    }

    public String getName(){
        return name;
    }
    public String getClassr(){
        return classr;
    }
    public String getType(){
        return type;
    }
    public String getCategory(){
        return category;
    }
    public ArrayList<Ingredient>getIng(){
        return ing;
    }
    public String getSteps(){
        return steps;
    }
    public String getCal(){return cal;}
    public String getCook(){return cook;}
    public String getPrep(){return prep;}


    public void setAll(String name, String classr, String type, String category,ArrayList<Ingredient> ing, String cook,String prep, String cal, String steps){
        this.name=name;
        this.classr=classr;
        this.type=type;
        this.category=category;
        this.ing=ing;
        this.steps=steps;
        this.cal=cal;
        this.cook=cook;
        this.prep=prep;

    }

}
