package com.example.cs2340proj1.ui.todo;

import android.os.Bundle;
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



        // gets floating action button and makes it so bottom sheet (editor) opens
        // when you click on it
        FloatingActionButton addTodoButton = root.findViewById(R.id.add_todo_button);
        addTodoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todoEditorFragment = new TodoEditorFragment();
                todoEditorFragment.show(getParentFragmentManager(), todoEditorFragment.getTag());
            }

        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}