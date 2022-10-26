package com.example.easeoffapplication.Healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.easeoffapplication.R;
import com.example.easeoffapplication.db.DBhelper;
import com.example.easeoffapplication.db.MedShedule;
import com.example.easeoffapplication.db.Medicines;

import java.util.Locale;

public class EditMedSchedule extends AppCompatActivity {

    EditText ScheduleName_edit;
    Button morn_time, noon_time, night_time;
    int mornHour, mornMin, noonHour, noonMin, nightHour, nightMin;
    Button save_schedule;
    CalendarView date;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_med_schedule);

        ScheduleName_edit = findViewById(R.id.scheduleName_edit);
        morn_time = findViewById(R.id.morning_time_edit);
        noon_time = findViewById(R.id.afternoon_time_edit);
        night_time = findViewById(R.id.night_time_edit);
        date = findViewById(R.id.scheduleDate_edit);
        save_schedule = findViewById(R.id.save_medSchedule_edit);

        createNotificationChannel();

        final String id = getIntent().getStringExtra("scheduleEdit");
        MedShedule schedule = getSingleSchedule(Integer.parseInt(id));

        ScheduleName_edit.setText(schedule.getName());

        mornHour = schedule.getMornHour();
        mornMin = schedule.getMornMin();
        noonHour = schedule.getNoonHour();
        noonMin = schedule.getNoonMin();
        nightHour = schedule.getNightHour();
        nightMin = schedule.getNightMin();

        morn_time.setText(String.format(Locale.getDefault(), "%02d:%02d",mornHour,mornMin));
        noon_time.setText(String.format(Locale.getDefault(), "%02d:%02d",noonHour,noonMin));
        night_time.setText(String.format(Locale.getDefault(), "%02d:%02d",nightHour,nightMin));

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
                TimePickerDialog timePickerDialog = new TimePickerDialog(EditMedSchedule.this,style,onTimeSetListener, mornHour, mornMin, true);
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
                TimePickerDialog timePickerDialog = new TimePickerDialog(EditMedSchedule.this,style,onTimeSetListener, noonHour, noonMin, true);
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
                TimePickerDialog timePickerDialog = new TimePickerDialog(EditMedSchedule.this,style,onTimeSetListener, nightHour, nightMin, true);
                timePickerDialog.setTitle("Select Time");
                timePickerDialog.show();
            }
        });

        save_schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ScheduleName_edit.getText().toString();

                MedShedule schedule = new MedShedule(Integer.parseInt(id),name,mornHour, mornMin, noonHour, noonMin, nightHour, nightMin);
                updateSingleSchedule(schedule);
                startActivity(new Intent(getApplicationContext(), DailyMedicine.class));
            }
        });


    }

    public MedShedule getSingleSchedule(int id){
        DBhelper dbHelper = new DBhelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String query = "SELECT * FROM " + MedShedule.medSchedules.TABLE_NAME + " WHERE " + MedShedule.medSchedules._ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(id)});

        MedShedule schedule;
        if(cursor != null){
            cursor.moveToFirst();
            schedule = new MedShedule(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getInt(3),
                    cursor.getInt(4),
                    cursor.getInt(5),
                    cursor.getInt(6),
                    cursor.getInt(7)
            );
            return schedule;
        }
        return null;
    }

    public int updateSingleSchedule(MedShedule schedule){
        DBhelper dbHelper = new DBhelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(MedShedule.medSchedules.COLUMN_NAME_SCHEDULENAME, schedule.getName());
        cv.put(MedShedule.medSchedules.COLUMN_NAME_MORNHOUR, schedule.getMornHour());
        cv.put(MedShedule.medSchedules.COLUMN_NAME_MORNMIN, schedule.getMornMin());
        cv.put(MedShedule.medSchedules.COLUMN_NAME_NOONHOUR, schedule.getNoonHour());
        cv.put(MedShedule.medSchedules.COLUMN_NAME_NOONMIN, schedule.getNoonMin());
        cv.put(MedShedule.medSchedules.COLUMN_NAME_NIGHTHOUR, schedule.getNightHour());
        cv.put(MedShedule.medSchedules.COLUMN_NAME_NIGHTMIN, schedule.getNightMin());

        int status = db.update(MedShedule.medSchedules.TABLE_NAME,
                cv,MedShedule.medSchedules._ID + " =?",
                new String[]{String.valueOf(schedule.getId())});
        db.close();
        return status;
    }

    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "EaseOffReminderChannel";
            String description = "Channel for Alarm manager";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("EaseOff",name,importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void setAlarm(){

        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(EditMedSchedule.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(EditMedSchedule.this,0,intent,0);
        if(alarmManager == null){
            System.out.println("alarm manager null");
        }
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,date.getDate(),AlarmManager.INTERVAL_DAY,pendingIntent);
        Toast.makeText(EditMedSchedule.this,"Alarm set successfully",Toast.LENGTH_SHORT).show();

    }
}