package com.project.daicuongbachkhoa.teacher.physicsoneteacher.tasksphysicsoneteacher;

import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.daicuongbachkhoa.R;

public class AdapterTasksPhysicsOneTeacher extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
    public TextView txtTitleTasks;
    public TextView txtContentTasks;
    public AdapterTasksPhysicsOneTeacher(@NonNull View itemView) {
        super(itemView);
        txtTitleTasks = itemView.findViewById(R.id.txtTitleTasks);
        txtContentTasks = itemView.findViewById(R.id.txtContentTasks);
        itemView.setOnCreateContextMenuListener(this);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        menu.add(0, 0, getAdapterPosition(), "Sửa");//cập nhật dữ liệu
        menu.add(0, 1, getAdapterPosition(), "Xoá");
    }
}
