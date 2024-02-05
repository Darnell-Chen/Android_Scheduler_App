package com.example.cs2340proj1.ui.todo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class TodoListViewModel extends ViewModel {
    private MutableLiveData<ArrayList<TodoInfo>> todoList = new MutableLiveData<ArrayList<TodoInfo>>();

    private boolean completedFilter = false;
    private boolean dateFilter = false;
    private boolean courseFilter = false;

    public LiveData<ArrayList<TodoInfo>> getTodoList() {
        return todoList;
    }

    public void setTodoList(ArrayList<TodoInfo> newList) {
        todoList.setValue(newList);
    }

    public void addTodoInfo(TodoInfo todoInfo) {
        ArrayList<TodoInfo> currentList = todoList.getValue();
        if (currentList == null) {
            currentList = new ArrayList<>();
        }
        currentList.add(todoInfo);
        todoList.setValue(currentList);
    }

    public void editTodoInfo(TodoInfo todoInfo, int currIndex) {
        ArrayList<TodoInfo> currentList = todoList.getValue();
        if (currentList != null) {
            currentList.set(currIndex, todoInfo);
        }
        todoList.setValue(currentList);
    }

    public void deleteTodoInfo(int currIndex) {
        ArrayList<TodoInfo> currentList = todoList.getValue();
        if (currentList != null) {
            currentList.remove(currIndex);
        }
        todoList.setValue(currentList);
    }

    public void setFilter(boolean completed, boolean date, boolean course) {
        this.completedFilter = completed;
        this.dateFilter = date;
        this.courseFilter = course;
    }
}