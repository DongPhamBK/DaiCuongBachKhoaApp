package com.project.daicuongbachkhoa.account;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.project.daicuongbachkhoa.R;
import com.project.daicuongbachkhoa.menubar.MenuBar;
import com.project.daicuongbachkhoa.student.StudentInfo;
import com.project.daicuongbachkhoa.student.physicsonestudent.OptionPhysicsOneStudent;
import com.project.daicuongbachkhoa.teacher.TeacherInfo;
import com.project.daicuongbachkhoa.teacher.physicsoneteacher.OptionPhysicsOneTeacher;


public class LoginUser extends AppCompatActivity {

    private TextView txtRegister, txtResetPassword;
    private EditText txtEmailLogin, txtPasswordLogin;
    private Button btnLogin;
    private ProgressBar progressBarLogin;// progressBar hiển thị trạng thái tiến trình !
    //private RadioGroup radGroup;
    private RadioButton radTeacher, radStudent;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;// để nhớ mật khẩu và autoLogin

    //private int loginKey;// xác thực đã thực hiện việc đăng nhập

    @Override
    protected void onStart() {
        super.onStart();
      //cum check mới
        //radGroup = findViewById(R.id.radGroup);
        radTeacher = findViewById(R.id.radTeacher);
        radStudent = findViewById(R.id.radStudent);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();// nhớ tài khoản
        radStudent.setChecked(updateRadio("RAD_STUDENT"));
        radTeacher.setChecked(updateRadio("RAD_TEACHER"));// kiểm trang trạng thái
        radStudent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean radStudentIsChecked) {
                saveRadio("RAD_STUDENT", radStudentIsChecked);
            }
        });
        radTeacher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean radTeacherIsChecked) {
                saveRadio("RAD_TEACHER", radTeacherIsChecked);
            }
        });
        //cum check moi
        // biết ngắn gọn thế này có phải nhanh không !
        if (firebaseUser != null) {// nếu đã có tài khoản
            // if(firebaseUser.getUid())
            FirebaseUser userLogin = FirebaseAuth.getInstance().getCurrentUser();
            if (userLogin.isEmailVerified()) {
                if (radTeacher.isChecked()) {
                    //startActivity(new Intent(LoginUser.this, TeacherInfo.class));
                    // ok, we do pakour !
                    startActivity(new Intent(LoginUser.this, TeacherInfo.class));
                    finish();
                } else if (radStudent.isChecked()) {
                    startActivity(new Intent(LoginUser.this, StudentInfo.class));
                    // startActivity(new Intent(LoginUser.this, MenuBar.class));
                    finish();
                } else {
                    Toast.makeText(LoginUser.this, "Vui lòng chọn quyền đăng nhập giảng viên hoặc sinh viên !", Toast.LENGTH_SHORT).show();
                }
            } else {
                userLogin.sendEmailVerification();
                Toast.makeText(LoginUser.this, "Kiểm tra email để xác nhận \nlần đăng nhập đầu tiên ! Sau đó \nthoát ứng dụng và đăng nhập lại !", Toast.LENGTH_LONG).show();
            }

           /* if (radTeacher.isChecked()) {
                //startActivity(new Intent(LoginUser.this, TeacherInfo.class));
                // ok, we do pakour !
                startActivity(new Intent(LoginUser.this, OptionPhysicsOneTeacher.class));
                finish();

            } else {
                startActivity(new Intent(LoginUser.this, StudentInfo.class));
                finish();
            }*/
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        txtRegister = findViewById(R.id.txtRegister);
        txtResetPassword = findViewById(R.id.txtResetPassword);
        txtEmailLogin = findViewById(R.id.txtEmailLogin);
        txtPasswordLogin = findViewById(R.id.txtPasswordLogin);
        btnLogin = findViewById(R.id.btnLogin);
        progressBarLogin = findViewById(R.id.progressBarLogin);
        /*ngày trc cụm check ở đây với radioButton*/
        mAuth = FirebaseAuth.getInstance();// connect
        //loginKey = RememberUser.loadKeyPreference(this);// load d


        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerAccount();
            }
        });
        txtResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPasswordAccount();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginAccount();
            }
        });
    }

    private void registerAccount() {
        startActivity(new Intent(LoginUser.this, RegisterUser.class));
        finish();
    }

    private void resetPasswordAccount() {
        startActivity(new Intent(LoginUser.this, ResetPasswordUser.class));
    }

    private void loginAccount() {
        // chỉnh lại chế độ đăng nhập ! = Restart process login
        btnLogin.setEnabled(false);
        progressBarLogin.setVisibility(View.VISIBLE); // khoá trạng thái

        String emailLogin = txtEmailLogin.getText().toString().trim();
        String passwordLogin = txtPasswordLogin.getText().toString().trim();

        //treat exception
        if (emailLogin.isEmpty()) {
            txtEmailLogin.setError("Gmail không để trống !");
            txtEmailLogin.requestFocus();
            progressBarLogin.setVisibility(View.GONE);
            btnLogin.setEnabled(true);
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emailLogin).matches()) {
            txtEmailLogin.setError("Gmail không hợp lệ !");
            txtEmailLogin.requestFocus();
            progressBarLogin.setVisibility(View.GONE);
            btnLogin.setEnabled(true);
            return;
        }
        if (!emailLogin.contains("@gmail.com")) {
            txtEmailLogin.setError("Gmail không hợp lệ !");
            txtEmailLogin.requestFocus();
            progressBarLogin.setVisibility(View.GONE);
            btnLogin.setEnabled(true);
            return;
        }
        if (passwordLogin.isEmpty()) {
            txtPasswordLogin.setError("Không được để trống");
            txtPasswordLogin.requestFocus();
            progressBarLogin.setVisibility(View.GONE);
            btnLogin.setEnabled(true);
            return;
        }

        // login with account gmail
        mAuth.signInWithEmailAndPassword(emailLogin, passwordLogin)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user.isEmailVerified()) {
                                if (radTeacher.isChecked()) {
                                    //startActivity(new Intent(LoginUser.this, TeacherInfo.class));
                                    // ok, we do pakour !
                                    startActivity(new Intent(LoginUser.this, TeacherInfo.class));
                                    finish();
                                } else if (radStudent.isChecked()) {
                                    startActivity(new Intent(LoginUser.this, StudentInfo.class));
                                   // startActivity(new Intent(LoginUser.this, MenuBar.class));
                                    finish();
                                } else {
                                    Toast.makeText(LoginUser.this, "Vui lòng chọn quyền đăng nhập giảng viên hoặc sinh viên !", Toast.LENGTH_SHORT).show();
                                    progressBarLogin.setVisibility(View.GONE);
                                    btnLogin.setEnabled(true);
                                }
                            } else {
                                user.sendEmailVerification();
                                Toast.makeText(LoginUser.this, "Kiểm tra email để xác nhận \nlần đăng nhập đầu tiên ! Sau đó \nthoát ứng dụng và đăng nhập lại !", Toast.LENGTH_LONG).show();
                                progressBarLogin.setVisibility(View.GONE);
                                btnLogin.setEnabled(true);
                            }
                        } else {
                            Toast.makeText(LoginUser.this, "Không đăng nhập được ! \nVui lòng kiểm tra lại Internet \nhoặc thông tin đăng nhập !", Toast.LENGTH_LONG).show();
                            progressBarLogin.setVisibility(View.GONE);
                            btnLogin.setEnabled(true);// bật lại trang thái !
                        }
                    }
                });
    }

    // save process radioButton
    private void saveRadio(String key, boolean value) {
        SharedPreferences sp = getSharedPreferences("CHECK_RADIO", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    //update process radioButton
    private boolean updateRadio(String key) {
        SharedPreferences sp = getSharedPreferences("CHECK_RADIO", MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }
}