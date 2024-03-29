package com.example.cs2340proj1.ui.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs2340proj1.R;

import java.util.ArrayList;

public class TodoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<TodoInfo> myTodoList;
    private Context context;
    private static final int TYPE_ASSIGNMENT = 0;
    private static final int TYPE_EXAM = 1;
    private static final int TYPE_GENERIC = 2;
    private TodoListViewModel viewModel;

    public TodoListAdapter(Context context, ArrayList<TodoInfo> inputCourses, TodoListViewModel viewModel) {
        this.myTodoList = inputCourses;
        this.context = context;
        this.viewModel = viewModel;
    }

    @Override
    public int getItemViewType(int position) {
        TodoInfo todoInfo = myTodoList.get(position);
        String currType = todoInfo.getType();
        if (currType.equals("assignment")) {
            return TYPE_ASSIGNMENT;
        } else if (currType.equals("exam")) {
            return TYPE_EXAM;
        } else {
            return TYPE_GENERIC;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_ASSIGNMENT) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.assignment_card_layout, parent, false);
            return new AssignmentViewHolder(view, context, myTodoList);
        } else if (viewType == TYPE_EXAM) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exam_card_layout, parent, false);
            return new ExamViewHolder(view, context, myTodoList);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.generic_card_layout, parent, false);
            return new GenericViewHolder(view, context, myTodoList);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TodoInfo todoInfo = myTodoList.get(position);
        if (getItemViewType(position) == TYPE_ASSIGNMENT) {
            ((AssignmentViewHolder) holder).bindData(todoInfo, position);
            checkIfFiltered(holder, TYPE_ASSIGNMENT, todoInfo.isCompleted());
        } else if (getItemViewType(position) == TYPE_EXAM) {
            ((ExamViewHolder) holder).bindData(todoInfo, position);
            checkIfFiltered(holder, TYPE_EXAM, todoInfo.isCompleted());
        } else {
            ((GenericViewHolder) holder).bindData(todoInfo, position);
            checkIfFiltered(holder, TYPE_GENERIC, todoInfo.isCompleted());
        }
    }

    @Override
    public int getItemCount() {
        return myTodoList.size();
    }

    private void checkIfFiltered(RecyclerView.ViewHolder newHolder, int todoType, boolean todoCompleted) {
        boolean assignmentFiltered = viewModel.getAssignmentFilter() && (todoType == TYPE_ASSIGNMENT);
        boolean examFiltered = (todoType == TYPE_EXAM) && (viewModel.getExamFilter());
        boolean completionFiltered = viewModel.getCompletedFilter() && todoCompleted;
        boolean genericFiltered = viewModel.getGenericFilter() && (todoType == TYPE_GENERIC);

        if (assignmentFiltered || examFiltered || completionFiltered || genericFiltered) {
            newHolder.itemView.setVisibility(View.INVISIBLE);
            newHolder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
        } else {
            newHolder.itemView.setVisibility(View.VISIBLE);
            newHolder.itemView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
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
        private CheckBox todoCheckbox; // Added


        public AssignmentViewHolder(View itemView, Context context, ArrayList<TodoInfo> todoList) {
            super(itemView);
            this.context = context;
            this.todoList = todoList;
            name = itemView.findViewById(R.id.assignment_todo_card_name);
            course = itemView.findViewById(R.id.assignment_todo_card_course);
            date = itemView.findViewById(R.id.assignment_todo_card_date);
            editButton = itemView.findViewById(R.id.assignment_todo_edit_button);
            todoCheckbox = itemView.findViewById(R.id.assignmentTodoCheckBox); // Added
        }

        void bindData(TodoInfo currTodoInfo, int position) {
            name.setText(currTodoInfo.getTodoName());
            course.setText(currTodoInfo.getCourse());
            date.setText(currTodoInfo.getDate());
            todoCheckbox.setChecked(currTodoInfo.isCompleted()); // Added

            editButton.setOnClickListener(v -> {
                FragmentActivity activity = (FragmentActivity) context;
                TodoEditorFragment todoEditorFragment = TodoEditorFragment.newInstance(todoList, getAdapterPosition(), "assignment");
                todoEditorFragment.show(activity.getSupportFragmentManager(), todoEditorFragment.getTag());
            });

            // Added
            todoCheckbox.setOnClickListener(v -> {
                boolean isChecked = todoCheckbox.isChecked();
                currTodoInfo.setCompleted(isChecked);
                if (context instanceof FragmentActivity) {
                    TodoListViewModel viewModel = new ViewModelProvider((FragmentActivity) context).get(TodoListViewModel.class);
                    viewModel.updateTodoCompletion(currTodoInfo, position);
                }
            });
        }
    }

    static class ExamViewHolder extends RecyclerView.ViewHolder {
        private TextView name, course, date, location;
        private ImageButton editButton;
        private ArrayList<TodoInfo> todoList;
        private Context context;
        private CheckBox todoCheckbox; // Added


        public ExamViewHolder(View itemView, Context context, ArrayList<TodoInfo> todoList) {
            super(itemView);
            this.context = context;
            this.todoList = todoList;
            name = itemView.findViewById(R.id.todo_card_exam_name);
            course = itemView.findViewById(R.id.todo_card_exam_course);
            date = itemView.findViewById(R.id.todo_card_exam_date);
            location = itemView.findViewById(R.id.todo_card_exam_location);
            editButton = itemView.findViewById(R.id.examtodo_edit_button);
            todoCheckbox = itemView.findViewById(R.id.examTodoCheckBox); // Added
        }

        void bindData(TodoInfo currTodoInfo, int position) {
            name.setText(currTodoInfo.getTodoName());
            course.setText(currTodoInfo.getCourse());
            date.setText(currTodoInfo.getDate());
            location.setText(currTodoInfo.getLocation());
            todoCheckbox.setChecked(currTodoInfo.isCompleted()); // Added

            editButton.setOnClickListener(v -> {
                FragmentActivity activity = (FragmentActivity) context;
                TodoEditorFragment todoEditorFragment = TodoEditorFragment.newInstance(todoList, getAdapterPosition(), "exam");
                todoEditorFragment.show(activity.getSupportFragmentManager(), todoEditorFragment.getTag());
            });

            // Added
            todoCheckbox.setOnClickListener(v -> {
                boolean isChecked = todoCheckbox.isChecked();
                currTodoInfo.setCompleted(isChecked);
                if (context instanceof FragmentActivity) {
                    TodoListViewModel viewModel = new ViewModelProvider((FragmentActivity) context).get(TodoListViewModel.class);
                    viewModel.updateTodoCompletion(currTodoInfo, position);
                }
            });
        }
    }

    static class GenericViewHolder extends RecyclerView.ViewHolder {
        private TextView name, date;
        private ImageButton editButton;
        private ArrayList<TodoInfo> todoList;
        private Context context;
        private CheckBox todoCheckbox; // Added


        public GenericViewHolder(View itemView, Context context, ArrayList<TodoInfo> todoList) {
            super(itemView);
            this.context = context;
            this.todoList = todoList;
            name = itemView.findViewById(R.id.generic_todo_card_name);
            date = itemView.findViewById(R.id.generic_todo_card_date);
            editButton = itemView.findViewById(R.id.generic_todo_edit_button);
            todoCheckbox = itemView.findViewById(R.id.genericTodoCheckBox); // Added
        }

        void bindData(TodoInfo currTodoInfo, int position) {
            name.setText(currTodoInfo.getTodoName());
            date.setText(currTodoInfo.getDate());
            todoCheckbox.setChecked(currTodoInfo.isCompleted()); // Added

            editButton.setOnClickListener(v -> {
                FragmentActivity activity = (FragmentActivity) context;
                TodoEditorFragment todoEditorFragment = TodoEditorFragment.newInstance(todoList, getAdapterPosition(), "generic task");
                todoEditorFragment.show(activity.getSupportFragmentManager(), todoEditorFragment.getTag());
            });

            // Added
            todoCheckbox.setOnClickListener(v -> {
                boolean isChecked = todoCheckbox.isChecked();
                currTodoInfo.setCompleted(isChecked);
                if (context instanceof FragmentActivity) {
                    TodoListViewModel viewModel = new ViewModelProvider((FragmentActivity) context).get(TodoListViewModel.class);
                    viewModel.updateTodoCompletion(currTodoInfo, position);
                }
            });
        }
    }
}