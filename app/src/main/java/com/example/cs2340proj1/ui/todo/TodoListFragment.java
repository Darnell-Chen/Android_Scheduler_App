package com.example.cs2340proj1.ui.todo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cs2340proj1.databinding.FragmentTodoListBinding;

public class TodoListFragment extends Fragment {

    private FragmentTodoListBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TodoListViewModel dashboardViewModel =
                new ViewModelProvider(this).get(TodoListViewModel.class);

        binding = FragmentTodoListBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.tasksText;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}