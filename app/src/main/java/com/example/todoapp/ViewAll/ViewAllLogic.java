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


        final File fileAll = new File(context.getFilesDir(), "alltodos.txt");
        final File fileTodos = new File(context.getFilesDir(), "todo.txt");
        final File fileRemove = new File(context.getFilesDir(), "removetodos.txt");


        final List<String> list = new ArrayList<>();

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, list);

        ArrayList<String> listAll = new ArrayList<>();

        listView.setAdapter(arrayAdapter);

        if (fileAll.exists()) {
            listAll = FileAccess.readFile(listAll, fileAll);

            if (listAll != null) {
                list.addAll(listAll);
            }
        }

        clearBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FileAccess.writeFile("Close", fileAll, false);
                FileAccess.writeFile("Close", fileTodos, false);
                FileAccess.writeFile("Close", fileRemove, false);

                arrayAdapter.clear();
                arrayAdapter.notifyDataSetChanged();
                Toast toast = Toast.makeText(context, "List cleared Successfully!", Toast.LENGTH_SHORT);
                toast.show();

            }
        });
    }


}

