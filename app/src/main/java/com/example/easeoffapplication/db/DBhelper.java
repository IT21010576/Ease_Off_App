package com.example.easeoffapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBhelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="EaseOffApp.db";

    public DBhelper(Context context){
        super(context,DATABASE_NAME,null,10);
    }

        private static final String SQL_CREATE_ENTRY1="CREATE TABLE "+MealPlans.mealPlans.TABLE_NAME +" ("+
                                                        MealPlans.mealPlans._ID +" INTEGER PRIMARY KEY,"+
                                                        MealPlans.mealPlans.COLUMN_NAME_USER+" TEXT,"+
                                                        MealPlans.mealPlans.COLUMN_NAME_DAY+" TEXT UNIQUE,"+
                                                        MealPlans.mealPlans.COLUMN_NAME_BREAKFAST+" TEXT,"+
                                                        MealPlans.mealPlans.COLUMN_NAME_LUNCH+" TEXT,"+
                                                        MealPlans.mealPlans.COLUMN_NAME_DINNER+" Text)";

        private static final String SQL_CREATE_ENTRY_Work="CREATE TABLE "+Workoutprofile.workoutprofile.TABLE_NAME +" ("+
                                                        Workoutprofile.workoutprofile._ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                                                        Workoutprofile.workoutprofile.COLUMN_NAME_name+" TEXT,"+
                                                        Workoutprofile.workoutprofile.COLUMN_NAME_age+" INTEGER,"+
                                                        Workoutprofile.workoutprofile.COLUMN_NAME_height+" DOUBLE,"+
                                                        Workoutprofile.workoutprofile.COLUMN_NAME_weight+" DOUBLE)";

        private static final String SQL_CREATE_ENTRY_Workplan="CREATE TABLE "+workoutplan.Workoutplan.TABLE_NAME +" ("+
                                                        workoutplan.Workoutplan._ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                                                        workoutplan.Workoutplan.COLUMN_NAME_name+" TEXT,"+
                                                        workoutplan.Workoutplan.COLUMN_NAME_squat+" INTEGER,"+
                                                        workoutplan.Workoutplan.COLUMN_NAME_bench_press+" INTEGER,"+
                                                        workoutplan.Workoutplan.COLUMN_NAME_leg_press+" INTEGER,"+
                                                        workoutplan.Workoutplan.COLUMN_NAME_overhead_press+" INTEGER,"+
                                                        workoutplan.Workoutplan.COLUMN_NAME_calorie+"REAL,"+
                                                        workoutplan.Workoutplan.COLUMN_NAME_workouttime+"REAL)";



    private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + MealPlans.mealPlans.TABLE_NAME;

    private static final String SQL_DELETE_ENTRIES_Work =
               "DROP TABLE IF EXISTS " + Workoutprofile.workoutprofile.TABLE_NAME;

    private static final String SQL_DELETE_ENTRIES_Workplan =
            "DROP TABLE IF EXISTS " + workoutplan.Workoutplan.TABLE_NAME;


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(SQL_CREATE_ENTRY1);
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRY_Work);
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRY_Workplan);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_DELETE_ENTRIES_Work);
        db.execSQL(SQL_DELETE_ENTRIES_Workplan);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


}
