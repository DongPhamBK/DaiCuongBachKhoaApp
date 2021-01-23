package com.project.daicuongbachkhoa.student.physicsonestudent.theoryphysicsonestudent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.project.daicuongbachkhoa.R;

public class TheoryPhysicsOneStudent extends AppCompatActivity {

    private  TextView txtGetChapterNamePhysicsOneStudent;
    private String chapterCode,chapterName;
    private PDFView pdfTheoryPhysicsOne;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theory_physics_one_student);
        txtGetChapterNamePhysicsOneStudent = findViewById(R.id.txtGetChapterNamePhysicsOneStudent);
        createChapterViewPhysicsOneStudent();


    }

    private void createChapterViewPhysicsOneStudent() {
        Intent intent = getIntent();
        chapterCode = intent.getStringExtra("CODE_CHAPTER_PHYSICS_ONE_STUDENT");
        chapterName = intent.getStringExtra("NAME_CHAPTER_PHYSICS_ONE_STUDENT");

        txtGetChapterNamePhysicsOneStudent.setText(chapterName);
        pdfTheoryPhysicsOne = findViewById(R.id.pdfTheoryPhysicsOne);
        pdfTheoryPhysicsOne.fromAsset(chapterCode + ".pdf")
                .defaultPage(0)
                .enableAnnotationRendering(true)
                .swipeHorizontal(true)
                .scrollHandle(new DefaultScrollHandle(this))
                .spacing(2)
                .load();
    }
}