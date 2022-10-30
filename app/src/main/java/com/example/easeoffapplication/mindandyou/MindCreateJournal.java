package com.example.easeoffapplication.mindandyou;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.easeoffapplication.R;
import com.example.easeoffapplication.db.DBhelper;

public class MindCreateJournal extends AppCompatActivity {

    EditText dateMJ, notesMJ,rateMJ;
    Button saveMJ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mind_create_journal);

        dateMJ = findViewById(R.id.dateEdt_createMindJ);
        rateMJ = findViewById(R.id.mind_journal_create_rate);
        notesMJ= findViewById(R.id.mind_journal_create_desc);
        saveMJ = findViewById(R.id.mind_journal_add_btn);
        saveMJ.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(dateMJ.getText().toString())){
                    dateMJ.setError("Enter date");
                    return;
                }
                DBhelper Db = new DBhelper(MindCreateJournal.this);
                Db.addThoughtJournal(dateMJ.getText().toString().trim(),
                        ((String) rateMJ.getText().toString().trim()),
                        notesMJ.getText().toString().trim()
                        );
            }
        });
    }
}