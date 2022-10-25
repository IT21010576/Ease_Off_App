package com.example.easeoffapplication.db;

import android.provider.BaseColumns;


public class MealPlans {

    private MealPlans(){

    }

    public static class mealPlans implements BaseColumns{
        public static final String TABLE_NAME="meal_plans";
        public static final String COLUMN_NAME_DAY="day";
        public static final String COLUMN_NAME_BREAKFAST="breakfast";
        public static final String COLUMN_NAME_LUNCH="lunch";
        public static final String COLUMN_NAME_DINNER="dinner";


    }

}
