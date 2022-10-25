package com.example.easeoffapplication.db;

import android.provider.BaseColumns;

public class MedicineLists {

    private int id;
    private String listName;
    private String medName1, medName2, medName3, medName4, medName5;
    private double total;

    public MedicineLists(int id, String listName, String medName1, String medName2, String medName3, String medName4, String medName5, double total) {
        this.id = id;
        this.listName = listName;
        this.medName1 = medName1;
        this.medName2 = medName2;
        this.medName3 = medName3;
        this.medName4 = medName4;
        this.medName5 = medName5;
        this.total = total;
    }

    public MedicineLists(String listName, String medName1, String medName2, String medName3, String medName4, String medName5, double total) {
        this.listName = listName;
        this.medName1 = medName1;
        this.medName2 = medName2;
        this.medName3 = medName3;
        this.medName4 = medName4;
        this.medName5 = medName5;
        this.total = total;
    }

    public MedicineLists(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getMedName1() {
        return medName1;
    }

    public void setMedName1(String medName1) {
        this.medName1 = medName1;
    }

    public String getMedName2() {
        return medName2;
    }

    public void setMedName2(String medName2) {
        this.medName2 = medName2;
    }

    public String getMedName3() {
        return medName3;
    }

    public void setMedName3(String medName3) {
        this.medName3 = medName3;
    }

    public String getMedName4() {
        return medName4;
    }

    public void setMedName4(String medName4) {
        this.medName4 = medName4;
    }

    public String getMedName5() {
        return medName5;
    }

    public void setMedName5(String medName5) {
        this.medName5 = medName5;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public static class medicineLists implements BaseColumns {
        public static final String TABLE_NAME="medicinesLists";
        public static final String COLUMN_NAME_LISTNAME="listName";
        public static final String COLUMN_NAME_MEDNAME1="medName1";
        public static final String COLUMN_NAME_MEDNAME2="medName2";
        public static final String COLUMN_NAME_MEDNAME3="medName3";
        public static final String COLUMN_NAME_MEDNAME4="medName4";
        public static final String COLUMN_NAME_MEDNAME5="medName5";
        public static final String COLUMN_NAME_TOTAL="total";
    }
}
