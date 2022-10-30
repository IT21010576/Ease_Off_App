package com.example.easeoffapplication.db;

import android.provider.BaseColumns;

public class Meditation {
    private Meditation(){

    }

    public static class meditation implements BaseColumns {
        public static final String TABLE_NAME="meditation_time";
        public static final String COLUMN_NAME_M_ID_ = "id";
        //public static final String COLUMN_NAME_TJ_USER="user";
        public static final String COLUMN_NAME_M_DATE="date";
        public static final String COLUMN_NAME_M_DUR="duration";
    }
}
