package com.example.pier.sveglia;

import android.provider.AlarmClock;

/**
 * Created by pier on 24/06/17.
 */

public class Alarm {
    private String label;
    private int year;
    private int month;
    private int day;
    private int hours;
    private int minutes;

    private static int count = 0;

    public Alarm(String label) {
        this.label = label;
    }

    public Alarm(int year, int month, int day, int hours, int minutes) {
        this("Sveglia " + (++count), year, month, day, hours, minutes);
    }

    public Alarm(String label, int year, int month, int day, int hours, int minutes) {
        this.label = label;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hours = hours;
        this.minutes = minutes;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public static void resetCount() {
        Alarm.count = 0;
    }

    public String toString() {
        String s = null;

        s = "[" + getLabel() + " - " + getDay() + "/" + getMonth() + "/" + getYear() +
                " " + getHours() + ":" + getMinutes();

        return s;
    }





















}
