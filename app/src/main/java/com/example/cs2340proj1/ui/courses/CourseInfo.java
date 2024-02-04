package com.example.cs2340proj1.ui.courses;

public class CourseInfo {
    private String courseName, professor, startTime, endTime, location;
    String[] date;

    public CourseInfo(String pCourse, String pProf, String pStart, String pEnd, String[] pDate,
                      String pLocation) {


        this.courseName = pCourse;
        this.professor = pProf;
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
}
