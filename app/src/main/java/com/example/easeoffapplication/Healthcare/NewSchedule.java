package com.example.easeoffapplication.Healthcare;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.easeoffapplication.EatHealthy.Update_trackCalorie;
import com.example.easeoffapplication.R;
import com.example.easeoffapplication.databinding.ActivityMainBinding;
import com.example.easeoffapplication.db.DBhelper;
import com.example.easeoffapplication.db.MedShedule;
import com.example.easeoffapplication.db.MedicineLists;
import com.example.easeoffapplication.db.Medicines;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Locale;

public class NewSchedule extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    EditText name;
    Button morn_time, noon_time, night_time;
    int mornHour, mornMin, noonHour, noonMin, nightHour, nightMin;
    Button save_schedule;
    CalendarView date;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;

    public NewSchedule() {
        // Required empty public constructor
    }
    public static NewSchedule newInstance(String param1, String param2) {
        NewSchedule fragment = new NewSchedule();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        createNotificationChannel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_schedule, container, false);
        getActivity().setTitle("New Schedule");

        name = view.findViewById(R.id.schedule_name);
        morn_time = view.findViewById(R.id.morning_time);
        noon_time = view.findViewById(R.id.afternoon_time);
        night_time = view.findViewById(R.id.night_time);
        date = view.findViewById(R.id.schedule_date);
        save_schedule = view.findViewById(R.id.save_medSchedule);


        morn_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        mornHour = i;
                        mornMin = i1;
                        morn_time.setText(String.format(Locale.getDefault(), "%02d:%02d",mornHour,mornMin));
                        setAlarm();
                    }
                };
                int style = AlertDialog.THEME_HOLO_LIGHT;
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),style,onTimeSetListener, mornHour, mornMin, true);
                timePickerDialog.setTitle("Select Time");
                timePickerDialog.show();
            }
        });

        noon_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        noonHour = i;
                        noonMin = i1;
                        noon_time.setText(String.format(Locale.getDefault(), "%02d:%02d",noonHour,noonMin));
                        setAlarm();
                    }
                };
                int style = AlertDialog.THEME_HOLO_LIGHT;
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),style,onTimeSetListener, noonHour, noonMin, true);
                timePickerDialog.setTitle("Select Time");
                timePickerDialog.show();
            }
        });

        night_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        nightHour = i;
                        nightMin = i1;
                        night_time.setText(String.format(Locale.getDefault(), "%02d:%02d",nightHour,nightMin));
                        setAlarm();
                    }
                };
                int style = AlertDialog.THEME_HOLO_LIGHT;
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),style,onTimeSetListener, nightHour, nightMin, true);
                timePickerDialog.setTitle("Select Time");
                timePickerDialog.show();
            }
        });

        save_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewSchedule();

                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.dailyMedDefFrag, new MedicineSchedules());
                ft.commit();
            }
        });


        return view;
    }

    public void addNewSchedule(){
        String scheduleName = name.getText().toString();
        int morn_H = mornHour;
        int morn_M = mornMin;
        int noon_H = noonHour;
        int noon_M = noonMin;
        int night_H = nightHour;
        int night_M = nightMin;

        MedShedule schedule = new MedShedule(scheduleName,morn_H, morn_M, noon_H, noon_M, night_H, night_M);

        DBhelper dbHelper = new DBhelper(getContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(MedShedule.medSchedules.COLUMN_NAME_SCHEDULENAME, schedule.getName());
        cv.put(MedShedule.medSchedules.COLUMN_NAME_MORNHOUR, schedule.getMornHour());
        cv.put(MedShedule.medSchedules.COLUMN_NAME_MORNMIN, schedule.getMornMin());
        cv.put(MedShedule.medSchedules.COLUMN_NAME_NOONHOUR, schedule.getNoonHour());
        cv.put(MedShedule.medSchedules.COLUMN_NAME_NOONMIN, schedule.getNoonMin());
        cv.put(MedShedule.medSchedules.COLUMN_NAME_NIGHTHOUR, schedule.getNightHour());
        cv.put(MedShedule.medSchedules.COLUMN_NAME_NIGHTMIN, schedule.getNightMin());

        long result = db.insert(MedShedule.medSchedules.TABLE_NAME, null, cv);
        db.close();
        if(result==-1){
            showToast("New Schedule Not Added!");
        }
        else {
            showToast("Schedule Added!");
        }
    }
    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "EaseOffReminderChannel";
            String description = "Channel for Alarm manager";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("EaseOff",name,importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getActivity().getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void setAlarm(){

        alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getContext(), AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(getContext(),0,intent,0);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,date.getDate(),AlarmManager.INTERVAL_DAY,pendingIntent);
        showToast("Alarm set successfully!");
    }

    void showToast(String message) {

        Toast toast = new Toast(getContext());

        View view = LayoutInflater.from(getContext()).inflate(R.layout.sucesstoast, null);

        TextView tvMessage = view.findViewById(R.id.tvMessage);
        tvMessage.setText(message);

        toast.setView(view);
        toast.show();

    }

}