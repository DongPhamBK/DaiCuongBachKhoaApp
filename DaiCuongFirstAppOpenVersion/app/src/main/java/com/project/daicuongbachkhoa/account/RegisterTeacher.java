package com.project.daicuongbachkhoa.account;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.project.daicuongbachkhoa.R;
import com.project.daicuongbachkhoa.model.Teacher;

public class RegisterTeacher extends AppCompatActivity {

    private EditText txtNameTeacherRegister, txtIdTeacherRegister, txtEmailTeacherRegister;
    private EditText txtPasswordTeacherRegister, txtPasswordConfirmTeacherRegister, txtSubjectTeacherCode;
    private Button btnTeacherRegister;
    private TextView txtTeacherLogin;
    private ProgressBar progressBarTeacher;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_teacher);

        //find view by Id
        txtNameTeacherRegister = findViewById(R.id.txtNameTeacherRegister);
        txtIdTeacherRegister = findViewById(R.id.txtIdTeacherRegister);
        txtEmailTeacherRegister = findViewById(R.id.txtEmailTeacherRegister);
        txtPasswordTeacherRegister = findViewById(R.id.txtPasswordTeacherRegister);
        txtPasswordConfirmTeacherRegister = findViewById(R.id.txtPasswordConfirmTeacherRegister);
        txtSubjectTeacherCode = findViewById(R.id.txtSubjectTeacherCode);
        btnTeacherRegister = findViewById(R.id.btnTeacherRegister);
        txtTeacherLogin = findViewById(R.id.txtTeacherLogin);
        progressBarTeacher = findViewById(R.id.progressBarTeacher);
        auth = FirebaseAuth.getInstance();

        //Control
        btnTeacherRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teacherRegister();
            }
        });
        txtTeacherLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teacherLogin();
            }
        });

    }

    private void teacherLogin() {
        Intent intent = new Intent(RegisterTeacher.this, LoginUser.class);
        startActivity(intent);
        finish();
    }

    private void teacherRegister() {
        // get data input
        final String nameTeacherRegister = txtNameTeacherRegister.getText().toString().trim();
        final String idTeacherRegister = txtIdTeacherRegister.getText().toString().trim();
        final String emailTeacherRegister = txtEmailTeacherRegister.getText().toString().trim();
        final String passwordTeacherRegister = txtPasswordTeacherRegister.getText().toString().trim();
        final String passwordConfirmTeacherRegister = txtPasswordConfirmTeacherRegister.getText().toString().trim();
        final String subjectTeacherCode = txtSubjectTeacherCode.getText().toString().trim();

        //create process start register
        btnTeacherRegister.setText("ĐĂNG KÝ");
        btnTeacherRegister.setTextColor(Color.CYAN);
        progressBarTeacher.setVisibility(View.VISIBLE);
        btnTeacherRegister.setEnabled(false);

        // treat exception
        if (nameTeacherRegister.isEmpty()) {
            txtNameTeacherRegister.setError("Tên không được để trống !");
            txtNameTeacherRegister.requestFocus();
            progressBarTeacher.setVisibility(View.GONE);
            btnTeacherRegister.setEnabled(true);
            return;
        }
        if (idTeacherRegister.isEmpty()) {
            txtIdTeacherRegister.setError("Mã số giảng viên không để trống !");
            txtIdTeacherRegister.requestFocus();
            progressBarTeacher.setVisibility(View.GONE);
            btnTeacherRegister.setEnabled(true);
            return;
        }
        if (emailTeacherRegister.isEmpty()) {
            txtEmailTeacherRegister.setError("Gmail không được để trống !");
            txtEmailTeacherRegister.requestFocus();
            progressBarTeacher.setVisibility(View.GONE);
            btnTeacherRegister.setEnabled(true);
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emailTeacherRegister).matches()) {
            // check mail
            txtEmailTeacherRegister.setError("Gmail không hợp lệ !");
            txtEmailTeacherRegister.requestFocus();
            progressBarTeacher.setVisibility(View.GONE);
            btnTeacherRegister.setEnabled(true);
            return;
        }
        if (!emailTeacherRegister.contains("@gmail.com")) {
            txtEmailTeacherRegister.setError("Gmail không hợp lệ !");
            txtEmailTeacherRegister.requestFocus();
            progressBarTeacher.setVisibility(View.GONE);
            btnTeacherRegister.setEnabled(true);
            return;
        }
        if (passwordTeacherRegister.isEmpty()) {
            txtPasswordTeacherRegister.setError("Mật khẩu không được để trống !");
            txtPasswordTeacherRegister.requestFocus();
            progressBarTeacher.setVisibility(View.GONE);
            btnTeacherRegister.setEnabled(true);
            return;
        }
        if (passwordTeacherRegister.length() < 6) {
            txtPasswordTeacherRegister.setError("Mật khẩu không nhỏ hơn 6 kí tự !");
            txtPasswordTeacherRegister.requestFocus();
            progressBarTeacher.setVisibility(View.GONE);
            btnTeacherRegister.setEnabled(true);
            return;
        }
        if (passwordConfirmTeacherRegister.isEmpty()) {
            txtPasswordConfirmTeacherRegister.setError("Vui lòng xác thực mật khẩu !");
            txtPasswordConfirmTeacherRegister.requestFocus();
            progressBarTeacher.setVisibility(View.GONE);
            btnTeacherRegister.setEnabled(true);
            return;
        }
        if (!passwordTeacherRegister.equals(passwordConfirmTeacherRegister)) {
            Toast.makeText(RegisterTeacher.this, "Mật khẩu không khớp nhau !", Toast.LENGTH_SHORT).show();
            progressBarTeacher.setVisibility(View.GONE);
            btnTeacherRegister.setEnabled(true);
            return;
        }
        if (subjectTeacherCode.isEmpty()) {
            txtSubjectTeacherCode.setError("Mã bộ môn không được để trống !");
            txtSubjectTeacherCode.requestFocus();
            progressBarTeacher.setVisibility(View.GONE);
            btnTeacherRegister.setEnabled(true);
            return;
        }
        //mã từng bộ môn
        /*
        Đại số: ds2020
        Vật lý 1: vl12020
        Pháp luật: pl2020
         */

        //create account
        if (subjectTeacherCode.equals("ds2020") || subjectTeacherCode.equals("vl12020") || subjectTeacherCode.equals("pl2020")) {
            auth.createUserWithEmailAndPassword(emailTeacherRegister, passwordTeacherRegister)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Teacher teacher = new Teacher(nameTeacherRegister, idTeacherRegister, emailTeacherRegister, passwordTeacherRegister, "None");
                                FirebaseDatabase.getInstance().getReference("Teachers")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(teacher).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(RegisterTeacher.this, "Đăng ký thành công !Vui lòng \nchọn đăng nhập để tiếp tục !", Toast.LENGTH_LONG).show();
                                            progressBarTeacher.setVisibility(View.GONE);
                                            btnTeacherRegister.setText("Thành Công");
                                            btnTeacherRegister.setTextColor(Color.CYAN);
                                            //btnRegister.setEnabled(true);
                                            //finish();
                                            FirebaseAuth.getInstance().signOut();
                                            // nếu không có thao tác này, việc xác nhận email lần đầu coi như vô nghĩa !
                                            // vì hệ thống sẽ tự động đăng nhập luôn, bỏ qua việc xác nhận email !
                                        } else {
                                            Toast.makeText(RegisterTeacher.this, "Chưa được, thử lại đi !", Toast.LENGTH_LONG).show();
                                            progressBarTeacher.setVisibility(View.GONE);
                                            btnTeacherRegister.setText("Thất bại");
                                            txtNameTeacherRegister.setText("");
                                            txtIdTeacherRegister.setText("");// remove data invalid
                                            txtEmailTeacherRegister.setText("");
                                            btnTeacherRegister.setEnabled(true);
                                        }
                                    }
                                });

                            } else {
                                Toast.makeText(RegisterTeacher.this, "Ôi hỏng, có vẻ email sai hoặc đã được dùng !", Toast.LENGTH_LONG).show();
                                progressBarTeacher.setVisibility(View.GONE);
                                btnTeacherRegister.setText("ĐĂNG KÝ LẠI");
                                btnTeacherRegister.setEnabled(true);
                                txtEmailTeacherRegister.setText("");
                            }
                        }
                    });

        } else {
            Toast.makeText(RegisterTeacher.this, "Mã bộ môn không đúng", Toast.LENGTH_SHORT).show();
            progressBarTeacher.setVisibility(View.GONE);
            btnTeacherRegister.setText("ĐĂNG KÝ LẠI");
            btnTeacherRegister.setEnabled(true);
            txtSubjectTeacherCode.setText("");
        }
    }
}