package com.project.daicuongbachkhoa.student.physicsonestudent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.daicuongbachkhoa.student.physicsonestudent.examphysicsonestudent.ExamPhysicsOneStudent;
import com.project.daicuongbachkhoa.student.physicsonestudent.examphysicsonestudent.ListExamPhysicsOneStudent;
import com.project.daicuongbachkhoa.student.physicsonestudent.quizphysicsonestudent.MenuQuizPhysicsOne;
import com.project.daicuongbachkhoa.R;
import com.project.daicuongbachkhoa.student.physicsonestudent.statisticsphysicsonestudent.StatisticsPhysicsOneStudent;
import com.project.daicuongbachkhoa.student.physicsonestudent.tasksphysicsonestudent.TasksPhysicsOneStudent;
import com.project.daicuongbachkhoa.student.physicsonestudent.theoryphysicsonestudent.ListChapterPhysicsOneStudent;
import com.project.daicuongbachkhoa.teacher.physicsoneteacher.tasksphysicsoneteacher.TasksPhysicsOneTeacher;

public class OptionPhysicsOneStudent extends AppCompatActivity {

    private Button btnOutlinePhysicsOneStudent,
            btnTheoryPhysicsOneStudent,
            btnQuizPhysicsOne,
            btnListExamPhysicsOneStudent,
            btnExperimentPhysicsOneStudent,
            btnStatisticsPhysicsOneStudent,
            btnTasksPhysicsOneStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_physics_one_student);
        btnOutlinePhysicsOneStudent = findViewById(R.id.btnOutlinePhysicsOneStudent);
        btnTheoryPhysicsOneStudent = findViewById(R.id.btnTheoryPhysicsOneStudent);
        btnQuizPhysicsOne = findViewById(R.id.btnQuizPhysicsOne);
        btnListExamPhysicsOneStudent = findViewById(R.id.btnListExamPhysicsOneStudent);
        btnExperimentPhysicsOneStudent = findViewById(R.id.btnExperimentPhysicsOneStudent);
        btnStatisticsPhysicsOneStudent = findViewById(R.id.btnStatisticsPhysicsOneStudent);
        btnTasksPhysicsOneStudent = findViewById(R.id.btnTasksPhysicsOneStudent);
        btnTasksPhysicsOneStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTasksPhysicsOneStudent();
            }
        });
        btnStatisticsPhysicsOneStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statisticsPhysicsOneStudent();
            }
        });
        btnExperimentPhysicsOneStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                experimentPhysicsOneStudent();
            }
        });
        btnOutlinePhysicsOneStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                outlinePhysicsOneStudent();
            }
        });
        btnTheoryPhysicsOneStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                theoryPhysicsOneStudent();
            }
        });
        btnQuizPhysicsOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quizPhisicsOneStudent();
            }
        });
        btnListExamPhysicsOneStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listExamPhysicsOneStudent();
            }
        });
    }

    private void showTasksPhysicsOneStudent() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseUser student = FirebaseAuth.getInstance().getCurrentUser();
        String studentID = student.getUid();
        ValueEventListener referenceTasks = database.getReference("Students").child(studentID).child("Tasks").child("PhysicsOne").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String idTeacher = String.valueOf(snapshot.child("idTeacher").getValue().toString());
                String linkGroup = String.valueOf(snapshot.child("linkGroup").getValue().toString());
                Intent intent = new Intent(OptionPhysicsOneStudent.this, TasksPhysicsOneStudent.class);
                intent.putExtra("ID_TEACHER_PHYSICS_ONE_STUDENT", idTeacher);
                intent.putExtra("LINK_GROUP_PHYSICS_ONE_STUDENT", linkGroup);
                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(OptionPhysicsOneStudent.this, "Hmmm ! Có vẻ như có lỗi xảy ra !", Toast.LENGTH_SHORT).show();
            }
        });


        // startActivity(new Intent(OptionPhysicsOneStudent.this, TasksPhysicsOneStudent.class));
    }

    private void statisticsPhysicsOneStudent() {
        startActivity(new Intent(OptionPhysicsOneStudent.this, StatisticsPhysicsOneStudent.class));
    }


    /* BroadcastReceiver wifiReceiver = new BroadcastReceiver() {
         @Override
         public void onReceive(Context context, Intent intent) {
             ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
             if (connectivityManager.getActiveNetworkInfo() != null) {
                 btnListExamPhysicsOneStudent.setEnabled(true);// have internet
                 btnListExamPhysicsOneStudent.setTextColor(Color.WHITE);
                 //Toast.makeText(OptionPhysicsOneStudent.this, "Bạn đã có thể đăng nhập", Toast.LENGTH_LONG).show();
             } else {
                 btnListExamPhysicsOneStudent.setEnabled(false);// need internet
                 btnListExamPhysicsOneStudent.setTextColor(Color.CYAN);
                 //Toast.makeText(OptionPhysicsOneStudent.this, "Vui lòng mở Internet lên !", Toast.LENGTH_LONG).show();
             }

         }
     };

     @Override
     // check wifi on/off
     protected void onResume() {
         super.onResume();
         IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
         registerReceiver(wifiReceiver, filter);

     }

     @Override
     protected void onPause() {
         super.onPause();
         if (wifiReceiver != null) {
             unregisterReceiver(wifiReceiver);
         }
     }
 */
    private void listExamPhysicsOneStudent() {
        startActivity(new Intent(OptionPhysicsOneStudent.this, ListExamPhysicsOneStudent.class));
    }

    private void quizPhisicsOneStudent() {
        startActivity(new Intent(OptionPhysicsOneStudent.this, MenuQuizPhysicsOne.class));
    }

    private void theoryPhysicsOneStudent() {
        startActivity(new Intent(OptionPhysicsOneStudent.this, ListChapterPhysicsOneStudent.class));
    }

    private void outlinePhysicsOneStudent() {
        startActivity(new Intent(OptionPhysicsOneStudent.this, OutlinePhysicsOne.class));
    }

    private void experimentPhysicsOneStudent() {
        startActivity(new Intent(OptionPhysicsOneStudent.this, ExperimentPhysicsOneStudent.class));
    }

}