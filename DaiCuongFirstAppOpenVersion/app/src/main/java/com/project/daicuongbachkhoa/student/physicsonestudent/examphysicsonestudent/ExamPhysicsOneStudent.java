package com.project.daicuongbachkhoa.student.physicsonestudent.examphysicsonestudent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ExamPhysicsOneStudent extends AppCompatActivity {

    private TextView txtContent, txtYourSelectExamPhysicsOneStudent, txtTimeExamPhysicsOneStudent, txtResetAllAnswerExamPhysicsOneStudent;
    private RadioButton radOptionAExamPhysicsOneStudent, radOptionBExamPhysicsOneStudent, radOptionCExamPhysicsOneStudent, radOptionDExamPhysicsOneStudent;
    private RadioGroup radGroupExamPhysicsOneStudent;
    private Button btnQuestionNextExamPhysicsOneStudent, btnQuestionPrevExamPhysicsOneStudent, btnSubmitExamPhysicsOneStudent;
    private TextView txtNumberExamPhysicsOneStudent;
    private FirebaseUser student;
    private DatabaseReference reference, referenceStudent, referenceExam, answerPhysicsOne, answerExam1;
    private String codeExam, nameExam;

    //private DatabaseReference referenceExam, answerPhysicsOne, answerExam1;
    private ValueEventListener referenceQuestion, referenceAnswer;

    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_physics_one_student);

        txtContent = findViewById(R.id.txtContentExamPhysicsOneStudent);
        txtYourSelectExamPhysicsOneStudent = findViewById(R.id.txtYourSelectExamPhysicsOneStudent);
        txtTimeExamPhysicsOneStudent = findViewById(R.id.txtTimeExamPhysicsOneStudent);
        txtResetAllAnswerExamPhysicsOneStudent = findViewById(R.id.txtResetAllAnswerExamPhysicsOneStudent);
        btnQuestionNextExamPhysicsOneStudent = findViewById(R.id.btnQuestionNextExamPhysicsOneStudent);
        btnQuestionPrevExamPhysicsOneStudent = findViewById(R.id.btnQuestionPrevExamPhysicsOneStudent);
        btnSubmitExamPhysicsOneStudent = findViewById(R.id.btnSubmitExamPhysicsOneStudent);
        radGroupExamPhysicsOneStudent = findViewById(R.id.radGroupExamPhysicsOneStudent);
        radOptionAExamPhysicsOneStudent = findViewById(R.id.radOptionAExamPhysicsOneStudent);
        radOptionBExamPhysicsOneStudent = findViewById(R.id.radOptionBExamPhysicsOneStudent);
        radOptionCExamPhysicsOneStudent = findViewById(R.id.radOptionCExamPhysicsOneStudent);
        radOptionDExamPhysicsOneStudent = findViewById(R.id.radOptionDExamPhysicsOneStudent);
        txtNumberExamPhysicsOneStudent = findViewById(R.id.txtNumberExamPhysicsOneStudent);


        loadCodeExam();
        startTimeExam();
        /*Intent intent = getIntent();
        nameExam = intent.getStringExtra("NAME_EXAM_PHYSICS_ONE_STUDENT");
        codeExam = intent.getStringExtra("CODE_EXAM_PHYSICS_ONE_STUDENT");
        txtNumberExamPhysicsOneStudent.setText(nameExam);
        btnQuestionPrevExamPhysicsOneStudent.setVisibility(View.GONE);*/
        txtResetAllAnswerExamPhysicsOneStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetAllAnswerExamPhysicsOneStudent();
            }
        });
        btnQuestionNextExamPhysicsOneStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnQuestionNextExamPhysicsOneStudent.setText("CÂU TIẾP");
                btnQuestionPrevExamPhysicsOneStudent.setVisibility(View.VISIBLE);
                txtResetAllAnswerExamPhysicsOneStudent.setEnabled(false);
                txtResetAllAnswerExamPhysicsOneStudent.setVisibility(View.GONE);
                showContentQuestionNext();
            }
        });

        btnQuestionPrevExamPhysicsOneStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showContentQuestionPrev();
            }
        });

        btnSubmitExamPhysicsOneStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitExamPhysicsOneStudent();

            }
        });

    }

    private void resetAllAnswerExamPhysicsOneStudent() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn chắn chắn muốn reset toàn bộ ?\n Các câu trả lời cũ của bạn sẽ được xoá hết !\nCác câu hỏi sẽ trở về trạng thái \"None\" ban đầu!\n Tuy nhiên, câu trả lời cũ của bạn có thể vẫn lưu lại ở lịch sử đề thi !")
                .setCancelable(false)
                .setPositiveButton("XÁC NHẬN", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        resetAll();
                        Toast.makeText(ExamPhysicsOneStudent.this, "Reset thành công !", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("THOÁT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private void resetAll() {
        FirebaseUser student = FirebaseAuth.getInstance().getCurrentUser();
        String studentID = student.getUid();
        DatabaseReference referenceStudent = FirebaseDatabase.getInstance().getReference("Students");
        DatabaseReference listQues = referenceStudent.child(studentID).child("PhysicsOne").child(codeExam);//check list exam
        for (int i = 1; i <= 10; i++) {
            listQues.child("Ques" + i).setValue("None");
        }
    }

    private void loadCodeExam() {
        Intent intent = getIntent();
        nameExam = intent.getStringExtra("NAME_EXAM_PHYSICS_ONE_STUDENT");
        codeExam = intent.getStringExtra("CODE_EXAM_PHYSICS_ONE_STUDENT");
        txtNumberExamPhysicsOneStudent.setText(nameExam);
        btnQuestionPrevExamPhysicsOneStudent.setVisibility(View.GONE);


    }

    private void showContentQuestionPrev() {
        if (i > 1) {
            i--;
            showContentQuestion(i);
        } else {
            i = 11;
            Toast.makeText(ExamPhysicsOneStudent.this, "Các câu đã sắp xếp từ dễ đến khó, nhấn 1 lần nữa để xem từ câu 20 !", Toast.LENGTH_SHORT).show();
        }
    }

    private void showContentQuestionNext() {
        if (i < 10) {
            i++;
            showContentQuestion(i);
        } else {
            i = 0;
            Toast.makeText(ExamPhysicsOneStudent.this, "Bạn đã làm xong bài, nhấn 1 lần nữa để xem lại từ câu 1 !", Toast.LENGTH_SHORT).show();
        }
    }

    private void submitExamPhysicsOneStudent() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Bạn chắn chắn muốn nộp bài ?\nBạn sẽ không thể sửa bài sau khi nộp !")
                .setCancelable(false)
                .setPositiveButton("XÁC NHẬN", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent = new Intent(ExamPhysicsOneStudent.this, ResultExamPhysicsOneStudent.class);
                        intent.putExtra("CODE_RESULT_EXAM_PHYSICS_ONE_STUDENT", codeExam);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("XEM LẠI BÀI THI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showContentQuestion(final int questionCount) {

        radGroupExamPhysicsOneStudent.clearCheck();
        reference = FirebaseDatabase.getInstance().getReference("PhysicsOneTeacher");

        referenceExam = reference.child(codeExam);
        referenceQuestion = referenceExam.child("Ques" + questionCount).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String content = String.valueOf(snapshot.child("Content").getValue());
                txtContent.setText(content);
                String optA = String.valueOf(snapshot.child("OptionA").getValue());
                radOptionAExamPhysicsOneStudent.setText("A. " + optA);
                String optB = String.valueOf(snapshot.child("OptionB").getValue());
                radOptionBExamPhysicsOneStudent.setText("B. " + optB);
                String optC = String.valueOf(snapshot.child("OptionC").getValue());
                radOptionCExamPhysicsOneStudent.setText("C. " + optC);
                String optD = String.valueOf(snapshot.child("OptionD").getValue());
                radOptionDExamPhysicsOneStudent.setText("D. " + optD);

                student = FirebaseAuth.getInstance().getCurrentUser();
                referenceStudent = FirebaseDatabase.getInstance().getReference("Students");
                String userID = student.getUid();
                referenceAnswer = referenceStudent.child(userID).child("PhysicsOne")
                        .child(codeExam).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                String yourAnswer = String.valueOf(snapshot.child("Ques" + questionCount).getValue());
                                txtYourSelectExamPhysicsOneStudent.setText("Bạn đã chọn: " + yourAnswer);

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });

                radGroupExamPhysicsOneStudent.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        RadioButton radOptionExamPhysicsOneStudent = (RadioButton) radGroupExamPhysicsOneStudent.findViewById(i);
                        int selectRadio = radGroupExamPhysicsOneStudent.indexOfChild(radOptionExamPhysicsOneStudent) + 1;
                        switch (selectRadio) {
                            case 1:
                                txtYourSelectExamPhysicsOneStudent.setText("Bạn đã chọn: A");
                                updateAnswerExamPhysicsOneStudent(questionCount, "A");
                                break;
                            case 2:
                                txtYourSelectExamPhysicsOneStudent.setText("Bạn đã chọn: B");
                                updateAnswerExamPhysicsOneStudent(questionCount, "B");
                                break;
                            case 3:
                                txtYourSelectExamPhysicsOneStudent.setText("Bạn đã chọn: C");
                                updateAnswerExamPhysicsOneStudent(questionCount, "C");
                                break;
                            case 4:
                                txtYourSelectExamPhysicsOneStudent.setText("Bạn đã chọn: D");
                                updateAnswerExamPhysicsOneStudent(questionCount, "D");
                                break;
                            default:
                                break;
                        }

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ExamPhysicsOneStudent.this, "Có lỗi xảy ra! Vui lòng kiểm tra Internet !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateAnswerExamPhysicsOneStudent(int questionCount, String answerStudent) {
        student = FirebaseAuth.getInstance().getCurrentUser();
        referenceStudent = FirebaseDatabase.getInstance().getReference("Students");
        String userID = student.getUid();
        answerPhysicsOne = referenceStudent.child(userID).child("PhysicsOne");// nhánh vật lí của student;// nhánh cá nhân
        answerExam1 = answerPhysicsOne.child(codeExam);
        answerExam1.child("Ques" + questionCount).setValue(answerStudent);
    }

    private void startTimeExam() {
        //20 minutes for exam
        new CountDownTimer(300000 + 5000, 1000) {
            public void onTick(long timeExam) {
                // Used for formatting digit to be in 2 digits only
                NumberFormat f = new DecimalFormat("00");
                long min = (timeExam / 60000) % 60;
                long sec = (timeExam / 1000) % 60;
                txtTimeExamPhysicsOneStudent.setText(f.format(min) + ":" + f.format(sec));

                if (Long.parseLong(f.format(min).toString()) < 5) {
                    txtTimeExamPhysicsOneStudent.setTextColor(Color.RED);
                }
            }

            // When the task is over
            public void onFinish() {
                txtTimeExamPhysicsOneStudent.setText("Hết giờ");
                btnQuestionNextExamPhysicsOneStudent.setVisibility(View.GONE);
                btnQuestionPrevExamPhysicsOneStudent.setVisibility(View.GONE);
                Toast.makeText(ExamPhysicsOneStudent.this, "Đã hết giờ, vui lòng nhấn nộp bài !", Toast.LENGTH_LONG).show();
            }
        }.start();
    }
}