package com.example.easeoffapplication.db;

import android.provider.BaseColumns;

public class workoutplan {

        private int id;
        private String setgoal_name;
        private int squat,bench_press,leg_press,overhead_press;
        private double calorie,workouttime;

    public workoutplan(int id, String setgoal_name, int squat, int bench_press, int leg_press, int overhead_press, double calorie, double workouttime) {
        this.id = id;
        this.setgoal_name = setgoal_name;
        this.squat = squat;
        this.bench_press = bench_press;
        this.leg_press = leg_press;
        this.overhead_press = overhead_press;
        this.calorie = calorie;
        this.workouttime = workouttime;
    }

    public workoutplan(String setgoal_name, int squat, int bench_press, int leg_press, int overhead_press, double calorie, double workouttime) {
        this.setgoal_name = setgoal_name;
        this.squat = squat;
        this.bench_press = bench_press;
        this.leg_press = leg_press;
        this.overhead_press = overhead_press;
        this.calorie = calorie;
        this.workouttime = workouttime;
    }

    public workoutplan(){

        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSetgoal_name() {
        return setgoal_name;
    }

    public void setSetgoal_name(String setgoal_name) {
        this.setgoal_name = setgoal_name;
    }

    public int getSquat() {
        return squat;
    }

    public void setSquat(int squat) {
        this.squat = squat;
    }

    public int getBench_press() {
        return bench_press;
    }

    public void setBench_press(int bench_press) {
        this.bench_press = bench_press;
    }

    public int getLeg_press() {
        return leg_press;
    }

    public void setLeg_press(int leg_press) {
        this.leg_press = leg_press;
    }

    public int getOverhead_press() {
        return overhead_press;
    }

    public void setOverhead_press(int overhead_press) {
        this.overhead_press = overhead_press;
    }

    public double getCalorie() {
        return calorie;
    }

    public void setCalorie(double calorie) {
        this.calorie = calorie;
    }

    public double getWorkouttime() {
        return workouttime;
    }

    public void setWorkouttime(double workouttime) {
        this.workouttime = workouttime;
    }

    public static class Workoutplan implements BaseColumns {

            public static final String TABLE_NAME="Daily_goal";
            public static final String COLUMN_NAME_name="name";
            public static final String COLUMN_NAME_squat="squat";
            public static final String COLUMN_NAME_bench_press="bench_press";
            public static final String COLUMN_NAME_leg_press="leg_press";
            public static final String COLUMN_NAME_overhead_press="overhead_press";
            public static final String COLUMN_NAME_calorie="calorie";
            public static final String COLUMN_NAME_workouttime="workout_time";

        }
    }


