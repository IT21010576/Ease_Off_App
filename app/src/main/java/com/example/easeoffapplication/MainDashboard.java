package com.example.easeoffapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.easeoffapplication.Healthcare.healthcare;

public class MainDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard);
    }


    public void moveToMindAndYou(View view) {
        Intent mind = new Intent(this, MindAndYouDashboard.class);
        startActivity(mind);
    }

    public void MoveToDietDash(View view) {
        Intent intent1 = new Intent(this, DietDashboard.class);
        startActivity(intent1);
    }

    public void moveToHealthcare(View view){
        Intent intent1=new Intent(this, healthcare.class);
        startActivity(intent1);

    }
}