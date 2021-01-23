package com.project.daicuongbachkhoa.student.physicsonestudent.quizphysicsonestudent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import com.project.daicuongbachkhoa.R;
import com.project.daicuongbachkhoa.student.physicsonestudent.examphysicsonestudent.ExamPhysicsOneStudent;
import com.project.daicuongbachkhoa.student.physicsonestudent.examphysicsonestudent.ResultExamPhysicsOneStudent;

public class QuizPhysicsOne extends AppCompatActivity {

    public static final String EXTRA_SCORE = "extraScore";
    private static final long COUNTDOWN_IN_MILLIS = 15000;// thời gian lựa chọn

    private static final String KEY_SCORE = "keyScore";
    private static final String KEY_QUESTION_COUNT = "keyQuestionCount";
    private static final String KEY_MILLIS_LEFT = "keyMillisLeft";
    private static final String KEY_ANSWERED = "keyAnswered";
    private static final String KEY_QUESTION_LIST = "keyQuestionList";

    private TextView txtQuestionPhysicsOne;
    private TextView txtYourScorePhysicsOne;
    private TextView txtQuestionCountPhysicsOne;
    private TextView txtCategoryPhysicsOne;
    private TextView txtLevelPhysicsOne;

    private TextView txtCountDownPhysisOne;
    private RadioGroup radGroupPhysicsOne;
    private RadioButton radOption1PhysicsOne;
    private RadioButton radOption2PhysicsOne;
    private RadioButton radOption3PhysicsOne;
    private Button btnConfirmPhysicsOne;
    private TextView txtAnswerPhysicsOne;

    private ColorStateList textColorDefaultRb;
    private ColorStateList textColorDefaultCd;
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;


    private ArrayList<QuestionQuizPhysicsOne> questionList;
    private int questionCounter;// bộ đếm câu hỏi
    private int questionCountTotal;// tổng số câu hỏi
    private QuestionQuizPhysicsOne currentQuestion;
    private int yourscore;
    private boolean answered;
    private long backPressdTime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_physics_one);

        txtQuestionPhysicsOne = findViewById(R.id.txtQuestionPhysicsOne);
        txtYourScorePhysicsOne = findViewById(R.id.txtYourScorePhysicsOne);
        txtCountDownPhysisOne = findViewById(R.id.txtCountDownPhysicsOne);
        txtQuestionCountPhysicsOne = findViewById(R.id.txtQuestionCountPhysicsOne);
        txtCategoryPhysicsOne = findViewById(R.id.txtCategoryPhysicsOne);
        txtLevelPhysicsOne = findViewById(R.id.txtLevelPhysicsOne);
        txtAnswerPhysicsOne = findViewById(R.id.txtAnswerPhysicsOne);
        radGroupPhysicsOne = findViewById(R.id.radGroupPhysicsOne);
        radOption1PhysicsOne = findViewById(R.id.radOption1PhysicsOne);
        radOption2PhysicsOne = findViewById(R.id.radOption2PhysicsOne);
        radOption3PhysicsOne = findViewById(R.id.radOption3PhysicsOne);
        btnConfirmPhysicsOne = findViewById(R.id.btnConfirmPhysicsOne);
        textColorDefaultRb = radOption1PhysicsOne.getTextColors();
        textColorDefaultCd = txtCountDownPhysisOne.getTextColors();

        Intent intent = getIntent();
        int categoryId = intent.getIntExtra(MenuQuizPhysicsOne.EXTRA_CATEGORY_ID, 0);
        String categoryName = intent.getStringExtra(MenuQuizPhysicsOne.EXTRA_CATEGORY_NAME);
        String level = intent.getStringExtra(MenuQuizPhysicsOne.EXTRA_LEVEL);

        txtCategoryPhysicsOne.setText("Chương: " + categoryName);
        txtLevelPhysicsOne.setText("Mức độ: " + level);
        if (savedInstanceState == null) {
            DbHelperQuizPhysicsOne dbHelper = DbHelperQuizPhysicsOne.getInstace(this);// chỗ này sửa lại từ nguyên mẫu, vì để dạng instance
            questionList = dbHelper.getQuestions(categoryId, level);// chèn độ khó
            questionCountTotal = questionList.size();
            Collections.shuffle(questionList);
            showNextQuestion();
        } else {
            questionList = savedInstanceState.getParcelableArrayList(KEY_QUESTION_LIST);
            questionCountTotal = questionList.size();
            questionCounter = savedInstanceState.getInt(KEY_QUESTION_COUNT);
            currentQuestion = questionList.get(questionCounter - 1);
            yourscore = savedInstanceState.getInt(KEY_SCORE);
            timeLeftInMillis = savedInstanceState.getLong(KEY_MILLIS_LEFT);
            answered = savedInstanceState.getBoolean(KEY_ANSWERED);

            if (!answered) {
                startCountDown();
            } else {
                updateCountDownText();
                showSolution();
            }
        }
        btnConfirmPhysicsOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!answered) {
                    if (radOption1PhysicsOne.isChecked() || radOption2PhysicsOne.isChecked() || radOption3PhysicsOne.isChecked()) {
                        checkAnswer();
                    } else {
                        Toast.makeText(QuizPhysicsOne.this, "Vui lòng chọn đáp án !", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion();// câu hỏi tiếp theo
                }
            }
        });

    }

    // hàm hiển thị câu hỏi tiếp theo
    private void showNextQuestion() {
        txtAnswerPhysicsOne.setText("");
        txtQuestionPhysicsOne.setBackgroundColor(Color.WHITE);// thêm câu mới, đổi lại màu của câu hỏi
        //txtQuestionPhysicsOne.setBackgroundColor(Color.rgb(221, 221, 221));// thêm mới, đổi lại màu của câu hỏi
        radOption1PhysicsOne.setTextColor(textColorDefaultRb);
        radOption2PhysicsOne.setTextColor(textColorDefaultRb);
        radOption3PhysicsOne.setTextColor(textColorDefaultRb);
        radGroupPhysicsOne.clearCheck();
        if (questionCounter < questionCountTotal) {// nếu vẫn còn câu hỏi

            currentQuestion = questionList.get(questionCounter);

            txtQuestionPhysicsOne.setText(currentQuestion.getQuestion());// hiển thị câu hỏi tiếp theo
            radOption1PhysicsOne.setText(currentQuestion.getOption1());
            radOption2PhysicsOne.setText(currentQuestion.getOption2());
            radOption3PhysicsOne.setText(currentQuestion.getOption3());
            questionCounter++;// tăng bộ đếm
            txtQuestionCountPhysicsOne.setText("Câu hỏi: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            //btnConfirmPhysicsOne.setBackgroundColor(Color.YELLOW);
            btnConfirmPhysicsOne.setText("XÁC NHẬN");
            timeLeftInMillis = COUNTDOWN_IN_MILLIS;
            startCountDown();

        } else {
            finishQuiz();
        }
    }

    // bộ đếm thời gian
    private void startCountDown() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMillis = l;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();
                checkAnswer();
            }
        }.start();
    }

    // định dạng bộ đếm thời gian
    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        txtCountDownPhysisOne.setText(timeFormatted);
        if (timeLeftInMillis < 10000) {
            txtCountDownPhysisOne.setTextColor(Color.RED);
        } else {
            txtCountDownPhysisOne.setTextColor(textColorDefaultCd);
        }
    }

    // kiểm tra kết quả
    private void checkAnswer() {
        answered = true;
        countDownTimer.cancel();
        RadioButton rbSelected = findViewById(radGroupPhysicsOne.getCheckedRadioButtonId());
        int answerNum = radGroupPhysicsOne.indexOfChild(rbSelected) + 1;

        if (answerNum == currentQuestion.getAnswerNum()) {
            txtQuestionPhysicsOne.setBackgroundColor(Color.GREEN);// nếu trả lời đúng sẽ có màu xanh
            yourscore++;
            txtYourScorePhysicsOne.setText("Điểm của bạn: " + yourscore);
        } else {
            txtQuestionPhysicsOne.setBackgroundColor(Color.RED);// trả lời sai
        }
        showSolution();


    }

    // hiển thị lời giải
    private void showSolution() {

        // đáp án nào đúng sẽ có màu xanh
        radOption1PhysicsOne.setTextColor(Color.RED);
        radOption2PhysicsOne.setTextColor(Color.RED);
        radOption3PhysicsOne.setTextColor(Color.RED);

        switch (currentQuestion.getAnswerNum()) {
            case 1:
                radOption1PhysicsOne.setTextColor(Color.GREEN);
                txtAnswerPhysicsOne.setText("Đáp án đúng: A");
                break;
            case 2:
                radOption2PhysicsOne.setTextColor(Color.GREEN);
                txtAnswerPhysicsOne.setText("Đáp án đúng: B");
                break;
            case 3:
                radOption3PhysicsOne.setTextColor(Color.GREEN);
                txtAnswerPhysicsOne.setText("Đáp án đúng: C");
                break;


        }
        if (questionCounter < questionCountTotal) {
            btnConfirmPhysicsOne.setText("Tiếp theo");
            //btnConfirmPhysicsOne.setTextColor(Color.CYAN);
            //btnConfirmPhysicsOne.setBackgroundColor(Colo);
            //btnConfirmPhysicsOne.setBackgroundColor(Color.rgb(100, 132, 124));
        } else {
            txtYourScorePhysicsOne.setBackgroundColor(Color.YELLOW);
            btnConfirmPhysicsOne.setText("Điểm của bạn:" + yourscore);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Chúc mừng ! Điểm của bạn là:\n\n" + "                                      " + yourscore)
                    .setCancelable(false)
                    .setNegativeButton("XÁC NHẬN", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
    }

    private void finishQuiz() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE, yourscore);
        setResult(RESULT_OK, resultIntent);

        finish();
    }

    //thời gian giữ nút back để thoát !
    @Override
    public void onBackPressed() {

        if (backPressdTime + 500 > System.currentTimeMillis()) {
            finishQuiz();
        } else {
            Toast.makeText(this, "Nhấn lại lần nữa để thoát", Toast.LENGTH_SHORT).show();
        }
        backPressdTime = System.currentTimeMillis();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_SCORE, yourscore);
        outState.putInt(KEY_QUESTION_COUNT, questionCounter);
        outState.putLong(KEY_MILLIS_LEFT, timeLeftInMillis);
        outState.putBoolean(KEY_ANSWERED, answered);
        outState.putParcelableArrayList(KEY_QUESTION_LIST, questionList);

    }

}
