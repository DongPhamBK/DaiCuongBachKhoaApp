package com.project.daicuongbachkhoa.landingpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.daicuongbachkhoa.R;
import com.project.daicuongbachkhoa.menubar.MenuBar;
import com.project.daicuongbachkhoa.news.ListNews;

public class LandingPage extends AppCompatActivity {
    private TextView txtImportantNotification;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        txtImportantNotification = findViewById(R.id.txtImportantNotification);
        linearLayout = findViewById(R.id.lnGoHome);

        txtImportantNotification.setSelected(true);  // Set focus to the textview
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMenu();
            }
        });
        Button btnGh = findViewById(R.id.btnGh);
        btnGh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LandingPage.this, ListNews.class));
            }
        });
    }

    private void goToMenu() {
        startActivity(new Intent(LandingPage.this, MenuBar.class));
    }
}