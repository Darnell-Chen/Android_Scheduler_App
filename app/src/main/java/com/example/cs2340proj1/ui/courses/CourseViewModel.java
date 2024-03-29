package com.example.cs2340proj1.ui.courses;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class CourseViewModel extends ViewModel {
    private MutableLiveData<ArrayList<CourseInfo>> courseList = new MutableLiveData<ArrayList<CourseInfo>>();

    public LiveData<ArrayList<CourseInfo>> getCourseList() {
        return courseList;
    }

    // sets the courselist - take for example when we're getting a whole list from SharedPref
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

    public void editCourseInfo(CourseInfo courseInfo, int currIndex) {
        ArrayList<CourseInfo> currentList = courseList.getValue();
        if (currentList != null) {
            currentList.set(currIndex, courseInfo);
        }
        courseList.setValue(currentList);
    }

    public void deleteCourseInfo(int currIndex) {
        ArrayList<CourseInfo> currentList = courseList.getValue();
        if (currentList != null) {
            currentList.remove(currIndex);
        }
        courseList.setValue(currentList);
    }
}