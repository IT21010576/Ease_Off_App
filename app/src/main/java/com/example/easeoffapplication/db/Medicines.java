package com.example.easeoffapplication.db;

import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class Medicines {

    private int id;
    private String medName, pharName, medDes;
    private double medPrice;

    public Medicines(int id, String medName, String pharName, String medDes, double medPrice) {
        this.id = id;
        this.medName = medName;
        this.pharName = pharName;
        this.medDes = medDes;
        this.medPrice = medPrice;
    }

    public Medicines(String medName, String pharName, String medDes, double medPrice) {
        this.medName = medName;
        this.pharName = pharName;
        this.medDes = medDes;
        this.medPrice = medPrice;
    }

    public Medicines(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public String getPharName() {
        return pharName;
    }

    public void setPharName(String pharName) {
        this.pharName = pharName;
    }

    public String getMedDes() {
        return medDes;
    }

    public void setMedDes(String medDes) {
        this.medDes = medDes;
    }

    public double getMedPrice() {
        return medPrice;
    }

    public void setMedPrice(double medPrice) {
        this.medPrice = medPrice;
    }

    public static class medicines implements BaseColumns {
        public static final String TABLE_NAME="medicines";
        public static final String COLUMN_NAME_MEDNAME="medName";
        public static final String COLUMN_NAME_PHARMACY="pharmacy";
        public static final String COLUMN_NAME_DESCRIPTION="description";
        public static final String COLUMN_NAME_PRICE="price";
    }

}
