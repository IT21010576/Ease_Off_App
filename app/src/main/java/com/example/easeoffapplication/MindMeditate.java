package com.example.easeoffapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MindMeditate extends AppCompatActivity {

    Button timerFragmentBtn, timesFragmentBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mind_meditate);
        timerFragmentBtn = findViewById(R.id.mind_meditate_timer_frag_btn);
        timesFragmentBtn=findViewById(R.id.mind_meditate_times_frag_btn);

        timerFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                replaceFragment(new MindMeditateTimerFr());
            }
        });

        timesFragmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new MindMeditateTimesFr());

            }
        });
    }

    private void replaceFragment (Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mind_meditate_frame_layout,fragment);
        fragmentTransaction.commit();
    }
}