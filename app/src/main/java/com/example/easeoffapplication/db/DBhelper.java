package com.example.easeoffapplication.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBhelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="EaseOffApp.db";

    public DBhelper(Context context){
        super(context,DATABASE_NAME,null,3);
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

    private static final String SQL_CREATE_MEDICINES="CREATE TABLE "+Medicines.medicines.TABLE_NAME +" ("+
                                                    Medicines.medicines._ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                                                    Medicines.medicines.COLUMN_NAME_MEDNAME+" TEXT,"+
                                                    Medicines.medicines.COLUMN_NAME_PHARMACY+" TEXT,"+
                                                    Medicines.medicines.COLUMN_NAME_DESCRIPTION+" TEXT,"+
                                                    Medicines.medicines.COLUMN_NAME_PRICE+" REAL)";

    private static final String SQL_DELETE_MEDICINES =
            "DROP TABLE IF EXISTS " + Medicines.medicines.TABLE_NAME;

    private static final String SQL_CREATE_MEDICINESLISTS="CREATE TABLE "+MedicineLists.medicineLists.TABLE_NAME +" ("+
            MedicineLists.medicineLists._ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            MedicineLists.medicineLists.COLUMN_NAME_LISTNAME+" TEXT,"+
            MedicineLists.medicineLists.COLUMN_NAME_MEDNAME1+" TEXT,"+
            MedicineLists.medicineLists.COLUMN_NAME_MEDNAME2+" TEXT,"+
            MedicineLists.medicineLists.COLUMN_NAME_MEDNAME3+" TEXT,"+
            MedicineLists.medicineLists.COLUMN_NAME_MEDNAME4+" TEXT,"+
            MedicineLists.medicineLists.COLUMN_NAME_MEDNAME5+" TEXT,"+
            MedicineLists.medicineLists.COLUMN_NAME_TOTAL+" REAL)";

    private static final String SQL_DELETE_MEDICINESLISTS =
            "DROP TABLE IF EXISTS " + MedicineLists.medicineLists.TABLE_NAME;

    private static final String SQL_CREATE_MEDSCHEDULES="CREATE TABLE "+MedShedule.medSchedules.TABLE_NAME +" ("+
            MedShedule.medSchedules._ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            MedShedule.medSchedules.COLUMN_NAME_DATE+" TEXT,"+
            MedShedule.medSchedules.COLUMN_NAME_MORNHOUR+" INTEGER,"+
            MedShedule.medSchedules.COLUMN_NAME_MORNMIN+" INTEGER,"+
            MedShedule.medSchedules.COLUMN_NAME_NOONHOUR+" INTEGER,"+
            MedShedule.medSchedules.COLUMN_NAME_NOONMIN+" INTEGER,"+
            MedShedule.medSchedules.COLUMN_NAME_NIGHTHOUR+" INTEGER,"+
            MedShedule.medSchedules.COLUMN_NAME_NIGHTMIN+" INTEGER)";

    private static final String SQL_DELETE_MEDSCHEDULES =
            "DROP TABLE IF EXISTS " + MedShedule.medSchedules.TABLE_NAME;


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(SQL_CREATE_ENTRY1);
        sqLiteDatabase.execSQL(SQL_CREATE_MEDICINES);
        sqLiteDatabase.execSQL(SQL_CREATE_MEDICINESLISTS);
        sqLiteDatabase.execSQL(SQL_CREATE_MEDSCHEDULES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_DELETE_MEDICINES);
        db.execSQL(SQL_DELETE_MEDICINESLISTS);
        db.execSQL(SQL_DELETE_MEDSCHEDULES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
