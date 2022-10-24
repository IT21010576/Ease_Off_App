package com.example.easeoffapplication.db;

import android.provider.BaseColumns;

public class User {

    private User(){

    }

    public static class userDetails implements BaseColumns {
        public static final String TABLE_NAME="user_details";
        public static final String COLUMN_NAME_USERNAME="username";
        public static final String COLUMN_NAME_PASSWORD="password";
        public static final String COLUMN_NAME_DOB="DOB";
        public static final String COLUMN_NAME_GENDER="gender";
    }
}
