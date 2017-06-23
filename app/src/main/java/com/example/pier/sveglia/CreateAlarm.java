package com.example.pier.sveglia;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.util.Date;

import helper.DBManager;

import static com.example.pier.sveglia.MainActivity.db;

/**
 * Created by pier on 16/06/17.
 */

public class CreateAlarm extends AppCompatActivity {
    final static String TAG = "CreateAlarm";

    private TimePicker tp;
    private EditText tvLabel;
    private Button btnSet;

    private String label;
    private int year;
    private int month;
    private int day;
    private int hours;
    private int minutes;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.create_alarm);

        db = new DBManager(this);

        clearTimePicker();

        tvLabel = (EditText) findViewById(R.id.txtLabel);

        btnSet = (Button) findViewById(R.id.btnSet);
        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlarm();
            }
        });
    }

    public void clearTimePicker() {
        tp = (TimePicker) findViewById(R.id.timePicker);

        Date d = new Date();
        tp.setHour(d.getHours());
        tp.setMinute(d.getMinutes());
    }

    public void setAlarm() {
        Toast.makeText(getBaseContext(), "CI ENTRO", Toast.LENGTH_LONG);
        Log.i(TAG, "SONO ENTRATO");
        int h = tp.getHour();
        int m = tp.getMinute();

        hours = h;
        minutes = m;

        Date d = new Date();
        year = d.getYear();
        month = d.getMonth();
        day = d.getDay();

        label = tvLabel.getEditableText().toString();

        if (db.insert(label, year, month, day, hours, minutes))
            Log.i(TAG, "ALLARME CREATO");
        else
            Log.i(TAG, "ERRORE NELLA CREAZIONE");

        finish();
    }
}
