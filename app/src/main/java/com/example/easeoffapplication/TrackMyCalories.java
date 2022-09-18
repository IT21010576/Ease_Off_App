package com.example.easeoffapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TrackMyCalories extends AppCompatActivity {

    Button recordFragBtn;
    Button viewFragBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_my_calories);
        recordFragBtn=findViewById(R.id.TrackCalbtn1);
        viewFragBtn=findViewById(R.id.TrackCalbtn2);


        recordFragBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                replaceFragment(new TrackMyCalorieMain());
            }
        });

        viewFragBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new PreviousCalorieIntakes());
            }


        });


    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FrameLayout1,fragment);
        fragmentTransaction.commit();
    }



}