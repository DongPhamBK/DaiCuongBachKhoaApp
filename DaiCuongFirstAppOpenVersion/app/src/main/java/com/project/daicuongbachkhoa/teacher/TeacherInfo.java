package com.project.daicuongbachkhoa.teacher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.daicuongbachkhoa.MainActivity;
import com.project.daicuongbachkhoa.R;
import com.project.daicuongbachkhoa.teacher.algebrateacher.OptionAlgebraTeacher;
import com.project.daicuongbachkhoa.teacher.lawteacher.OptionLawTeacher;
import com.project.daicuongbachkhoa.teacher.physicsoneteacher.OptionPhysicsOneTeacher;
import com.project.daicuongbachkhoa.model.Teacher;

public class TeacherInfo extends AppCompatActivity {

    private FirebaseUser teacher;
    private DatabaseReference reference;
    private String teacherInfo;
    private String teacherCode;
    private EditText txtTeacherCode;
    private TextView txtLogoutTeacher;
    private Button btnTeacherVerification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_info);

        txtTeacherCode = findViewById(R.id.txtTeacherCode);
        txtLogoutTeacher = findViewById(R.id.txtLogoutTeacher);
        btnTeacherVerification = findViewById(R.id.btnTeacherVerification);

        btnTeacherVerification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teacherVerification();
            }
        });

        txtLogoutTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutTeacher();
            }
        });
        getNameOfTeacher();
    }

    private void logoutTeacher() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(TeacherInfo.this, MainActivity.class));
        finish();
    }

    private void teacherVerification() {

        String getTeacherCode = txtTeacherCode.getText().toString();
        if (getTeacherCode.equals(teacherCode)) {
            if (getTeacherCode.contains("ph1")) {
                startActivity(new Intent(TeacherInfo.this, OptionPhysicsOneTeacher.class));
                finish();
            } else if (getTeacherCode.contains("al")) {
                startActivity(new Intent(TeacherInfo.this, OptionAlgebraTeacher.class));
                finish();
            } else if (getTeacherCode.contains("la")) {
                startActivity(new Intent(TeacherInfo.this, OptionLawTeacher.class));
                finish();
            } else {
                Toast.makeText(this, "Vui lòng nhập lại mã code !", Toast.LENGTH_SHORT).show();
                finish();
            }
        } else {
            Toast.makeText(this, "Mã không hợp lệ !\nVui lòng nhập lại !", Toast.LENGTH_SHORT).show();
        }
    }

    private void getNameOfTeacher() {

        teacher = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Teachers");
        teacherInfo = teacher.getUid();
        final TextView txtNameTeacher = findViewById(R.id.txtNameTeacher);

        reference.child(teacherInfo).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Teacher teacherProfile = snapshot.getValue(Teacher.class);
                if (teacherProfile != null) {
                    String nameTeacher = teacherProfile.getNameTeacher();
                    teacherCode = teacherProfile.getCodeTeacher();
                    txtTeacherCode.setText(teacherCode);
                    txtNameTeacher.setText(nameTeacher);
                    if(txtNameTeacher.getText().toString() != null){
                        teacherVerification();//auto login
                    }else{
                        Toast.makeText(TeacherInfo.this, "Vui lòng nhập mã xác thực !", Toast.LENGTH_SHORT).show();
                    }
            }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                txtNameTeacher.setText("Lỗi mạng hoặc bạn đăng nhập không đúng quyền !");
                Toast.makeText(TeacherInfo.this, "Hmmm ! Có vẻ lỗi mạng hoặc lí do gì đó !", Toast.LENGTH_SHORT).show();

            }
        });

    }
}