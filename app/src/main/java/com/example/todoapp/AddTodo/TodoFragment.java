package com.example.todoapp.AddTodo;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;

import com.example.todoapp.Methods.Dialogs;
import com.example.todoapp.R;

import java.sql.Time;
import java.util.Calendar;
import static com.example.todoapp.Methods.Dialogs.*;


public class TodoFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.todo, container, false);

        Button btnAdd = view.findViewById(R.id.addBtn);
        FloatingActionButton btnDate = view.findViewById(R.id.dateBtn);
        FloatingActionButton btnDelete = view.findViewById(R.id.deleteBtn);
        EditText editText = view.findViewById(R.id.editText);
        ListView listView = view.findViewById(R.id.listView);

        final Calendar calendar = Calendar.getInstance();

        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
        final int minute = calendar.get(Calendar.MINUTE);

        final AlertDialog.Builder alert = new AlertDialog.Builder(getContext());
        alert.setPositiveButton("Time", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Dialogs.timeDialog(getContext(), hour, minute);
            }
        });
        alert.setNegativeButton("Date", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Dialogs.dateDialog(getContext(), year, month, day);
            }
        });
        alert.setNeutralButton("Cancel", null);



        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.show();
            }

        });




        AddTodoLogic addtodo = new AddTodoLogic(btnAdd, btnDelete, editText, listView, getContext(), year, month, day, hour, minute);

        return view;
    }

}
