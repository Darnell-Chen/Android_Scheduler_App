package com.example.cs2340proj1.ui.courses;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class CourseViewModel extends ViewModel {
    // Create a MutableLiveData object to hold course-related data
    private MutableLiveData<ArrayList<CourseInfo>> courseList = new  MutableLiveData<ArrayList<CourseInfo>>();

    // Constructor to initialize the LiveData with an initial value
    public CourseViewModel(ArrayList<CourseInfo> initialCourses) {
        // You can set an initial value here if needed
        courseList.setValue(initialCourses);
    }

    // Getter method to expose the LiveData to the CourseFragment
    public LiveData<ArrayList<CourseInfo>> getCourseList() {
        return courseList;
    }

    // Method to update the LiveData's value
    public void setCourseList(ArrayList<CourseInfo> courses) {
        courseList.setValue(courses);
    }

    public void addCourseInfo(CourseInfo courseInfo) {
        ArrayList<CourseInfo> currentList = courseList.getValue();
        if (currentList == null) {
            currentList = new ArrayList<>();
        }
        currentList.add(courseInfo);
        courseList.setValue(currentList);
    }
}