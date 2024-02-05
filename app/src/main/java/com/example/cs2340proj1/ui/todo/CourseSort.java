package com.example.cs2340proj1.ui.todo;

import java.util.Comparator;

public class CourseSort implements Comparator<TodoInfo> {
    @Override
    public int compare(TodoInfo o1, TodoInfo o2) {
        String formattedStr1 = o1.getCourse().replaceAll("\\s", "").toUpperCase();
        String formattedStr2 = o2.getCourse().replaceAll("\\s", "").toUpperCase();
        return formattedStr1.compareTo(formattedStr2);
    }
}
