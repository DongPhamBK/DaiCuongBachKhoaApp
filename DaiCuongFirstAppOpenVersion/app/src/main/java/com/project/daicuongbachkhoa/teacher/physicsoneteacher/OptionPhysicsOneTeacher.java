package com.project.daicuongbachkhoa.teacher.physicsoneteacher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.project.daicuongbachkhoa.MainActivity;
import com.project.daicuongbachkhoa.R;
import com.project.daicuongbachkhoa.student.physicsonestudent.OutlinePhysicsOne;
import com.project.daicuongbachkhoa.teacher.TeacherHelp;
import com.project.daicuongbachkhoa.teacher.physicsoneteacher.examphysicsoneteacher.ListExamPhysicsOneTeacher;
import com.project.daicuongbachkhoa.teacher.physicsoneteacher.tasksphysicsoneteacher.TasksPhysicsOneTeacher;


public class OptionPhysicsOneTeacher extends AppCompatActivity {

    private TextView txtPhysicsOneTeacherLogout, txtPhysicsOneTeacherHelp;
    private Button btnOutlinePhysicsOneTeacher, btnListExamPhysicsOneTeacher, btnNotificationPhysicsOneTeacher, btnTasksPhysicsOneTeacher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_physics_one_teacher);

        txtPhysicsOneTeacherLogout = findViewById(R.id.txtPhysicsOneTeacherLogout);
        txtPhysicsOneTeacherHelp = findViewById(R.id.txtPhysicsOneTeacherHelp);
        btnOutlinePhysicsOneTeacher = findViewById(R.id.btnOutLinePhysicsOneTeacher);
        btnListExamPhysicsOneTeacher = findViewById(R.id.btnListExamPhysicsOneTeacher);
        btnNotificationPhysicsOneTeacher = findViewById(R.id.btnNotificationPhysicsOneTeacher);
        btnTasksPhysicsOneTeacher = findViewById(R.id.btnTasksPhysicsOneTeacher);
        btnTasksPhysicsOneTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tasksPhysicsOneTeacher();
            }
        });
        btnOutlinePhysicsOneTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                outlinePhysics();
            }
        });
        btnListExamPhysicsOneTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listExamPhysicsOneTeacher();
            }
        });

        btnNotificationPhysicsOneTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notificationPhysicsOneTeacher();
            }
        });
        txtPhysicsOneTeacherLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                physicsOneTeacherLogout();

            }
        });

        txtPhysicsOneTeacherHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                physicsOneTeacherHelp();
            }
        });
    }

    private void tasksPhysicsOneTeacher() {
        startActivity(new Intent(OptionPhysicsOneTeacher.this, TasksPhysicsOneTeacher.class));
    }

    private void notificationPhysicsOneTeacher() {
        startActivity(new Intent(OptionPhysicsOneTeacher.this, NotificationPhysicsOneTeacher.class));
    }

    private void listExamPhysicsOneTeacher() {
        startActivity(new Intent(OptionPhysicsOneTeacher.this, ListExamPhysicsOneTeacher.class));
    }

    private void outlinePhysics() {
        startActivity(new Intent(OptionPhysicsOneTeacher.this, OutlinePhysicsOne.class));
    }

    private void physicsOneTeacherHelp() {
        startActivity(new Intent(OptionPhysicsOneTeacher.this, TeacherHelp.class));
    }

    private void physicsOneTeacherLogout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(OptionPhysicsOneTeacher.this, MainActivity.class));
        finish();
    }
}