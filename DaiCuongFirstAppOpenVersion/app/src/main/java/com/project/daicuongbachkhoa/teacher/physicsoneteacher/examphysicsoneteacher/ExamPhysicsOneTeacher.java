package com.project.daicuongbachkhoa.teacher.physicsoneteacher.examphysicsoneteacher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
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
import com.project.daicuongbachkhoa.R;
import com.project.daicuongbachkhoa.model.Exam;

public class ExamPhysicsOneTeacher extends AppCompatActivity {

    private EditText txtContentExamPhysicsOneTeacher, txtOptionAExamPhysicsOneTeacher, txtOptionBExamPhysicsOneTeacher,
            txtOptionCExamPhysicsOneTeacher, txtOptionDExamPhysicsOneTeacher, txtRightAnsExamPhysicsOneTeacher;
    private TextView txtNumberExamPhysicsOneTeacher, txtUpdateInfoExamPhysicsOneTeacher;
    private Button btnQuestionNextExamPhysicsOneTeacher, btnQuestionPrevExamPhysicsOneTeacher, btnQuestionEditExamPhysicsOneTeacher;
    private DatabaseReference reference, referenceStudent, referenceExam, answerPhysicsOne, answerExam1;
    private ValueEventListener referenceQuestion, referenceAnswer;
    private String codeExam, nameExam;
    private int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_physics_one_teacher);

        txtNumberExamPhysicsOneTeacher = findViewById(R.id.txtNumberExamPhysicsOneTeacher);
        txtUpdateInfoExamPhysicsOneTeacher = findViewById(R.id.txtUpdateInfoExamPhysicsOneTeacher);
        txtContentExamPhysicsOneTeacher = findViewById(R.id.txtContentExamPhysicsOneTeacher);
        txtOptionAExamPhysicsOneTeacher = findViewById(R.id.txtOptionAExamPhysicsOneTeacher);
        txtOptionBExamPhysicsOneTeacher = findViewById(R.id.txtOptionBExamPhysicsOneTeacher);
        txtOptionCExamPhysicsOneTeacher = findViewById(R.id.txtOptionCExamPhysicsOneTeacher);
        txtOptionDExamPhysicsOneTeacher = findViewById(R.id.txtOptionDExamPhysicsOneTeacher);
        txtRightAnsExamPhysicsOneTeacher = findViewById(R.id.txtRightAnsPhysicsOneTeacher);
        btnQuestionPrevExamPhysicsOneTeacher = findViewById(R.id.btnQuestionPrevExamPhysicsOneTeacher);
        btnQuestionNextExamPhysicsOneTeacher = findViewById(R.id.btnQuestionNextExamPhysicsOneTeacher);
        btnQuestionEditExamPhysicsOneTeacher = findViewById(R.id.btnQuestionEditExamPhysicsOneTeacher);
        //btnQuestionEditExamPhysicsOneTeacher.setText("Chỉnh sửa");

       /* txtContentExamPhysicsOneTeacher.setFocusable(false);
        txtOption1ExamPhysicsOneTeacher.setFocusable(false);
        txtOption2ExamPhysicsOneTeacher.setFocusable(false);
        txtOption3ExamPhysicsOneTeacher.setFocusable(false);
        txtOption4ExamPhysicsOneTeacher.setFocusable(false);
        txtRightAnsExamPhysicsOneTeacher.setFocusable(false);*/
        loadCodeExam();
        showQuestion(1);
        txtUpdateInfoExamPhysicsOneTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateInfoExamPhysicsOneTeacher();
            }


        });
        btnQuestionNextExamPhysicsOneTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showQuestionNext();
            }
        });

        btnQuestionPrevExamPhysicsOneTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showQuestionPrev();
            }
        });

        btnQuestionEditExamPhysicsOneTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editQuestion();
            }
        });

    }

    private void updateInfoExamPhysicsOneTeacher() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        TextView title = new TextView(this);
        title.setText("CẬP NHẬT THÔNG TIN");
        title.setPadding(0, 5, 0, 5);
        title.setTextColor(Color.rgb(236, 134, 14));
        title.setGravity(Gravity.CENTER);
        title.setTextSize(18);
        builder.setCustomTitle(title);
        View update_layout = LayoutInflater.from(this).inflate(R.layout.dialog_update_info_exam_physics_one_teacher, null);
        final EditText txtUpdateNameExamPhysicsOneTeacher = update_layout.findViewById(R.id.txtUpdateNameExamPhysicsOneTeacher);
        final EditText txtUpdateTeacherExamPhysicsOneTeacher = update_layout.findViewById(R.id.txtUpdateTeacherExamPhysicsOneTeacher);
        final EditText txtUpdateDeadlineExamPhysicsOneTeacher = update_layout.findViewById(R.id.txtUpdateDeadlineExamPhysicsOneTeacher);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("PhysicsOneTeacher");
        ValueEventListener referenceInfo = reference.child("ExamInfo").child(codeExam).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String nameExam = String.valueOf(snapshot.child("examName").getValue());
                txtUpdateNameExamPhysicsOneTeacher.setText(nameExam);
                String nameTeacher = String.valueOf(snapshot.child("examTeacher").getValue());
                txtUpdateTeacherExamPhysicsOneTeacher.setText(nameTeacher);
                String deadline = String.valueOf(snapshot.child("examDeadline").getValue());
                txtUpdateDeadlineExamPhysicsOneTeacher.setText(deadline);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        builder.setView(update_layout);
        builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Exam exam = new Exam(codeExam,txtUpdateNameExamPhysicsOneTeacher.getText().toString(),txtUpdateTeacherExamPhysicsOneTeacher.getText().toString(),txtUpdateDeadlineExamPhysicsOneTeacher.getText().toString());
                FirebaseDatabase.getInstance().getReference("PhysicsOneTeacher").child("ExamInfo").child(codeExam).setValue(exam);
                FirebaseUser teacher = FirebaseAuth.getInstance().getCurrentUser();
                String teacherID = teacher.getUid();
                DatabaseReference referenceTeacher = FirebaseDatabase.getInstance().getReference("Teachers").child(teacherID).child("Subjects").child("PhysicsOne").child("Exam").child(codeExam);
                referenceTeacher.setValue(exam);
                Toast.makeText(ExamPhysicsOneTeacher.this, "Thay đổi thành công", Toast.LENGTH_SHORT).show();

            }
        });
        builder.setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        });

        builder.show();//hiển thị

    }

    private void loadCodeExam() {
        Intent intent = getIntent();
        nameExam = intent.getStringExtra("NAME_EXAM_PHYSICS_ONE_TEACHER");
        codeExam = intent.getStringExtra("CODE_EXAM_PHYSICS_ONE_TEACHER");
        txtNumberExamPhysicsOneTeacher.setText(nameExam);
    }

    //Edit question !
    private void editQuestion() {

        String status = btnQuestionEditExamPhysicsOneTeacher.getText().toString();
        if (status.equals("Chỉnh sửa")) {
            btnQuestionEditExamPhysicsOneTeacher.setText("Lưu");
            //txtContentExamPhysicsOneTeacher.setFocusable(true);
            txtContentExamPhysicsOneTeacher.setEnabled(true);
            //txtContentExamPhysicsOneTeacher.setInputType(InputType.TYPE_CLASS_TEXT);
            txtContentExamPhysicsOneTeacher.setFocusable(true);
            txtContentExamPhysicsOneTeacher.setFocusableInTouchMode(true);
            txtContentExamPhysicsOneTeacher.setTextColor(Color.rgb(9, 0, 0));
            txtOptionAExamPhysicsOneTeacher.setFocusable(true);
            txtOptionAExamPhysicsOneTeacher.setFocusableInTouchMode(true);
            txtOptionAExamPhysicsOneTeacher.setTextColor(Color.rgb(9, 0, 0));
            txtOptionBExamPhysicsOneTeacher.setFocusable(true);
            txtOptionBExamPhysicsOneTeacher.setFocusableInTouchMode(true);
            txtOptionBExamPhysicsOneTeacher.setTextColor(Color.rgb(9, 0, 0));
            txtOptionCExamPhysicsOneTeacher.setFocusable(true);
            txtOptionCExamPhysicsOneTeacher.setFocusableInTouchMode(true);
            txtOptionCExamPhysicsOneTeacher.setTextColor(Color.rgb(9, 0, 0));
            txtOptionDExamPhysicsOneTeacher.setFocusable(true);
            txtOptionDExamPhysicsOneTeacher.setFocusableInTouchMode(true);
            txtOptionDExamPhysicsOneTeacher.setTextColor(Color.rgb(9, 0, 0));
            txtRightAnsExamPhysicsOneTeacher.setFocusable(true);
            txtRightAnsExamPhysicsOneTeacher.setFocusableInTouchMode(true);
            txtRightAnsExamPhysicsOneTeacher.setTextColor(Color.rgb(9, 0, 0));
            btnQuestionNextExamPhysicsOneTeacher.setVisibility(View.GONE);
            btnQuestionPrevExamPhysicsOneTeacher.setVisibility(View.GONE);

        } else if (status.equals("Lưu")) {
            btnQuestionEditExamPhysicsOneTeacher.setText("Chỉnh sửa");

            btnQuestionNextExamPhysicsOneTeacher.setVisibility(View.VISIBLE);
            btnQuestionPrevExamPhysicsOneTeacher.setVisibility(View.VISIBLE);
            reference = FirebaseDatabase.getInstance().getReference("PhysicsOneTeacher");
            referenceExam = reference.child(codeExam);
            referenceExam.child("Ques" + i).child("Content").setValue(txtContentExamPhysicsOneTeacher.getText().toString());
            referenceExam.child("Ques" + i).child("OptionA").setValue(txtOptionAExamPhysicsOneTeacher.getText().toString());
            referenceExam.child("Ques" + i).child("OptionB").setValue(txtOptionBExamPhysicsOneTeacher.getText().toString());
            referenceExam.child("Ques" + i).child("OptionC").setValue(txtOptionCExamPhysicsOneTeacher.getText().toString());
            referenceExam.child("Ques" + i).child("OptionD").setValue(txtOptionDExamPhysicsOneTeacher.getText().toString());
            referenceExam.child("Ques" + i).child("RightAns").setValue(txtRightAnsExamPhysicsOneTeacher.getText().toString());
            showQuestion(i);

        }


    }

    private void showQuestionPrev() {
        if (i > 1) {
            i--;
            showQuestion(i);
        } else {
            i = 11;
            Toast.makeText(ExamPhysicsOneTeacher.this, "Nhấn 1 lần nữa để tiếp tục !", Toast.LENGTH_SHORT).show();
        }
    }

    private void showQuestionNext() {
        if (i < 10) {
            i++;
            showQuestion(i);
        } else {
            i = 0;
            Toast.makeText(ExamPhysicsOneTeacher.this, "Bạn đã duyệt xong đề thi, nhấn 1 lần nữa để xem lại từ câu 1 !", Toast.LENGTH_SHORT).show();
        }
    }


    private void showQuestion(int questionCount) {

        txtContentExamPhysicsOneTeacher.setFocusable(false);
        txtContentExamPhysicsOneTeacher.setTextColor(Color.rgb(99, 99, 99));
        txtOptionAExamPhysicsOneTeacher.setFocusable(false);
        txtOptionAExamPhysicsOneTeacher.setTextColor(Color.rgb(99, 99, 99));
        txtOptionBExamPhysicsOneTeacher.setFocusable(false);
        txtOptionBExamPhysicsOneTeacher.setTextColor(Color.rgb(99, 99, 99));
        txtOptionCExamPhysicsOneTeacher.setFocusable(false);
        txtOptionCExamPhysicsOneTeacher.setTextColor(Color.rgb(99, 99, 99));
        txtOptionDExamPhysicsOneTeacher.setFocusable(false);
        txtOptionDExamPhysicsOneTeacher.setTextColor(Color.rgb(99, 99, 99));
        txtRightAnsExamPhysicsOneTeacher.setFocusable(false);
        txtRightAnsExamPhysicsOneTeacher.setTextColor(Color.rgb(99, 99, 99));

        reference = FirebaseDatabase.getInstance().getReference("PhysicsOneTeacher");
        referenceExam = reference.child(codeExam);
        referenceQuestion = referenceExam.child("Ques" + questionCount).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String content = String.valueOf(snapshot.child("Content").getValue());
                txtContentExamPhysicsOneTeacher.setText(content);
                String optA = String.valueOf(snapshot.child("OptionA").getValue());
                txtOptionAExamPhysicsOneTeacher.setText(optA);
                String optB = String.valueOf(snapshot.child("OptionB").getValue());
                txtOptionBExamPhysicsOneTeacher.setText(optB);
                String optC = String.valueOf(snapshot.child("OptionC").getValue());
                txtOptionCExamPhysicsOneTeacher.setText(optC);
                String optD = String.valueOf(snapshot.child("OptionD").getValue());
                txtOptionDExamPhysicsOneTeacher.setText(optD);
                String rightAns = String.valueOf(snapshot.child("RightAns").getValue());
                txtRightAnsExamPhysicsOneTeacher.setText(rightAns);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}