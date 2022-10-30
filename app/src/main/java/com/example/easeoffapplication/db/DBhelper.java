package com.example.easeoffapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DBhelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="EaseOffApp.db";
    private Context context;

    public DBhelper(Context context){
        super(context,DATABASE_NAME,null,3);
        this.context = context;
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

    private static final String SQL_CREATE_ENTRY_TJ = "CREATE TABLE "+ThoughtJournals.thoughtJournals.TABLE_NAME +" ("+
                ThoughtJournals.thoughtJournals.COLUMN_NAME_TJ_ID_ +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                //ThoughtJournals.thoughtJournals.COLUMN_NAME_TJ_USER+" TEXT, "+
                ThoughtJournals.thoughtJournals.COLUMN_NAME_TJ_DATE+" TEXT, "+
                ThoughtJournals.thoughtJournals.COLUMN_NAME_TJ_RATE+" INTEGER, "+
                ThoughtJournals.thoughtJournals.COLUMN_NAME_TJ_NOTE+" TEXT);";

    private static final String SQL_CREATE_ENTRY_MEDITATE = "CREATE TABLE "+Meditation.meditation.TABLE_NAME +" ("+
            Meditation.meditation.COLUMN_NAME_M_ID_ +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            //ThoughtJournals.thoughtJournals.COLUMN_NAME_TJ_USER+" TEXT, "+
            Meditation.meditation.COLUMN_NAME_M_DATE+" TEXT, "+
            //Meditation.meditation.COLUMN_NAME_M_DUR+" INTEGER, "+
            Meditation.meditation.COLUMN_NAME_M_DUR+" TEXT);";


    private static final String SQL_DELETE_ENTRIES_TJ =
            "DROP TABLE IF EXISTS " + ThoughtJournals.thoughtJournals.TABLE_NAME;



    private static final String SQL_DELETE_ENTRIES_MEDITATE =
            "DROP TABLE IF EXISTS " + Meditation.meditation.TABLE_NAME;

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
            MedShedule.medSchedules.COLUMN_NAME_SCHEDULENAME+" TEXT,"+
            MedShedule.medSchedules.COLUMN_NAME_MORNHOUR+" INTEGER,"+
            MedShedule.medSchedules.COLUMN_NAME_MORNMIN+" INTEGER,"+
            MedShedule.medSchedules.COLUMN_NAME_NOONHOUR+" INTEGER,"+
            MedShedule.medSchedules.COLUMN_NAME_NOONMIN+" INTEGER,"+
            MedShedule.medSchedules.COLUMN_NAME_NIGHTHOUR+" INTEGER,"+
            MedShedule.medSchedules.COLUMN_NAME_NIGHTMIN+" INTEGER)";

    private static final String SQL_DELETE_MEDSCHEDULES =
            "DROP TABLE IF EXISTS " + MedShedule.medSchedules.TABLE_NAME;

        private static final String SQL_DELETE_ENTRIES_TC =
                "DROP TABLE IF EXISTS " + TrackCalories.savedCalories.TABLE_NAME;

        private static final String SQL_DELETE_ENTRIES_USER =
                "DROP TABLE IF EXISTS " + User.userDetails.TABLE_NAME;



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_MEDICINES);
        sqLiteDatabase.execSQL(SQL_CREATE_MEDICINESLISTS);
        sqLiteDatabase.execSQL(SQL_CREATE_MEDSCHEDULES);
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRY_MP);
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRY_TC);
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRY_USER);
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRY_TJ);
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRY_MEDITATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_DELETE_ENTRIES_TJ);
        db.execSQL(SQL_DELETE_ENTRIES_MEDITATE);
        db.execSQL(SQL_DELETE_MEDICINES);
        db.execSQL(SQL_DELETE_MEDICINESLISTS);
        db.execSQL(SQL_DELETE_MEDSCHEDULES);
        db.execSQL(SQL_DELETE_ENTRIES_MP);
        db.execSQL(SQL_DELETE_ENTRIES_TC);
        db.execSQL(SQL_DELETE_ENTRIES_USER);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
//for Mind And You 
    public void addThoughtJournal(String date, String rate, String note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        //cv.put(ThoughtJournals.thoughtJournals.COLUMN_NAME_TJ_USER,user);
        cv.put(ThoughtJournals.thoughtJournals.COLUMN_NAME_TJ_DATE,date);
        cv.put(ThoughtJournals.thoughtJournals.COLUMN_NAME_TJ_RATE,rate);
        cv.put(ThoughtJournals.thoughtJournals.COLUMN_NAME_TJ_NOTE,note);

        long result = db.insert(ThoughtJournals.thoughtJournals.TABLE_NAME,null,cv);

    if(result == -1){
        Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
    }else{
        Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
    }
    }

    public Cursor readAllJournals(){
        String query_mj = "SELECT * FROM " + ThoughtJournals.thoughtJournals.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query_mj,null);
        }
        return cursor;
    }

    public void updateThoughtJournal(String row_id, String date, String rate, String note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        ///
        cv.put(ThoughtJournals.thoughtJournals.COLUMN_NAME_TJ_ID_,row_id);
        cv.put(ThoughtJournals.thoughtJournals.COLUMN_NAME_TJ_DATE,date);
        cv.put(ThoughtJournals.thoughtJournals.COLUMN_NAME_TJ_RATE,rate);
        cv.put(ThoughtJournals.thoughtJournals.COLUMN_NAME_TJ_NOTE,note);

    long result = db.update(ThoughtJournals.thoughtJournals.TABLE_NAME, cv, "id=?", new String[]{row_id});

    if(result == -1){
        Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
    }else{
        Toast.makeText(context, "updated", Toast.LENGTH_SHORT).show();

    }

    }

    public void deleteOneThoughtJournal(String row_id){

        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(ThoughtJournals.thoughtJournals.TABLE_NAME,"id=?", new String[]{row_id});

        if(result == -1){
            Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show();

        }
    }


    public void addMeditatedTime(String date, String duration){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Meditation.meditation.COLUMN_NAME_M_DATE, date);
        cv.put(Meditation.meditation.COLUMN_NAME_M_DUR,duration);
        long result = db.insert(Meditation.meditation.TABLE_NAME,null,cv);
        if(result == -1){
            Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "saved", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readAllMeditationTimes(){
        String query_meditate_time = "SELECT * FROM " + Meditation.meditation.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query_meditate_time,null);
        }
        return cursor;
    }
}
