package com.project.daicuongbachkhoa.student.physicsonestudent.tasksphysicsonestudent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.daicuongbachkhoa.R;
import com.project.daicuongbachkhoa.model.Tasks;

public class TasksPhysicsOneStudent extends AppCompatActivity {
    private TextView txtSetGroupPhysicsOneStudent;
    private Button btnGoToGroupPhysicsOneStudent;
    private RecyclerView revListTasksPhysicsOneStudent;
    private FirebaseUser student;
    private FirebaseDatabase database;
    private DatabaseReference referenceTasks;
    private FirebaseRecyclerOptions<Tasks> recyclerOptions;//kiểu dữ liệu
    private FirebaseRecyclerAdapter<Tasks, AdapterTasksPhysicsOneStudent> adapter;
    private String linkGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_physics_one_student);

        txtSetGroupPhysicsOneStudent = findViewById(R.id.txtSetGroupPhysicsOneStudent);
        revListTasksPhysicsOneStudent = findViewById(R.id.revListTasksPhysicsOneStudent);
        btnGoToGroupPhysicsOneStudent = findViewById(R.id.btnGoToGroupPhysicsOneStudent);
        //student = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance();
        Intent intent = getIntent();
        String idTeacher = intent.getStringExtra("ID_TEACHER_PHYSICS_ONE_STUDENT");
        linkGroup = intent.getStringExtra("LINK_GROUP_PHYSICS_ONE_STUDENT");
        referenceTasks = database.getReference("Teachers").child(idTeacher).child("Subjects").child("PhysicsOne").child("Tasks");
        //referenceTasks.keepSynced(true);
        showTasksPhysicsOneStudent();
        txtSetGroupPhysicsOneStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setGroupPhysicsOneStudent();
            }
        });

        btnGoToGroupPhysicsOneStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToGroupPhysicsOneStudent(linkGroup);
            }
        });

    }

    private void goToGroupPhysicsOneStudent(String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    private void showTasksPhysicsOneStudent() {
        revListTasksPhysicsOneStudent.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);//đảo ngược danh sách, rất thú vị !
        revListTasksPhysicsOneStudent.setLayoutManager(mLayoutManager);
        //String studentID = student.getUid();
        //referenceTasks
        recyclerOptions = new FirebaseRecyclerOptions.Builder<Tasks>().setQuery(referenceTasks, Tasks.class).build();//khá dài
        adapter = new FirebaseRecyclerAdapter<Tasks, AdapterTasksPhysicsOneStudent>(recyclerOptions) {
            @Override
            protected void onBindViewHolder(@NonNull AdapterTasksPhysicsOneStudent holder, int i, @NonNull Tasks model) {
                holder.txtTitleTasks.setText(model.getTitleTasks());
                holder.txtContentTasks.setText(model.getContentTasks());

            }

            @NonNull
            @Override
            public AdapterTasksPhysicsOneStudent onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_tasks, parent, false);
                //tạo lập view
                AdapterTasksPhysicsOneStudent viewHolder = new AdapterTasksPhysicsOneStudent(view);
                return viewHolder;

            }
        };
        revListTasksPhysicsOneStudent.setAdapter(adapter);//ok

    }

    @Override
    protected void onStart() {
        super.onStart();//đừng quên hàm này, nó là sự lắng nghe thay đổi !
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    private void setGroupPhysicsOneStudent() {
        startActivity(new Intent(TasksPhysicsOneStudent.this, GetTasksPhysicsOneStudent.class));
    }
}