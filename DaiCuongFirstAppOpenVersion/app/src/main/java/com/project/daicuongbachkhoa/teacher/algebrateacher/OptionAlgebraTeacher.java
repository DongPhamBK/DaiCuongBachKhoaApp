package com.project.daicuongbachkhoa.teacher.algebrateacher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.project.daicuongbachkhoa.MainActivity;
import com.project.daicuongbachkhoa.R;
import com.project.daicuongbachkhoa.teacher.TeacherHelp;
import com.project.daicuongbachkhoa.teacher.lawteacher.OptionLawTeacher;

public class OptionAlgebraTeacher extends AppCompatActivity {

    private TextView txtAlgeblaTeacherLogout, txtAlgebraTeacherHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_algebra_teacher);

        txtAlgeblaTeacherLogout = findViewById(R.id.txtAlgebraTeacherLogout);
        txtAlgebraTeacherHelp = findViewById(R.id.txtAlgebraTeacherHelp);

        txtAlgeblaTeacherLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                algebraTeacherLogout();
            }
        });

        txtAlgebraTeacherHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtAlgebraTeacherHelp();
            }
        });
    }

    private void txtAlgebraTeacherHelp() {
        startActivity(new Intent(OptionAlgebraTeacher.this, TeacherHelp.class));
    }

    private void algebraTeacherLogout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(OptionAlgebraTeacher.this, MainActivity.class));
        finish();
    }
}