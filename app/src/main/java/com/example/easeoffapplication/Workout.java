package com.example.easeoffapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Workout extends AppCompatActivity {

    Button fragment2btn,fragment3btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        fragment2btn=findViewById(R.id.fragment2btn);
        fragment3btn=findViewById(R.id.fragment3btn);

        fragment2btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                replaceFragment(new MyProfile());

            }
        });
        fragment3btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                replaceFragment(new MyWorkout());

            }
        });


    }

    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();



    }
}