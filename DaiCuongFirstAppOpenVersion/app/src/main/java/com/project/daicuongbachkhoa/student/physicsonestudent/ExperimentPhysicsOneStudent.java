package com.project.daicuongbachkhoa.student.physicsonestudent;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.project.daicuongbachkhoa.R;

public class ExperimentPhysicsOneStudent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experiment_physics_one_student);

        YouTubePlayerView ytbePhysicsOne01 = findViewById(R.id.ytbePhysicsOne01);
        getLifecycle().addObserver(ytbePhysicsOne01);

        YouTubePlayerView ytbePhysicsOne02 = findViewById(R.id.ytbePhysicsOne02);
        getLifecycle().addObserver(ytbePhysicsOne02);

        YouTubePlayerView ytbePhysicsOne03 = findViewById(R.id.ytbePhysicsOne03);
        getLifecycle().addObserver(ytbePhysicsOne03);

        YouTubePlayerView ytbePhysicsOne04 = findViewById(R.id.ytbePhysicsOne04);
        getLifecycle().addObserver(ytbePhysicsOne04);

        YouTubePlayerView ytbePhysicsOne05 = findViewById(R.id.ytbePhysicsOne05);
        getLifecycle().addObserver(ytbePhysicsOne05);

        YouTubePlayerView ytbePhysicsOne06 = findViewById(R.id.ytbePhysicsOne06);
        getLifecycle().addObserver(ytbePhysicsOne06);
    }
}