package com.example.cs2340proj1.ui.todo;

public class TodoInfo {

    private String todoName, date, course, time, location;

    public TodoInfo(String pTodoName, String pDate, String pCourse, String pTime,
                      String pLocation) {


        this.todoName = pTodoName;
        this.date = pDate;
        this.course = pCourse;
        this.time = pTime;
        this.location = pLocation;
    }

    public String getTodoName() {
        return todoName;
    }

    public String getDate() {
        return date;
    }

    public String getCourse() {
        return course;
    }

    public String getEndTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }
}
