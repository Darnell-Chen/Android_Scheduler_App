package com.example.cs2340proj1.ui.courses;

import android.annotation.SuppressLint;

public class CourseInfo {
    private String courseName, professor, startTime, endTime, location;
    String[] date;

    public CourseInfo(String pCourse, String pProf, String pStart, String pEnd, String[] pDate,
                      String pLocation) {


        if (!pCourse.equals("")) {
            this.courseName = pCourse;
        }

        if (!pProf.equals("")) {
            this.professor = pProf;
        }

        this.date = pDate;

        this.startTime = pStart;
        this.endTime = pEnd;
        this.location = pLocation;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getProfessor() {
        return professor;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String[] getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setDate(String[] date) {
        this.date = date;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
