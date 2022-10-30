package com.example.easeoffapplication.mindandyou;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.easeoffapplication.R;
import com.example.easeoffapplication.db.DBhelper;

public class MindThoughtJournalUpdate extends AppCompatActivity {

    EditText date_tj_input, rate_tj_input,note_tj_input;
    //TextView jid;
    Button update_tj_btn, delete_tj_btn;
    String id, date,note, rate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mind_thought_journal_update);
        ///
        //jid = findViewById(R.id.mind_journal_card_id);
        date_tj_input = findViewById(R.id.dateEdt_updateMindJ);
        //date_tj_input = findViewById(R.id.dateEdt_createMindJ);
        rate_tj_input = findViewById(R.id.mind_journal_update_rate);
        note_tj_input = findViewById(R.id.mind_journal_update_desc);
        update_tj_btn = findViewById(R.id.min);
        delete_tj_btn = findViewById(R.id.deleteMindTJBtn);

        ActionBar ab = getSupportActionBar();

        if(ab != null){
            ab.setTitle(date);

        }
        getIntentDataTJupdate();
        update_tj_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(date_tj_input.getText().toString())){
                    date_tj_input.setError("Enter date");
                    return;
                }
                DBhelper dBhelper = new DBhelper(MindThoughtJournalUpdate.this);
                dBhelper.updateThoughtJournal(id, date, rate, note);

            }
        });

        delete_tj_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                DBhelper dBhelper = new DBhelper(MindThoughtJournalUpdate.this);
//                dBhelper.deleteOneThoughtJournal(id);
                confirmDialogTJdelete();
            }
        });

    }

    void getIntentDataTJupdate(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("date") &&
        getIntent().hasExtra("rate") && getIntent().hasExtra("note")){
            id = getIntent().getStringExtra("id");
            date = getIntent().getStringExtra("date");
            rate = getIntent().getStringExtra("rate");
            note = getIntent().getStringExtra("note");

            //jid.setText(id);
            date_tj_input.setText(date);
            rate_tj_input.setText(rate);
            note_tj_input.setText(note);

        }else{
            Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialogTJdelete(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + date + " journal?");
        builder.setMessage("are you sure you want to delete journal?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBhelper dBhelper = new DBhelper(MindThoughtJournalUpdate.this);
                dBhelper.deleteOneThoughtJournal(id);
                finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }}