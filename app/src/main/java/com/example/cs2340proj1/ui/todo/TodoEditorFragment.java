package com.example.cs2340proj1.ui.todo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cs2340proj1.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class TodoEditorFragment extends BottomSheetDialogFragment {

    Button addButton, dateButton, deleteButton;
    EditText nameEdit, courseEdit, locationEdit;
    TodoInfo newTodo;
    TodoListViewModel viewModel;
    int currPosition = -1;


    public TodoEditorFragment() {
    }

    public static TodoEditorFragment newInstance(ArrayList<TodoInfo> currTodoList, int currIndex, String type) {
        TodoEditorFragment myFragment = new TodoEditorFragment();
        Bundle args = new Bundle();
        args.putSerializable("currList", currTodoList);
        args.putInt("currPosition", currIndex);
        args.putString("type", type);
        myFragment.setArguments(args);
        return myFragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {Bundle args = getArguments();
//        String type = args != null ? args.getString("type", "assignment") : "assignment";
        String type;
        if (args !=null) {
            type = args.getString("type");
        } else {
            type = "assignment";
        }

        int layoutId;
        if (type.equals("assignment")) {
            layoutId = R.layout.todo_assignment_editor;
        } else if (type.equals("exam")){
            layoutId = R.layout.todo_exam_editor;
        } else {
            layoutId = R.layout.todo_generic_editor;
        }

        View view = inflater.inflate(layoutId, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(TodoListViewModel.class);

        // always have this before setLayout() since this will find the components that setLayout uses
        findLayout(view, type);

        if (args != null) {
            currPosition = args.getInt("currPosition");
            ArrayList<TodoInfo> todoList = (ArrayList<TodoInfo>) args.getSerializable("currList");
            if (todoList != null && currPosition >= 0 && currPosition < todoList.size()) {
                setLayout(todoList.get(currPosition), type);
            }
        }

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = args != null ? args.getString("type", "assignment") : "assignment";

                String todoName = nameEdit.getText().toString();
                String date = dateButton.getText().toString();
                String course = (type.equals("assignment") || type.equals("exam")) ? courseEdit.getText().toString() : "";
                String location = (type.equals("exam")) ? locationEdit.getText().toString() : "";

                if(!checkFormFilled(type, todoName, date, course, location)){
                    Toast.makeText(getActivity(), "Please fill in all parts of the form", Toast.LENGTH_LONG).show();

                } else {
                    newTodo = new TodoInfo(todoName, date, course, location, type);

                    if (currPosition > -1) {
                        viewModel.editTodoInfo(newTodo, currPosition);
                    } else {
                        viewModel.addTodoInfo(newTodo);
                    }

                    dismiss();
                }
            }

        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deleteButton.getText().equals("Delete")) {
                    deleteDialog();
                } else {
                    dismiss();
                }
            }
        });


        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerPopup(dateButton);
            }
        });


        return view;
    }

    private boolean checkFormFilled(String type, String todoName, String date, String course, String location) {
        if (type.equals("assignment")) {
            return !todoName.equals("") && !date.equals("Select Date") && !course.equals("");
        } else if (type.equals("exam")){
            return !todoName.equals("") && !date.equals("Select Date") && !course.equals("") && !location.equals("");
        } else {
            return !todoName.equals("") && !date.equals("Select Date");
        }
    }


    private void deleteDialog() {
        new AlertDialog.Builder(getContext())
                .setTitle("Delete Confirmation")
                .setMessage("Are you sure you want to delete this?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Continue with delete operation
                        if (currPosition > -1) {
                            viewModel.deleteTodoInfo(currPosition);
                        }
                        dismiss();
                    }
                })
                .setNegativeButton(android.R.string.no, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


    private void setLayout(TodoInfo currTodo, String type) {
        nameEdit.setText(currTodo.getTodoName());
//        courseEdit.setText(currTodo.getCourse());
        dateButton.setText(currTodo.getDate());

        // Handle location field for exams
        if (type.equals("exam")) {
            locationEdit.setText(currTodo.getLocation());
        } else if (locationEdit != null) {
            locationEdit.setVisibility(View.GONE);
        }

        if (type.equals("exam") || type.equals("assignment")) {
            courseEdit.setText(currTodo.getCourse());
        } else if (courseEdit != null) {
            courseEdit.setVisibility(View.GONE);
        }

        deleteButton.setText("Delete");
    }

    private void findLayout(View view, String type) {
        if (type.equals("assignment")) {
            addButton = view.findViewById(R.id.assignmenttodo_save_card_edit);
            deleteButton = view.findViewById(R.id.assignmenttodo_cancel_card_edit);
            dateButton = view.findViewById(R.id.assignmenttodo_date);

            nameEdit = view.findViewById(R.id.assignmenttodoInputEdit);
            courseEdit = view.findViewById(R.id.assignmenttodo_courseInputEdit);
        } else if (type.equals("exam")){
            addButton = view.findViewById(R.id.examtodo_save_card_edit);
            deleteButton = view.findViewById(R.id.examtodo_cancel_card_edit);
            dateButton = view.findViewById(R.id.examtodo_date);

            nameEdit = view.findViewById(R.id.examtodoInputEdit);
            courseEdit = view.findViewById(R.id.examtodo_courseInputEdit);
            locationEdit = view.findViewById(R.id.examtodo_locationInputEdit);
        } else {
            addButton = view.findViewById(R.id.generictodo_save_card_edit);
            deleteButton = view.findViewById(R.id.generictodo_cancel_card_edit);
            dateButton = view.findViewById(R.id.generictodo_date);

            nameEdit = view.findViewById(R.id.generictodoInputEdit);
        }
    }

    public void DatePickerPopup(Button dateButton) {
        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                // Note: Month value is 0-based. January is 0.
                String selectedDate = String.format(Locale.getDefault(), "%04d-%02d-%02d", year, month + 1, dayOfMonth);
                dateButton.setText(selectedDate);
            }
        };

        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), onDateSetListener, year, month, day);
        datePickerDialog.show();
    }
}