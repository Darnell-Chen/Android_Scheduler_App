package com.example.cs2340proj1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.cs2340proj1.ui.courses.CourseViewModel;
import com.example.cs2340proj1.ui.todo.TodoListViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.cs2340proj1.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private CourseViewModel courseViewModel;
    private TodoListViewModel todoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // The sharedPref will be created in the main activity as it's the parent context
        // of all of the fragments that we navigate to
        SharedPreferences sharedPref = getSharedPreferences("storedInformation", Context.MODE_PRIVATE);

        // Creates a view model, which will provide the live data to every fragment in the activity
        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        courseViewModel.setCourseList(new ArrayList<>());

        todoViewModel = new ViewModelProvider(this).get(TodoListViewModel.class);
        todoViewModel.setTodoList(new ArrayList<>());


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.class_schedule_dashboard, R.id.todo_list_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

}