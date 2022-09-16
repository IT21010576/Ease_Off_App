package com.example.easeoffapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void moveToSignUp(View view){
        Intent intent1=new Intent(this,Signup.class);
        startActivity(intent1);
    }

    public void moveToDashboard(View view){
        Intent intent2=new Intent(this,MainDashboard.class);
        startActivity(intent2);
    }
}