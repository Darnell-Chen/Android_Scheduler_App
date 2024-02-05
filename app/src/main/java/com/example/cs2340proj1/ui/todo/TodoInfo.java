package com.example.cs2340proj1.ui.todo;

public class TodoInfo {

    private String todoName, date, course, location, type;
    private boolean completed; // Field to indicate if the task is completed

    public TodoInfo(String pTodoName, String pDate, String pCourse,
                    String pLocation, String pType) {


        this.todoName = pTodoName;
        this.date = pDate;
        this.course = pCourse;
        this.location = pLocation;
        this.type = pType;
//        this.completed = pCompleted;
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

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    // Function to compare two strings
    public static int compareStrings(String str1, String str2) {
        String formattedStr1 = str1.replaceAll("\\s", "").toUpperCase();
        String formattedStr2 = str2.replaceAll("\\s", "").toUpperCase();
        return formattedStr1.compareTo(formattedStr2);
    }

    // Function to compare by date
    public static int compareByDate(String date1, String date2) {
        String[] parts1 = date1.split("-");
        String[] parts2 = date2.split("-");
        int yearComparison = parts1[2].compareTo(parts2[2]);
        if (yearComparison != 0) return yearComparison;
        int monthComparison = parts1[0].compareTo(parts2[0]);
        if (monthComparison != 0) return monthComparison;
        return parts1[1].compareTo(parts2[1]);
    }

    // Function to compare by task completion
    public static int compareByCompletion(TodoInfo todo1, TodoInfo todo2) {
        return Boolean.compare(todo1.completed, todo2.completed);
    }

}
