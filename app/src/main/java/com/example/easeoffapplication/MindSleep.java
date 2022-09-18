package com.example.easeoffapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MindSleep extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mind_sleep);
    }

    public void moveToMindSleepFeedback(View view) {
        Intent intent = new Intent(this,MindSleepFeedback.class );
        startActivity(intent);
    }
}