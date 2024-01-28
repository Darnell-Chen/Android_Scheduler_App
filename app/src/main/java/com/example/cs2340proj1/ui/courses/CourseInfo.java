package com.example.cs2340proj1.ui.courses;

import android.annotation.SuppressLint;

public class CourseInfo {
    private String courseName;
    private String professor;
    private String timeslots;
    private String date;

    private String location;

    public CourseInfo(String pCourse, String pProf, String pTime, String pDate, String pLocation) {
        this.courseName = pCourse;
        this.professor = pProf;
        this.timeslots = pTime;
        this.date = pDate;
        this.location = pLocation;
    }
}
