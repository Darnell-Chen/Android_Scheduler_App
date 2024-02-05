package com.example.cs2340proj1.ui.todo;

public class TodoInfo {

    private String todoName, date, course, location, type;
    private boolean completed;

    public TodoInfo(String pTodoName, String pDate, String pCourse,
                    String pLocation, String pType, boolean pCompleted) {


        this.todoName = pTodoName;
        this.date = pDate;
        this.course = pCourse;
        this.location = pLocation;
        this.type = pType;
        this.completed = pCompleted;
    }

    public TodoInfo(String pTodoName, String pDate, String pCourse,
                    String pLocation, String pType) {
        this(pTodoName, pDate, pCourse, pLocation, pType, false);
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

    // Function to compare by task completion
    public static int compareByCompletion(TodoInfo todo1, TodoInfo todo2) {
        return Boolean.compare(todo1.completed, todo2.completed);
    }

}
