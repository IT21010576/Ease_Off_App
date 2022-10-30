package com.example.easeoffapplication.db;

import android.content.Intent;
import android.provider.BaseColumns;

public class ThoughtJournals {
    private ThoughtJournals(){

    }

    public static class thoughtJournals implements BaseColumns {
        public static final String TABLE_NAME="thought_journals";
        public static final String COLUMN_NAME_TJ_ID_ = "id";
        //public static final String COLUMN_NAME_TJ_USER="user";
        public static final String COLUMN_NAME_TJ_DATE="date";
        public static final String COLUMN_NAME_TJ_RATE="rate";
        public static final String COLUMN_NAME_TJ_NOTE="note";
    }
}
