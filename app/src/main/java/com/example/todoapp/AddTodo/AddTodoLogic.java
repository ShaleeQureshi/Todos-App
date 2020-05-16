package com.example.todoapp.AddTodo;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.example.todoapp.Methods.Dialogs;
import com.example.todoapp.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;

import static com.example.todoapp.Methods.FileAccess.readFile;
import static com.example.todoapp.Methods.FileAccess.writeFile;
import static com.example.todoapp.Methods.FileAccess.writeFileArray;

/*
Author: Shahrukh Qureshi
Date: May 10, 2020
Description: This class creates the Todos and puts them onto a list starting at the most recent
*/


class AddTodoLogic {

    private static String date;
    private static String time;

    //Constructor
    AddTodoLogic(Button addButton, FloatingActionButton btnDate, FloatingActionButton btnTime, final EditText textValue, final ListView listView, final Context context) {

        final File fileCompleted = new File(context.getFilesDir(), "completedtodos.txt");
        final File fileTodos = new File(context.getFilesDir(), "todo.txt");

        final ArrayList<String> list = new ArrayList<>(); //ArrayList for the list of Todos

        ArrayList<String> data = new ArrayList<>(); //ArrayList for previously added data

        //ArrayAdapter returns a view for each collection of data in the list
        final ArrayAdapter<String> listAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_multiple_choice, list);
        //Adding the adapter to the list
        listView.setAdapter(listAdapter);

        //If the file exists it means the user has used the app before so we will get the todos from before and display them
        if (fileTodos.exists()) {
            data = readFile(data, fileTodos); //Reading from the todos file
            assert data != null; //Ensuring the data is not null
            list.addAll(data); //Adding all of the previous todos to the list
        }


        Dialogs.date(btnDate, context);
        Dialogs.time(btnTime, context);


        //When the user presses the addButton the following will occur
        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //Checking to see if the user entered a valid todo
                if (textValue.getText().length() == 0 || (textValue.getText().toString().replaceAll(" ", "")).equals("")) {

                    //Alert Box
                    AlertDialog.Builder alert = new AlertDialog.Builder(context);
                    alert.setMessage("Please add a Todo\n\nFor Example:\n\nWalk the dog\nGo for a run\nCode a Todos App");
                    alert.setPositiveButton("OK", null);
                    alert.show(); //If they did not then this alert will show

                    textValue.setText(date); //Resetting their entry

                } else {

                    String output; //The output to the files/user
                    String fileOutput;

                    if (Dialogs.dateVal == null && Dialogs.timeVal == null) {
                        output = textValue.getText().toString();
                        fileOutput = textValue.getText().toString();
                    } else if (Dialogs.dateVal == null && Dialogs.timeVal != null) {
                        output = textValue.getText().toString() + "\nTo be completed by: " + Dialogs.timeVal;
                        fileOutput = textValue.getText().toString() + "To be completed by: " + Dialogs.timeVal;
                    } else if (Dialogs.dateVal != null && Dialogs.timeVal == null) {
                        output = textValue.getText().toString() + "\nTo be completed by: " + Dialogs.dateVal;
                        fileOutput = textValue.getText().toString() + "To be completed by: " + Dialogs.dateVal;

                    } else {
                        output = textValue.getText().toString() + "\nTo be completed by: " + Dialogs.dateVal + ", " + Dialogs.timeVal;
                        fileOutput = textValue.getText().toString() + "To be completed by: " + Dialogs.dateVal + ", " + Dialogs.timeVal;

                    }

                    writeFile(fileOutput, fileTodos, true); //Writing to the todos file

                    list.add(output); //Adding the todo to the list which will display it
                    listAdapter.notifyDataSetChanged(); //Notifying the list adapter that a change has been made

                    textValue.setText(""); //Resetting the text field to nothing so the user can enter a new one
                }

            }//onClick Method
        }); //OnClickListener Method


        //The following will occur when the user presses the box beside each list value
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String todo = list.get(position);

                list.remove(position); //Removing it
                listAdapter.notifyDataSetChanged(); //Notifying the list adapter that a change has been made
                listView.getCheckedItemPositions().clear(); //Ensuring no other items are checked by clearing all checked positions
                Toast toast = Toast.makeText(context, todo + "\nMarked as Completed!", Toast.LENGTH_SHORT);

                writeFile(todo + " - Completed", fileCompleted, true);
                writeFileArray(list, fileTodos, false); //Writing to the todos file with the updated list

                toast.show();
            }
        });


    }//Constructor

}//class AddTodo
