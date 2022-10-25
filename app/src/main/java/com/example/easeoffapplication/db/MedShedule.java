package com.example.easeoffapplication.db;

import android.provider.BaseColumns;

public class MedShedule {

    private int id;
    private long date;
    private int mornHour;
    private int mornMin;
    private int noonHour;
    private int noonMin;
    private int nightHour;
    private int nightMin;

    public MedShedule(int id, long date, int mornHour, int mornMin, int noonHour, int noonMin, int nightHour, int nightMin) {
        this.id = id;
        this.date = date;
        this.mornHour = mornHour;
        this.mornMin = mornMin;
        this.noonHour = noonHour;
        this.noonMin = noonMin;
        this.nightHour = nightHour;
        this.nightMin = nightMin;
    }

    public MedShedule(long date, int mornHour, int mornMin, int noonHour, int noonMin, int nightHour, int nightMin) {
        this.date = date;
        this.mornHour = mornHour;
        this.mornMin = mornMin;
        this.noonHour = noonHour;
        this.noonMin = noonMin;
        this.nightHour = nightHour;
        this.nightMin = nightMin;
    }

    public MedShedule(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public int getMornHour() {
        return mornHour;
    }

    public void setMornHour(int mornHour) {
        this.mornHour = mornHour;
    }

    public int getMornMin() {
        return mornMin;
    }

    public void setMornMin(int mornMin) {
        this.mornMin = mornMin;
    }

    public int getNoonHour() {
        return noonHour;
    }

    public void setNoonHour(int noonHour) {
        this.noonHour = noonHour;
    }

    public int getNoonMin() {
        return noonMin;
    }

    public void setNoonMin(int noonMin) {
        this.noonMin = noonMin;
    }

    public int getNightHour() {
        return nightHour;
    }

    public void setNightHour(int nightHour) {
        this.nightHour = nightHour;
    }

    public int getNightMin() {
        return nightMin;
    }

    public void setNightMin(int nightMin) {
        this.nightMin = nightMin;
    }

    public static class medSchedules implements BaseColumns {
        public static final String TABLE_NAME="medSchedules";
        public static final String COLUMN_NAME_DATE="date";
        public static final String COLUMN_NAME_MORNHOUR="morn_hour";
        public static final String COLUMN_NAME_MORNMIN="morn_min";
        public static final String COLUMN_NAME_NOONHOUR="noon_hour";
        public static final String COLUMN_NAME_NOONMIN="noon_min";
        public static final String COLUMN_NAME_NIGHTHOUR="night_hour";
        public static final String COLUMN_NAME_NIGHTMIN="night_min";
    }
}
