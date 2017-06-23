package com.example.pier.sveglia;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.*;

import java.util.Date;

import helper.DBManager;

import static com.example.pier.sveglia.Constants.IMPOSTA_SVEGLIA;
import static com.example.pier.sveglia.MainActivity.db;
import static helper.Constants.*;

/**
 * Created by pier on 16/06/17.
 */

public class CreateAlarm extends AppCompatActivity {
    final static String TAG = "CreateAlarm";

    //private DBManager db;

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

        if (db.insert(/*0, */label, year, month, day, hours, minutes))
            //Toast.makeText(this, "Inserimento effettuato", Toast.LENGTH_LONG);
            Log.i(TAG, "CREATO");
        else
//            Toast.makeText(this, "ERRORE NELL'INSERIMENTO", Toast.LENGTH_LONG);
            Log.i(TAG, "ERRORE");

        finish();
        //Toast.makeText(getBaseContext(), h + ":" + m, Toast.LENGTH_SHORT).show();
        /*Intent setAlarm = new Intent();

        setAlarm.putExtra(C_YEAR, 2017);
        setAlarm.putExtra(C_MONTH, 06);
        setAlarm.putExtra(C_DAY, 17);
        setAlarm.putExtra(C_HOURS, h);
        setAlarm.putExtra(C_MINUTES, m);
        if (tvLabel.getEditableText().toString() != null)
            setAlarm.putExtra(C_LABEL, tvLabel.getEditableText().toString());
        else
            setAlarm.putExtra(C_LABEL, "Nuova Sveglia");*/


        /*Log.i(TAG, setAlarm.toString());

        setResult(Activity.RESULT_OK, setAlarm);

        finish();*/
    }
}
