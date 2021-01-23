package com.project.daicuongbachkhoa.teacher.physicsoneteacher.tasksphysicsoneteacher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.project.daicuongbachkhoa.R;
import com.project.daicuongbachkhoa.model.Tasks;

public class TasksPhysicsOneTeacher extends AppCompatActivity {

    private Button btnNewTasksPhysicsOneTeacher;
    private RecyclerView revListTasksPhysicsOneTeacher;
    private FirebaseUser teacher;
    private FirebaseDatabase database;
    private DatabaseReference referenceTasks;
    private FirebaseRecyclerOptions<Tasks> recyclerOptions;//kiểu dữ liệu
    private FirebaseRecyclerAdapter<Tasks, AdapterTasksPhysicsOneTeacher> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_physics_one_teacher);
        btnNewTasksPhysicsOneTeacher = findViewById(R.id.btnNewTasksPhysicsOneTeacher);
        revListTasksPhysicsOneTeacher = findViewById(R.id.revListTasksPhysicsOneTeacher);
        teacher = FirebaseAuth.getInstance().getCurrentUser();
        database = FirebaseDatabase.getInstance();

        showTasksPhysicsOneTeacher();
        btnNewTasksPhysicsOneTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newTasksPhysicsOneTeacher();
            }
        });

    }

    private void showTasksPhysicsOneTeacher() {

        String teacherID = teacher.getUid();
        referenceTasks = database.getReference("Teachers").child(teacherID).child("Subjects").child("PhysicsOne").child("Tasks");
        revListTasksPhysicsOneTeacher.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);//đảo ngược danh sách, rất thú vị !
        revListTasksPhysicsOneTeacher.setLayoutManager(mLayoutManager);

        recyclerOptions = new FirebaseRecyclerOptions.Builder<Tasks>().setQuery(referenceTasks, Tasks.class).build();//khá dài
        adapter = new FirebaseRecyclerAdapter<Tasks, AdapterTasksPhysicsOneTeacher>(recyclerOptions) {
            @Override
            protected void onBindViewHolder(@NonNull AdapterTasksPhysicsOneTeacher holder, int i, @NonNull Tasks model) {
                holder.txtTitleTasks.setText(model.getTitleTasks());
                holder.txtContentTasks.setText(model.getContentTasks());

            }

            @NonNull
            @Override
            public AdapterTasksPhysicsOneTeacher onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_tasks, parent, false);
                //tạo lập view
                AdapterTasksPhysicsOneTeacher viewHolder = new AdapterTasksPhysicsOneTeacher(view);
                return viewHolder;

            }
        };
        revListTasksPhysicsOneTeacher.setAdapter(adapter);//ok
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

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getTitle().equals("Sửa")) {
            showUpdateDialog(adapter.getRef(item.getOrder()).getKey(), adapter.getItem(item.getOrder()));
        } else if (item.getTitle().equals("Xoá")) {
            deleteTask(adapter.getRef(item.getOrder()).getKey());
        }
        return super.onContextItemSelected(item);
    }

    private void showUpdateDialog(final String key, Tasks item) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        TextView title = new TextView(this);
        title.setText("CẬP NHẬT THÔNG TIN");
        title.setPadding(0, 5, 0, 5);
        title.setTextColor(Color.rgb(236, 134, 14));
        title.setGravity(Gravity.CENTER);
        title.setTextSize(18);
        builder.setCustomTitle(title);
        View update_layout = LayoutInflater.from(this).inflate(R.layout.dialog_update_tasks, null);
        final EditText updateTitleTasks = update_layout.findViewById(R.id.txtUpdateTitleTasks);
        final EditText updateContentTasks = update_layout.findViewById(R.id.txtUpdateContentTasks);
        updateTitleTasks.setText(item.getTitleTasks());
        updateContentTasks.setText(item.getContentTasks());//lấy dữ liệu cũ
        builder.setView(update_layout);
        builder.setPositiveButton("XÁC NHẬN", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String title = updateTitleTasks.getText().toString();
                String content = updateContentTasks.getText().toString();
                Tasks tasks = new Tasks(title, content);
                referenceTasks.child(key).setValue(tasks);

                Toast.makeText(TasksPhysicsOneTeacher.this, "Thay đổi thành công", Toast.LENGTH_SHORT).show();

            }
        });
        builder.setNegativeButton("THOÁT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                dialog.dismiss();
            }
        });

        builder.show();//hiển thị

    }
    private void deleteTask(String key){
        referenceTasks.child(key).removeValue();//xoá
    }

    private void newTasksPhysicsOneTeacher() {
        startActivity(new Intent(TasksPhysicsOneTeacher.this, CreateTasksPhysicsOneTeacher.class));
    }
}