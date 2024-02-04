package com.example.cs2340proj1.ui.courses;

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

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseHolder> {

    // We'll make a list of courseInfos in both CourseFragment and here - both of which are synced
    // This will make it easier for the two classes to share infos since both can create/edit cards
    private ArrayList<CourseInfo> myCourses;

    private Context context;



    public CourseAdapter(Context context, ArrayList<CourseInfo> inputCourses) {
        this.myCourses = inputCourses;
        this.context = context;
    }

    @NonNull
    @Override
    public CourseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cardView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_layout, parent, false);
        return new CourseHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseHolder holder, int position) {
        CourseInfo courseInfo = myCourses.get(position);
        holder.setCard(courseInfo);
    }

    @Override
    public int getItemCount() {
        return myCourses.size();
    }

    public void setCourseList(ArrayList<CourseInfo> newList) {
        myCourses = newList;
        notifyDataSetChanged();
    }


    class CourseHolder extends RecyclerView.ViewHolder{
        private TextView course, prof, start, end, dates, location;
        public ImageButton editButton;
        public int position;
        public CourseHolder(@NonNull View itemView) {
            super(itemView);
            course = itemView.findViewById(R.id.courseSection);
            prof = itemView.findViewById(R.id.professorName);
            start = itemView.findViewById(R.id.classTimeStart);
            end = itemView.findViewById(R.id.classTimeEnd);
            dates = itemView.findViewById(R.id.classDays);
            location = itemView.findViewById(R.id.classLocation);


            editButton = itemView.findViewById(R.id.course_edit_button);

            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentActivity activity = (FragmentActivity) context;
                    CourseEditorFragment courseEditorFragment = (CourseEditorFragment) CourseEditorFragment.newInstance(myCourses, getAdapterPosition());

                    courseEditorFragment.show(activity.getSupportFragmentManager(), courseEditorFragment.getTag());
                }
            });
        }


        void setCard(CourseInfo currCourseInfo) {
            course.setText(currCourseInfo.getCourseName());
            prof.setText(currCourseInfo.getProfessor());
            start.setText(currCourseInfo.getStartTime());
            end.setText(currCourseInfo.getEndTime());
            dates.setText(getDates(currCourseInfo));
            location.setText(currCourseInfo.getLocation());

        }

        private String getDates(CourseInfo currCourseInfo) {

            String returnedString = "";
            String[] dayList = currCourseInfo.getDate();

            for (int i = 0; i < dayList.length; i++) {
                returnedString = returnedString.concat(dayList[i]);
            }

            System.out.println(returnedString);

            if (returnedString.equals("")) {
                return "TBA / Online Class";
            } else {
                return returnedString;
            }
        }

    }
}
