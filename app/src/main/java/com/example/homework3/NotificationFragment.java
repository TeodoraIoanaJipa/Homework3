package com.example.homework3;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.homework3.picker.DatePickerFragment;
import com.example.homework3.picker.TimePickerFragment;

import java.text.DateFormat;
import java.util.Calendar;


public class NotificationFragment extends Fragment {

    private static TextView timeTextView;
    private static TextView dateTextView;
    private Button timePickerButton;
    private Button datePickerButton;
    private Button notificationButton;
    private String title;
    private static Calendar timeCalendar = Calendar.getInstance();
    public static final int REQUEST_CODE = 101;

    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;
    private Fragment fragment1;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.date_and_time_fragment, container, false);

    }

    public NotificationFragment(String title) {
        this.title = title;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        timeTextView = ((DateAndTimeActivity)getActivity()).getTimeTextView();
//        dateTextView = ((DateAndTimeActivity)getActivity()).getDateTextView();
        timeTextView = getView().findViewById(R.id.time_textview);
        dateTextView = getView().findViewById(R.id.date_text_view);

        initTimePickerButton();
        initDatePickerButton();
        initNotificationButton();
    }


    public static void updateTimeTextView(Calendar c){
        String timeText = "Selected time: "+ DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        System.out.println(timeText);
        timeTextView.setText(timeText);
    }

    public static void updateDateTextView(Calendar c){
        String text = "Selected date: "+ DateFormat.getDateInstance().format(c.getTime());
        dateTextView.setText(text);
    }

//    @Override
    public static void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        timeCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        timeCalendar.set(Calendar.MINUTE, minute);
        timeCalendar.set(Calendar.SECOND, 0);

        updateTimeTextView(calendar);
    }

//    @Override
    public static void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        timeCalendar.set(Calendar.YEAR, year);
        timeCalendar.set(Calendar.MONTH, month);
        timeCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        updateDateTextView(calendar);
    }

    private void initDatePickerButton() {
        datePickerButton = getView().findViewById(R.id.date_picker_button);
        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                if (getFragmentManager() != null) {
                    datePicker.show(getFragmentManager(), "date picker");
                }
            }
        });
    }

    private void initTimePickerButton() {
        timePickerButton = getView().findViewById(R.id.time_picker_button);

        timePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                if (getFragmentManager() != null) {
                    timePicker.show(getFragmentManager(), "time picker");
                }
            }
        });
    }

    private void initNotificationButton(){
        notificationButton = getView().findViewById(R.id.notification_button);
        notificationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOnChannel(title,"Todo", timeCalendar);
            }
        });
    }

    public void sendOnChannel(String title, String message, Calendar calendar) {
        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getContext(), AlarmReceiver.class);
        intent.putExtra("title",title);
        intent.putExtra("message",message);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        Toast.makeText(getContext(), "Alarm successfully created.", Toast.LENGTH_SHORT).show();
        closeFragment();
    }

    private void closeFragment() {
        fragmentManager = getFragmentManager();
        fragment1 = fragmentManager.findFragmentById(R.id.notification_fragment);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fragment1);
        fragmentTransaction.commit();
//        getActivity().getSupportFragmentManager().popBackStack();
    }


}
