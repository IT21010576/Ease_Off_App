package com.example.easeoffapplication.EatHealthy;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easeoffapplication.R;
import com.example.easeoffapplication.db.DBhelper;
import com.example.easeoffapplication.db.MealPlans;

public class CreateMealPlan extends Fragment {


    EditText day,breakfast,lunch,dinner;
    Button createBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_meal_plan, container, false);
        //EditTexts
        day=view.findViewById(R.id.tbDay);
        breakfast=view.findViewById(R.id.carbIntake);
        lunch=view.findViewById(R.id.proIntake);
        dinner=view.findViewById(R.id.tbDinner);
        //Button
        createBtn=view.findViewById(R.id.createMealBtn);
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Day=day.getText().toString();
                String Breakfast=breakfast.getText().toString();
                String Lunch=lunch.getText().toString();
                String Dinner=dinner.getText().toString();
                addMealPlan(Day,Breakfast,Lunch,Dinner);
            }
        });

        return view;
    }

    public boolean addMealPlan(String Day,String Breakfast,String Lunch,String Dinner){
        DBhelper dbHelper = new DBhelper(getContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MealPlans.mealPlans.COLUMN_NAME_DAY, Day);
        values.put(MealPlans.mealPlans.COLUMN_NAME_BREAKFAST, Breakfast);
        values.put(MealPlans.mealPlans.COLUMN_NAME_LUNCH, Lunch);
        values.put(MealPlans.mealPlans.COLUMN_NAME_DINNER, Dinner);

        long newRowId = db.insert(MealPlans.mealPlans.TABLE_NAME,null, values);
        if(newRowId==-1){
            showToast("Cannot Create Plan");
            return false;
        }
        else {
            showToast("Meal Plan Added!");
            reset();
            return true;
        }

    }

    public void reset(){

        day.setText("");
        breakfast.setText("");
        lunch.setText("");
        dinner.setText("");

    }

    //custom Toast message
    void showToast(String message) {

        Toast toast = new Toast(getContext());

        View view = LayoutInflater.from(getContext()).inflate(R.layout.sucesstoast, null);

        TextView tvMessage = view.findViewById(R.id.tvMessage);
        tvMessage.setText(message);

        toast.setView(view);
        toast.show();

    }

}