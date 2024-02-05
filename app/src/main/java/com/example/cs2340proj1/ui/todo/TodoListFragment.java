package com.example.cs2340proj1.ui.todo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs2340proj1.R;
import com.example.cs2340proj1.databinding.FragmentTodoListBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TodoListFragment extends Fragment {

    private FragmentTodoListBinding binding;
    private TodoListViewModel viewModel;
    TodoEditorFragment todoEditorFragment;
    ArrayList<TodoInfo> todoList;
    TodoListAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentTodoListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        viewModel = new ViewModelProvider(requireActivity()).get(TodoListViewModel.class);

        initializeCardView(root);
        viewModel.getTodoList().observe(getViewLifecycleOwner(), new Observer<ArrayList<TodoInfo>>() {
            @Override
            public void onChanged(ArrayList<TodoInfo> newData) {
                todoList = viewModel.getTodoList().getValue();
                adapter.setTodoList(todoList);
            }
        });

        return root;
    }



    public void initializeCardView(View root) {

        todoList = new ArrayList<TodoInfo>();

        adapter = new TodoListAdapter(this.getContext(), todoList);

        // finds the recycler view and sets its adapter - which will funnel the data
        RecyclerView myRecycler = binding.todoRecyclerview;
        myRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        myRecycler.setAdapter(adapter);


        FloatingActionButton addTodoButton = root.findViewById(R.id.add_todo_button);
        addTodoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddTodoOptions();
            }
        });

    }

    private void showAddTodoOptions() {
        CharSequence[] options = new CharSequence[]{"Add Assignment", "Add Exam"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Choose an option");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    openTodoEditor("assignment");
                } else {
                    openTodoEditor("exam");
                }
            }
        });
        builder.show();
    }

    private void openTodoEditor(String type) {
        TodoEditorFragment todoEditorFragment = TodoEditorFragment.newInstance(new ArrayList<>(), -1, type);
        todoEditorFragment.show(getParentFragmentManager(), todoEditorFragment.getTag());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
