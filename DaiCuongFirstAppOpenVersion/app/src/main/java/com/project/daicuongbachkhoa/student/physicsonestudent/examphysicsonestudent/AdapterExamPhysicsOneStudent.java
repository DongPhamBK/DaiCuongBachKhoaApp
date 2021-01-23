package com.project.daicuongbachkhoa.student.physicsonestudent.examphysicsonestudent;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.daicuongbachkhoa.R;
import com.project.daicuongbachkhoa.model.Exam;


public class AdapterExamPhysicsOneStudent extends FirebaseRecyclerAdapter<Exam, AdapterExamPhysicsOneStudent.ExamViewHolder> {

    private FirebaseUser student;
    private DatabaseReference reference, referenceStudent;
    private DatabaseReference listPhysicsOne, answerExamReset, answerPhysicsOne;
    private ValueEventListener referenceExam;
    public AdapterExamPhysicsOneStudent(@NonNull FirebaseRecyclerOptions<Exam> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ExamViewHolder holder, int position, @NonNull final Exam exam) {
        holder.btnNumberExamPhysicsOneStudent.setText("Đề số " + exam.getExamCode().charAt(exam.getExamCode().length() - 2) + exam.getExamCode().charAt(exam.getExamCode().length() - 1));
        //holder.btnNumberExamPhysicsOneStudent.setText(exam.getExamCode());
        holder.txtNameExamPhysicsOneStudent.setText(exam.getExamName());
        holder.txtTeacherExamPhysicsOneStudent.setText("Giáo viên: " + exam.getExamTeacher());
        holder.txtDeadlineExamPhysicsOneStudent.setText("Thời hạn: " + exam.getExamDeadline());
        holder.btnNumberExamPhysicsOneStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startExamPhysicsOneStudent(exam, view);
                // AppCompatActivity activity = (AppCompatActivity) view.getContext();
                // Toast.makeText(activity, "Truy cập" + holder.btnNumberExamPhysicsOneStudent.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void startExamPhysicsOneStudent(final Exam exam, View view) {

        final AppCompatActivity activity = (AppCompatActivity) view.getContext();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("Hệ thống sẽ bắt đầu tính thời gian thi.\nBạn đã sẵn sàng ?")
                .setCancelable(false)
                .setPositiveButton("XÁC NHẬN", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent intent = new Intent(activity, ExamPhysicsOneStudent.class);
                        intent.putExtra("NAME_EXAM_PHYSICS_ONE_STUDENT", exam.getExamName());
                        intent.putExtra("CODE_EXAM_PHYSICS_ONE_STUDENT", exam.getExamCode());
                        checkStatusExam(exam.getExamCode());
                        activity.startActivity(intent);
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

       /* AppCompatActivity activity = (AppCompatActivity) view.getContext();
        Intent intent = new Intent(activity, ExamPhysicsOneStudent.class);
        intent.putExtra("NAME_EXAM_PHYSICS_ONE_STUDENT", exam.getExamName());
        intent.putExtra("CODE_EXAM_PHYSICS_ONE_STUDENT", exam.getExamCode());
        activity.startActivity(intent);*/
    }

    private void checkStatusExam(final String codeExam) {
        student = FirebaseAuth.getInstance().getCurrentUser();
        referenceStudent = FirebaseDatabase.getInstance().getReference("Students");
        final String studentId = student.getUid();
        listPhysicsOne = referenceStudent.child(studentId).child("ListPhysicsOne");//check list exam
        referenceExam = listPhysicsOne.child(codeExam).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String status = String.valueOf(snapshot.child("Status").getValue());
                if (status.equals("No")) {
                    // student = FirebaseAuth.getInstance().getCurrentUser();
                    // referenceStudent = FirebaseDatabase.getInstance().getReference("Students");
                    // String userID = student.getUid();
                    answerPhysicsOne = referenceStudent.child(studentId).child("PhysicsOne");// nhánh vật lí của student;// nhánh cá nhân
                    answerExamReset = answerPhysicsOne.child(codeExam);
                    for (int i = 1; i <= 10; i++) {
                        answerExamReset.child("Ques" + i).setValue("None");
                    }
                    listPhysicsOne.child(codeExam).child("Status").setValue("Yes");
                    // startActivity(new Intent(ListExamPhysicsOneStudent.this, ExamPhysicsOneStudent.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //Toast.makeText(ListExamPhysicsOneStudent.this, "Có lỗi xảy ra! Vui lòng kiểm tra Internet !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @NonNull
    @Override
    public ExamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_exam_physics_one_student, parent, false);
        return new ExamViewHolder(view);


    }

    class ExamViewHolder extends RecyclerView.ViewHolder {
        Button btnNumberExamPhysicsOneStudent;
        TextView txtNameExamPhysicsOneStudent, txtTeacherExamPhysicsOneStudent, txtDeadlineExamPhysicsOneStudent;

        public ExamViewHolder(@NonNull View itemView) {
            super(itemView);

            btnNumberExamPhysicsOneStudent = itemView.findViewById(R.id.btnNumberExamPhysicsOneStudent);
            txtNameExamPhysicsOneStudent = itemView.findViewById(R.id.txtNameExamPhysicsOneStudent);
            txtTeacherExamPhysicsOneStudent = itemView.findViewById(R.id.txtTeacherExamPhysicsOneStudent);
            txtDeadlineExamPhysicsOneStudent = itemView.findViewById(R.id.txtDeadlineExamPhysicsOneStudent);
        }
    }
}
