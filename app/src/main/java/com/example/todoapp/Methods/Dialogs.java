package com.example.todoapp.Methods;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;

public class Dialogs {

    public static String dateVal;
    public static String timeVal;

    private final static Calendar calendar = Calendar.getInstance();

    public static void date (FloatingActionButton btn, final Context context) {


        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(context, android.R.style.Theme_DeviceDefault_Dialog_Alert, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1; //Adding 1 to the month
                        dateVal = day + "/" + month + "/" + year; //Formatting the date

                    }
                }, year, month, dayOfMonth);

                datePickerDialog.show(); //Showing the datePicker dialog
            }
        });

    }

    public static void time (FloatingActionButton btn, final Context context){

        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(context, android.R.style.Theme_DeviceDefault_Dialog_Alert, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String newMinute;
                        if (minute < 10){
                            newMinute = "0" + minute;
                        }
                        else {
                            newMinute = minute + "";
                        }

                        if (hourOfDay >= 12){
                            timeVal = hourOfDay + ":" + newMinute + "PM";

                        }
                        else {
                            timeVal = hourOfDay + ":" + newMinute + "AM";
                        }
                    }
                }, hour, minute, false);
                timePickerDialog.show();
            }
        });

    }

}
