package com.example.huang.cookhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class Main3Activity extends AppCompatActivity {

    //for DB
    private RecipesDataSource dataSource;

    private Random rand=new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        //for db
        dataSource = new RecipesDataSource(this);
        dataSource.open();


    }
    public void openSearch(View view){
        Intent search=new Intent(this,MainActivity.class);
        startActivity(search);

    }
    public void add(View view){
        Intent add=new Intent(this,Main2Activity.class);
        startActivity(add);
    }
    @Override
    protected void onResume() {
        dataSource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        dataSource.close();
        super.onPause();
    }
}
