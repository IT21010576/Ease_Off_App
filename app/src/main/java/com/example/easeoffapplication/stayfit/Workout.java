package com.example.easeoffapplication.stayfit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.easeoffapplication.R;

public class Workout extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

    }
    public void moveToMyprofile(View view){
        Intent myprofile=new Intent(this, Myprofile1.class);
        startActivity(myprofile);

    }
    public void moveToMyworkouts(View view){
        Intent myworkout=new Intent(this, Myworkout1.class);
        startActivity(myworkout);

    }
    public void moveToToday(View view){
        Intent mytoday=new Intent(this, SetGoal.class);
        startActivity(mytoday);

    }

}