package com.project.daicuongbachkhoa.teacher.physicsoneteacher.tasksphysicsoneteacher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.daicuongbachkhoa.R;
import com.project.daicuongbachkhoa.model.Tasks;

public class CreateTasksPhysicsOneTeacher extends AppCompatActivity {

    private EditText txtNewTitleTasksPhysicsOneTeacher, txtNewContentTasksPhysicsOneTeacher;
    private Button btnConfirmNewTasksPhysicsOneTeacher;
    private FirebaseUser teacher;
    private FirebaseDatabase database;
    private DatabaseReference referenceNewTasks;
    private String teacherID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_tasks_physics_one_teacher);
        txtNewTitleTasksPhysicsOneTeacher = findViewById(R.id.txtNewTitleTasksPhysicsOneTeacher);
        txtNewContentTasksPhysicsOneTeacher = findViewById(R.id.txtNewContentTasksPhysicsOneTeacher);
        btnConfirmNewTasksPhysicsOneTeacher = findViewById(R.id.btnConfirmNewTasksPhysicsOneTeacher);
        teacher = FirebaseAuth.getInstance().getCurrentUser();
        teacherID = teacher.getUid();
        database = FirebaseDatabase.getInstance();
        referenceNewTasks = database.getReference("Teachers").child(teacherID).child("Subjects").child("PhysicsOne").child("Tasks");

        btnConfirmNewTasksPhysicsOneTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newTitle = txtNewTitleTasksPhysicsOneTeacher.getText().toString();
                String newContent = txtNewContentTasksPhysicsOneTeacher.getText().toString();
                if (!TextUtils.isEmpty(newTitle) && !TextUtils.isEmpty(newContent)) {
                    Tasks tasks = new Tasks(newTitle, newContent);
                    referenceNewTasks.push().setValue(tasks).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(CreateTasksPhysicsOneTeacher.this, "Tạo mới nhiệm vụ thành công !", Toast.LENGTH_SHORT).show();
                            txtNewTitleTasksPhysicsOneTeacher.setText("");
                            txtNewContentTasksPhysicsOneTeacher.setText("");
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(CreateTasksPhysicsOneTeacher.this, "Hmmm ! Có vẻ có lỗi xảy ra !", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else {
                    Toast.makeText(CreateTasksPhysicsOneTeacher.this, "Vui lòng điền đầy đủ thông tin !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}