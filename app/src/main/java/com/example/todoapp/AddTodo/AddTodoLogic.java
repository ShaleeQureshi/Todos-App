package com.example.todoapp.AddTodo;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.todoapp.R;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static com.example.todoapp.Methods.FileAccess.*;

/*
Author: Shahrukh Qureshi
Date: May 10, 2020
Description: This class creates the Todos and puts them onto a list starting at the most recent
*/

class AddTodoLogic {

    //Constructor
    AddTodoLogic(Button addButton, FloatingActionButton btnDelete, EditText textValue, final ListView listView, final Context context, final int year, final int month, final int day, final int hour, final int minute) {

        final File fileAll = new File(context.getFilesDir(), "alltodos.txt"); //File path to internal storage
        final File fileTodos = new File(context.getFilesDir(), "todo.txt");

        final String ampm;

        final List<String> list = new ArrayList<>(); //ArrayList for the list of Todos

        ArrayList<String> data = new ArrayList<>(); //ArrayList for previously added data

        //ArrayAdapter returns a view for each collection of data in the list
        final ArrayAdapter<String> listAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_multiple_choice, list);
        final ArrayAdapter<String> newList = new ArrayAdapter<>(context, android.R.layout.simple_list_item_multiple_choice, updatedList);
        //Adding the adapter to the list
        listView.setAdapter(listAdapter);

        //Alert Box
        final AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setMessage("Please add a Todo\n\nFor Example:\n\nWalk the dog\nGo for a run\nCode a Todos App");
        alert.setPositiveButton("OK", null);

        list.add("");

        if (hour >= 12) {
            ampm = "PM";
        } else {
            ampm = "AM";
        }

        //If the file exists it means the user has used the app before so we will get the todos from before and display them
        if (fileTodos.exists()) {
            data = readFile(data, fileTodos);
            assert data != null;
            list.addAll(data);
        }

        final EditText finalTextValue = textValue; //Temp final variable

        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (finalTextValue.getText().length() == 0 || (finalTextValue.getText().toString().replaceAll(" ", "")).equals("")) {
                    alert.show();
                    finalTextValue.setText("");
                } else {

                    String output = finalTextValue.getText().toString() + "     For: " + year + "-" + month + "-" + day + " AT " + hour + ":" + minute + ampm;


                    writeFile(output, fileTodos, true);
                    writeFile(output, fileAll, true);

                    list.add(output);
                    listAdapter.notifyDataSetChanged();

                    finalTextValue.setText("");
                }

            }//onClick Method
        }); //OnClickListener Method

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<String> updatedList = new ArrayList<>();
                ArrayList<String> completedList = new ArrayList<>();


                int items = listView.getCount();

                SparseBooleanArray checkedItemsCheck = listView.getCheckedItemPositions();

                if (checkedItemsCheck.size() == 0) {
                    alert.show();
                } else {

                    SparseBooleanArray checkedItems = listView.getCheckedItemPositions();

                    for (int i = 0; i < items; i++) {
                        if (!checkedItems.get(i) && !list.get(i).equals("")) {
                            updatedList.add(list.get(i));
                        } else {
                            completedList.add(list.get(i) + " - Completed");
                        }

                    }

                    ArrayAdapter<String> newList = new ArrayAdapter<>(context, android.R.layout.simple_list_item_multiple_choice, updatedList);

                    listView.setAdapter(newList);
                    newList.notifyDataSetChanged();

                    writeFileArray(updatedList, fileTodos);
                    writeFileArray(updatedList, fileAll);
                    writeFileArray(completedList, fileAll);
                }

            }
        });



    }//Constructor

    public static String dateFormat(int year, int month, int day) {
        return year + "-" + month + "-" + day;
    }

    public static String timeFormat(int hour, int minute) {
        if (hour >= 12) {
            return hour + ":" + minute + "PM";
        } else {
            return hour + ":" + minute + "AM";
        }
    }

}//class AddTodo
