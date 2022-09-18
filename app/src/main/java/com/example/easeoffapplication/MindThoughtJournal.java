package com.example.easeoffapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MindThoughtJournal extends AppCompatActivity {

    //FloatingActionButton mindJournalAddBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mind_thought_journal);
//
//        mindJournalAddBtn = findViewById(R.id.mind_journal_add_btn);
//        mindJournalAddBtn.setOnClickListener();
    }

    public void moveToMindNewJournal(View view) {
        Intent newJournal = new Intent(this,MindCreateJournal.class );
        startActivity(newJournal);
    }
}