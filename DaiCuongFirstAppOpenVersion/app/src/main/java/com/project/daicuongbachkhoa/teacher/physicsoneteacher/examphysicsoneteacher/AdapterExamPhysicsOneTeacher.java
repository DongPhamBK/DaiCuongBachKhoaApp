package com.project.daicuongbachkhoa.teacher.physicsoneteacher.examphysicsoneteacher;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.project.daicuongbachkhoa.R;
import com.project.daicuongbachkhoa.model.Exam;

public class AdapterExamPhysicsOneTeacher extends FirebaseRecyclerAdapter<Exam, AdapterExamPhysicsOneTeacher.ExamViewHolder> {
    private FirebaseUser teacher;
    private DatabaseReference reference, referenceTeacher;
    private DatabaseReference listPhysicsOne, answerExam1, answerPhysicsOne;
    private ValueEventListener referenceExam;

    public AdapterExamPhysicsOneTeacher(@NonNull FirebaseRecyclerOptions<Exam> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ExamViewHolder holder, int position, @NonNull final Exam exam) {
        holder.btnNumberExamPhysicsOneTeacher.setText("Đề số " + exam.getExamCode().charAt(exam.getExamCode().length() - 2) + exam.getExamCode().charAt(exam.getExamCode().length() - 1));
        holder.txtNameExamPhysicsOneTeacher.setText(exam.getExamName());
        holder.txtTeacherExamPhysicsOneTeacher.setText("Giáo viên: " + exam.getExamTeacher());
        holder.txtDeadlineExamPhysicsOneTeacher.setText("Thời hạn: " + exam.getExamDeadline());
        holder.btnNumberExamPhysicsOneTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startExamPhysicsOneTeacher(exam, view);
            }
        });
    }

    private void startExamPhysicsOneTeacher(Exam exam, View view) {

        final AppCompatActivity activity = (AppCompatActivity) view.getContext();
        Intent intent = new Intent(activity, ExamPhysicsOneTeacher.class);
        intent.putExtra("NAME_EXAM_PHYSICS_ONE_TEACHER", exam.getExamName());
        intent.putExtra("CODE_EXAM_PHYSICS_ONE_TEACHER", exam.getExamCode());
        activity.startActivity(intent);
    }

    @NonNull
    @Override
    public ExamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_exam_physics_one_teacher, parent, false);
        return new ExamViewHolder(view);
    }

    class ExamViewHolder extends RecyclerView.ViewHolder {
        Button btnNumberExamPhysicsOneTeacher;
        TextView txtNameExamPhysicsOneTeacher, txtTeacherExamPhysicsOneTeacher, txtDeadlineExamPhysicsOneTeacher;

        public ExamViewHolder(@NonNull View itemView) {
            super(itemView);

            btnNumberExamPhysicsOneTeacher = itemView.findViewById(R.id.btnNumberExamPhysicsOneTeacher);
            txtNameExamPhysicsOneTeacher = itemView.findViewById(R.id.txtNameExamPhysicsOneTeacher);
            txtTeacherExamPhysicsOneTeacher = itemView.findViewById(R.id.txtTeacherExamPhysicsOneTeacher);
            txtDeadlineExamPhysicsOneTeacher = itemView.findViewById(R.id.txtDeadlineExamPhysicsOneTeacher);
        }
    }
}
