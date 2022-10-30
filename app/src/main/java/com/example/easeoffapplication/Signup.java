package com.example.easeoffapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easeoffapplication.db.DBhelper;
import com.example.easeoffapplication.db.User;

public class Signup extends AppCompatActivity {

    EditText username,pwd,renter_pwd,dob;
    RadioButton male,female;
    Button signup;
    String Username,Password,Password2,DOB,Gender;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username=findViewById(R.id.createUsername);
        dob=findViewById(R.id.enterdob);
        pwd=findViewById(R.id.create_password);
        renter_pwd=findViewById(R.id.create_password2);
        male=findViewById(R.id.maleBtn);
        female=findViewById(R.id.femaleBtn);
        signup=findViewById(R.id.signup_btn);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signupUser();
            }
        });

    }

    public void signupUser(){

        //getting the values
        Username=username.getText().toString();
        Password=pwd.getText().toString();
        Password2=renter_pwd.getText().toString();
        DOB=dob.getText().toString();
        if(male.isChecked()){
            Gender="Male";
        }
        else{
            Gender="Female";
        }

        //validations
        if(checkUsername(Username)==true){
            showToast("Username Already Exists!");
        }
        else if(Password.equals(Password2)==false){
            showToast("Passwords Don't Match!");
        }
        else if(Password.length()<8){
            showToast("Password Must Have At Least 8 Characters!");
        }
        else{
            insertUser();
        }

    }

    //function to check if username already exists
    public boolean checkUsername(String username){
        DBhelper dbHelper = new DBhelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor cursor=db.rawQuery("Select * from "+User.userDetails.TABLE_NAME+" where "+
                User.userDetails.COLUMN_NAME_USERNAME+" = ?",new String[]{username});

        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }

    public void insertUser(){
        DBhelper dbHelper = new DBhelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(User.userDetails.COLUMN_NAME_USERNAME, Username);
        values.put(User.userDetails.COLUMN_NAME_PASSWORD, Password);
        values.put(User.userDetails.COLUMN_NAME_BYEAR, DOB);
        values.put(User.userDetails.COLUMN_NAME_GENDER, Gender);

        long newRowId = db.insert(User.userDetails.TABLE_NAME,null, values);
        if(newRowId==-1){
            showToast("Registration Failed");
        }
        else {
            showToast("Successfully Registered!");
            Intent intent=new Intent(this,Login.class);
            startActivity(intent);
        }


    }
    //custom Toast message
    void showToast(String message) {

        Toast toast = new Toast(this);

        View view = LayoutInflater.from(Signup.this).inflate(R.layout.sucesstoast, null);

        TextView tvMessage = view.findViewById(R.id.tvMessage);
        tvMessage.setText(message);

        toast.setView(view);
        toast.show();

    }


}