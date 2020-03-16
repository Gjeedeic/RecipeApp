package com.example.huang.cookhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

        public static final String TABLE_RECIPES = "recipes";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_CLASSR = "classr";
        public static final String COLUMN_TYPE = "type";
        public static final String COLUMN_CATEGORY = "category";
        public static final String COLUMN_ING = "ing";
        public static final String COLUMN_CAL = "cal";
        public static final String COLUMN_COOK = "cook";
        public static final String COLUMN_PREP = "prep";
        public static final String COLUMN_STEPS = "steps";

        private static final String DATABASE_NAME = "recipes.db";
        private static final int DATABASE_VERSION = 1;

        // Database creation sql statement
        private static final String DATABASE_CREATE = "create table "
                        + TABLE_RECIPES + "( " + COLUMN_NAME
                        + " text primary key, " + COLUMN_CLASSR
                        + " text, " + COLUMN_TYPE + " text, "
                        + COLUMN_CATEGORY + " text, " + COLUMN_ING
                        + " text, " + COLUMN_CAL + " text, "
                        + COLUMN_COOK + " text, "+ COLUMN_PREP
                        + " text, " + COLUMN_STEPS + " text" + " )";

        public MySQLiteHelper(Context context) {
                super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase database) {
                database.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                Log.w(MySQLiteHelper.class.getName(),
                                "Upgrading database from version " + oldVersion + " to "
                                                + newVersion + ", which will destroy all old data");
                db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECIPES);
                onCreate(db);
        }

}
