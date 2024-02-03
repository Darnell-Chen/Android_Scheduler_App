package com.example.cs2340proj1.ui.courses;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cs2340proj1.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.Locale;

public class CourseEditorFragment extends BottomSheetDialogFragment {

    Button addButton, startButton, endButton;
    EditText courseEdit, professorEdit, locationEdit;
    int hour, minute;
    CourseInfo newCourse;
    CourseViewModel viewModel;

    int currPosition = -1;

    public CourseEditorFragment() {
    }

    public static Fragment newInstance(ArrayList<CourseInfo> currCourseList, int currIndex)
    {
        CourseEditorFragment myFragment = new CourseEditorFragment();
        Bundle args = new Bundle();
        args.putSerializable("currList", currCourseList);
        args.putInt("currPosition", currIndex);
        myFragment.setArguments(args);
        return myFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.card_editor, container, false);

        // simply instantiates all of the buttons and EditText
        findLayout(view);

        if (getArguments() != null) {
            ArrayList<CourseInfo> courseList = (ArrayList<CourseInfo>) getArguments().getSerializable("currList");
            currPosition = getArguments().getInt("currPosition");
        }

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String courseName = courseEdit.getText().toString();
                String professor = professorEdit.getText().toString();
                String startTime = startButton.getText().toString();
                String endTime = endButton.getText().toString();
                String location = locationEdit.getText().toString();

                String[] dates = getDates(view);

                newCourse = new CourseInfo(courseName, professor, startTime, endTime, dates, location);

                viewModel = new ViewModelProvider(requireActivity()).get(CourseViewModel.class);

                System.out.println("My Index is " + currPosition);

                if (currPosition > -1) {
                    viewModel.editCourseInfo(newCourse, currPosition);
                } else {
                    viewModel.addCourseInfo(newCourse);
                }

                dismiss();
            }

        });


        // buttom two onClick Listeners will open the Time Dialog for start and end time buttons
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerPopup(startButton);
            }
        });

        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerPopup(endButton);
            }
        });

        return view;
    }

    private int setLayout(View view, Bundle myBundle) {
        ArrayList<CourseInfo> currCourse = (ArrayList<CourseInfo>) myBundle.getSerializable("currList");

        System.out.println("hello!!!!");

        System.out.println(myBundle.getInt("currPosition"));

        return (int) myBundle.getInt("currPosition");
    }

    private void findLayout(View view) {
        addButton = view.findViewById(R.id.save_card_edit);
        startButton = view.findViewById(R.id.start_time_button);
        endButton = view.findViewById(R.id.end_time_button);

        courseEdit = view.findViewById(R.id.courseInputEdit);
        professorEdit = view.findViewById(R.id.professorInputEdit);
        locationEdit = view.findViewById(R.id.locationInputEdit);
    }


    private String[] getDates(View view) {
        String[] dates = new String[5];

        Switch monSwitch = view.findViewById(R.id.mon_switch);
        Switch tuesSwitch = view.findViewById(R.id.tue_switch);
        Switch wedSwitch = view.findViewById(R.id.wed_switch);
        Switch thurSwitch = view.findViewById(R.id.thur_switch);
        Switch friSwitch = view.findViewById(R.id.fri_switch);


        // ternary operators where 1 = true, 0 = false
        // depending on if the switch for that date is on or off, each element in the string
        // is set to 0 or 1
        dates[0] = (monSwitch.isChecked()) ? " Mon " : "";
        dates[1] = (tuesSwitch.isChecked()) ? " Tues " : "";
        dates[2] = (wedSwitch.isChecked()) ? " Wed " : "";
        dates[3] = (thurSwitch.isChecked()) ? " Thurs " : "";
        dates[4] = (friSwitch.isChecked()) ? " Fri " : "";

        return dates;
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
