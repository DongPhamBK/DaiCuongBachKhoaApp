package com.project.daicuongbachkhoa.teacher.physicsoneteacher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.daicuongbachkhoa.R;

public class NotificationPhysicsOneTeacher extends AppCompatActivity {

    private TextView txtNotificationPhysicsOneTeacher;
    private DatabaseReference teacher;
    private DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_physics_one_teacher);

        txtNotificationPhysicsOneTeacher = findViewById(R.id.txtNotificationPhysicsOneTeacher);
        FirebaseDatabase.getInstance().getReference("Notification").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String notificationTeacher = String.valueOf(snapshot.child("Teacher").getValue());
                String notificationPhysicsOne= String.valueOf(snapshot.child("PhysicsOne").getValue());
                txtNotificationPhysicsOneTeacher.setText(notificationTeacher + "\n\n" + notificationPhysicsOne);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                txtNotificationPhysicsOneTeacher.setText("Có lỗi xảy ra ! Vui lòng thử lại hoặc liên hệ với quản trị viên để khắc phục !");
            }
        });



    }
}