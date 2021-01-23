package com.project.daicuongbachkhoa.student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.project.daicuongbachkhoa.menubar.MenuBar;
import com.project.daicuongbachkhoa.model.Student;

public class StudentInfo extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference reference;
    private String userId;
    private Button btnConti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        btnConti = findViewById(R.id.btnConti);

        btnConti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                continu();
            }
        });
        getInformationUser();
    }

    private void continu() {
        startActivity(new Intent(StudentInfo.this, MenuBar.class));
        finish();
    }

    private void getInformationUser() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Students");
        userId = user.getUid();
        final TextView txtName = findViewById(R.id.txtName);
        final TextView txtEmail = findViewById(R.id.txtEmail);
        final TextView txtId = findViewById(R.id.txtId);

        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Student studentProfile = snapshot.getValue(Student.class);

                if (studentProfile != null) {
                    String name = studentProfile.getName();
                    String id = studentProfile.getId();
                    String email = studentProfile.getEmail();

                    txtName.setText("Tên sinh viên: " + name);
                    txtId.setText("MSSV: " + id);
                    txtEmail.setText("Email: " + email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                txtName.setText("Lỗi mạng hoặc bạn đăng nhập không đúng quyền !");
                Toast.makeText(StudentInfo.this, "Hmmm ! Có vẻ lỗi mạng hoặc lí do gì đó !", Toast.LENGTH_SHORT).show();
            }
        });
    }
    /*private void logoutAccount() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(HomeActivity.this, LoginUser.class));
        finish();
    }*/
}