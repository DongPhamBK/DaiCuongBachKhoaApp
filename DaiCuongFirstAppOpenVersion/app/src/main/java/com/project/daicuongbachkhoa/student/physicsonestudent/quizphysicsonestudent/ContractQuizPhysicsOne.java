package com.project.daicuongbachkhoa.student.physicsonestudent.quizphysicsonestudent;

import android.provider.BaseColumns;

public class ContractQuizPhysicsOne {

    public ContractQuizPhysicsOne() {
    }

    //declare const Contract of table
    public static class CategoriesTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_categories";
        public static final String COLUMN_NAME = "name";

    }

    public static class QuestionTable implements BaseColumns {
        //bảng câu hỏi
        public static final String TABLE_NAME = "quiz_questions";
        public static final String COLUMN_QUESTION = "question";
        public static  final String COLUMN_OPTION1 = "option1";
        public static  final String COLUMN_OPTION2 = "option2";
        public static  final String COLUMN_OPTION3 = "option3";
        public static final String COLUMN_ANSWER_NUM = "answer_num";
        public static final String COLUMN_LEVEL = "level";
        public static final String COLUMN_CATEGORY_ID = "category_id";

    }
}
