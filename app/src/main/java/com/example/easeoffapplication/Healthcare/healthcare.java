package com.example.easeoffapplication.Healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.easeoffapplication.R;

public class healthcare extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_healthcare);
    }

    public void moveToPharmacy(View view){
        Intent intent1=new Intent(this, PharmacyMain.class);
        startActivity(intent1);

    }

    public void moveToDailyMedicine(View view){
        Intent intent1=new Intent(this, DailyMedicine.class);
        startActivity(intent1);

    }

    public void moveToDiseasesUpdate(View view){
        Intent intent1=new Intent(this, DiseasesUpdate.class);
        startActivity(intent1);

    }

}