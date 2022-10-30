package com.example.easeoffapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easeoffapplication.db.DBhelper;
import com.example.easeoffapplication.db.User;

public class Login extends AppCompatActivity {

    EditText username,password;
    Button login;
    String Username,Password;
    public static String Name=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        login=findViewById(R.id.loginBtn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

    }

    void showToast(String message) {

        Toast toast = new Toast(this);

        View view = LayoutInflater.from(Login.this).inflate(R.layout.sucesstoast, null);

        TextView tvMessage = view.findViewById(R.id.tvMessage);
        tvMessage.setText(message);

        toast.setView(view);
        toast.show();

    }
    public boolean login(){
        Username=username.getText().toString();
        Password=password.getText().toString();

        boolean valid=validateUser(Username,Password);

        if(valid==true){
            Name=Username;
            Intent intent2=new Intent(this,MainDashboard.class);
            startActivity(intent2);
        }
        else{
            showToast("Invalid Username or Password!");
        }
        return valid;

    }


    //checks if username and password is correct
    public Boolean validateUser(String username,String password){
        DBhelper dbHelper = new DBhelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor cursor=db.rawQuery("Select * from "+ User.userDetails.TABLE_NAME+" where "+
                User.userDetails.COLUMN_NAME_USERNAME+" = ? and "+User.userDetails.COLUMN_NAME_PASSWORD+" = ?",
                new String[]{username,password});

        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }

    //method to redirect to signup page
    public void moveToSignUp(View view){
        Intent intent1=new Intent(this,Signup.class);
        startActivity(intent1);
    }
}