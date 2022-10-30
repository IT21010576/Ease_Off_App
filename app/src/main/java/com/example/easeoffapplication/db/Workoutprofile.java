package com.example.easeoffapplication.db;

import android.provider.BaseColumns;

public class Workoutprofile {

    private int id,age;
    private String uname;
    private double weight,height;

    public Workoutprofile(int id, String uname,int age, double height, double weight ) {
        this.id = id;
        this.uname = uname;
        this.age = age;
        this.height = height;
        this.weight = weight;

    }

    public Workoutprofile( String uname,int age, double height, double weight ) {
        this.uname = uname;
        this.age = age;
        this.height = height;
        this.weight = weight;

    }


    public Workoutprofile(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public static class workoutprofile implements BaseColumns {

        public static final String TABLE_NAME="My_profile";
        public static final String COLUMN_NAME_name="name";
        public static final String COLUMN_NAME_age="age";
        public static final String COLUMN_NAME_height="height";
        public static final String COLUMN_NAME_weight="weight";


    }
}


