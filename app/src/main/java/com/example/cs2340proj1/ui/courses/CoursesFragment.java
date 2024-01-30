package com.example.cs2340proj1.ui.courses;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs2340proj1.R;
import com.example.cs2340proj1.databinding.CardEditorBinding;
import com.example.cs2340proj1.databinding.FragmentCoursesBinding;
import com.example.cs2340proj1.ui.CourseAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CoursesFragment extends Fragment {

    // Note: xml layouts automatically generate an object class that corresponds to the layout
    // you just have to implement it
    private FragmentCoursesBinding binding;
    private ArrayList<CourseInfo> myCourses;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // binding gets and renders the layout the its class name corresponds to
        binding = FragmentCoursesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // simply instantiate the empty list of courses
        myCourses = new ArrayList<>();

        FloatingActionButton addCourseButton = root.findViewById(R.id.add_course_button);
        addCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CourseBottomSheet bottomSheet = new CourseBottomSheet();
                bottomSheet.show(getChildFragmentManager(), "doesnt matter");
            }

        });

        // This is the object that accesses the recyclerview in our course layout
        RecyclerView courseRecycler = binding.coursesRecyclerview;
        courseRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        CourseAdapter adapter = new CourseAdapter();


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
