package com.example.easeoffapplication.mindandyou;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.easeoffapplication.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MindSleep extends AppCompatActivity {

    TextView sleep_timePicker1, sleep_timePicker2;
    int t1Hour, t1min, t2Hour, t2min;
    Button calcSleepTime;

    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mind_sleep);

        sleep_timePicker1 = findViewById(R.id.mind_sleep_start_time);
        sleep_timePicker2 = findViewById(R.id.mind_sleep_end_time);

        dialog = new Dialog(this);
        sleep_timePicker1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        MindSleep.this,
                        new TimePickerDialog.OnTimeSetListener(){
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute){
                                t1Hour = hourOfDay;
                                t1min = minute;

                                Calendar calendar = Calendar.getInstance();
                                calendar.set(0,0,0,t1Hour,t1min);


                                sleep_timePicker1.setText(new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(calendar.getTimeInMillis()));

                            }

                        },12,0,false
                );
                timePickerDialog.updateTime(t1Hour, t1min);
                timePickerDialog.show();

            }
        });


        sleep_timePicker2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        MindSleep.this,
                        new TimePickerDialog.OnTimeSetListener(){
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute){
                                t2Hour = hourOfDay;
                                t2min = minute;

                                Calendar calendar = Calendar.getInstance();
                                calendar.set(0,0,0,t2Hour,t2min);


                                sleep_timePicker2.setText(new SimpleDateFormat("hh:mm a", Locale.getDefault()).format(calendar.getTimeInMillis()));

                            }

                        },12,0,false
                );
                timePickerDialog.updateTime(t2Hour, t2min);
                timePickerDialog.show();

            }
        });

        calcSleepTime = findViewById(R.id.mind_sleep_calc_btn);
        calcSleepTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int hours = t2Hour - t1Hour;
                int minutes = t2min - t1min;

                if(hours < 8)
                {
                    dialogNotEnoughSleep();
                }
                else
                {
                    dialogEnoughSleep();
                }
                System.out.print(hours + " : " + minutes);
            }
        });

    }

    private void dialogNotEnoughSleep() {
        dialog.setContentView(R.layout.mind_sleep_negative_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView imageViewClose = dialog.findViewById(R.id.mind_sleep_warn_img);
        Button btnWarn = dialog.findViewById(R.id.mind_sleep_neg_dialog_close);
        dialog.show();
        btnWarn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    private void dialogEnoughSleep() {
        dialog.setContentView(R.layout.mind_sleep_positive_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView imageViewClose = dialog.findViewById(R.id.mind_sleep_enough_img);
        Button btnWarn = dialog.findViewById(R.id.mind_sleep_pos_dialog_close);
        dialog.show();

        btnWarn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    public void moveToMindSleepFeedback(View view) {
        Intent intent = new Intent(this, MindSleepFeedback.class );
        startActivity(intent);
    }

//    public void closeDialog(View view){
//        dialog.dismiss();
//    }
}