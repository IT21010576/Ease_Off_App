package com.example.easeoffapplication.mindandyou;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.easeoffapplication.R;

public class MindMeditateTimerFr extends Fragment {

    View view;
    private int second=0;
    private boolean running;
    private boolean wasRunning;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(savedInstanceState != null){
            second = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }

        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_mind_meditate_timer, container, false);
        runTimer();
        return view;
    }

    private void runTimer() {

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