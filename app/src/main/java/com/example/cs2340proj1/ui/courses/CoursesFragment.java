package com.example.cs2340proj1.ui.courses;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs2340proj1.R;
import com.example.cs2340proj1.databinding.FragmentCoursesBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class CoursesFragment extends Fragment {

    // Note: xml layouts automatically generate an object class that corresponds to the layout
    // you just have to implement it
    private FragmentCoursesBinding binding;

    private ArrayList<CourseInfo> courseList;

    CourseEditorFragment courseEditorFragment;
    CourseViewModel viewModel;

    CourseAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        // binding gets and renders the layout the its class name corresponds to
        binding = FragmentCoursesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // This will grab the view model that was created in main activity.
        viewModel = new ViewModelProvider(requireActivity()).get(CourseViewModel.class);

        initializeCardView(root);

        // This is the viewmodel observer. It will be notified everytime there is a charge to the
        // list in the viewmodel
        viewModel.getCourseList().observe(getViewLifecycleOwner(), new Observer<ArrayList<CourseInfo>>() {
            @Override
            public void onChanged(ArrayList<CourseInfo> newData) {
                // This will destroy the course editor layout if we click on another tab
                if (courseEditorFragment != null) {
                    destroyEditor();
                    courseList = viewModel.getCourseList().getValue();

                    adapter.setCourseList(courseList);
                }
            }
        });

        return root;
    }




    public void initializeCardView(View root) {

        courseList = new ArrayList<CourseInfo>();

        // CourseAdapter will update the information in card
        adapter = new CourseAdapter(this.getContext(), courseList);

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
                courseEditorFragment = new CourseEditorFragment();
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.courseXML, courseEditorFragment, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }

        });
    }

    @Override
    public void onDestroyView() {

        destroyEditor();

        super.onDestroyView();
        binding = null;
    }

    public void destroyEditor() {
        // This will destroy the course editor layout if we click on another tab
        if (courseEditorFragment != null) {
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .remove(courseEditorFragment)
                    .commit();
            courseEditorFragment = null;
        }
    }
}
