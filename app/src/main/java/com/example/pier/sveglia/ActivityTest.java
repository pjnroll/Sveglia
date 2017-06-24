package com.example.pier.sveglia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.DatePicker;

import java.util.Date;

public class ActivityTest extends AppCompatActivity {

    private DatePicker cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test);

        cv = (DatePicker) findViewById(R.id.datePicker);

        //cv.setMinDate(new Date().getTime());
    }


}
