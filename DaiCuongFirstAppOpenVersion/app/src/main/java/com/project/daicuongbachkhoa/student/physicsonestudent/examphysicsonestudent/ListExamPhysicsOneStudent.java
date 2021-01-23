package com.project.daicuongbachkhoa.student.physicsonestudent.examphysicsonestudent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.daicuongbachkhoa.R;
import com.project.daicuongbachkhoa.model.Exam;


public class ListExamPhysicsOneStudent extends AppCompatActivity {


    private RecyclerView revListExamPhysicsOneStudent;
    private AdapterExamPhysicsOneStudent adapterExam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_exam_physics_one_student);

        revListExamPhysicsOneStudent = findViewById(R.id.revListExamPhysicsOneStudent);
        setListExamPhysicsOneStudent();


    }

    private void setListExamPhysicsOneStudent() {
        revListExamPhysicsOneStudent.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<Exam> options =
                new FirebaseRecyclerOptions.Builder<Exam>().setQuery(FirebaseDatabase.getInstance().getReference().child("PhysicsOneTeacher").child("ExamInfo"), Exam.class).build();

        adapterExam = new AdapterExamPhysicsOneStudent(options);
        revListExamPhysicsOneStudent.setAdapter(adapterExam);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapterExam.startListening();

    }

    @Override
    protected void onStop() {
        super.onStop();
        adapterExam.stopListening();
    }
}