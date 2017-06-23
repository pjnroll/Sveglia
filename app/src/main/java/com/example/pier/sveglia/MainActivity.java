package com.example.pier.sveglia;

import android.app.Activity;

import android.content.Intent;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import helper.DBManager;

import static com.example.pier.sveglia.Constants.*;
import static helper.Constants.*;
import helper.*;


public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";

    //private int id = 33;

    public static DBManager db;
    private String mionome = "PIER";

    private ListView lwList;
    private Button newAlarm;

    /*private String[] cognome = {"Casieri", "Farfalletta", "Laviano", "Pisciotta"};
    private String[] nome = {"Matteo", "Stefano", "Pier Luigi", "Gabriele"};*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DBManager(this);
        //db.doQuery("ALTER TABLE " + TABLE_NAME + " AUTOINCREMENT = 0");
        //getBaseContext().deleteDatabase(DB_NAME);
        //db.doQuery("UPDATE SQLITE_SEQUENCE SET SEQ=0 WHERE NAME=" + C_ID);

        newAlarm = (Button) findViewById(R.id.btnAdd);
        newAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), CreateAlarm.class));
            }
        });

        //getBaseContext().deleteDatabase(DB_NAME);

        CustomAdapter adapter = null;
        Cursor crs = db.query();
        Map<Integer, ArrayList<String>> data = new HashMap<>();
        if (crs.getCount() != 0) {
            Log.i(TAG, "**********COUNT = " + crs.getCount() + "**********");
            while (crs.moveToNext()) {
                ArrayList<String> values = new ArrayList<>();
                values.add(crs.getString(crs.getColumnIndex(C_LABEL)));
                values.add(String.valueOf(crs.getInt(crs.getColumnIndex(C_YEAR))));
                values.add(String.valueOf(crs.getInt(crs.getColumnIndex(C_MONTH))));
                values.add(String.valueOf(crs.getInt(crs.getColumnIndex(C_DAY))));
                values.add(String.valueOf(crs.getInt(crs.getColumnIndex(C_HOURS))));
                values.add(String.valueOf(crs.getInt(crs.getColumnIndex(C_MINUTES))));

                data.put(crs.getInt(crs.getColumnIndex(C_ID)),
                        values);
            }
            adapter = new CustomAdapter(getBaseContext(), data);

        } else Log.i(TAG, "DATABASE VUOTO");



        /*CustomAdapter adapter = new CustomAdapter(getBaseContext(), cognome, nome);*/

        lwList = (ListView) findViewById(R.id.lwList);
        if (adapter != null)
            lwList.setAdapter(adapter);
    }

    protected void onResume() {
        super.onResume();
        CustomAdapter adapter = null;
        Cursor crs = db.query();
        Map<Integer, ArrayList<String>> data = new TreeMap<>();
        if (crs.getCount() != 0) {
            Log.i(TAG, "**********COUNT = " + crs.getCount() + "**********");
            while (crs.moveToNext()) {
                ArrayList<String> values = new ArrayList<>();
                values.add(crs.getString(crs.getColumnIndex(C_LABEL)));
                values.add(String.valueOf(crs.getInt(crs.getColumnIndex(C_YEAR))));
                values.add(String.valueOf(crs.getInt(crs.getColumnIndex(C_MONTH))));
                values.add(String.valueOf(crs.getInt(crs.getColumnIndex(C_DAY))));
                values.add(String.valueOf(crs.getInt(crs.getColumnIndex(C_HOURS))));
                values.add(String.valueOf(crs.getInt(crs.getColumnIndex(C_MINUTES))));
                values.add(String.valueOf(crs.getInt(crs.getColumnIndex(C_ID))));

                data.put(crs.getInt(crs.getColumnIndex(C_ID)),
                        values);
            }
            adapter = new CustomAdapter(getBaseContext(), data);

        } else Log.i(TAG, "DATABASE VUOTO");



        /*CustomAdapter adapter = new CustomAdapter(getBaseContext(), cognome, nome);*/

        lwList = (ListView) findViewById(R.id.lwList);
        if (adapter != null)
            lwList.setAdapter(adapter);
    }

    /*protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ALARM_DETAILS) {
            if (resultCode == Activity.RESULT_OK) {
                String label = data.getStringExtra(C_LABEL);
                int year = data.getIntExtra(C_YEAR, -1);
                int month = data.getIntExtra(C_MONTH, -1);
                int day = data.getIntExtra(C_DAY, 1);
                int hours = data.getIntExtra(C_HOURS, -1);
                int minutes = data.getIntExtra(C_MINUTES, -1);

                Toast.makeText(getBaseContext(),
                        day + "/" + month + "/" + year
                        + " " + hours + ":" + minutes,
                        Toast.LENGTH_LONG).show();

                *//*save(id, label, year, month, day, hours, minutes);
                id++;
*//*

                //CustomAdapter adapter = new CustomAdapter(getBaseContext(), cognome, nome);

                lwList = (ListView) findViewById(R.id.lwList);
                //lwList.setAdapter(adapter);
            }
        }
    }*/

    public void save(int id, String label, int y, int mo, int d, int h, int mi) {
        //db.insert(id, label, y, mo, d, h, mi);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
