//this is the view recipe activity

package com.example.huang.cookhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Main4Activity extends AppCompatActivity {
    private String selectedItem;
    private Recipe recipe;
    private ArrayList<Ingredient> ingredients;
    private ListView ingre;
    private ArrayAdapter<String> ingreAdapter ;
    private TextView name,prepareTime,cookTime,calories,steps,classr,category,type;

    //for DB
    private RecipesDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Intent intent=getIntent();
        selectedItem=intent.getStringExtra("selected");

        // for DB
        dataSource = new RecipesDataSource(this);
        dataSource.open();

        recipe=((MyApplication)this.getApplication()).getRecipe();;
        name=(TextView) findViewById(R.id.name);
        name.setText(recipe.getName());


        ingre=(ListView) findViewById(R.id.ingredient);

        prepareTime=(TextView)findViewById(R.id.prepareTime);
        cookTime=(TextView)findViewById(R.id.cookTime);
        calories=(TextView)findViewById(R.id.calories);
        steps=(TextView)findViewById(R.id.steps);
        classr=(TextView)findViewById(R.id.classr);
        category=(TextView)findViewById(R.id.category);
        type=(TextView)findViewById(R.id.type);

        //Test

        //should be as following
        prepareTime.setText(recipe.getPrep());
        cookTime.setText(recipe.getCook());
        calories.setText(recipe.getCal());
        steps.setText(recipe.getSteps());
        classr.setText(recipe.getClassr());
        category.setText(recipe.getCategory());
        type.setText(recipe.getType());

        final String[] ingrelist = new String[recipe.getIng().size()];
        for(int i=0;i<recipe.getIng().size();i++){
            ingrelist[i]=recipe.getIng().get(i).getname()+" "+recipe.getIng().get(i).getQuantity()+" "+recipe.getIng().get(i).getMestype();
        }
        ingreAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, ingrelist);
        ingre.setAdapter(ingreAdapter);





    }
    public void backToList(View view){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
    public void editPage(View view){
        Intent intent=new Intent(this,Main6Activity.class);
        startActivity(intent);
    }
    public void deletePage(View view){
        Intent intent=new Intent(this,Main5Activity.class);
        startActivity(intent);
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