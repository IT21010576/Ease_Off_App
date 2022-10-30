package com.example.easeoffapplication.stayfit;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.easeoffapplication.R;
import com.example.easeoffapplication.db.DBhelper;
import com.example.easeoffapplication.db.Workoutprofile;
import com.example.easeoffapplication.db.workoutplan;

public class SetGoal extends AppCompatActivity {

    TextView date,cal_calories,cal_wotime;
    EditText setgoal_name,squat,bench_press,leg_press,overhead_press;
    Button save,calculate;
    double totcal;
    double tottime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_goal);

        date=findViewById(R.id.setgoal_date);
        cal_calories=findViewById(R.id.setgoal_cb);
        cal_wotime=findViewById(R.id.setgoal_wotime);
        setgoal_name=findViewById(R.id.setgoal_name);
        squat=findViewById(R.id.setgoal_squat);
        bench_press=findViewById(R.id.setgoal_bench);
        leg_press=findViewById(R.id.setgoal_legpress);
        overhead_press=findViewById(R.id.setgoal_overhead);
        //button
        save=findViewById(R.id.setgoal_savebtn);
        calculate=findViewById(R.id.setgoal_calculatebtn);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                totcal=Integer.valueOf( squat.getText().toString())*10+ Integer.valueOf( bench_press.getText().toString())*10+
                        Integer.valueOf( leg_press.getText().toString())*10+Integer.valueOf( overhead_press.getText().toString())*10;
                tottime=Integer.valueOf( squat.getText().toString())*2+ Integer.valueOf( bench_press.getText().toString())*2+
                        Integer.valueOf( leg_press.getText().toString())*2+Integer.valueOf( overhead_press.getText().toString())*2;

                cal_calories.setText(String.valueOf(totcal));
                cal_wotime.setText(String.valueOf(tottime));
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // addsetgoal();
            }

        });
    }

    public void addsetgoal(){

        String name=setgoal_name.getText().toString();
        int squat_input=Integer.valueOf( squat.getText().toString());
        int bench_input=Integer.valueOf( bench_press.getText().toString());
        int leg_input=Integer.valueOf( leg_press.getText().toString());
        int overhead_input=Integer.valueOf( overhead_press.getText().toString());
        double calorie_input=Double.valueOf( cal_calories.getText().toString());
        double time_input=Double.valueOf(cal_wotime.getText().toString());


        System.out.println(name+squat_input +bench_input+leg_input+overhead_input+calorie_input+time_input);

       // workoutplan plan=new workoutplan(name,squat_input,bench_input,leg_input,overhead_input,calorie_input,time_input);
        DBhelper dBhelper=new DBhelper(getApplicationContext());
        SQLiteDatabase db=dBhelper.getWritableDatabase();
        ContentValues cv=new ContentValues();

        cv.put(workoutplan.Workoutplan.COLUMN_NAME_name,name);
        cv.put(workoutplan.Workoutplan.COLUMN_NAME_squat,squat_input);
        cv.put(workoutplan.Workoutplan.COLUMN_NAME_bench_press,bench_input);
        cv.put(workoutplan.Workoutplan.COLUMN_NAME_leg_press,leg_input);
        cv.put(workoutplan.Workoutplan.COLUMN_NAME_overhead_press,overhead_input);
        cv.put(workoutplan.Workoutplan.COLUMN_NAME_calorie,calorie_input);
        cv.put(workoutplan.Workoutplan.COLUMN_NAME_workouttime,time_input);

        long result=db.insert(workoutplan.Workoutplan.TABLE_NAME,null,cv);
        db.close();

        if(result==-1){
            System.out.println("workout not added ");
        }
        else{
            System.out.println("workout added ");
        }

    }


}