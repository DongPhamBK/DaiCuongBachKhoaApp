package com.project.daicuongbachkhoa.teacher.lawteacher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.project.daicuongbachkhoa.MainActivity;
import com.project.daicuongbachkhoa.R;
import com.project.daicuongbachkhoa.account.LoginUser;
import com.project.daicuongbachkhoa.teacher.TeacherHelp;
import com.project.daicuongbachkhoa.teacher.TeacherInfo;

public class OptionLawTeacher extends AppCompatActivity {

    private TextView txtLawTeacherLogout, txtLawTeacherHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_law_teacher);

        txtLawTeacherLogout = findViewById(R.id.txtLawTeacherLogout);
        txtLawTeacherHelp = findViewById(R.id.txtLawTeacherHelp);

        txtLawTeacherLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lawTeacherLogout();
            }
        });

        txtLawTeacherHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lawTeacherHelp();
            }
        });
    }

    private void lawTeacherHelp() {
        startActivity(new Intent(OptionLawTeacher.this, TeacherHelp.class));
    }

    private void lawTeacherLogout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(OptionLawTeacher.this, MainActivity.class));
        finish();
    }
}