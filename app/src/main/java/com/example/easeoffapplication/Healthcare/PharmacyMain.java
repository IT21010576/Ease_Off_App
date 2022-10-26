package com.example.easeoffapplication.Healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.easeoffapplication.Healthcare.newMedList;
import com.example.easeoffapplication.R;

public class PharmacyMain extends AppCompatActivity {

    Button newList;
    Button allLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_main);

        newList = findViewById(R.id.newListBtn_pharmacy);
        allLists = findViewById(R.id.viewAllMedLists);

        newList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToNewMedList();
            }
        });
        allLists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), allMedLists.class);
                startActivity(intent);
            }
        });
    }
    public void moveToNewMedList(){
        Intent intent = new Intent(this, newMedList.class);
        startActivity(intent);
    }
}