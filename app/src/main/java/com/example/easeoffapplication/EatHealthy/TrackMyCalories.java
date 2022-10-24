package com.example.easeoffapplication.EatHealthy;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.easeoffapplication.R;

public class TrackMyCalories extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_my_calories);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //restart activity after updating
        if(requestCode==1){
            recreate();
        }
    }

    public void changeFragment(View view){
        Fragment fragment;
        if (view == findViewById(R.id.TrackCalbtn1)){
            fragment = new TrackMyCalorieMain();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragmentContainer2,fragment);
            ft.commit();
        }
        if (view == findViewById(R.id.TrackCalbtn2)){
            fragment = new PreviousCalorieIntakes();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragmentContainer2,fragment);
            ft.commit();
        }
    }


}