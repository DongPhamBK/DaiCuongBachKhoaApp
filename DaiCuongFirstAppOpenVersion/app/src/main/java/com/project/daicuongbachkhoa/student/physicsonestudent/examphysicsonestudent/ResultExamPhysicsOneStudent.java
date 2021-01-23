package com.project.daicuongbachkhoa.student.physicsonestudent.examphysicsonestudent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.daicuongbachkhoa.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ResultExamPhysicsOneStudent extends AppCompatActivity {

    private TextView txtPointResultExamPhysicsOneStudent, txtDateExamPhysicsOneStudent, txtShowResultExamPhysicsOneStudent;
    private Button btnDetailResultExamPhysicsOneStudent;
    private FirebaseUser student;
    private DatabaseReference referenceRightAnswer, referenceStudent, referenceExam, answerPhysicsOne, answerExam1;
    private ValueEventListener referenceQuestion, referenceAnswer;
    private String userID, showResultExamPhysicsOneStudent;
    private int pointResultExamPhysicsOneStudent;
    private String examCodeResult  ="Exam01";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_exam_physics_one_student);
        txtPointResultExamPhysicsOneStudent = findViewById(R.id.txtPointResultExamPhysicsOneStudent);
        txtDateExamPhysicsOneStudent = findViewById(R.id.txtDateExamPhysicsOneStudent);
        txtShowResultExamPhysicsOneStudent = findViewById(R.id.txtShowResultExamPhysicsOneStudent);
        btnDetailResultExamPhysicsOneStudent = findViewById(R.id.btnDetailResultExamPhysicsOneStudent);

        dateExamPhysicsOneStudent();
        resultExamPhysicsOneStudent();
        btnDetailResultExamPhysicsOneStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detailResultExamPhysicsOneStudent();
            }
        });
    }

    private void dateExamPhysicsOneStudent() {
        String date = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(date);
        txtDateExamPhysicsOneStudent.setText("Ngày thi: " + simpleDateFormat.format(new Date()));
    }

    private void detailResultExamPhysicsOneStudent() {
        String status = btnDetailResultExamPhysicsOneStudent.getText().toString();
        examCodeResult = getIntent().getStringExtra("CODE_RESULT_EXAM_PHYSICS_ONE_STUDENT");
        if (status.equals("Chi tiết")) {
            btnDetailResultExamPhysicsOneStudent.setText("Thu gọn");
            student = FirebaseAuth.getInstance().getCurrentUser();
            referenceStudent = FirebaseDatabase.getInstance().getReference("Students");
            userID = student.getUid();
            showResultExamPhysicsOneStudent = "Bài làm của bạn:\n";
            pointResultExamPhysicsOneStudent = 0;

            referenceAnswer = referenceStudent.child(userID).child("PhysicsOne")
                    .child(examCodeResult).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (int questionCount = 1; questionCount <= 10; questionCount++) {
                                final String yourAnswer = String.valueOf(snapshot.child("Ques" + questionCount).getValue());
                                referenceRightAnswer = FirebaseDatabase.getInstance().getReference("PhysicsOneTeacher");
                                referenceExam = referenceRightAnswer.child(examCodeResult);
                                final int finalQuestionCount = questionCount;
                                referenceQuestion = referenceExam.child("Ques" + questionCount).addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        String contentQuestion = String.valueOf(snapshot.child("Content").getValue());
                                        String optionA = String.valueOf(snapshot.child("OptionA").getValue());
                                        String optionB = String.valueOf(snapshot.child("OptionB").getValue());
                                        String optionC = String.valueOf(snapshot.child("OptionC").getValue());
                                        String optionD = String.valueOf(snapshot.child("OptionD").getValue());
                                        String rightAnswer = String.valueOf(snapshot.child("RightAns").getValue());
                                        if (yourAnswer.equals(rightAnswer)) {
                                            pointResultExamPhysicsOneStudent++;
                                        }

                                        showResultExamPhysicsOneStudent = showResultExamPhysicsOneStudent + contentQuestion + "\nA: " + optionA + "\nB: " + optionB + "\nC: " + optionC + "\nD: " + optionD + "\nBạn chọn: " + yourAnswer + "     Đáp án đúng: " + rightAnswer + "\t\n\t_____________________________________\n\n";
                                        txtShowResultExamPhysicsOneStudent.setText(showResultExamPhysicsOneStudent + "Xem đáp án và cải thiện kỹ năng của bạn !");
                                        //save history
                                        referenceStudent.child(userID).child("PhysicsOne").child("History").setValue("Mã đề thi: "+ examCodeResult+ "\n"+ showResultExamPhysicsOneStudent);
                                        txtPointResultExamPhysicsOneStudent.setText("Điểm của bạn: " + pointResultExamPhysicsOneStudent + "/10\n(" + pointResultExamPhysicsOneStudent + " câu đúng/10 câu)");
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
        } else if (status.equals("Thu gọn")) {
            btnDetailResultExamPhysicsOneStudent.setText("Chi tiết");
            resultExamPhysicsOneStudent();
        }
    }

    private void resultExamPhysicsOneStudent() {
        student = FirebaseAuth.getInstance().getCurrentUser();
        referenceStudent = FirebaseDatabase.getInstance().getReference("Students");
        userID = student.getUid();
        showResultExamPhysicsOneStudent = "Bài làm của bạn:\n";
        pointResultExamPhysicsOneStudent = 0;
        Intent intent = getIntent();
        examCodeResult = getIntent().getStringExtra("CODE_RESULT_EXAM_PHYSICS_ONE_STUDENT");
        referenceAnswer = referenceStudent.child(userID).child("PhysicsOne")
                .child(examCodeResult).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (int questionCount = 1; questionCount <= 10; questionCount++) {
                            final String yourAnswer = String.valueOf(snapshot.child("Ques" + questionCount).getValue());
                            referenceRightAnswer = FirebaseDatabase.getInstance().getReference("PhysicsOneTeacher");
                            referenceExam = referenceRightAnswer.child(examCodeResult);
                            final int finalQuestionCount = questionCount;
                            referenceQuestion = referenceExam.child("Ques" + questionCount).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    String rightAnswer = String.valueOf(snapshot.child("RightAns").getValue());
                                    if (yourAnswer.equals(rightAnswer)) {
                                        pointResultExamPhysicsOneStudent++;
                                    }

                                    showResultExamPhysicsOneStudent = showResultExamPhysicsOneStudent + "\nCâu " + finalQuestionCount + ":     Bạn chọn: " + yourAnswer + "     Đáp án đúng: " + rightAnswer + "\n_____________________________________\n";
                                    txtShowResultExamPhysicsOneStudent.setText(showResultExamPhysicsOneStudent + "\nHoàn thành !");
                                    txtPointResultExamPhysicsOneStudent.setText("Điểm của bạn: " + pointResultExamPhysicsOneStudent);
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
    }
}