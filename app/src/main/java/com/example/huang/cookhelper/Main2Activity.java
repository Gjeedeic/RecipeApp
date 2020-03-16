//this is the "add recipe" activity
package com.example.huang.cookhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String selected;
    private LinearLayout mLayout;
    private EditText mEditText;
    private Button mButton;
    private String errormssg="The following fields are empty:\n";
    private boolean preverror=false;
    private TextView textView;

    //for DB
    private RecipesDataSource dataSource;


    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            String selected = parent.getItemAtPosition(pos).toString();
        }

    public void onNothingSelected(AdapterView parent) {
        // Do nothing.
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mLayout = (LinearLayout) findViewById(R.id.linearLayout);
        TextView textView = new TextView(this);
        textView.setText("New text");
        dataSource = new RecipesDataSource(this);
        dataSource.open();

    }

            public void addClick(View view) {
                boolean end = false;

                EditText eTextname = (EditText) findViewById(R.id.editText5);
                EditText eTextprep = (EditText) findViewById(R.id.editText6);
                EditText eTextcook = (EditText) findViewById(R.id.cooktime);
                EditText eTextcal = (EditText) findViewById(R.id.editText8);
                EditText eTextstep = (EditText) findViewById(R.id.editText2);
                Spinner category = (Spinner) findViewById(R.id.category);
                Spinner type = (Spinner) findViewById(R.id.type);
                Spinner classr=(Spinner) findViewById(R.id.classr);


                ArrayList<Ingredient> inglist = new ArrayList<Ingredient>();
                // Ingredient 1

                Spinner ingname1 = (Spinner) findViewById(R.id.spinner11);
                Spinner ingenum1 = (Spinner) findViewById(R.id.spinner12);
                EditText ingquan1 = (EditText) findViewById(R.id.editText11);
                if (!ingname1.getSelectedItem().toString().equals("Choose ingredient")) {
                    if (ingquan1.getText().toString().isEmpty()) {
                        errormssg=errormssg+"Please type quantity for ingredient 1 \n";
                        end = true;
                    } else if (ingenum1.getSelectedItem().toString().equals("Choose enumeration")) {
                        errormssg=errormssg+"Please choose enumerator type for ingredient 1 \n";
                        end = true;
                    }
                    else if(!isParsable(ingquan1)) {
                        errormssg=errormssg+"Quantity has to be a number for Ingredient 1 \n";

                        end = true;
                    }
                    else {
                        inglist.add(new Ingredient(ingname1.getSelectedItem().toString(), Float.parseFloat(ingquan1.getText().toString()), ingenum1.getSelectedItem().toString()));
                    }
                } else {
                    errormssg=errormssg+"Recipe must contain at least one ingredient \n";
                    end = true;
                }


                // Ingredient 2
                Spinner ingname2 = (Spinner) findViewById(R.id.spinner21);
                Spinner ingenum2 = (Spinner) findViewById(R.id.spinner22);
                EditText ingquan2 = (EditText) findViewById(R.id.editText21);
                if (!ingname2.getSelectedItem().toString().equals("Choose ingredient")) {
                    if (ingquan2.getText().toString().isEmpty()) {
                        errormssg=errormssg+"Please type quantity for ingredient 2 \n";
                        end = true;
                    } else if (ingenum2.getSelectedItem().toString().equals("Choose enumeration")) {
                        errormssg=errormssg+"Please choose enumerator type for ingredient 2 \n";
                        end = true;
                    }
                    else if(!isParsable(ingquan2) ){
                        errormssg=errormssg+"Quantity has to be a number for Ingredient 2 \n";
                        end = true;
                    }
                    else {
                        inglist.add(new Ingredient(ingname2.getSelectedItem().toString(), Float.parseFloat(ingquan2.getText().toString()), ingenum2.getSelectedItem().toString()));
                    }
                }
                //Ingredient 3
                Spinner ingname3 = (Spinner) findViewById(R.id.spinner31);
                Spinner ingenum3 = (Spinner) findViewById(R.id.spinner32);
                EditText ingquan3 = (EditText) findViewById(R.id.editText31);
                if (!ingname3.getSelectedItem().toString().equals("Choose ingredient")) {
                    if (ingquan3.getText().toString().isEmpty()) {
                        errormssg=errormssg+"Please type quantity for ingredient 3 \n";
                        end = true;
                    } else if (ingenum3.getSelectedItem().toString().equals("Choose enumeration")) {
                        errormssg=errormssg+"Please choose enumerator type for ingredient 3 \n";
                        end = true;
                    }
                    else if(!isParsable(ingquan3)) {
                        end = true;
                        errormssg=errormssg+"Quantity has to be a number for Ingredient 3 \n";
                    }
                    else {
                        inglist.add(new Ingredient(ingname3.getSelectedItem().toString(), Float.parseFloat(ingquan3.getText().toString()), ingenum3.getSelectedItem().toString()));
                    }
                }
                // Ingredient 4
                Spinner ingname4 = (Spinner) findViewById(R.id.spinner41);
                Spinner ingenum4 = (Spinner) findViewById(R.id.spinner42);
                EditText ingquan4 = (EditText) findViewById(R.id.editText41);
                if (!ingname4.getSelectedItem().toString().equals("Choose ingredient")) {
                    if (ingquan4.getText().toString().isEmpty()) {
                        errormssg=errormssg+"Please type quantity for ingredient 4 \n";
                        end = true;
                    } else if (ingenum4.getSelectedItem().toString().equals("Choose enumeration")) {
                        errormssg=errormssg+"Please choose enumerator type for ingredient 4 \n";
                        end = true;
                    }
                    else if(!isParsable(ingquan4)) {
                        errormssg=errormssg+"Quantity has to be a number for Ingredient 4 \n";
                        end = true;

                    }
                    else {
                        inglist.add(new Ingredient(ingname4.getSelectedItem().toString(), Float.parseFloat(ingquan4.getText().toString()), ingenum4.getSelectedItem().toString()));
                    }
                }

                //checks the empty fields


                if (eTextname.getText().toString().isEmpty()) {
                    errormssg=errormssg+"Name field\n";
                    end = true;
                }
                if (eTextcal.getText().toString().isEmpty()) {
                    errormssg=errormssg+"Calories field\n";
                    end = true;

                }
                if (eTextcook.getText().toString().isEmpty()) {
                    errormssg=errormssg+"Cook Time field\n";
                    end = true;

                }
                if (eTextprep.getText().toString().isEmpty()) {
                    errormssg=errormssg+"Preparation Time field\n";
                    end = true;
                }
                if (eTextstep.getText().toString().isEmpty()) {
                    errormssg=errormssg+"Steps field\n";
                    end = true;
                }
                if (type.getSelectedItem().toString().equals("Choose type")){
                    errormssg=errormssg+"Type Dropdown\n";
                    end = true;}
                if (category.getSelectedItem().toString().equals("Choose category")){
                    errormssg=errormssg+"Category Dropdown\n";
                    end = true;}
                if (classr.getSelectedItem().toString().equals("Choose Class")){
                    errormssg=errormssg+"Class Dropdown\n";
                    end = true;}
                if (end){
                    if (preverror){
                        textView.setText(errormssg);
                    }
                    else {
                        mLayout.addView(createNewTextView(eTextcal.getText().toString()));
                        preverror=true;
                    }

                    errormssg="";
                    return;
                }

                //creates the objects
                Recipe addrecipe = new Recipe((eTextname.getText().toString()), classr.getSelectedItem().toString(), type.getSelectedItem().toString(), category.getSelectedItem().toString(), inglist, (eTextcook.getText().toString()), (eTextprep.getText().toString()), (eTextcal.getText().toString()), (eTextstep.getText().toString()));

                //DB write call
                dataSource.createRecipe(addrecipe);
                dataSource.close();

                ((MyApplication)this.getApplication()).setRecipe(addrecipe);



                Intent move=new Intent(this,Main4Activity.class);
                startActivity(move);


            }


    private TextView createNewTextView(String text) {
        final LayoutParams lparams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        textView = new TextView(this);
        textView.setLayoutParams(lparams);
        textView.setText(errormssg);
        textView.setTextColor(getResources().getColor(R.color.errorColor));
        return textView;

    }
    private boolean isParsable(EditText editText){
        try{
            Float.parseFloat(editText.getText().toString());


        }
        catch (NumberFormatException nfe){
            return false;
        }

            return true;

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
