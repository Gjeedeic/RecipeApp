//this is the delete recipe activity

package com.example.huang.cookhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class Main5Activity extends AppCompatActivity {
    private Recipe incomingrec;
    private TextView eTextname;

    //for DB
    private RecipesDataSource dataSource;
    private Recipe recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        eTextname = (TextView) findViewById(R.id.name);
        eTextname.setText(((MyApplication)this.getApplication()).getRecipe().getName());

        //for db
        dataSource = new RecipesDataSource(this);
        dataSource.open();
        recipe = ((MyApplication)this.getApplication()).getRecipe();
    }

    public void deleteClick(View view){


        dataSource.deleteRecipe(recipe);
        dataSource.close();


        Intent search=new Intent(this,MainActivity.class);
        startActivity(search);

    }
    public void cancelClick(View view){
        Intent search=new Intent(this,Main4Activity.class);
        startActivity(search);
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
