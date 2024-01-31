package com.example.cs2340proj1.ui.courses;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cs2340proj1.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class CourseBottomSheet extends BottomSheetDialogFragment {
    public CourseBottomSheet() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.card_editor, container, false);

        Button addButton = view.findViewById(R.id.save_card_edit);
        addButton.setOnClickListener();

        return view;
    }
}
