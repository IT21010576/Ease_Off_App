package com.example.easeoffapplication.EatHealthy;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.BaseColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.easeoffapplication.EatHealthy.ViewCaloryAdaptor;
import com.example.easeoffapplication.R;
import com.example.easeoffapplication.db.DBhelper;
import com.example.easeoffapplication.db.TrackCalories;

import java.util.ArrayList;


public class PreviousCalorieIntakes extends Fragment {

    RecyclerView recyclerView;
    ViewCaloryAdaptor customAdaptor;
    ArrayList<String> date,comment;
    ArrayList<Double> totalCal;
    ArrayList<Long> id;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_previous_calorie_intakes, container, false);
        recyclerView=view.findViewById(R.id.recyView);

        date=new ArrayList<>();
        totalCal=new ArrayList<>();
        comment=new ArrayList<>();
        id=new ArrayList<>();

        displayData();
        customAdaptor=new ViewCaloryAdaptor(getActivity(),getContext(),date,totalCal,comment,id);
        recyclerView.setAdapter(customAdaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    public Cursor readAllRecords(){
        String query="SELECT * FROM "+ TrackCalories.savedCalories.TABLE_NAME;
        DBhelper dbHelper = new DBhelper(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor=null;

        cursor=db.rawQuery(query,null);

        return cursor;
    }

    public void displayData(){
        Cursor cursor=readAllRecords();
        if(cursor.getCount()==0){
            Toast.makeText(getActivity(),"No data",Toast.LENGTH_LONG).show();
        }else{
            while(cursor.moveToNext()){
                date.add(cursor.getString(
                        cursor.getColumnIndexOrThrow(TrackCalories.savedCalories.COLUMN_NAME_DATE)));
                totalCal.add(cursor.getDouble(
                        cursor.getColumnIndexOrThrow(TrackCalories.savedCalories.COLUMN_NAME_TOTALCALORIES)));
                comment.add(cursor.getString(
                        cursor.getColumnIndexOrThrow(TrackCalories.savedCalories.COLUMN_NAME_COMMENT)));
                id.add(cursor.getLong(
                        cursor.getColumnIndexOrThrow(BaseColumns._ID)));
            }
        }
    }
}