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

    private Alarm mAlarm;

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

        Date d = new Date();

        /*label = tvLabel.getEditableText().toString();

        year = d.getYear();
        month = d.getMonth();
        day = d.getDay();

        int h = tp.getHour();
        int m = tp.getMinute();

        hours = h;
        minutes = m;

        mAlarm = new Alarm(label, year, month, day, h, m);
*/
        String l = tvLabel.getEditableText().toString();
        if (l.equals(""))
            mAlarm = new Alarm(d.getYear(),
                    d.getMonth(),
                    d.getDay(),
                    tp.getHour(),
                    tp.getMinute());
        else
            mAlarm = new Alarm(l,
                    d.getYear(),
                    d.getMonth(),
                    d.getDay(),
                    tp.getHour(),
                    tp.getMinute());




        if (db.insert(mAlarm))
            Log.i(TAG, "ALLARME CREATO");
        else
            Log.i(TAG, "ERRORE NELLA CREAZIONE");

        finish();
    }
}
