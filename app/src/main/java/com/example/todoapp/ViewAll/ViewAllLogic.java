package com.example.todoapp.ViewAll;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.todoapp.Methods.FileAccess;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

class ViewAllLogic {

    ViewAllLogic(Button clearBtn, ListView listView, final Context context) {

        final File fileCompleted = new File(context.getFilesDir(), "completedtodos.txt");
        final File fileTodos = new File(context.getFilesDir(), "todo.txt");


        final List<String> list = new ArrayList<>();

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, list);

        ArrayList<String> listTodos = new ArrayList<>();
        ArrayList<String> listCompleted = new ArrayList<>();


        listView.setAdapter(arrayAdapter);

        if (fileTodos.exists()) {
            listTodos = FileAccess.readFile(listTodos, fileTodos);

            assert listTodos != null;
            list.addAll(listTodos);
        }
        if (fileCompleted.exists()) {
            listCompleted = FileAccess.readFile(listCompleted, fileCompleted);

            assert listCompleted != null;
            list.addAll(listCompleted);

        }

        clearBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FileAccess.writeFile("Close", fileCompleted, false);
                FileAccess.writeFile("Close", fileTodos, false);

                arrayAdapter.clear();
                arrayAdapter.notifyDataSetChanged();
                Toast toast = Toast.makeText(context, "List cleared Successfully!", Toast.LENGTH_SHORT);
                toast.show();

            }
        });
    }


}

