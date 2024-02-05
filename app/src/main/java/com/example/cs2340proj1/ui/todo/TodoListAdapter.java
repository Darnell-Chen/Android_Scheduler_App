package com.example.cs2340proj1.ui.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs2340proj1.R;
import com.example.cs2340proj1.ui.courses.CourseEditorFragment;
import com.example.cs2340proj1.ui.courses.CourseInfo;

import java.util.ArrayList;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.TodoHolder> {

    // We'll make a list of courseInfos in both CourseFragment and here - both of which are synced
    // This will make it easier for the two classes to share infos since both can create/edit cards
    private ArrayList<TodoInfo> myTodoList;
    private Context context;



    public TodoListAdapter(Context context, ArrayList<TodoInfo> inputCourses) {
        this.myTodoList = inputCourses;
        this.context = context;
    }

    @NonNull
    @Override
    public TodoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cardView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_todo, parent, false);
        return new TodoHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoHolder holder, int position) {
        TodoInfo todoInfo = myTodoList.get(position);
        holder.setCard(todoInfo);
    }

    @Override
    public int getItemCount() {
        return myTodoList.size();
    }

    public void setTodoList(ArrayList<TodoInfo> newList) {
        myTodoList = newList;
        notifyDataSetChanged();
    }


    class TodoHolder extends RecyclerView.ViewHolder{
        private TextView name, course, date, time, location;
        public ImageButton editButton;
        public int position;
        public TodoHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.todo_card_name);
            course = itemView.findViewById(R.id.todo_card_course);
            location = itemView.findViewById(R.id.todo_card_location);
            date = itemView.findViewById(R.id.todo_card_date);
            time = itemView.findViewById(R.id.todo_card_time);


            editButton = itemView.findViewById(R.id.todo_edit_button);

            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentActivity activity = (FragmentActivity) context;
                    TodoEditorFragment todoEditorFragment = (TodoEditorFragment) TodoEditorFragment.newInstance(myTodoList, getAdapterPosition());
                    todoEditorFragment.show(activity.getSupportFragmentManager(), todoEditorFragment.getTag());
                }
            });
        }


        void setCard(TodoInfo currTodoInfo) {
            name.setText(currTodoInfo.getTodoName());
            course.setText(currTodoInfo.getCourse());
            location.setText(currTodoInfo.getLocation());
            date.setText(currTodoInfo.getDate());
            time.setText(currTodoInfo.getTime());
        }
    }
}
