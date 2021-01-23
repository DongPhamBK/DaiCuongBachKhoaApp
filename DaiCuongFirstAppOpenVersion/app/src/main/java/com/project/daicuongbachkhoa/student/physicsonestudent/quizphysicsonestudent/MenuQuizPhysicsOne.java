package com.project.daicuongbachkhoa.student.physicsonestudent.quizphysicsonestudent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.project.daicuongbachkhoa.R;

import java.util.List;

public class MenuQuizPhysicsOne extends AppCompatActivity {

    private static final int REQUEST_CODE_QUIZ = 1;
    public static final String EXTRA_LEVEL = "extraLevel";
    public static final String EXTRA_CATEGORY_ID = "extraCategoryId";
    public static final String EXTRA_CATEGORY_NAME = "extraCategoryName";
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCOREPHYSICSONE = "keyHighscorePhysicsOne";

    private TextView txtHightScorePhysicsOne;
    private Spinner spCategoryPhysicsOne;
    private Spinner spLevelPhysicsOne;
    private int highscore;
    private Button btnStartQuizPhysicsOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_quiz_physics_one);
        txtHightScorePhysicsOne = findViewById(R.id.txtHightScorePhysicsOne);
        spCategoryPhysicsOne = findViewById(R.id.spCategoryPhysicsOne);
        spLevelPhysicsOne = findViewById(R.id.spLevelPhysicsOne);

        loadCategories();
        loadLevels();
         /*
        String[] levels = Question.getAllLevels();
        ArrayAdapter<String> adaperLevel = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,levels);
        adaperLevel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spLevelPhysicsOne.setAdapter(adaperLevel);*/
        loadHighscore();
        btnStartQuizPhysicsOne = findViewById(R.id.btnStartQuizPhysicsOne);
        btnStartQuizPhysicsOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startQuizPhysicsOne();
            }
        });
    }

    private void startQuizPhysicsOne() {
        CategoryQuizPhysicsOne selectedCategory = (CategoryQuizPhysicsOne) spCategoryPhysicsOne.getSelectedItem();
        int categoryId = selectedCategory.getId();
        String categoryName = selectedCategory.getName();
        String level = spLevelPhysicsOne.getSelectedItem().toString();
        Intent intent = new Intent(MenuQuizPhysicsOne.this, QuizPhysicsOne.class);
        intent.putExtra(EXTRA_CATEGORY_ID, categoryId);// để lưu trữ trong máy !
        intent.putExtra(EXTRA_CATEGORY_NAME, categoryName);
        intent.putExtra(EXTRA_LEVEL, level);
        startActivityForResult(intent, REQUEST_CODE_QUIZ);
    }

    // cập nhật màn hình !
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_QUIZ) {
            if (resultCode == RESULT_OK) {
                int score = data.getIntExtra(QuizPhysicsOne.EXTRA_SCORE, 0);

                if (score > highscore) {

                    updateHighscore(score);
                }

            }
        }
    }

    //lấy chương
    private void loadCategories() {

        DbHelperQuizPhysicsOne dbHelper = DbHelperQuizPhysicsOne.getInstace(this);
        List<CategoryQuizPhysicsOne> categories = dbHelper.getAllCategories();
        ArrayAdapter<CategoryQuizPhysicsOne> adapterCategories = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, categories);
        adapterCategories.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategoryPhysicsOne.setAdapter(adapterCategories);
    }

    // lấy mức độ
    private void loadLevels() {
        String[] difficultyLevels = QuestionQuizPhysicsOne.getAllDifficultyLevels();
        ArrayAdapter<String> adaperDifficulty = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, difficultyLevels);
        adaperDifficulty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spLevelPhysicsOne.setAdapter(adaperDifficulty);
    }

    // Lấy điểm cao !
    private void loadHighscore() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highscore = prefs.getInt(KEY_HIGHSCOREPHYSICSONE, 0);
        txtHightScorePhysicsOne.setText("Điểm cao nhất: " + highscore);


    }

    // cập nhật điểm cao nhất !
    private void updateHighscore(int highscoreNew) {
        highscore = highscoreNew;// cập nhật điểm cao
        txtHightScorePhysicsOne.setText("Điểm cao nhất: " + highscore);
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCOREPHYSICSONE, highscore);
        editor.apply();
    }

}