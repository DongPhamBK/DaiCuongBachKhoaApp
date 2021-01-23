package com.project.daicuongbachkhoa.student.physicsonestudent.quizphysicsonestudent;

import android.os.Parcel;
import android.os.Parcelable;

public class QuestionQuizPhysicsOne implements Parcelable {



    public static final String LEVEL_EASY = "Dễ";
    public static final String LEVEL_MEDIUM = "Vừa";
    public static final String LEVEL_HARD = "Khó";
    private int id;// chuong
    private String question;// câu hỏi
    private String option1;
    private String option2;
    private String option3;// lựa chọn
    private int answerNum;// câu trả lời chuẩn
    private String level;// độ khó

    private int categoryId;

    public QuestionQuizPhysicsOne() {
    }


    public QuestionQuizPhysicsOne(String question, String option1, String option2, String option3, int answerNum, String difficulty, int categoryId) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.answerNum = answerNum;
        this.level = difficulty;
        this.categoryId = categoryId;
    }

    protected QuestionQuizPhysicsOne(Parcel in) {
        id = in.readInt();
        question = in.readString();
        option1 = in.readString();
        option2 = in.readString();
        option3 = in.readString();
        answerNum = in.readInt();
        level = in.readString();
        categoryId = in.readInt();
    }

    // kèm trong bộ nhớ !
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(question);
        dest.writeString(option1);
        dest.writeString(option2);
        dest.writeString(option3);
        dest.writeInt(answerNum);
        dest.writeString(level);
        dest.writeInt(categoryId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<QuestionQuizPhysicsOne> CREATOR = new Creator<QuestionQuizPhysicsOne>() {
        @Override
        public QuestionQuizPhysicsOne createFromParcel(Parcel in) {
            return new QuestionQuizPhysicsOne(in);
        }

        @Override
        public QuestionQuizPhysicsOne[] newArray(int size) {
            return new QuestionQuizPhysicsOne[size];
        }
    };


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public int getAnswerNum() {
        return answerNum;
    }

    public void setAnswerNum(int answerNum) {
        this.answerNum = answerNum;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    // các mức độ
    public static String[] getAllDifficultyLevels() {
        return new String[]{
                LEVEL_EASY,
                LEVEL_MEDIUM,
                LEVEL_HARD
        };
    }
}
