package com.example.cs2340proj1.ui.courses;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.cs2340proj1.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class CourseBottomSheet extends BottomSheetDialogFragment {
    public static CourseBottomSheet newInstance() {
        return new CourseBottomSheet();
    }
}
