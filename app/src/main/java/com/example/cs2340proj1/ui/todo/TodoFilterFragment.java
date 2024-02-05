package com.example.cs2340proj1.ui.todo;

import static java.util.Collections.*;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.cs2340proj1.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.Collections;

public class TodoFilterFragment extends BottomSheetDialogFragment{

    private TodoListViewModel viewModel;
    Switch completionSwitch, courseSwitch, dateSwitch;

    Button filterButton;
    public TodoFilterFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.todo_filter, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(TodoListViewModel.class);

        setSwitches(view);

        filterButton = view.findViewById(R.id.todo_set_filter);

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dateSwitch.isChecked()) {
                    ArrayList<TodoInfo> currList = viewModel.getTodoList().getValue();
                    Collections.sort(currList, new DateSort());
                    viewModel.setTodoList(currList);
                }

                if (courseSwitch.isChecked()) {
                    ArrayList<TodoInfo> currList = viewModel.getTodoList().getValue();
                    Collections.sort(currList, new CourseSort());
                    viewModel.setTodoList(currList);
                }

                dismiss();
            }
        });

        return view;
    }

    public void setSwitches(View view) {
        completionSwitch = view.findViewById(R.id.completedSwitch);
        courseSwitch = view.findViewById(R.id.courseSwitch);
        dateSwitch = view.findViewById(R.id.dateSwitch);

        CompoundButton.OnCheckedChangeListener changeChecker = new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    if (buttonView != completionSwitch) {
                        completionSwitch.setChecked(false);
                    }
                    if (buttonView != courseSwitch) {
                        courseSwitch.setChecked(false);
                    }
                    if (buttonView != dateSwitch) {
                        dateSwitch.setChecked(false);
                    }
                }
            }
        };

        completionSwitch.setOnCheckedChangeListener(changeChecker);
        courseSwitch.setOnCheckedChangeListener(changeChecker);
        dateSwitch.setOnCheckedChangeListener(changeChecker);
    }



}
