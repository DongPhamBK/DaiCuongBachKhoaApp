package com.project.daicuongbachkhoa.student.physicsonestudent.theoryphysicsonestudent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.project.daicuongbachkhoa.R;
import com.project.daicuongbachkhoa.model.Chapter;

import java.util.List;

public class AdapterChapterPhysicsOneStudent extends ArrayAdapter<Chapter> {

    Activity context;// màn hình hiển thị
    int resource;//layout cần thiết, mong muốn
    List<Chapter> objects;// nguồn dữ liệu muốn hiển thị

    public AdapterChapterPhysicsOneStudent(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public AdapterChapterPhysicsOneStudent(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public AdapterChapterPhysicsOneStudent(@NonNull Activity context, int resource, @NonNull List<Chapter> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource, null);

        Button btnChapterPhysicsOneStudent = row.findViewById(R.id.btnChapterPhysicsOneStudent);
        TextView txtChapterNamePhysicsOneStudent = row.findViewById(R.id.txtChapterNamePhysicsOneStudent);
        TextView txtChapterContentPhysicsOneStudent = row.findViewById(R.id.txtChapterContentPhysicsOneStudent);

        final Chapter chapter = this.objects.get(position);
        btnChapterPhysicsOneStudent.setText("Xem ngay !");
        txtChapterNamePhysicsOneStudent.setText(chapter.getChapterName());
        txtChapterContentPhysicsOneStudent.setText(chapter.getChapterContent());
        btnChapterPhysicsOneStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openChapterPhysicsOneStudent(view,chapter);
            }
        });
        return row;
    }

    private void openChapterPhysicsOneStudent(View view,Chapter chapter) {
        //Toast.makeText(this.context, "Ok", Toast.LENGTH_SHORT).show();
        final AppCompatActivity activity = (AppCompatActivity) view.getContext();
        Intent intent = new Intent(activity,TheoryPhysicsOneStudent.class);
        intent.putExtra("NAME_CHAPTER_PHYSICS_ONE_STUDENT", chapter.getChapterName());
        intent.putExtra("CODE_CHAPTER_PHYSICS_ONE_STUDENT", chapter.getChapterCode());
        activity.startActivity(intent);

    }
}
