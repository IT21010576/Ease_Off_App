package com.example.easeoffapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBhelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="EaseOffApp.db";

    public DBhelper(Context context){
        super(context,DATABASE_NAME,null,6);
    }

        private static final String SQL_CREATE_ENTRY_MP="CREATE TABLE "+MealPlans.mealPlans.TABLE_NAME +" ("+
                                                        MealPlans.mealPlans._ID +" INTEGER PRIMARY KEY,"+
                                                        MealPlans.mealPlans.COLUMN_NAME_DAY+" TEXT UNIQUE,"+
                                                        MealPlans.mealPlans.COLUMN_NAME_BREAKFAST+" TEXT,"+
                                                        MealPlans.mealPlans.COLUMN_NAME_LUNCH+" TEXT,"+
                                                        MealPlans.mealPlans.COLUMN_NAME_DINNER+" TEXT)";

        private static final String SQL_CREATE_ENTRY_TC="CREATE TABLE "+TrackCalories.savedCalories.TABLE_NAME +" ("+
                                                        TrackCalories.savedCalories._ID +" INTEGER PRIMARY KEY,"+
                                                        TrackCalories.savedCalories.COLUMN_NAME_DATE+" TEXT,"+
                                                        TrackCalories.savedCalories.COLUMN_NAME_TOTALCALORIES+" DOUBLE,"+
                                                        TrackCalories.savedCalories.COLUMN_NAME_COMMENT+" TEXT)";

        private static final String SQL_CREATE_ENTRY_USER="CREATE TABLE "+User.userDetails.TABLE_NAME +" ("+
                                                            User.userDetails.COLUMN_NAME_USERNAME+" TEXT PRIMARY KEY,"+
                                                            User.userDetails.COLUMN_NAME_PASSWORD+" TEXT,"+
                                                            User.userDetails.COLUMN_NAME_BYEAR+" INTEGER,"+
                                                            User.userDetails.COLUMN_NAME_GENDER+" TEXT)";

        private static final String SQL_DELETE_ENTRIES_MP =
                "DROP TABLE IF EXISTS " + MealPlans.mealPlans.TABLE_NAME;

        private static final String SQL_DELETE_ENTRIES_TC =
                "DROP TABLE IF EXISTS " + TrackCalories.savedCalories.TABLE_NAME;

        private static final String SQL_DELETE_ENTRIES_USER =
                "DROP TABLE IF EXISTS " + User.userDetails.TABLE_NAME;



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(SQL_CREATE_ENTRY_MP);
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRY_TC);
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRY_USER);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES_MP);
        db.execSQL(SQL_DELETE_ENTRIES_TC);
        db.execSQL(SQL_DELETE_ENTRIES_USER);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


}
