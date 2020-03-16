//this is the edit page

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

import java.util.ArrayList;

public class Main6Activity extends AppCompatActivity {
    private String selected;
    private LinearLayout mLayout;
    private EditText mEditText;
    private Button mButton;
    private String errormssg="The following fields are empty:\n";
    private boolean preverror=false;
    private TextView textView;
    private TextView eTextname;
    private EditText eTextprep ;
    private EditText eTextcook;
    private EditText eTextcal ;
    private EditText eTextstep ;
    private Spinner category ;
    private Spinner type;
    private Spinner classr;
    private Spinner ingname1;
    private Spinner ingenum1;
    private EditText ingquan1;
    private Spinner ingname2;
    private Spinner ingenum2;
    private EditText ingquan2;
    private Spinner ingname3;
    private Spinner ingenum3;
    private EditText ingquan3;
    private Spinner ingname4;
    private Spinner ingenum4;
    private EditText ingquan4;


    //private Intent intent;


    //for DB
    private RecipesDataSource dataSource;


    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        String selected = parent.getItemAtPosition(pos).toString();
    }

    public void onNothingSelected(AdapterView parent) {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        mLayout = (LinearLayout) findViewById(R.id.linearLayout);
        TextView textView = new TextView(this);
        textView.setText("New text");


        // for DB
        dataSource = new RecipesDataSource(this);
        dataSource.open();

        Recipe incomingrec=((MyApplication)this.getApplication()).getRecipe();
        //intent= getIntent();
       // Recipe incomingrec= (Recipe) intent.getExtras().getSerializable("KEY");
//        Ingredient ing= new Ingredient("Tomatos",15,"gm");
//        ArrayList<Ingredient> inglist=new ArrayList<Ingredient>();
//        inglist.add(ing);
//        Recipe incomingrec  = new Recipe("pasta","Main dish","Main dish","Greek",inglist,"11","11","11","11");

        eTextname = (TextView) findViewById(R.id.textView);
        eTextprep = (EditText) findViewById(R.id.editText6);
        eTextcook = (EditText) findViewById(R.id.cooktime);
        eTextcal = (EditText) findViewById(R.id.editText8);
        eTextstep = (EditText) findViewById(R.id.editText2);
        category = (Spinner) findViewById(R.id.category);
        type = (Spinner) findViewById(R.id.type);
        classr = (Spinner) findViewById(R.id.classr);
        ingname1 = (Spinner) findViewById(R.id.spinner11);
        ingenum1 = (Spinner) findViewById(R.id.spinner12);
        ingquan1 = (EditText) findViewById(R.id.editText11);
        ingname2 = (Spinner) findViewById(R.id.spinner21);
        ingenum2 = (Spinner) findViewById(R.id.spinner22);
        ingquan2 = (EditText) findViewById(R.id.editText21);
        ingname3 = (Spinner) findViewById(R.id.spinner31);
        ingenum3 = (Spinner) findViewById(R.id.spinner32);
        ingquan3 = (EditText) findViewById(R.id.editText31);
        ingname4 = (Spinner) findViewById(R.id.spinner41);
        ingenum4 = (Spinner) findViewById(R.id.spinner42);
        ingquan4 = (EditText) findViewById(R.id.editText41);

        eTextname.setText(incomingrec.getName());
        eTextcal.setText(incomingrec.getCal());
        eTextprep.setText(incomingrec.getPrep());
        eTextstep.setText(incomingrec.getSteps());
        eTextcook.setText(incomingrec.getCook());
        classr.setSelection(indexofvalue(incomingrec.getClassr(),classr));
        type.setSelection(indexofvalue(incomingrec.getType(),type));
        category.setSelection(indexofvalue(incomingrec.getCategory(),category));
        ArrayList<Ingredient> temping=incomingrec.getIng();
        if(incomingrec.getIng().size()>=1){
            ingquan1.setText(Double.toString(temping.get(0).getQuantity()));
            ingname1.setSelection(indexofvalue(temping.get(0).getname(),ingname1));
            //System.out.println(temping.get(0).getname());
            ingenum1.setSelection(indexofvalue(temping.get(0).getMestype(),ingenum1));
        }
        if(incomingrec.getIng().size()>=2){
            ingquan2.setText(Double.toString(temping.get(1).getQuantity()));
            ingname2.setSelection(indexofvalue(temping.get(1).getname(),ingname2));
            ingenum2.setSelection(indexofvalue(temping.get(1).getMestype(),ingenum2));
        }
        if(incomingrec.getIng().size()>=3){
            ingquan3.setText(Double.toString(temping.get(2).getQuantity()));
            ingname3.setSelection(indexofvalue(temping.get(2).getname(),ingname3));
            ingenum3.setSelection(indexofvalue(temping.get(2).getMestype(),ingenum3));
        }
        if(incomingrec.getIng().size()>=4){
            ingquan4.setText(Double.toString(temping.get(3).getQuantity()));
            ingname4.setSelection(indexofvalue(temping.get(3).getname(),ingname4));
            ingenum4.setSelection(indexofvalue(temping.get(3).getMestype(),ingenum4));
        }




    }

    public void editClick(View view) {
        boolean end = false;




        ArrayList<Ingredient> inglist = new ArrayList<Ingredient>();
        // Ingredient 1


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
                //System.out.println(Float.class.isInstance(Float.parseFloat(ingquan1.getText().toString())));
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
        Recipe addrecipe = new Recipe(((MyApplication)this.getApplication()).getRecipe().getName(), classr.getSelectedItem().toString(), type.getSelectedItem().toString(), category.getSelectedItem().toString(), inglist, (eTextcook.getText().toString()), (eTextprep.getText().toString()), (eTextcal.getText().toString()), (eTextstep.getText().toString()));



        ((MyApplication)this.getApplication()).setRecipe(addrecipe);


        dataSource.editRecipe(addrecipe);
        dataSource.close();

        Intent search=new Intent(this,Main4Activity.class);
        startActivity(search);




    }

    //create warning sign
    private TextView createNewTextView(String text) {
        final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        textView = new TextView(this);
        textView.setLayoutParams(lparams);
        textView.setText(errormssg);
        textView.setTextColor(getResources().getColor(R.color.errorColor));
        return textView;

    }
    //check if the input is a number
    private boolean isParsable(EditText editText){
        try{
            Float.parseFloat(editText.getText().toString());


        }
        catch (NumberFormatException nfe){
            return false;
        }

        return true;

    }
    //choose the right spinner value
    private int indexofvalue(String value, Spinner spin){
        int index=0;
        for (int i=0; i<spin.getCount();i++){
            if (value.equals(spin.getItemAtPosition(i))){
                index=i;
            }
        }
        return index;
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

