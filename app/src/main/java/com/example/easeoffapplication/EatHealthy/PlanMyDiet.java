package com.example.easeoffapplication.EatHealthy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.easeoffapplication.R;

public class PlanMyDiet extends AppCompatActivity {

    Button createPlanBtn;
    Button viewPlansBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_my_diet);

    }

    public void changeFragment(View view){
        Fragment fragment;
        if (view == findViewById(R.id.createPlanBtn)){
            fragment = new CreateMealPlan();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragmentContainer,fragment);
            ft.commit();
        }
        if (view == findViewById(R.id.viewPlanBtn)){
            fragment = new ViewMealPlans();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragmentContainer,fragment);
            ft.commit();
        }
        if (view == findViewById(R.id.setRminderBtn)){
            fragment = new setReminderForMeal();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragmentContainer,fragment);
            ft.commit();
        }
    }
}