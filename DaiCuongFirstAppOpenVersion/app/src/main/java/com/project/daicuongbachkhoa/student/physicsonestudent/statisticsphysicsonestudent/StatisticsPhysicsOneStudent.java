package com.project.daicuongbachkhoa.student.physicsonestudent.statisticsphysicsonestudent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.daicuongbachkhoa.R;

public class StatisticsPhysicsOneStudent extends AppCompatActivity {
    private String userID;
    private TextView txtHis;
    private DatabaseReference referenceRightAnswer, referenceStudent, referenceExam, answerPhysicsOne, answerExam1;
    private ValueEventListener referenceQuestion, referenceAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_physics_one_student);
        txtHis = findViewById(R.id.txtHis);
        FirebaseUser student = FirebaseAuth.getInstance().getCurrentUser();
        referenceStudent = FirebaseDatabase.getInstance().getReference("Students");
        userID = student.getUid();
        referenceAnswer = referenceStudent.child(userID).child("PhysicsOne")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String history = String.valueOf(snapshot.child("History").getValue());
                        txtHis.setText(history);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}