package com.project.daicuongbachkhoa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.project.daicuongbachkhoa.R;
import com.project.daicuongbachkhoa.account.LoginUser;
import com.project.daicuongbachkhoa.landingpage.LandingPage;
import com.project.daicuongbachkhoa.landingpage.Notification;
import com.project.daicuongbachkhoa.menubar.MenuBar;
import com.project.daicuongbachkhoa.student.physicsonestudent.OptionPhysicsOneStudent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // xử lý hoạt hoạ
        setContentView(R.layout.activity_main);
       /* Thread opening = new Thread() {
            public void run() {
                try {
                    sleep(1);
                } catch (Exception e) {
                } finally {
                    Intent intent = new Intent(MainActivity.this, LoginUser.class);
                    startActivity(intent);
                }
            }
        };

        opening.start();*/
        //Intent intent = new Intent(this, LoginUser.class);
        Intent intent = new Intent(this, LoginUser.class);
        startActivity(intent);
        // finish();// để không ngược lại !
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();// xong !
    }
}