package com.example.easeoffapplication.Healthcare;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easeoffapplication.EatHealthy.Update_trackCalorie;
import com.example.easeoffapplication.R;
import com.example.easeoffapplication.db.DBhelper;
import com.example.easeoffapplication.db.MedShedule;
import com.example.easeoffapplication.db.Medicines;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MedicineSchedules extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FloatingActionButton addScheduleBtn;
    ListView all_schedules;
    List<MedShedule> schedules;
    private Dialog dialog;

    public MedicineSchedules() {
        // Required empty public constructor
    }

    public static MedicineSchedules newInstance(String param1, String param2) {
        MedicineSchedules fragment = new MedicineSchedules();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_medicine_schedules, container, false);
        getActivity().setTitle("All Reminders");

        addScheduleBtn = view.findViewById(R.id.addSchedule_floating);
        all_schedules = view.findViewById(R.id.listView_schedules);

        //Create the Dialog here
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.custom_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false); //Optional
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation; //Setting the animations to dialog

        Button DialogYes = dialog.findViewById(R.id.btn_okay);
        Button Cancel = dialog.findViewById(R.id.btn_cancel);

        schedules = new ArrayList<>();
        schedules = getAllSchedules();

        MedicineSchedulesAdapter medicineSchedulesAdapter = new MedicineSchedulesAdapter(getContext(),R.layout.single_medicine_schedule, schedules);
        all_schedules.setAdapter(medicineSchedulesAdapter);

        addScheduleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.dailyMedDefFrag, new NewSchedule());
                ft.commit();
            }
        });

        all_schedules.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MedShedule schedule = schedules.get(i);

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle(schedule.getName());

                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.show();
                    }
                });

                DialogYes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteSchedule(schedule.getId());
                        dialog.dismiss();
                        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.dailyMedDefFrag, new MedicineSchedules());
                        ft.commit();
                    }
                });

                Cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                builder.setNeutralButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(getContext(), EditMedSchedule.class);
                        intent.putExtra("scheduleEdit", String.valueOf(schedule.getId()));
                        startActivity(intent);
                    }
                });
                builder.show();
            }

        });

        return view;
    }

    public List<MedShedule> getAllSchedules(){

        List<MedShedule> schedules = new ArrayList<>();

        DBhelper dbHelper = new DBhelper(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "SELECT * FROM " + MedShedule.medSchedules.TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                MedShedule schedule = new MedShedule();

                schedule.setId(cursor.getInt(0));
                schedule.setName(cursor.getString(1));
                schedule.setMornHour(cursor.getInt(2));
                schedule.setMornMin(cursor.getInt(3));
                schedule.setNoonHour(cursor.getInt(4));
                schedule.setNoonMin(cursor.getInt(5));
                schedule.setNightHour(cursor.getInt(6));
                schedule.setNightMin(cursor.getInt(7));

                schedules.add(schedule);
            }while (cursor.moveToNext());
        }
        return schedules;
    }

    public void deleteSchedule(int id){
        DBhelper dbHelper = new DBhelper(getContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        long result = db.delete(MedShedule.medSchedules.TABLE_NAME, MedShedule.medSchedules._ID + " =?",
                new String[]{String.valueOf(id)});
        if(result==-1){
            showToast("Failed To Delete!");
        }
        else {
            showToast("Record Deleted!");
        }
        db.close();
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