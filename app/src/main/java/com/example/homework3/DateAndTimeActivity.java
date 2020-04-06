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

    private TextView timeTextView;
    private TextView dateTextView;
    private Button timePickerButton;
    private Button datePickerButton;
    private Button notificationButton;
    private String title;
    private Calendar timeCalendar = Calendar.getInstance();
    public static final int REQUEST_CODE = 101;

    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private NotificationFragment fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_and_time);

//        openFragment();

        timeTextView = findViewById(R.id.time_text_view);
        dateTextView = findViewById(R.id.date_text_view);

        initTimePickerButton();
        initDatePickerButton();
        getSelectedTitle(savedInstanceState);

        initNotificationButton();

    }

    private void openFragment() {
        fragment1 = new NotificationFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.notification_fragment, fragment1);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void getSelectedTitle(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                title = null;
            } else {
                title = extras.getString("todo_title");
            }
        } else {
            title = (String) savedInstanceState.getSerializable("todo_title");
        }
    }

    public void updateTimeTextView(Calendar c){
        String timeText = "Selected time: "+ DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        timeTextView.setText(timeText);
    }

    public void updateDateTextView(Calendar c){
        String text = "Selected date: "+ DateFormat.getDateInstance().format(c.getTime());
        dateTextView.setText(text);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        this.timeCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        this.timeCalendar.set(Calendar.MINUTE, minute);
        this.timeCalendar.set(Calendar.SECOND, 0);

        updateTimeTextView(calendar);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        this.timeCalendar.set(Calendar.YEAR, year);
        this.timeCalendar.set(Calendar.MONTH, month);
        this.timeCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        updateDateTextView(calendar);
    }

    private void initDatePickerButton() {
        datePickerButton = findViewById(R.id.date_picker_button);
        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                if (getFragmentManager() != null) {
                    datePicker.show(getSupportFragmentManager(), "date picker");
                }
            }
        });
    }

    private void initTimePickerButton() {
        timePickerButton = findViewById(R.id.time_picker_button);

        timePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                if (getFragmentManager() != null) {
                    timePicker.show(getSupportFragmentManager(), "time picker");
                }
            }
        });
    }

    private void initNotificationButton(){
        notificationButton = findViewById(R.id.notification_button);
        notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOnChannel(title,"Todo", timeCalendar);
            }
        });
    }

    public void sendOnChannel(String title, String message, Calendar calendar) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlarmReceiver.class);
        intent.putExtra("title",title);
        intent.putExtra("message",message);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        Toast.makeText(DateAndTimeActivity.this, "Alarm successfully created.", Toast.LENGTH_SHORT).show();
//        closeFragment();
    }

    private void closeFragment() {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fragment1);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}















