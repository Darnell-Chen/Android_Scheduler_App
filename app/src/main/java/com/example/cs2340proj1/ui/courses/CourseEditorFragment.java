package com.example.cs2340proj1.ui.courses;

import android.app.TimePickerDialog;
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

import com.example.cs2340proj1.R;

import java.util.Locale;

public class CourseEditorFragment extends Fragment {

    public CourseEditorFragment() {
    }
    Button addButton, startButton, endButton;
    EditText courseEdit, professorEdit, locationEdit;
    int hour, minute;
    CourseInfo newCourse;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.card_editor, container, false);

        // simply instantiates all of the buttons and EditText
        findLayout(view);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String courseName = courseEdit.getText().toString();
                String professor = professorEdit.getText().toString();
                String startTime = startButton.getText().toString();
                String endTime = endButton.getText().toString();
                String location = locationEdit.getText().toString();

                boolean[] dates = getDates(view);

                newCourse = new CourseInfo(courseName, professor, startTime, endTime, dates, location, "");
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

    private void findLayout(View view) {
        addButton = view.findViewById(R.id.save_card_edit);
        startButton = view.findViewById(R.id.start_time_button);
        endButton = view.findViewById(R.id.end_time_button);

        courseEdit = view.findViewById(R.id.courseInputEdit);
        professorEdit = view.findViewById(R.id.professorInputEdit);
        locationEdit = view.findViewById(R.id.locationInputEdit);
    }


    private boolean[] getDates(View view) {
        boolean[] dates = new boolean[5];

        Switch mon = view.findViewById(R.id.mon_switch);
        Switch tues = view.findViewById(R.id.tue_switch);
        Switch wed = view.findViewById(R.id.wed_switch);
        Switch thur = view.findViewById(R.id.thur_switch);
        Switch fri = view.findViewById(R.id.fri_switch);

        dates[0] = mon.isChecked();
        dates[1] = tues.isChecked();
        dates[2] = wed.isChecked();
        dates[3] = thur.isChecked();
        dates[4] = fri.isChecked();

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
