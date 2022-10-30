package com.example.easeoffapplication.stayfit;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import com.example.easeoffapplication.R;
import com.example.easeoffapplication.db.DBhelper;
import com.example.easeoffapplication.db.Workoutprofile;

import java.util.ArrayList;
import java.util.List;

public class setprofile_list extends AppCompatActivity {

    ListView setprofile_list1;
    List<Workoutprofile> works;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setprofile_list);
        setprofile_list1=findViewById(R.id.setprofile_list);

        works = new ArrayList<>();
        works = getAllplan();
/*
        MyprofileAdapter myprofileAdapter= new SetGoalAdapter(setprofile_lists.this,R.layout.single_setg, plans);
        setprofile_list1.setAdapter(setgoalAdapter);*/
    }

    public List<Workoutprofile> getAllplan(){

        List<Workoutprofile> works = new ArrayList<>();

        DBhelper dbHelper = new DBhelper(setprofile_list.this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + Workoutprofile.workoutprofile.TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                Workoutprofile work= new Workoutprofile();
                work.setId(cursor.getInt(0));
                work.setUname(cursor.getString(1));
                work.setAge(cursor.getInt(2));
                work.setHeight(cursor.getInt(3));
                work.setWeight(cursor.getInt(4));

                works.add(work);
            }while (cursor.moveToNext());
        }
        return works;
    }


}