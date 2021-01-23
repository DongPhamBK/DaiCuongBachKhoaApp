package com.project.daicuongbachkhoa.student.physicsonestudent.theoryphysicsonestudent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.project.daicuongbachkhoa.R;
import com.project.daicuongbachkhoa.model.Chapter;

import java.util.ArrayList;


public class ListChapterPhysicsOneStudent extends AppCompatActivity {

    private ListView lvListChapterPhysicsOneStudent;
    ArrayList<Chapter> arrChapter;
    AdapterChapterPhysicsOneStudent adapterChapterPhysicsOneStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_chapter_physics_one_student);
        lvListChapterPhysicsOneStudent =  findViewById(R.id.lvListChapterPhysicsOneStudent);
        arrChapter = new ArrayList<Chapter>();
        arrChapter.add(new Chapter("pdf_physicsone_chapter1","Chương 1:","Động học chất điểm"));
        arrChapter.add(new Chapter("pdf_physicsone_chapter2","Chương 2:","Động lực học chất điểm"));
        arrChapter.add(new Chapter("pdf_physicsone_chapter3","Chương 3:","Năng lượng"));
        arrChapter.add(new Chapter("pdf_physicsone_chapter4","Chương 4:","Hệ chất điểm"));
        arrChapter.add(new Chapter("pdf_physicsone_chapter5","Chương 5:","Vật rắn"));
        arrChapter.add(new Chapter("pdf_physicsone_chapter6","Chương 6:","Khí lí tưởng"));

        arrChapter.add(new Chapter("pdf_physicsone_chapter1","Chương 1:","Động học chất điểm"));
        arrChapter.add(new Chapter("pdf_physicsone_chapter2","Chương 2:","Động lực học chất điểm"));
        arrChapter.add(new Chapter("pdf_physicsone_chapter3","Chương 3:","Năng lượng"));
        arrChapter.add(new Chapter("pdf_physicsone_chapter4","Chương 4:","Hệ chất điểm"));
        arrChapter.add(new Chapter("pdf_physicsone_chapter5","Chương 5:","Vật rắn"));
        arrChapter.add(new Chapter("pdf_physicsone_chapter6","Chương 6:","Khí lí tưởng"));
        adapterChapterPhysicsOneStudent = new AdapterChapterPhysicsOneStudent(ListChapterPhysicsOneStudent.this,R.layout.list_chapter_theory_physics_one_student,arrChapter);
        lvListChapterPhysicsOneStudent.setAdapter(adapterChapterPhysicsOneStudent);
    }
}