package com.project.daicuongbachkhoa.menubar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.daicuongbachkhoa.R;

public class Target extends AppCompatActivity {

    private EditText txtPoint1, txtPoint2, txtFactor1, txtFactor2;
    private TextView txtResult;
    private Button btnResult;
    private ImageView imgTargetGoHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);

        txtPoint2 = findViewById(R.id.txtPoint2);
        txtPoint1 = findViewById(R.id.txtPoint1);
        txtFactor1 = findViewById(R.id.txtFactor1);
        txtFactor2 = findViewById(R.id.txtFactor2);
        txtResult = findViewById(R.id.txtResult);
        btnResult = findViewById(R.id.btnResult);
        imgTargetGoHome = findViewById(R.id.imgFeedbackGoHome);

        imgTargetGoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                targetGoHome();
            }
        });

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pointTarget();
            }
        });
    }

    private void targetGoHome() {
       // startActivity(new Intent(Target.this, MenuBar.class));
        finish();
    }

    private void pointTarget() {
        try {
            // chuyển dạng số thập phân
            float point1 = 0;
            point1 = Float.parseFloat(txtPoint1.getText().toString());
            float point2 = 0;
            point2 = Float.parseFloat(txtPoint2.getText().toString());
            float factor1 = Float.parseFloat(txtFactor1.getText().toString());
            float factor2 = Float.parseFloat(txtFactor2.getText().toString());
            float sum = point1 * factor1 + point2 * factor2;
            String result = String.valueOf(sum);


            // hiện ra màn hình
            txtResult.setText("Kết quả của bạn: " + result);
            Toast.makeText(Target.this, "Cố gắng đạt mục tiêu nhé ! ", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            txtResult.setText("Hmmm ! Có vẻ bạn chưa nhập đủ điểm và trọng số !");
            Toast.makeText(Target.this, "Vui lòng nhập đủ hệ số !", Toast.LENGTH_SHORT).show();
        }
    }
}