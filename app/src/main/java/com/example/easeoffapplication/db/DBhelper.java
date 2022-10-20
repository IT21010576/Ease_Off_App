package com.example.easeoffapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBhelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="EaseOffApp.db";

    public DBhelper(Context context){
        super(context,DATABASE_NAME,null,2);
    }

        private static final String SQL_CREATE_ENTRY1="CREATE TABLE "+MealPlans.mealPlans.TABLE_NAME +" ("+
                                                        MealPlans.mealPlans._ID +" INTEGER PRIMARY KEY,"+
                                                        MealPlans.mealPlans.COLUMN_NAME_USER+" TEXT,"+
                                                        MealPlans.mealPlans.COLUMN_NAME_DAY+" TEXT UNIQUE,"+
                                                        MealPlans.mealPlans.COLUMN_NAME_BREAKFAST+" TEXT,"+
                                                        MealPlans.mealPlans.COLUMN_NAME_LUNCH+" TEXT,"+
                                                        MealPlans.mealPlans.COLUMN_NAME_DINNER+" Text)";

        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + MealPlans.mealPlans.TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(SQL_CREATE_ENTRY1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
