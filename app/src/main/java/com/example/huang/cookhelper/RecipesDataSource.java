package com.example.huang.cookhelper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class RecipesDataSource {

        // Database fields
        private SQLiteDatabase database;
        private MySQLiteHelper dbHelper;
        private String[] allColumns = { MySQLiteHelper.COLUMN_NAME,
                        MySQLiteHelper.COLUMN_CLASSR, MySQLiteHelper.COLUMN_TYPE,
                        MySQLiteHelper.COLUMN_CATEGORY, MySQLiteHelper.COLUMN_ING,
                        MySQLiteHelper.COLUMN_CAL, MySQLiteHelper.COLUMN_COOK,
                        MySQLiteHelper.COLUMN_PREP, MySQLiteHelper.COLUMN_STEPS};

        public RecipesDataSource(Context context) {
                dbHelper = new MySQLiteHelper(context);
        }

        public void open() throws SQLException {
                database = dbHelper.getWritableDatabase();
        }

        public void close() {
                dbHelper.close();
        }

        public void createRecipe(Recipe recipe) {
                database = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();

                //a bunch of puts for all the columns
                values.put(MySQLiteHelper.COLUMN_NAME, recipe.getName());
                values.put(MySQLiteHelper.COLUMN_CLASSR, recipe.getClassr());
                values.put(MySQLiteHelper.COLUMN_TYPE, recipe.getType());
                values.put(MySQLiteHelper.COLUMN_CATEGORY, recipe.getCategory());
                values.put(MySQLiteHelper.COLUMN_ING, android.text.TextUtils.join(",", recipe.getIng()));
                values.put(MySQLiteHelper.COLUMN_CAL, recipe.getCal());
                values.put(MySQLiteHelper.COLUMN_COOK, recipe.getCook());
                values.put(MySQLiteHelper.COLUMN_PREP, recipe.getPrep());
                values.put(MySQLiteHelper.COLUMN_STEPS, recipe.getSteps());


            try {
                database.insert(MySQLiteHelper.TABLE_RECIPES, null,
                        values);
            } catch(Exception e) {
                System.out.println("needs unique name");
            }
            /*
                Cursor cursor = database.query(MySQLiteHelper.TABLE_RECIPES,
                                allColumns, MySQLiteHelper.COLUMN_NAME + " = " + insertId, null,
                                null, null, null);
                cursor.moveToFirst();
                Recipe newRecipe = cursorToRecipe(cursor);
                cursor.close();
                return newRecipe;
                */
        }

        public void deleteRecipe(Recipe recipe) {
            database = dbHelper.getWritableDatabase();
            // Define 'where' part of query.
            String selection = MySQLiteHelper.COLUMN_NAME + " LIKE ?";
// Specify arguments in placeholder order.
            String[] selectionArgs = { recipe.getName() };
// Issue SQL statement.
            database.delete(MySQLiteHelper.TABLE_RECIPES, selection, selectionArgs);

        }

        public ArrayList<Recipe> getAllRecipes() {
                database = dbHelper.getWritableDatabase();
                ArrayList<Recipe> recipes = new ArrayList<>();

                Cursor cursor = database.query(MySQLiteHelper.TABLE_RECIPES,
                                allColumns, null, null, null, null, null);

                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                        Recipe recipe = cursorToRecipe(cursor);
                        recipes.add(recipe);
                        cursor.moveToNext();
                }
                // make sure to close the cursor
                cursor.close();
                return recipes;
        }

        private Recipe cursorToRecipe(Cursor cursor) {
                Recipe recipe = new Recipe();

                //split into separate ingredients
                String[] ingredients = cursor.getString(cursor.getColumnIndex("ing")).split(",");

                //create ingredients by splitting into parts
                ArrayList<Ingredient> ingForWrite = new ArrayList<>();
                for(int i=0;i<ingredients.length;i++){
                    String[] parts = ingredients[i].split(" ");
                    ingForWrite.add(new Ingredient(parts[0],Float.parseFloat(parts[1]),parts[2]));
                }

                recipe.setAll(cursor.getString(cursor.getColumnIndex("name")),
                        cursor.getString(cursor.getColumnIndex("classr")),
                        cursor.getString(cursor.getColumnIndex("type")),
                        cursor.getString(cursor.getColumnIndex("category")),
                        ingForWrite,
                        cursor.getString(cursor.getColumnIndex("cal")),
                        cursor.getString(cursor.getColumnIndex("cook")),
                        cursor.getString(cursor.getColumnIndex("prep")),
                        cursor.getString(cursor.getColumnIndex("steps")));
                return recipe;
        }

        public Recipe getRecipeAtPosition(int position){
                List<Recipe> Recipes = new ArrayList<Recipe>();

                Cursor cursor = database.query(MySQLiteHelper.TABLE_RECIPES,
                        allColumns, null, null, null, null, null);

                cursor.moveToFirst();

                Recipe recipe = cursorToRecipe(cursor);
                // make sure to close the cursor
                cursor.close();
                return recipe;
        }

        public Recipe getRecipeByName(String name){
            Cursor cursor = database.query(MySQLiteHelper.TABLE_RECIPES,
                    allColumns, MySQLiteHelper.COLUMN_NAME + " = " + name, null,
                    null, null, null);
            if (cursor != null)
                cursor.moveToFirst();
            else
                return null;

            Recipe foundRecipe = cursorToRecipe(cursor);
            cursor.close();
            return foundRecipe;
        }

        public String searchRecipes(String query, String category, String type){
            String retName;
            if(!query.isEmpty() && !category.isEmpty() && !type.isEmpty()){
                Cursor cursor = database.
                        rawQuery("select " + MySQLiteHelper.COLUMN_NAME + " from "
                                        + MySQLiteHelper.TABLE_RECIPES
                                        + " where " + MySQLiteHelper.COLUMN_CATEGORY + " = ? and "
                                        + MySQLiteHelper.COLUMN_TYPE + " = ? and "
                                        + MySQLiteHelper.COLUMN_ING + " like = ?"
                                , new String[]{category, type, query});
                retName = cursor.getString(cursor.getColumnIndex("name"));
                cursor.close();
            }else if(query.isEmpty() && !category.isEmpty() && !type.isEmpty()) {
                Cursor cursor = database.
                        rawQuery("SELECT " + MySQLiteHelper.COLUMN_NAME + " FROM "
                                        + MySQLiteHelper.TABLE_RECIPES
                                        + " where " + MySQLiteHelper.COLUMN_CATEGORY + " = ? AND "
                                        + MySQLiteHelper.COLUMN_TYPE + " = ? "
                                , new String[]{category, type});
                retName = cursor.getString(cursor.getColumnIndex("name"));;
                cursor.close();
            } else if(query.isEmpty() && !category.isEmpty() && type.isEmpty()){
                Cursor cursor = database.
                        rawQuery("SELECT " + MySQLiteHelper.COLUMN_NAME + " FROM "
                                        + MySQLiteHelper.TABLE_RECIPES
                                        + " where " + MySQLiteHelper.COLUMN_CATEGORY + " = ? "
                                , new String[]{category});
                retName = cursor.getString(cursor.getColumnIndex("name"));;
                cursor.close();
            } else {return null;}
            return retName;
        }

    public void editRecipe(Recipe recipe) {
        database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        //a bunch of puts for all the columns
        values.put(MySQLiteHelper.COLUMN_NAME, recipe.getName());
        values.put(MySQLiteHelper.COLUMN_CLASSR, recipe.getClassr());
        values.put(MySQLiteHelper.COLUMN_TYPE, recipe.getType());
        values.put(MySQLiteHelper.COLUMN_CATEGORY, recipe.getCategory());
        values.put(MySQLiteHelper.COLUMN_ING, android.text.TextUtils.join(",", recipe.getIng()));
        values.put(MySQLiteHelper.COLUMN_CAL, recipe.getCal());
        values.put(MySQLiteHelper.COLUMN_COOK, recipe.getCook());
        values.put(MySQLiteHelper.COLUMN_PREP, recipe.getPrep());
        values.put(MySQLiteHelper.COLUMN_STEPS, recipe.getSteps());


        // Which row to update, based on the title
        String selection = MySQLiteHelper.COLUMN_NAME + " LIKE ?";
        String[] selectionArgs = { recipe.getName() };

        try {
            database.update(
                    MySQLiteHelper.TABLE_RECIPES,
                    values,
                    selection,
                    selectionArgs);
        } catch(Exception e) {
            System.out.println("needs unique name");
        }
            /*
                Cursor cursor = database.query(MySQLiteHelper.TABLE_RECIPES,
                                allColumns, MySQLiteHelper.COLUMN_NAME + " = " + insertId, null,
                                null, null, null);
                cursor.moveToFirst();
                Recipe newRecipe = cursorToRecipe(cursor);
                cursor.close();
                return newRecipe;
                */
    }

}