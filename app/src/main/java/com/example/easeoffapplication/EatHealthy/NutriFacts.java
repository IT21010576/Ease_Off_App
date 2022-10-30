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

    int Age,birthYear,currentYear;
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

        //getting the birthYear from getDOB() method
        birthYear=getDOB();
        //getting the currentYear
        currentYear= Calendar.getInstance().get(Calendar.YEAR);
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
    public int calculateAge(int birthYear,int currentYear){
        return (currentYear-birthYear);
    }

    //Display Facts according to age group
    public void displayFacts(){

        Age=calculateAge(birthYear,currentYear);

        if(Age<=25){
            String Fact1="Cutting down on fast food intake will improve your life in unbelievable ways." +
                    "Find out ways to cut down intake";
            String Fact2="Never forget to stay hydrated it will boost your life. Find out more below";
            String Fact3="Did you know breakfast is the most important meal of the day. Watch to find out how " +
                    "it helps in memory retention and more";
            textView1.setText(Fact1);
            textView2.setText(Fact2);
            textView3.setText(Fact3);
        }
        else if(Age>25 && Age<=45){
            String Fact1="Don't miss to eat a mix of colorful vegetables each day. Find out more of their importance" +
                    "below";
            String Fact2="Here is why you need to limit foods and beverages that are high in sugar and salt.";
            String Fact3="Oatmeal food are extremely healthy and easy to prepare! Watch to learn to make Oatmeal snacks";
            textView1.setText(Fact1);
            textView2.setText(Fact2);
            textView3.setText(Fact3);
        }
        else{
            String Fact1="Don't forget to include Calcium and Vitamin D rich food in your diet.Find out more about this";
            String Fact2="Choose food with little to no added Sugar, Fats, and Sodium, to reduce disease risk.";
            String Fact3="Remember to drink plenty of water and stay hydrated. Watch and learn the benefits";
            textView1.setText(Fact1);
            textView2.setText(Fact2);
            textView3.setText(Fact3);
        }
    }

    public void setLinks(){
        Age=calculateAge(birthYear,currentYear);

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
                    intent.setData(Uri.parse("https://www.youtube.com/watch?v=JNZkXx8eB90&t=3s"));
                    startActivity(intent);
                }
            });
        }
        else if(Age>25 && Age<=45){
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://www.hsph.harvard.edu/nutritionsource/what-should-you-eat/vegetables-and-fruits/"));
                    startActivity(intent);
                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://www.hsph.harvard.edu/news/hsph-in-the-news/benefits-of-limiting-sugary-beverages/"));
                    startActivity(intent);
                }
            });
            btn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://www.youtube.com/watch?v=tMZHstlBHRY"));
                    startActivity(intent);
                }
            });
        }
        else{
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://www.bonehealthandosteoporosis.org/news/a-diet-rich-in-calcium-and-vitamin-d-can-improve-health-and-add-to-your-longevity/"));
                    startActivity(intent);
                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://medlineplus.gov/nutritionforolderadults.html"));
                    startActivity(intent);
                }
            });
            btn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://www.webmd.com/diet/features/6-reasons-to-drink-water"));
                    startActivity(intent);
                }
            });

        }
    }
}