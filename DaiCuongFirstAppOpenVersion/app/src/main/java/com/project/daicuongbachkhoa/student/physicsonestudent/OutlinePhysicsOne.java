package com.project.daicuongbachkhoa.student.physicsonestudent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.project.daicuongbachkhoa.R;

public class OutlinePhysicsOne extends AppCompatActivity {

    PDFView outlinePhysicsOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outline_physics_one);
        outlinePhysicsOne = findViewById(R.id.outlinePhysicsOne);

        outlinePhysicsOne.fromAsset("outline_physicsone.pdf")
                .defaultPage(0)
                .enableAnnotationRendering(true)
                .swipeHorizontal(true)
                .scrollHandle(new DefaultScrollHandle(this))
                .spacing(2)
                .load();
    }
}