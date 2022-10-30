package com.example.easeoffapplication.stayfit;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.provider.BaseColumns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easeoffapplication.R;
import com.example.easeoffapplication.db.DBhelper;
import com.example.easeoffapplication.db.Workoutprofile;
import com.example.easeoffapplication.db.workoutplan;

public class Myprofile1 extends AppCompatActivity {

    EditText set_name;
    EditText age;
    EditText weight;
    EditText height;
    Button submit,edit;
    String currentID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile1);

        set_name=findViewById(R.id.set_name);
        age=findViewById(R.id.set_age);
        weight=findViewById(R.id.set_height);
        height=findViewById(R.id.set_weight);
        submit=findViewById(R.id.wsubmitbtn);
        edit=findViewById(R.id.weditbtn);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addprofile();
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateprofile();
            }
        });
    }
    public void updateprofile(){
        DBhelper dbHelper = new DBhelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // New values or value
        String newage=age.getText().toString();
        String newheight=height.getText().toString();
        String newweight=weight.getText().toString();

        ContentValues values = new ContentValues();
        values.put(Workoutprofile.workoutprofile.COLUMN_NAME_age,newage);
        values.put(Workoutprofile.workoutprofile.COLUMN_NAME_height,newheight);
        values.put(Workoutprofile.workoutprofile.COLUMN_NAME_weight,newweight);


        String selection = BaseColumns._ID + " LIKE ?";
        String[] selectionArgs = { currentID };

        long result=db.update(Workoutprofile.workoutprofile.TABLE_NAME, values, selection, selectionArgs);

        if(result==-1){
            Toast.makeText(getApplicationContext(),"Failed To Update!",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(),"Record Updated sucessfully!",Toast.LENGTH_LONG).show();
        }

    }
    public void addprofile(){
        DBhelper dbHelper = new DBhelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        String get_uname=set_name.getText().toString();
        String Age=age.getText().toString();
        String Height=height.getText().toString();
        String Weight=weight.getText().toString();

        ContentValues values = new ContentValues();
        values.put(Workoutprofile.workoutprofile.COLUMN_NAME_name,get_uname);
        values.put(Workoutprofile.workoutprofile.COLUMN_NAME_age,Age);
        values.put(Workoutprofile.workoutprofile.COLUMN_NAME_height,Height);
        values.put(Workoutprofile.workoutprofile.COLUMN_NAME_weight,Weight);

        long newRowId = db.insert(Workoutprofile.workoutprofile.TABLE_NAME,null, values);

        if(newRowId==-1){
            Toast.makeText(getApplicationContext(),"Failed To Add!",Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(),"Record Added Sucessfully!",Toast.LENGTH_LONG).show();
        }

    }}

