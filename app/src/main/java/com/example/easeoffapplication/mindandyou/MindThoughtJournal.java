package com.example.easeoffapplication.mindandyou;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.easeoffapplication.R;
import com.example.easeoffapplication.db.DBhelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MindThoughtJournal extends AppCompatActivity {

    FloatingActionButton mindJournalAddBtn;
    RecyclerView mindJournalrecyclerView;

    DBhelper db;
    ArrayList<String> mj_id, mj_date, mj_rate,mj_note;

    CustomAdapterMindTJ customAdapterMindTJ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mind_thought_journal);
//
       mindJournalAddBtn = findViewById(R.id.mind_journal_add_btn);
        mindJournalrecyclerView = findViewById(R.id.recyclerviewMindJournal);
        mindJournalAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MindThoughtJournal.this, MindCreateJournal.class);
                startActivity(intent);
            }
        });

        db = new DBhelper(MindThoughtJournal.this);
        mj_id = new ArrayList<>();
        mj_date = new ArrayList<>();
        mj_rate = new ArrayList<>();
        mj_note = new ArrayList<>();

        storeTJinArrays();
        customAdapterMindTJ = new CustomAdapterMindTJ(MindThoughtJournal.this,this, mj_id,mj_date,mj_rate,mj_note);
        mindJournalrecyclerView.setAdapter(customAdapterMindTJ);
        mindJournalrecyclerView.setLayoutManager(new LinearLayoutManager(MindThoughtJournal.this));

//
//    public void moveToMindNewJournal(View view) {
//        Intent newJournal = new Intent(this,MindCreateJournal.class );
//        startActivity(newJournal);
//    }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }
    void storeTJinArrays(){
        Cursor cursor = db.readAllJournals();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                mj_id.add(cursor.getString(0));
                mj_date.add(cursor.getString(1));
                mj_rate.add(cursor.getString(2));
                mj_note.add(cursor.getString(3));

            }
        }
    }
}