package com.example.easeoffapplication.stayfit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.example.easeoffapplication.R;

public class Myworkout1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myworkout1);
    }
    public void changeFragment1(View view){
        Fragment fragment;
        if (view == findViewById(R.id.button13)){
            fragment = new Fullbody();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragmentcontainer,fragment);
            ft.commit();
        }
        if (view == findViewById(R.id.button15)){
            fragment = new Workoutwithequp();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragmentcontainer,fragment);
            ft.commit();
        }
        if (view == findViewById(R.id.button14)){
            fragment = new absWorkout();
            FragmentManager fm = getSupportFragmentManager(); FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragmentcontainer,fragment);
            ft.commit();
        }
    }
}