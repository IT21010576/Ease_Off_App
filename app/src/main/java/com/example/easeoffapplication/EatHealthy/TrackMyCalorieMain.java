package com.example.easeoffapplication.EatHealthy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.easeoffapplication.R;


public class TrackMyCalorieMain extends Fragment {

    Button calcBtn;
    EditText carbGrams,proGrams,fatGrams,date;
    int carbsInCal,proteinsInCal,fatsInCal;
    int totalCalories;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_track_my_calorie_main, container, false);
        //Defining the elements
        carbGrams=view.findViewById(R.id.carbIntake);
        proGrams=view.findViewById(R.id.proIntake);
        fatGrams=view.findViewById(R.id.fatIntake);
        calcBtn=view.findViewById(R.id.calCalorieBtn);
        date=view.findViewById(R.id.tbDay);

        calcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcTotalCalories();
                displayResults();
            }
        });
        return view;
    }

    //method to convert Carbohydrate input in g to Calories
    public int carbToCal(int carbsInG){
        return carbsInG*4;
    }

    //method to convert Protein input in g to Calories
    public int protToCal(int protInG){
        return protInG*4;
    }

    //method to convert Fat input in g to Calories
    public int fatToCal(int fatsInG){
        return fatsInG*9;
    }

    //method to calculate the total calories
    public int getTotalCalories(int carbCal,int protCal,int fatCal){
        return carbCal+protCal+fatCal;
    }

    public void calcTotalCalories(){
        //getting the values entered as Integers
        int carbsInG=Integer.valueOf(carbGrams.getText().toString());
        int proteinsInG=Integer.valueOf(proGrams.getText().toString());
        int fatsInG=Integer.valueOf(fatGrams.getText().toString());

        //converting grams of each type to calories
        carbsInCal=carbToCal(carbsInG);
        proteinsInCal=protToCal(proteinsInG);
        fatsInCal=fatToCal(fatsInG);

        //getting total calories
        totalCalories=getTotalCalories(carbsInCal,proteinsInCal,fatsInCal);
    }

    public void displayResults(){

        DisplaySaveCalcTotal frag = new DisplaySaveCalcTotal();
        String Date=date.getText().toString();

        Bundle args = new Bundle();

        args.putString("Date",Date);
        args.putString("CarbsGrams",String.valueOf(carbsInCal));
        args.putString("ProteinGrams",String.valueOf(proteinsInCal));
        args.putString("FatGrams",String.valueOf(fatsInCal));
        args.putString("CalculatedTotal",String.valueOf(totalCalories));

        frag.setArguments(args);

        //Inflate the fragment
        getFragmentManager().beginTransaction().replace(R.id.fragmentContainer2, frag).commit();
    }



}