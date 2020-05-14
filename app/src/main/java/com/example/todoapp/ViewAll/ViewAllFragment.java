package com.example.todoapp.ViewAll;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.todoapp.R;

public class ViewAllFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.view_all, container, false);

        Button btn = view.findViewById(R.id.clearBtn);
        ListView listView = view.findViewById(R.id.clearList);

        ViewAllLogic viewAll = new ViewAllLogic(btn, listView, getContext());

        return view;
    }
}
