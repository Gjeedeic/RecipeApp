package com.example.huang.cookhelper;

import android.app.Application;

import java.util.ArrayList;

public class MyApplication extends Application {
private Recipe rec;
   // private ArrayList<Recipe> list=new ArrayList<Recipe>();

    public Recipe getRecipe(){
        return rec;
    }
    public void setRecipe(Recipe rec){
        this.rec=rec;
    }

   // public ArrayList<Recipe> getList() {
    //    return list;
    //}

   // public void addlist(Recipe rec) {
    //    list.add(rec);
   // }

   // public void removefromList(Recipe rec) {
   //     list.remove(rec);
   // }
}
