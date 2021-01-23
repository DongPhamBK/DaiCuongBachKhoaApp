package com.project.daicuongbachkhoa.student.physicsonestudent.tasksphysicsonestudent;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.daicuongbachkhoa.R;

public class AdapterTasksPhysicsOneStudent extends RecyclerView.ViewHolder {
    public TextView txtTitleTasks;
    public TextView txtContentTasks;
    public AdapterTasksPhysicsOneStudent(@NonNull View itemView) {
        super(itemView);
        txtTitleTasks = itemView.findViewById(R.id.txtTitleTasks);
        txtContentTasks = itemView.findViewById(R.id.txtContentTasks);
    }
}
