package com.example.easeoffapplication.mindandyou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.easeoffapplication.R;

public class MindAndYouDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mind_and_you_dashboard);
    }
    public void moveToMindThoughtJournal(View view) {
        Intent mind=new Intent(this, MindThoughtJournal.class);
        startActivity(mind);
    }
    public void moveToMindSleep(View view) {
        Intent mind=new Intent(this, MindSleep.class);
        startActivity(mind);
    }
    public void moveToMindMeditate(View view) {
        Intent mind=new Intent(this, MindMeditate.class);
        startActivity(mind);
    }
    public void moveToMindTips(View view) {
        Intent mind=new Intent(this, MindTips.class);
        startActivity(mind);
    }
}