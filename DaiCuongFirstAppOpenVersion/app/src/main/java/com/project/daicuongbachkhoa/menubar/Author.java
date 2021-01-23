package com.project.daicuongbachkhoa.menubar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.project.daicuongbachkhoa.R;

public class Author extends AppCompatActivity {

    private Button btnBlog;
    private ImageView imgAuthorGoHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);

        imgAuthorGoHome = findViewById(R.id.imgFeedbackGoHome);
        btnBlog = findViewById(R.id.btnBlog);


        btnBlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBlog("https://ptdonghust.blogspot.com");
            }
        });


        imgAuthorGoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authorGoHome();
            }
        });
    }

    private void authorGoHome() {
        //startActivity(new Intent(Author.this, MenuBar.class));
        finish();
    }

    private void openBlog(String url) {
        Uri uri = Uri.parse(url);
        Intent goWeb = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(goWeb);
    }
}