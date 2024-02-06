package com.example.cs2340proj1.ui.todo;

import java.util.Comparator;

public class DateSort implements Comparator<TodoInfo> {

    @Override
    public int compare(TodoInfo o1, TodoInfo o2) {
        String date1 = o1.getDate();
        String date2 = o2.getDate();

        String[] parts1 = date1.split("-");
        String[] parts2 = date2.split("-");
        int yearComparison = parts1[2].compareTo(parts2[2]);
        if (yearComparison != 0) return yearComparison;
        int monthComparison = parts1[0].compareTo(parts2[0]);
        if (monthComparison != 0) return monthComparison;

        return parts1[1].compareTo(parts2[1]);
    }
}
