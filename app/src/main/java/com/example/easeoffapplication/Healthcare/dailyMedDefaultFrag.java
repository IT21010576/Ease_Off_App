package com.example.easeoffapplication.Healthcare;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.easeoffapplication.R;

public class dailyMedDefaultFrag extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    Button startSchedule,viewAllSchedules;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public dailyMedDefaultFrag() {
        // Required empty public constructor
    }

    public static dailyMedDefaultFrag newInstance(String param1, String param2) {
        dailyMedDefaultFrag fragment = new dailyMedDefaultFrag();
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
        View view = inflater.inflate(R.layout.fragment_daily_med_default, container, false);

        viewAllSchedules = view.findViewById(R.id.viewschedulebtn_dailymed);

        viewAllSchedules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.dailyMedDefFrag, new MedicineSchedules());
                ft.commit();
            }
        });

        startSchedule = view.findViewById(R.id.startScheduleBtn);
        startSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.dailyMedDefFrag, new NewSchedule());
                ft.commit();
            }
        });
        return view;
    }
}