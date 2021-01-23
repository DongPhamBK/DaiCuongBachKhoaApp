package com.project.daicuongbachkhoa.account;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.project.daicuongbachkhoa.R;

public class ResetPasswordUser extends AppCompatActivity {


    private EditText txtEmailResetPassword;
    private Button btnResetPassword;
    private ProgressBar progressBarReset;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password_user);

        txtEmailResetPassword = findViewById(R.id.txtEmailResetPassword);
        btnResetPassword = findViewById(R.id.btnResetPassword);
        progressBarReset = findViewById(R.id.progressBarReset);// tiến trình trạng thái
        mAuth = FirebaseAuth.getInstance();

        btnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPass();
            }
        });
    }

    private void resetPass() {

        //create process reset password
        progressBarReset.setVisibility(View.VISIBLE);
        btnResetPassword.setEnabled(false);
        String emailResetPassword = txtEmailResetPassword.getText().toString().trim();

        // check email
        if (emailResetPassword.isEmpty()) {
            txtEmailResetPassword.setError("Email không được để trống !");
            txtEmailResetPassword.requestFocus();
            progressBarReset.setVisibility(View.GONE);
            btnResetPassword.setEnabled(true);
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emailResetPassword).matches()) {
            txtEmailResetPassword.setError("Gmail không hợp lệ !");
            txtEmailResetPassword.requestFocus();
            progressBarReset.setVisibility(View.GONE);
            btnResetPassword.setEnabled(true);
            return;
        }
        if (!emailResetPassword.contains("@gmail.com")) {
            txtEmailResetPassword.setError("Gmail không hợp lệ !");
            txtEmailResetPassword.requestFocus();
            progressBarReset.setVisibility(View.GONE);
            btnResetPassword.setEnabled(true);
            return;
        }

        // start reset password
        mAuth.sendPasswordResetEmail(emailResetPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(ResetPasswordUser.this, "Reset mật khẩu thành công", Toast.LENGTH_SHORT).show();
                    btnResetPassword.setText("Thành công");
                    btnResetPassword.setTextColor(Color.CYAN);
                    progressBarReset.setVisibility(View.GONE);
                } else {
                    Toast.makeText(ResetPasswordUser.this, "Có vẻ email không hợp lệ !\nVui lòng thử lại !", Toast.LENGTH_SHORT).show();
                    progressBarReset.setVisibility(View.GONE);
                    btnResetPassword.setEnabled(true);
                }
            }
        });
    }
}