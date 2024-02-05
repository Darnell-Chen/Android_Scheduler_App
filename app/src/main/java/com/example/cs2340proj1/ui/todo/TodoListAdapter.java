package com.example.cs2340proj1.ui.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs2340proj1.R;

import java.util.ArrayList;

public class TodoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<TodoInfo> myTodoList;
    private Context context;
    private static final int TYPE_ASSIGNMENT = 0;
    private static final int TYPE_EXAM = 1;

    public TodoListAdapter(Context context, ArrayList<TodoInfo> inputCourses) {
        this.myTodoList = inputCourses;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        TodoInfo todoInfo = myTodoList.get(position);
        return todoInfo.getType().equals("assignment") ? TYPE_ASSIGNMENT : TYPE_EXAM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_ASSIGNMENT) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.assignment_card_layout, parent, false);
            return new AssignmentViewHolder(view, context, myTodoList);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exam_card_layout, parent, false);
            return new ExamViewHolder(view, context, myTodoList);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TodoInfo todoInfo = myTodoList.get(position);
        if (getItemViewType(position) == TYPE_ASSIGNMENT) {
            ((AssignmentViewHolder) holder).bindData(todoInfo, position);
        } else {
            ((ExamViewHolder) holder).bindData(todoInfo, position);
        }
    }

    @Override
    public int getItemCount() {
        return myTodoList.size();
    }

    public void setTodoList(ArrayList<TodoInfo> newList) {
        myTodoList = newList;
        notifyDataSetChanged();
    }

    static class AssignmentViewHolder extends RecyclerView.ViewHolder {
        private TextView name, course, date;
        private ImageButton editButton;
        private ArrayList<TodoInfo> todoList;
        private Context context;

        public AssignmentViewHolder(View itemView, Context context, ArrayList<TodoInfo> todoList) {
            super(itemView);
            this.context = context;
            this.todoList = todoList;
            name = itemView.findViewById(R.id.assignment_todo_card_name);
            course = itemView.findViewById(R.id.assignment_todo_card_course);
            date = itemView.findViewById(R.id.assignment_todo_card_date);
            editButton = itemView.findViewById(R.id.assignment_todo_edit_button);
        }

        void bindData(TodoInfo currTodoInfo, int position) {
            name.setText(currTodoInfo.getTodoName());
            course.setText(currTodoInfo.getCourse());
            date.setText(currTodoInfo.getDate());

            editButton.setOnClickListener(v -> {
                FragmentActivity activity = (FragmentActivity) context;
                TodoEditorFragment todoEditorFragment = TodoEditorFragment.newInstance(todoList, getAdapterPosition(), "assignment");
                todoEditorFragment.show(activity.getSupportFragmentManager(), todoEditorFragment.getTag());
            });
        }
    }

    static class ExamViewHolder extends RecyclerView.ViewHolder {
        private TextView name, course, date, location;
        private ImageButton editButton;
        private ArrayList<TodoInfo> todoList;
        private Context context;

        public ExamViewHolder(View itemView, Context context, ArrayList<TodoInfo> todoList) {
            super(itemView);
            this.context = context;
            this.todoList = todoList;
            name = itemView.findViewById(R.id.todo_card_exam_name);
            course = itemView.findViewById(R.id.todo_card_exam_course);
            date = itemView.findViewById(R.id.todo_card_exam_date);
            location = itemView.findViewById(R.id.todo_card_exam_location);
            editButton = itemView.findViewById(R.id.examtodo_edit_button);
        }

        void bindData(TodoInfo currTodoInfo, int position) {
            name.setText(currTodoInfo.getTodoName());
            course.setText(currTodoInfo.getCourse());
            date.setText(currTodoInfo.getDate());
            location.setText(currTodoInfo.getLocation());

            editButton.setOnClickListener(v -> {
                FragmentActivity activity = (FragmentActivity) context;
                TodoEditorFragment todoEditorFragment = TodoEditorFragment.newInstance(todoList, getAdapterPosition(), "exam");
                todoEditorFragment.show(activity.getSupportFragmentManager(), todoEditorFragment.getTag());
            });
        }
    }
}