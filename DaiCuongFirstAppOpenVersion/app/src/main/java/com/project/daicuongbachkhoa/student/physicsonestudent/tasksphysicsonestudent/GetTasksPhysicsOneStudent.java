package com.project.daicuongbachkhoa.student.physicsonestudent.tasksphysicsonestudent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.daicuongbachkhoa.R;
import com.project.daicuongbachkhoa.student.physicsonestudent.examphysicsonestudent.ExamPhysicsOneStudent;
import com.project.daicuongbachkhoa.student.physicsonestudent.examphysicsonestudent.ResultExamPhysicsOneStudent;

public class GetTasksPhysicsOneStudent extends AppCompatActivity {

    private TextView
            txtIdTeacherPhysicsOneStudent,
            txtLinkGroupPhysicsOneStudent;
    private Button btnUpdateGroupPhysicsOneStudent;

    private FirebaseUser student;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_tasks_physics_one_student);
        txtIdTeacherPhysicsOneStudent = findViewById(R.id.txtIdTeacherPhysicsOneStudent);
        txtLinkGroupPhysicsOneStudent = findViewById(R.id.txtLinkGroupPhysicsOneStudent);
        btnUpdateGroupPhysicsOneStudent = findViewById(R.id.btnUpdateGroupPhysicsOneStudent);
        student = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Students").child(student.getUid()).child("Tasks").child("PhysicsOne");

        getIdAndLink();
        btnUpdateGroupPhysicsOneStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateGroupPhysicsOneStudent();
            }
        });

    }

    private void updateGroupPhysicsOneStudent() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn chắc chắn muốn cập nhật dữ liệu ?")
                .setCancelable(false)
                .setPositiveButton("XÁC NHẬN", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String idTeacherUpdate = txtIdTeacherPhysicsOneStudent.getText().toString();
                        String linkGroupUpdate = txtLinkGroupPhysicsOneStudent.getText().toString();
                        reference.child("idTeacher").setValue(idTeacherUpdate);
                        reference.child("linkGroup").setValue(linkGroupUpdate);
                        Toast.makeText(GetTasksPhysicsOneStudent.this, "Cập nhật dữ liệu thành công !", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .setNegativeButton("THOÁT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private void getIdAndLink() {
        ValueEventListener referenceTasks = reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String idTeacher = String.valueOf(snapshot.child("idTeacher").getValue().toString());
                txtIdTeacherPhysicsOneStudent.setText(idTeacher);
                String linkGroup = String.valueOf(snapshot.child("linkGroup").getValue().toString());
                txtLinkGroupPhysicsOneStudent.setText(linkGroup);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(GetTasksPhysicsOneStudent.this, "Hmmm ! Có vẻ như có lỗi xảy ra !", Toast.LENGTH_SHORT).show();
            }
        });

    }
}