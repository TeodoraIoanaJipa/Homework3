package com.example.homework3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.homework3.picker.DatePickerFragment;
import com.example.homework3.picker.TimePickerFragment;

import java.text.DateFormat;
import java.util.Calendar;

public class DateAndTimeActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener,
        DatePickerDialog.OnDateSetListener {

    private String title;

    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private NotificationFragment fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_and_time);

        getSelectedTitle(savedInstanceState);
        openFragment(savedInstanceState);
    }


    private void openFragment(Bundle savedInstanceState) {
        fragment1 = new NotificationFragment(title);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.notification_fragment, fragment1);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    private void getSelectedTitle(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                title = null;
            } else {
                title = extras.getString("todo_title");
            }
        } else {
            title = (String) savedInstanceState.getSerializable("todo_title");
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        NotificationFragment.onDateSet(view, year, month, dayOfMonth);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        NotificationFragment.onTimeSet(view, hourOfDay, minute);
    }
}















