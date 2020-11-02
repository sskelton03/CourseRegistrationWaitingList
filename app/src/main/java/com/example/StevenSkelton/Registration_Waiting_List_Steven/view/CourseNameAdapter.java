package com.example.StevenSkelton.Registration_Waiting_List_Steven.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.StevenSkelton.StevenSkeltonWaitingList.R;
import com.example.StevenSkelton.Registration_Waiting_List_Steven.database.model.CourseName;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CourseNameAdapter extends RecyclerView.Adapter<CourseNameAdapter.MyViewHolder> {
    private Context context;
    private List<CourseName> courseList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView TableCourse;
        public TextView TableStudent;
        public TextView PriorityCase;
        public TextView dot;
        public TextView TableTime;

        public MyViewHolder(View view) {
            super(view);
            TableCourse = view.findViewById(R.id.TableCourse);
            TableStudent = view.findViewById(R.id.TableStudent);
            PriorityCase = view.findViewById(R.id.PriorityCase);
            dot = view.findViewById(R.id.dot);
            TableTime = view.findViewById(R.id.TableTime);
        }
    }


    public CourseNameAdapter(Context context, List<CourseName> notesList) {
        this.context = context;
        this.courseList = notesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CourseName TableCourse = courseList.get(position);

        holder.TableCourse.setText(TableCourse.getTableCOURSE());
        holder.TableStudent.setText(TableCourse.getTableSTUDENT());
        holder.PriorityCase.setText(TableCourse.getPriorityCASE());

        // Displaying dot from HTML character code
        holder.dot.setText(Html.fromHtml("&#8226;"));

        // Formatting and displaying timestamp
        holder.TableTime.setText(formatDate(TableCourse.getTableTIME()));
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    /**
     * Formatting timestamp to `MMM d` format
     * Input: 2018-02-21 00:15:42
     * Output: Feb 21
     */
    private String formatDate(String dateStr) {
        try {
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = fmt.parse(dateStr);
            SimpleDateFormat fmtOut = new SimpleDateFormat("MMM d");
            return fmtOut.format(date);
        } catch (ParseException e) {

        }

        return "";
    }
}