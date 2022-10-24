package com.example.easeoffapplication.EatHealthy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.easeoffapplication.Login;
import com.example.easeoffapplication.R;
import com.example.easeoffapplication.db.DBhelper;
import com.example.easeoffapplication.db.User;

import java.util.Calendar;

public class NutriFacts extends AppCompatActivity {

    Integer Age;
    TextView textView1,textView2,textView3;
    Button btn1,btn2,btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutri_facts);

        textView1=findViewById(R.id.fact1Text);
        textView2=findViewById(R.id.fact2Text);
        textView3=findViewById(R.id.fact3Text);

        btn1=findViewById(R.id.fact1Btn);
        btn2=findViewById(R.id.fact2Btn);
        btn3=findViewById(R.id.fact3Btn);

        calculateAge();
        displayFacts();
        setLinks();

    }

    //get the Birth year of current user
    public Integer  getDOB(){

        Integer YearOfBirth=null;
        String username=Login.Name;
        DBhelper dbHelper = new DBhelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String query="Select * from "+User.userDetails.TABLE_NAME+" where "+
                User.userDetails.COLUMN_NAME_USERNAME+" = ?";

        Cursor cursor=db.rawQuery(query,new String[]{username});

        while(cursor.moveToNext()) {
            YearOfBirth = cursor.getInt(
                    cursor.getColumnIndexOrThrow(User.userDetails.COLUMN_NAME_BYEAR));

        }
        return YearOfBirth;
    }

    //calulate age of user from Birth Year
    public void calculateAge(){
        Integer birthYear=getDOB();
        Integer currentYear= Calendar.getInstance().get(Calendar.YEAR);
        Age=currentYear-birthYear;
    }

    //Display Facts according to age group
    public void displayFacts(){

        if(Age<=25){
            String Fact1="Cutting Down on Fast Food Intake Will Improve Your Life In Unbelievable Ways.Read To" +
                    "Find Out Ways To Cut Down Intake";
            String Fact2="Never Forget To Stay Hydrated It Will Boost Your Life. Read To Find Out More ";
            String Fact3="Did You Know Breakfast Is The Most Important Meal of the Day. Read To Find Out How " +
                    "it Helps in Memory Retention and More";
            textView1.setText(Fact1);
            textView2.setText(Fact2);
            textView3.setText(Fact3);
        }
        else if(Age>25 && Age<=45){
            String Fact1="Don't Miss to Eat a Mix Of Colorful Vegetables Each Day";
            String Fact2="Limit Foods and Beverages That Are High In Sugar and Salt.";
            String Fact3="Try Out This Healthy Oatmeal Recipe";
            textView1.setText(Fact1);
            textView2.setText(Fact2);
            textView3.setText(Fact3);
        }
        else{
            String Fact1="Don't Forget to Include Calcium and Vitamin D Rich Food In Your Diet";
            String Fact2="Choose Foods with Little to No Added Sugar, Fats, and Sodium, to Reduce Disease Risk.";
            String Fact3="Remember To Drink Plenty Of Water and Stay Hydrated";
            textView1.setText(Fact1);
            textView2.setText(Fact2);
            textView3.setText(Fact3);
        }
    }

    public void setLinks(){
        if(Age<=25){
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://www.heartfoundation.org.nz/about-us/news/blogs/eight-ways-to-cut-the-junk"));
                    startActivity(intent);
                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://archive.nutrition.org.uk/healthyliving/hydration/adults-teens.html"));
                    startActivity(intent);
                }
            });
            btn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://blog.innerdrive.co.uk/the-7-psychological-benefits-of-students-eating-breakfast"));
                    startActivity(intent);
                }
            });
        }
        else if(Age>25 && Age<=45){
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://www.heartfoundation.org.nz/about-us/news/blogs/eight-ways-to-cut-the-junk"));
                    startActivity(intent);
                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://archive.nutrition.org.uk/healthyliving/hydration/adults-teens.html"));
                    startActivity(intent);
                }
            });
            btn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://blog.innerdrive.co.uk/the-7-psychological-benefits-of-students-eating-breakfast"));
                    startActivity(intent);
                }
            });
        }
    }
}