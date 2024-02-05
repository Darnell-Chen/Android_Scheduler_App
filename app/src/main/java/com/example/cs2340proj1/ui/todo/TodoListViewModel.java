package com.example.cs2340proj1.ui.todo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Collections;

public class TodoListViewModel extends ViewModel {
    private MutableLiveData<ArrayList<TodoInfo>> todoList = new MutableLiveData<ArrayList<TodoInfo>>();
    private boolean completedFilter , dateFilter, courseFilter, examFilter, assignmentFilter = false;

    public LiveData<ArrayList<TodoInfo>> getTodoList() {
        return todoList;
    }

    public void setTodoList(ArrayList<TodoInfo> newList) {
        if (dateFilter) {
            Collections.sort(newList, new DateSort());
        }
        if (courseFilter) {
            Collections.sort(newList, new CourseSort());
        }
        todoList.setValue(newList);
    }

    public void addTodoInfo(TodoInfo todoInfo) {
        ArrayList<TodoInfo> currentList = todoList.getValue();
        if (currentList == null) {
            currentList = new ArrayList<>();
        }
        currentList.add(todoInfo);
        setTodoList(currentList);
    }

    public void editTodoInfo(TodoInfo todoInfo, int currIndex) {
        ArrayList<TodoInfo> currentList = todoList.getValue();
        if (currentList != null) {
            currentList.set(currIndex, todoInfo);
        }
        setTodoList(currentList);
    }

    public void deleteTodoInfo(int currIndex) {
        ArrayList<TodoInfo> currentList = todoList.getValue();
        if (currentList != null) {
            currentList.remove(currIndex);
        }
        todoList.setValue(currentList);
    }

    // Added
    public void updateTodoCompletion(TodoInfo todoInfo, int currIndex) {
        ArrayList<TodoInfo> currentList = todoList.getValue();
        if (currentList != null) {
            currentList.set(currIndex, todoInfo);
        }
        setTodoList(currentList);
    }

    public void setCompletedFilter(boolean completed) {
        this.completedFilter = completed;
    }

    public void setDateFilter(boolean date) {
        this.dateFilter = date;
        if (date) {
            ArrayList<TodoInfo> currentList = todoList.getValue();
            Collections.sort(currentList, new DateSort());
            todoList.setValue(currentList);
        }
    }

    public void setCourseFilter(boolean course) {
        this.courseFilter = course;
        if (course) {
            ArrayList<TodoInfo> currentList = todoList.getValue();
            Collections.sort(currentList, new CourseSort());
            todoList.setValue(currentList);
        }
    }

    public void setExamFilter(boolean exam) {
        this.examFilter = exam;
        todoList.setValue(todoList.getValue());
    }

    public void setAssignmentFilter(boolean assignment) {
        this.assignmentFilter = assignment;
        todoList.setValue(todoList.getValue());
    }

    public boolean getDateFilter() {
        return dateFilter;
    }

    public boolean getCourseFilter() {
        return courseFilter;
    }

    public boolean getCompletedFilter() {
        return completedFilter;
    }

    public boolean getExamFilter() {
        return examFilter;
    }

    public boolean getAssignmentFilter() {
        return assignmentFilter;
    }
}