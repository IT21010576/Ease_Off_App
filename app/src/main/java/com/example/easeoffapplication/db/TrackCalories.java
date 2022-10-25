package com.example.easeoffapplication.db;

import android.provider.BaseColumns;

public class TrackCalories {

    private TrackCalories(){

    }

    public static class savedCalories implements BaseColumns{
        public static final String TABLE_NAME="saved_calories";
        public static final String COLUMN_NAME_DATE="date";
        public static final String COLUMN_NAME_TOTALCALORIES="totalcalories";
        public static final String COLUMN_NAME_COMMENT="comment";
    }
}
