package com.example.huang.cookhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

//for DB
import java.util.List;

public class list extends AppCompatActivity {


    private ListView recipeList ;
    private ArrayAdapter<String> listAdapter ;
    private ArrayList<Recipe> recilist;
    private String category,require,foodtype,selectedItem;
    private int selectednumber;
    private LinearLayout mLayout;
    //for DB
    private RecipesDataSource datasource=new RecipesDataSource(this);
    private ArrayList<Recipe> searchresult=new ArrayList<Recipe>();
    //for DB
    private RecipesDataSource dataSource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mLayout = (LinearLayout) findViewById(R.id.linearLayout);

        Intent intent=getIntent();
        category = intent.getStringExtra("category");
        foodtype= intent.getStringExtra("foodtype");
        require= intent.getStringExtra("require");

        recipeList=(ListView) findViewById(R.id.listRecipe);

        //for db
        dataSource = new RecipesDataSource(this);
        dataSource.open();

        ArrayList<Recipe> values = dataSource.getAllRecipes();

        String[] searching;
        if (require.contains(" ")){
        searching=require.split(" ");
        }
        else{
            searching =new String[1];
            searching[0]=require;
        }


        for (int i=0; i<values.size();i++){
            int found=0;
            for (int x=0; x<searching.length;x++){
                for (int j=0; j<values.get(i).getIng().size();j++){


                    if (values.get(i).getIng().get(j).getname().equals(searching[x])) {
                        found++;


                    }
                }

                if((searching.length<=found)&&(values.get(i).getCategory().equals(category))&&(values.get(i).getType().equals(foodtype))){
                 searchresult.add(values.get(i));
                        }
                    }

            }


        final String[] list = new String[searchresult.size()];
        for(int i=0;i<searchresult.size();i++){
            list[i]=searchresult.get(i).getName();
        }
        listAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, list);
        recipeList.setAdapter(listAdapter);
        recipeList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                Toast.makeText(list.this, list[position], Toast.LENGTH_SHORT).show();
                selectedItem=(String) recipeList.getItemAtPosition(position);
                selectednumber=position;

            }
        });
        if(searchresult.isEmpty()){
           mLayout.addView(createNewTextView());
        }


    }
    private TextView createNewTextView() {
        final LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        TextView textView = new TextView(this);
        textView.setLayoutParams(lparams);
        textView.setText("There is no such recipe based on your requirement, please go back and search again");
        textView.setTextColor(getResources().getColor(R.color.errorColor));
        return textView;

    }
    public void goViewPage(View view){
        if(!searchresult.isEmpty()) {
            Intent viewPage = new Intent(this, Main4Activity.class);
            ((MyApplication)this.getApplication()).setRecipe(searchresult.get(selectednumber));
            viewPage.putExtra("selected", selectedItem);
            startActivity(viewPage);
        }
    }
    public void back(View view){
        Intent viewPage=new Intent(this,MainActivity.class);
        startActivity(viewPage);
    }

    @Override
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }

}