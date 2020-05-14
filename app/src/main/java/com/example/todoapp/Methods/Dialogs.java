package com.example.todoapp.Methods;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.res.Resources;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;

public class Dialogs {

    private static final Calendar calendar = Calendar.getInstance();

    public static void dateDialog(Context context, int year, int month, int dayOfMonth) {

         year = calendar.get(Calendar.YEAR);
         month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        final int finalYear = year;
        final int finalMonth = month;
        final int finalDayOfMonth = dayOfMonth;
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year2, int month2, int dayOfMonth2) {
                year2 = finalYear;
                month2 = finalMonth;
                dayOfMonth2 = finalDayOfMonth;
            }
        }, finalYear, finalMonth, finalDayOfMonth);
        datePickerDialog.show();
    }

    public static void timeDialog(Context context, int hour, int minute) {

        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(context, (new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            }
        }), hour, minute, false);

        timePickerDialog.show();
    }

}


