package com.project.daicuongbachkhoa.teacher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.project.daicuongbachkhoa.R;

public class TeacherHelp extends AppCompatActivity {

    private TextView txtHelpPhysicsOneTeacher;
    private Button btnGmailHelpPhysicsOneTeacher, btnFacebooklHelpPhysicsOneTeacher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_help);
        txtHelpPhysicsOneTeacher = findViewById(R.id.txtHelpPhysicsOneTeacher);
        btnGmailHelpPhysicsOneTeacher = findViewById(R.id.btnGmailHelpPhysicsOneTeacher);
        btnFacebooklHelpPhysicsOneTeacher = findViewById(R.id.btnFacebookHelpPhysicsOneTeacher);
        btnGmailHelpPhysicsOneTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUrlLink("https://mail.google.com/mail/u/0/?tab=rm#inbox");
            }
        });
        btnFacebooklHelpPhysicsOneTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUrlLink("https://www.facebook.com/profile.php?id=100012086771102");
            }
        });

        txtHelpPhysicsOneTeacher.setText("Chào mừng bạn đã đến với trang hướng dẫn của Đại Cương Bách Khoa.\n Khi đăng nhập, hệ thống sẽ tựu động" +
                " kiểm tra và xác thực danh tính giáo viên, và điều hướng tới trang làm việc tương ứng của bạn !\n\n" +
                " Chức năng \"ĐỀ CƯƠNG\" giúp bạn có thể theo dõi tiến độ của học kì.\n\n" +
                " Chức năng \"NHIỆM VỤ\" giúp bạn tạo, sửa đổi, xoá với các nhiệm vụ thông báo cho các sinh viên.\n" +
                "+ Để tạo 1 nhiệm vụ mới, chọn \"TẠO MỚI NHIỆM VỤ\", sau đó điền các thông tin như Tiêu đề, Nội dung của nhiệm vụ," +
                " nhấn \"XÁC NHẬN\" và kiểm tra kết quả trên màn hình." +
                "+ Để sử hay xoá 1 nhiệm vụ, nhấn và giữ vào phần tử bạn muốn thao tác trên danh sách nhiệm vụ, chọn chức năng " +
                "tương ứng !\n\n" +
                "Chức năng \"ĐỀ THI\" giúp bạn xem và thay đổi nội dung của các đề thi do bạn quản lí, bạn cũng có thể " +
                "thay đổi các thông số về thời gian của đề !\n\n" +
                "Chức năng \"THÔNG BÁO\" cập nhật thông báo từ phía nhà trường một cách nhanh và chính xác nhất !\n\n" +
                "Để đảm bảo ứng dụng được thao tác tốt nhất, vui lòng giữ Internet ổn định !\n\n" +
                "Mọi thắc mắc xin thông qua Gmail:\n   thanhdongcat@gmail.com\n");
    }

    void openUrlLink(String url) {
        Uri uri = Uri.parse(url);
        Intent goFace = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(goFace);
    }
}