package com.example.pier.sveglia;

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

import java.util.*;

import helper.DBManager;

import static helper.Constants.*;
import helper.*;


public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";

    public static DBManager db;

    private ListView lwList;
    private Button btnNewAlarm;
    private Button btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db = new DBManager(this);

        btnNewAlarm = (Button) findViewById(R.id.btnAdd);
        btnNewAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), CreateAlarm.class));
            }
        });

        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });

    }

    private void delete() {
        Log.i(TAG, "ESEGUO QUERY CANCELLAZIONE");
        boolean exists = false;
        String[] lista = getApplicationContext().databaseList();
        for (int i = 0; i < lista.length && exists == false; i++) {
            Log.i("LISTADB", lista.toString());
            if (lista[i].equals(DB_NAME))
                exists = true;
        }
        if (exists) {
            db.doQuery("DELETE FROM " + TABLE_NAME);
            getBaseContext().deleteDatabase(DB_NAME);
            updateListView();
        }
    }

    private void updateListView() {
        lwList = (ListView) findViewById(R.id.lwList);
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

        if (adapter != null)
            lwList.setAdapter(adapter);
        else {
            lwList.setAdapter(null);
        }
    }

    protected void onResume() {
        //Non so ancora se ha senso
        super.onResume();
        updateListView();
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
