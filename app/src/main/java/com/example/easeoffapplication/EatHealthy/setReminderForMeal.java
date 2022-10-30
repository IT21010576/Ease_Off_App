package com.example.easeoffapplication.EatHealthy;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.easeoffapplication.EatHealthy.Reminder;
import com.example.easeoffapplication.R;

import java.util.Calendar;


public class setReminderForMeal extends Fragment {

    TimePicker picktime;
    Button setAlarm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_set_reminder_for_meal, container, false);

        picktime=view.findViewById(R.id.timePicker);
        setAlarm=view.findViewById(R.id.alarmBtn);

        setAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calender=Calendar.getInstance();
                if(Build.VERSION.SDK_INT>=23) {
                    calender.set(
                            calender.get(Calendar.YEAR),
                            calender.get(Calendar.MONDAY),
                            calender.get(Calendar.DAY_OF_MONTH),
                            picktime.getHour(),
                            picktime.getMinute(),
                            0
                    );
                }
                else{
                    calender.set(
                            calender.get(Calendar.YEAR),
                            calender.get(Calendar.MONDAY),
                            calender.get(Calendar.DAY_OF_MONTH),
                            picktime.getCurrentHour(),
                            picktime.getCurrentMinute(),
                            0
                    );

                }
                setAlarm(calender.getTimeInMillis());
            }
        });
        return view;
    }

    private void setAlarm(long timeInMillis){
        AlarmManager alarmManager=(AlarmManager)this.getContext().getSystemService(Context.ALARM_SERVICE);
        Intent intent=new Intent(getContext(), Reminder.class);

        PendingIntent pendingIntent=PendingIntent.getBroadcast(getContext(),0,intent,0);
        alarmManager.setRepeating(AlarmManager.RTC,timeInMillis,AlarmManager.INTERVAL_DAY,pendingIntent);
        Toast.makeText(getContext(),"Alarm is set",Toast.LENGTH_LONG).show();
    }
}