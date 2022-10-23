package com.example.easeoffapplication.EatHealthy;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easeoffapplication.R;
import com.example.easeoffapplication.db.DBhelper;
import com.example.easeoffapplication.db.TrackCalories;


public class DisplayCalculatedTotal extends Fragment {

    String CarbsValue,ProteinValue,FatsValue,TotalCalories,Date;
    String comment="";
    TextView CarbTot,ProteinTot,FatsTot,TotalCal,Label;
    Button saveBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_display_calculated_total, container, false);

        Label=view.findViewById(R.id.lblSummary);
        CarbTot=view.findViewById(R.id.carbSmry);
        ProteinTot=view.findViewById(R.id.proSmry);
        FatsTot=view.findViewById(R.id.fatsSmry);
        TotalCal=view.findViewById(R.id.TotalSmry);
        saveBtn=view.findViewById(R.id.savebtn_CalCalories);

        setData();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCalulatedRecord();
            }
        });

        return view;
    }

    public void setData(){
        //getting values from the previous fragment
        CarbsValue = getArguments().getString("CarbsGrams");
        ProteinValue = getArguments().getString("ProteinGrams");
        FatsValue = getArguments().getString("FatGrams");
        TotalCalories = getArguments().getString("CalculatedTotal");
        Date = getArguments().getString("Date");

        //setting the values to the textviews
        CarbTot.setText(CarbsValue);
        ProteinTot.setText(ProteinValue);
        FatsTot.setText(FatsValue);
        TotalCal.setText(TotalCalories);
        Label.setText("Calorie Summary For "+Date);

    }


    public void addCalulatedRecord(){
        DBhelper dbHelper = new DBhelper(getContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TrackCalories.savedCalories.COLUMN_NAME_DATE,Date);
        values.put(TrackCalories.savedCalories.COLUMN_NAME_TOTALCALORIES, TotalCalories);
        values.put(TrackCalories.savedCalories.COLUMN_NAME_COMMENT, comment);

        long newRowId = db.insert(TrackCalories.savedCalories.TABLE_NAME,null, values);

        Toast.makeText(getActivity(), "Record Saved!", Toast.LENGTH_SHORT).show();
    }


}