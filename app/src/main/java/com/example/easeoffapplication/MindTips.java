package com.example.easeoffapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class MindTips extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mind_tips);
    }

    public void onClickFindTips(View view) {
        TextView tips = (TextView) findViewById(R.id.mind_tips_display_textview);

        Spinner topics=(Spinner) findViewById(R.id.mind_tips_spinner);

        String tipsFound = String.valueOf(topics.getSelectedItem());
        tips.setText(tipsFound);
    }
}