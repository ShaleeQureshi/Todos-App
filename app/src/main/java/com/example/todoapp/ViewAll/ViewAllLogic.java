package com.example.todoapp.ViewAll;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.todoapp.Methods.FileAccess;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.example.todoapp.Methods.FileAccess.writeFile;
import static com.example.todoapp.Methods.FileAccess.writeFileArray;

class ViewAllLogic {

    ViewAllLogic(Button clearBtn, final ListView listView, final Context context) {

        final File fileCompleted = new File(context.getFilesDir(), "completedtodos.txt");
        final File fileTodos = new File(context.getFilesDir(), "todo.txt");


        final List<String> list = new ArrayList<>();

        final ArrayAdapter<String> listAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_multiple_choice, list);

        ArrayList<String> listTodos = new ArrayList<>();
        ArrayList<String> listCompleted = new ArrayList<>();


        listView.setAdapter(listAdapter);

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
                writeFile("Close", fileCompleted, false);
                writeFile("Close", fileTodos, false);

                listAdapter.clear();
                listAdapter.notifyDataSetChanged();
                Toast toast = Toast.makeText(context, "List cleared Successfully!", Toast.LENGTH_SHORT);
                toast.show();

            }
        });

        //The following will occur when the user presses the box beside each list value
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String todo = list.get(position);

                list.remove(position);
                listAdapter.notifyDataSetChanged();
                listView.getCheckedItemPositions().clear(); //Ensuring no other items are checked by clearing all checked positions

                writeFile("Close", fileCompleted, false);
                writeFile("Close", fileTodos, false);

                for (int i = 0; i < list.size(); i++){
                    if (list.get(i).contains(" - Completed")){
                        writeFile(list.get(i), fileCompleted, true);
                    }else {
                        writeFile(list.get(i), fileTodos, true);
                    }
                }

                Toast toast = Toast.makeText(context, todo + "\nHas Successfully been deleted!", Toast.LENGTH_SHORT);
                toast.show();

            }
        });

    }


}

