package com.example.todoapp.AddTodo;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import static com.example.todoapp.Methods.FileAccess.*;

/*
Author: Shahrukh Qureshi
Date: May 10, 2020
Description: This class creates the Todos and puts them onto a list starting at the most recent
*/

class AddTodoLogic {

    //Constructor
    AddTodoLogic(Button addButton, FloatingActionButton btnDelete, final EditText textValue, final ListView listView, final Context context, final int year, final int month, final int day, final int hour, final int minute) {

        final File fileAll = new File(context.getFilesDir(), "alltodos.txt"); //File path to internal storage
        final File fileTodos = new File(context.getFilesDir(), "todo.txt");

        final ArrayList<String> list = new ArrayList<>(); //ArrayList for the list of Todos

        ArrayList<String> data = new ArrayList<>(); //ArrayList for previously added data

        //ArrayAdapter returns a view for each collection of data in the list
        final ArrayAdapter<String> listAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_multiple_choice, list);
        //Adding the adapter to the list
        listView.setAdapter(listAdapter);

        //Alert Box
        final AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setMessage("Please add a Todo\n\nFor Example:\n\nWalk the dog\nGo for a run\nCode a Todos App");
        alert.setPositiveButton("OK", null);

        //If the file exists it means the user has used the app before so we will get the todos from before and display them
        if (fileTodos.exists()) {
            data = readFile(data, fileTodos);
            assert data != null;
            list.addAll(data);
        }



        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (textValue.getText().length() == 0 || (textValue.getText().toString().replaceAll(" ", "")).equals("")) {
                    alert.show();
                    textValue.setText("");
                } else {

                    String output = textValue.getText().toString() + "     For: " + year + "-" + month + "-" + day + " AT " + hour + ":" + minute;


                    writeFile(output, fileTodos, true);
                    writeFile(output, fileAll, true);

                    list.add(output);
                    listAdapter.notifyDataSetChanged();

                    textValue.setText("");
                }

            }//onClick Method
        }); //OnClickListener Method

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SparseBooleanArray checkedItemsCheck = listView.getCheckedItemPositions();

                if (checkedItemsCheck.size() == 0) {
                    alert.show();
                } else {

                    SparseBooleanArray checkedItems = listView.getCheckedItemPositions();

                    for (int i = 0; i < listView.getCount(); i++) {
                        if (checkedItems.get(i)) {
                            writeFile(list.get(i) + " - Completed", fileAll, true);
                            list.remove(list.get(i));
                        }


                    }

                    listAdapter.notifyDataSetChanged();
                    Toast toast = Toast.makeText(context, "Item(s) deleted successfully!", Toast.LENGTH_SHORT);
                    toast.show();

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
