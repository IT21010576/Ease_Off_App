package com.example.easeoffapplication.mindandyou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.easeoffapplication.R;
import com.example.easeoffapplication.db.DBhelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MindMeditate extends AppCompatActivity {
//
//    Button timerFragmentBtn, timesFragmentBtn;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_mind_meditate);
//        timerFragmentBtn = findViewById(R.id.mind_meditate_timer_frag_btn);
//        timesFragmentBtn=findViewById(R.id.mind_meditate_times_frag_btn);
//
//        timerFragmentBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                replaceFragment(new MindMeditateTimerFr());
//            }
//        });
//
//        timesFragmentBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                replaceFragment(new MindMeditateTimesFr());
//
//            }
//        });
//    }
//
//    private void replaceFragment (Fragment fragment){
//
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.mind_meditate_frame_layout,fragment);
//        fragmentTransaction.commit();
//    }

    TextView timeViewMindMeditation;
    FloatingActionButton saveTimeMeditate;
    Button meditationTimesBtn;

    View view;
    private int second=0;
    private boolean running;
    private boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mind_meditate);

        timeViewMindMeditation = findViewById(R.id.mind_meditation_time_view);
        saveTimeMeditate = findViewById(R.id.saveTimeMeditateMind);

        meditationTimesBtn = findViewById(R.id.mind_meditation_times_frag_btn);
        meditationTimesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MindMeditate.this, MindMeditationTimes.class);
                startActivity(intent);
            }
        });
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy");
        String date = simpleDateFormat.format(calendar.getTime());
        saveTimeMeditate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBhelper db = new DBhelper(MindMeditate.this);
                db.addMeditatedTime(date.trim(),timeViewMindMeditation.getText().toString().trim());
            }
        });
        if(savedInstanceState != null){
            second = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }

        runTimer();
    }

    private void runTimer() {

        final TextView timeView = findViewById(R.id.mind_meditation_time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = second / 3600;
                int minutes = (second % 3600) / 60;
                int sec = second % 60;

                String time = String.format(Locale.getDefault(),
                        "%d:%02d:%02d",hours,
                        minutes,sec);

                timeView.setText(time);
                if(running){
                    second++;
                }

                handler.postDelayed(this,1000);
            }
        });
    }

    @Override
    protected void onPause(){
        super.onPause();
        wasRunning = running;
        running = false;
    }
    @Override
    protected void onResume(){
        super.onResume();
        if(wasRunning){
            running = true;
        }
    }
    public void onClickStartTimer(View view){

        running = true;
    }
    public void onClickStopTimer(View view){

        running = false;
    }public void onClickResetTimer(View view){
        running = false;

        second = 0;
    }
    }