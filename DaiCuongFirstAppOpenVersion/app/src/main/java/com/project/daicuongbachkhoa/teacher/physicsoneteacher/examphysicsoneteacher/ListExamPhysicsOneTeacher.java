package com.project.daicuongbachkhoa.teacher.physicsoneteacher.examphysicsoneteacher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.daicuongbachkhoa.R;
import com.project.daicuongbachkhoa.model.Exam;


public class ListExamPhysicsOneTeacher extends AppCompatActivity {

    private RecyclerView revListExamPhysicsOneTeacher;
    AdapterExamPhysicsOneTeacher adapterExam;
    private DatabaseReference reference, referenceTeacher;
    private DatabaseReference listPhysicsOne, answerExam1, answerPhysicsOne;
    private ValueEventListener referenceExam;
    private FirebaseUser teacher;
    private String teacherID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_exam_physics_one_teacher);
        revListExamPhysicsOneTeacher = findViewById(R.id.revListExamPhysicsOneTeacher);
        setListExamPhysicsOneTeacher();

    }

    private void setListExamPhysicsOneTeacher() {

       teacher = FirebaseAuth.getInstance().getCurrentUser();
       teacherID = teacher.getUid();
       referenceTeacher = FirebaseDatabase.getInstance().getReference("Teachers");

        revListExamPhysicsOneTeacher.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<Exam> options =
                new FirebaseRecyclerOptions.Builder<Exam>().setQuery(referenceTeacher.child(teacherID).child("Subjects").child("PhysicsOne").child("Exam"), Exam.class).build();
        adapterExam = new AdapterExamPhysicsOneTeacher(options);

        revListExamPhysicsOneTeacher.setAdapter(adapterExam);
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