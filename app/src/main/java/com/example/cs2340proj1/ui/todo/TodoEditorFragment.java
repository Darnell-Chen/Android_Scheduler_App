package com.example.cs2340proj1.ui.todo;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cs2340proj1.R;
import com.example.cs2340proj1.ui.courses.CourseEditorFragment;
import com.example.cs2340proj1.ui.courses.CourseInfo;
import com.example.cs2340proj1.ui.courses.CourseViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.Locale;

public class TodoEditorFragment extends BottomSheetDialogFragment {

    Button addButton, dateButton, timeButton, deleteButton;
    EditText nameEdit, courseEdit, locationEdit;
    int hour, minute;
    TodoInfo newTodo;
    TodoListViewModel viewModel;
    int currPosition = -1;

    public TodoEditorFragment() {
    }

    public static Fragment newInstance(ArrayList<TodoInfo> currTodoList, int currIndex)
    {
        TodoEditorFragment myFragment = new TodoEditorFragment();
        Bundle args = new Bundle();
        args.putSerializable("currList", currTodoList);
        args.putInt("currPosition", currIndex);
        myFragment.setArguments(args);
        return myFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.todo_editor, container, false);

        findLayout(view);

        if (getArguments() != null) {
            setLayout();
        }

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String todoName = nameEdit.getText().toString();
                String date = dateButton.getText().toString();
                String course = courseEdit.getText().toString();
                String time = timeButton.getText().toString();
                String location = locationEdit.getText().toString();
//                boolean completed = completedSwitch.isChecked();

                newTodo = new TodoInfo(todoName, date, course, time, location);

                viewModel = new ViewModelProvider(requireActivity()).get(TodoListViewModel.class);

                if (currPosition > -1) {
                    viewModel.editTodoInfo(newTodo, currPosition);
                } else {
                    viewModel.addTodoInfo(newTodo);
                }

                dismiss();
            }

        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext())
                        .setTitle("Delete Confirmation")
                        .setMessage("Are you sure you want to delete this?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                                viewModel = new ViewModelProvider(requireActivity()).get(TodoListViewModel.class);
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
        });



        // buttom two onClick Listeners will open the Time Dialog for start and end time buttons
        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerPopup(dateButton);
            }
        });

        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerPopup(timeButton);
            }
        });

        return view;
    }

    public boolean[] confirmationDialog() {

        final boolean[] returnedBoolean = new boolean[1];
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        returnedBoolean[0] = true;
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        returnedBoolean[0] = false;
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();

        return returnedBoolean;
    }


    private void setLayout() {
        currPosition = getArguments().getInt("currPosition");

        ArrayList<TodoInfo> todoList = (ArrayList<TodoInfo>) getArguments().getSerializable("currList");
        TodoInfo currTodo = todoList.get(currPosition);

        nameEdit.setText(currTodo.getTodoName());
        courseEdit.setText(currTodo.getCourse());
        locationEdit.setText(currTodo.getLocation());
        dateButton.setText(currTodo.getDate());
        timeButton.setText(currTodo.getTime());

        deleteButton.setText("Delete");
    }

    private void findLayout(View view) {
        addButton = view.findViewById(R.id.todo_save_card_edit);
        deleteButton = view.findViewById(R.id.todo_cancel_card_edit);
        dateButton = view.findViewById(R.id.todo_date);
        timeButton = view.findViewById(R.id.todo_start_time_button);

        nameEdit = view.findViewById(R.id.todoInputEdit);
        courseEdit = view.findViewById(R.id.todo_courseInputEdit);
        locationEdit = view.findViewById(R.id.todo_locationInputEdit);
//        completedSwitch = view.findViewById(R.id.todo_completed_switch);
    }




    // the method that creates a TimePickerDialog
    public void TimePickerPopup(Button currButton) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;

                currButton.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));

            }
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), onTimeSetListener, hour, minute, true);
        timePickerDialog.show();
    }



}
