package com.example.easeoffapplication.Healthcare;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.easeoffapplication.R;
import com.example.easeoffapplication.db.DBhelper;
import com.example.easeoffapplication.db.MedShedule;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

        addScheduleBtn = view.findViewById(R.id.addSchedule_floating);
        all_schedules = view.findViewById(R.id.listView_schedules);
        schedules = new ArrayList<>();

        schedules = getAllSchedules();
        if(all_schedules == null){
            System.out.println("list null");
        }

        MedicineSchedulesAdapter medicineSchedulesAdapter = new MedicineSchedulesAdapter(getContext(),R.layout.single_medicine_schedule, schedules);
        all_schedules.setAdapter(medicineSchedulesAdapter);

        addScheduleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.pharmacyDefaultfrag, new NewSchedule());
                ft.commit();
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
                schedule.setDate(cursor.getLong(1));
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
}