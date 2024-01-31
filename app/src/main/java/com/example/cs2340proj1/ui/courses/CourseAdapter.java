package com.example.cs2340proj1.ui.courses;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs2340proj1.R;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseHolder> {
    public CourseAdapter(@NonNull View itemView) {
        super(itemView);
    }

    @NonNull
    @Override
    public CourseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CourseHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }








    class CourseHolder extends RecyclerView.ViewHolder{

        private TextView course, prof, start, end, dates, location;
        public CourseHolder(@NonNull View itemView) {
            super(itemView);
            course = itemView.findViewById(R.id.courseSection);
            prof = itemView.findViewById(R.id.professorName);
            start = itemView.findViewById(R.id.classTimeStart);
            end = itemView.findViewById(R.id.classTimeEnd);
            dates = itemView.findViewById(R.id.classDays);
            location = itemView.findViewById(R.id.classLocation);
        }

        void setCard(CourseInfo courseCard) {

            course.setText("Hello World");
            prof.setText("Hello Profesor");
            start.setText("start time");
            end.setText("Hello World");
            dates.setText("Hello World");
            location.setText("Hello World");

        }
    }
}
