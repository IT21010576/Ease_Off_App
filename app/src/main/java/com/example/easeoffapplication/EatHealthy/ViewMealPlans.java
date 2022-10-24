package com.example.easeoffapplication.EatHealthy;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.BaseColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.easeoffapplication.R;
import com.example.easeoffapplication.db.DBhelper;
import com.example.easeoffapplication.db.MealPlans;

public class ViewMealPlans extends Fragment {

    EditText input,displayBrkfst,displayLunch,displayDinner;
    Button btn,deleteBtn,editBtn;
    String currentID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_meal_plans, container, false);

        //defining the elements
        input=view.findViewById(R.id.searchTb);
        displayBrkfst=view.findViewById(R.id.tbViewMeal1);
        displayLunch=view.findViewById(R.id.tbViewMeal2);
        displayDinner=view.findViewById(R.id.tbViewMeal3);
        btn=view.findViewById(R.id.viewmpBtn);
        deleteBtn=view.findViewById(R.id.viewPlanDel);
        editBtn=view.findViewById(R.id.viewPlanEdit);

        //set onclick listener to buttons
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                viewMealPlan();
            }
        });

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePlan();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletePlan();
            }
        });

        return view;
    }

    //method that gets the relevant days meal plan and display
    public void viewMealPlan(){
        DBhelper dbHelper = new DBhelper(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        //If user clicks btn without entering a day
        if(input.getText().toString().isEmpty()){
            Toast.makeText(getActivity(), "Please Enter day of the week", Toast.LENGTH_LONG).show();
        }
        else {
            String day = input.getText().toString();

            String[] projection = {
                    BaseColumns._ID,
                    MealPlans.mealPlans.COLUMN_NAME_DAY,
                    MealPlans.mealPlans.COLUMN_NAME_BREAKFAST,
                    MealPlans.mealPlans.COLUMN_NAME_LUNCH,
                    MealPlans.mealPlans.COLUMN_NAME_DINNER,
            };

            // Filter results WHERE day is the day user enters
            String selection = MealPlans.mealPlans.COLUMN_NAME_DAY + " = ?";
            String[] selectionArgs = { day };

            Cursor cursor = db.query(
                    MealPlans.mealPlans.TABLE_NAME,   // The table to query
                    projection,             // The array of columns to return (pass null to get all)
                    selection,              // The columns for the WHERE clause
                    selectionArgs,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    null              // don't sort
            );

            while(cursor.moveToNext()) {
                currentID= cursor.getString(
                        cursor.getColumnIndexOrThrow(BaseColumns._ID));
                String breakfast = cursor.getString(
                        cursor.getColumnIndexOrThrow(MealPlans.mealPlans.COLUMN_NAME_BREAKFAST));
                String lunch = cursor.getString(
                        cursor.getColumnIndexOrThrow(MealPlans.mealPlans.COLUMN_NAME_LUNCH));
                String dinner = cursor.getString(
                        cursor.getColumnIndexOrThrow(MealPlans.mealPlans.COLUMN_NAME_DINNER));

                displayBrkfst.setText(String.valueOf(breakfast));
                displayLunch.setText(String.valueOf(lunch));
                displayDinner.setText(String.valueOf(dinner));

            }
            cursor.close();

        }
    }

    //method to delete displayed Meal Plan
    public void deletePlan(){
        DBhelper dbHelper = new DBhelper(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selection = BaseColumns._ID + " LIKE ?";
        String[] selectionArgs = { currentID };

        long result=db.delete(MealPlans.mealPlans.TABLE_NAME, selection, selectionArgs);
        if(result==-1){
            Toast.makeText(getContext(), "Failed to Delete!", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getContext(), "Meal Plan Deleted!", Toast.LENGTH_LONG).show();
        }
        displayBrkfst.setText("");
        displayLunch.setText("");
        displayDinner.setText("");

    }

    //Mehtod to update displayed Meal Plan
    public void updatePlan(){
        DBhelper dbHelper = new DBhelper(getContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // New values or value
        String newBreakfast=displayBrkfst.getText().toString();
        String newLunch=displayLunch.getText().toString();
        String newDinner=displayDinner.getText().toString();

        ContentValues values = new ContentValues();
        values.put(MealPlans.mealPlans.COLUMN_NAME_BREAKFAST, newBreakfast);
        values.put(MealPlans.mealPlans.COLUMN_NAME_LUNCH, newLunch);
        values.put(MealPlans.mealPlans.COLUMN_NAME_DINNER, newDinner);

        String selection = BaseColumns._ID + " LIKE ?";
        String[] selectionArgs = { currentID };

        long result = db.update(MealPlans.mealPlans.TABLE_NAME, values, selection, selectionArgs);

        if(result==-1){
            Toast.makeText(getContext(), "Failed to Update!", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getContext(), "Meal Plan Updated!", Toast.LENGTH_LONG).show();
        }
    }


}