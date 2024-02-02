package com.example.cs2340proj1.ui.courses;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs2340proj1.R;
import com.example.cs2340proj1.databinding.FragmentCoursesBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class CoursesFragment extends Fragment {

    // Note: xml layouts automatically generate an object class that corresponds to the layout
    // you just have to implement it
    private FragmentCoursesBinding binding;

    private ArrayList<CourseInfo> courseList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // binding gets and renders the layout the its class name corresponds to
        binding = FragmentCoursesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        initializeCardView(root);

        return root;
    }




    public void initializeCardView(View root) {

        courseList = new ArrayList<CourseInfo>();

        // CourseAdapter will update the information in card
        CourseAdapter adapter = new CourseAdapter(this, courseList);

        // finds the recycler view and sets its adapter - which will funnel the data
        RecyclerView myRecycler = binding.coursesRecyclerview;
        myRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        myRecycler.setAdapter(adapter);



        // gets floating action button and makes it so bottom sheet (editor) opens
        // when you click on it
        FloatingActionButton addCourseButton = root.findViewById(R.id.add_course_button);
        addCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CourseEditorFragment courseEditorFragment = new CourseEditorFragment();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.card_editor_layout, courseEditorFragment, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
