package com.example.cs2340proj1.ui.courses;

import static android.app.TimePickerDialog.*;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cs2340proj1.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Calendar;
import java.util.Locale;

public class CourseBottomSheet extends BottomSheetDialogFragment {
    public CourseBottomSheet() {

    }
    Button addButton, startButton, endButton;
    int hour, minute;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.card_editor, container, false);

        addButton = view.findViewById(R.id.save_card_edit);
        startButton = view.findViewById(R.id.start_time_button);
        endButton = view.findViewById(R.id.end_time_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
