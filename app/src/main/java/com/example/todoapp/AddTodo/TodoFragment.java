package com.example.todoapp.AddTodo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.todoapp.R;


public class TodoFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.todo, container, false);

        Button btnAdd = view.findViewById(R.id.addBtn);
        FloatingActionButton btnDate = view.findViewById(R.id.dateBtn);
        EditText editText = view.findViewById(R.id.editText);
        ListView listView = view.findViewById(R.id.view);




        AddTodoLogic addtodo = new AddTodoLogic(btnAdd, btnDate, editText, listView, getContext());

        return view;
    }

}
