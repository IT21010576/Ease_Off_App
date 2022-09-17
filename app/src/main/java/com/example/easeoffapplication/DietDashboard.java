package com.example.easeoffapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DietDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_dashboard);
    }

    public void MoveToTrackCalorie(View view){
        Intent intent1=new Intent(this,TrackMyCalories.class);
        startActivity(intent1);
    }

    public void MoveToPlanDiet(View view){
        Intent intent2=new Intent(this,PlanMyDiet.class);
        startActivity(intent2);
    }

    public void MoveToHealthyRecipe(View view){
        Intent intent3=new Intent(this,MyHealthyRecipies.class);
        startActivity(intent3);
    }

    public void MoveToNutriFacts(View view){
        Intent intent4=new Intent(this,NutriFacts.class);
        startActivity(intent4);
    }
}