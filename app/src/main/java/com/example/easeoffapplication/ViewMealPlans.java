package com.example.easeoffapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.BaseColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.easeoffapplication.db.DBhelper;
import com.example.easeoffapplication.db.MealPlans;

import java.util.ArrayList;
import java.util.List;


public class ViewMealPlans extends Fragment {
    TextView result;
    Button btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_meal_plans, container, false);
        result=view.findViewById(R.id.tvMealPln);
        btn=view.findViewById(R.id.viewmpBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showData();
            }
        });
        return view;
    }

    public void showData(){
        DBhelper dbHelper = new DBhelper(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                MealPlans.mealPlans.COLUMN_NAME_DAY,
                MealPlans.mealPlans.COLUMN_NAME_BREAKFAST,
                MealPlans.mealPlans.COLUMN_NAME_LUNCH,
                MealPlans.mealPlans.COLUMN_NAME_DINNER,
        };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                MealPlans.mealPlans.COLUMN_NAME_DAY + " DESC";

        Cursor cursor = db.query(
                MealPlans.mealPlans.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        List itemIds = new ArrayList<>();
        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(MealPlans.mealPlans.COLUMN_NAME_LUNCH));
            itemIds.add(itemId);
        }
        String res= String.valueOf((CharSequence) itemIds.toString());
        result.setText(res);
        cursor.close();

    }


}