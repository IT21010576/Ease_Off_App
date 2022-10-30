package com.example.easeoffapplication.mindandyou;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.example.easeoffapplication.R;
import com.example.easeoffapplication.db.DBhelper;

import java.util.ArrayList;

public class MindMeditationTimes extends AppCompatActivity {

    RecyclerView meditationTimeRecView;

    DBhelper myDb;
    ArrayList<String> meditate_id,meditate_date,meditate_duration;
    CustomAdapterMindMeditationTime customAdapterMindMeditationTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mind_meditation_times);

        meditationTimeRecView = findViewById(R.id.mind_meditation_recycler_view);

        myDb = new DBhelper(MindMeditationTimes.this);

        meditate_id = new ArrayList<>();
        meditate_date = new ArrayList<>();
        meditate_duration = new ArrayList<>();

        displayMeditationTimes();

        customAdapterMindMeditationTime = new CustomAdapterMindMeditationTime(MindMeditationTimes.this,this,meditate_id,meditate_date,meditate_duration);
        meditationTimeRecView.setAdapter(customAdapterMindMeditationTime);
        meditationTimeRecView.setLayoutManager(new LinearLayoutManager(MindMeditationTimes.this));

    }


    void displayMeditationTimes(){
        Cursor cursor = myDb.readAllMeditationTimes();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show();
        }
        else
        {while(cursor.moveToNext()) {
            meditate_id.add(cursor.getString(0));
            meditate_date.add(cursor.getString(1));
            meditate_duration.add(cursor.getString(2));
        }
        }
    }
}