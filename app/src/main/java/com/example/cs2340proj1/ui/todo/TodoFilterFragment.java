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
    Switch completionSwitch, courseSwitch, dateSwitch, examSwitch, assignmentSwitch, genericSwitch;

    boolean initialCompletion, initialCourse, initialDate, initialExam, initialAssignment, initialGeneric;
    Button filterButton;
    public TodoFilterFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.todo_filter, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(TodoListViewModel.class);

        findSwitches(view);

        filterButton = view.findViewById(R.id.todo_set_filter);

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateFilters();
                dismiss();
            }
        });

        return view;
    }


    public void findSwitches(View view) {
        this.completionSwitch = view.findViewById(R.id.completedSwitch);
        this.courseSwitch = view.findViewById(R.id.courseSwitch);
        this.dateSwitch = view.findViewById(R.id.dateSwitch);
        this.examSwitch = view.findViewById(R.id.examSwitch);
        this.assignmentSwitch = view.findViewById(R.id.assignmentSwitch);
        this.genericSwitch = view.findViewById(R.id.genericSwitch);

        // this will toggle on/off the switches based on previous inputs that the user already had
        setSwitches();

        // these two methods are the toggling logics, where we toggle some switches off when
        // other switches are toggled on
        sortingOnClick();
        filteringTodo();
    }

    private void setSwitches() {
        initialDate = viewModel.getDateFilter();
        initialCourse = viewModel.getCourseFilter();;
        initialCompletion = viewModel.getCompletedFilter();
        initialExam = viewModel.getExamFilter();
        initialAssignment = viewModel.getAssignmentFilter();
        initialGeneric = viewModel.getGenericFilter();

        dateSwitch.setChecked(initialDate);
        courseSwitch.setChecked(initialCourse);
        completionSwitch.setChecked(initialCompletion);
        examSwitch.setChecked(initialExam);
        assignmentSwitch.setChecked(initialAssignment);
        genericSwitch.setChecked(initialGeneric);
    }

    private void filteringTodo() {
        CompoundButton.OnCheckedChangeListener changeChecker = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    if (examSwitch.isChecked() && genericSwitch.isChecked()) {
                        assignmentSwitch.setChecked(false);
                    }
                    if (examSwitch.isChecked() && assignmentSwitch.isChecked()) {
                        genericSwitch.setChecked(false);
                    }
                    if (buttonView == assignmentSwitch) {
                        if (examSwitch.isChecked() && genericSwitch.isChecked()) {
                            genericSwitch.setChecked(false);
                        }
                        assignmentSwitch.setChecked(isChecked);
                    }
                }
            }
        };

        examSwitch.setOnCheckedChangeListener(changeChecker);
        assignmentSwitch.setOnCheckedChangeListener(changeChecker);
        genericSwitch.setOnCheckedChangeListener(changeChecker);

    }


    public void sortingOnClick () {
        CompoundButton.OnCheckedChangeListener changeChecker = new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    if (courseSwitch.isChecked()) {
                        dateSwitch.setChecked(false);
                        genericSwitch.setChecked(true);
                    }
                    if (buttonView == dateSwitch) {
                        if (courseSwitch.isChecked() && genericSwitch.isChecked()) {
                            courseSwitch.setChecked(false);
                            genericSwitch.setChecked(false);
                            dateSwitch.setChecked(true);
                        } else if (dateSwitch.isChecked()) {
                            courseSwitch.setChecked(false);
                        }
                    }

                }
            }
        };

        courseSwitch.setOnCheckedChangeListener(changeChecker);
        dateSwitch.setOnCheckedChangeListener(changeChecker);
    }

    public void updateFilters (){
        if (initialDate != dateSwitch.isChecked()) {
            viewModel.setDateFilter(dateSwitch.isChecked());
        }

        if (initialCourse != courseSwitch.isChecked()) {
            viewModel.setCourseFilter(courseSwitch.isChecked());
        }

        if (initialCompletion != completionSwitch.isChecked()) {
            viewModel.setCompletedFilter(completionSwitch.isChecked());
        }

        if (initialExam != examSwitch.isChecked()) {
            viewModel.setExamFilter(examSwitch.isChecked());
        }

        if (initialAssignment != assignmentSwitch.isChecked()) {
            viewModel.setAssignmentFilter(assignmentSwitch.isChecked());
        }

        if (initialGeneric != genericSwitch.isChecked()) {
            viewModel.setGenericFilter(genericSwitch.isChecked());
        }
    }
}
