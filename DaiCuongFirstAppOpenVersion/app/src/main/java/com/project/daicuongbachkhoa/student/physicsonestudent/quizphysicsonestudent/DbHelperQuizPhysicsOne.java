package com.project.daicuongbachkhoa.student.physicsonestudent.quizphysicsonestudent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import androidx.annotation.RequiresApi;
import com.project.daicuongbachkhoa.student.physicsonestudent.quizphysicsonestudent.ContractQuizPhysicsOne.CategoriesTable;
import com.project.daicuongbachkhoa.student.physicsonestudent.quizphysicsonestudent.ContractQuizPhysicsOne.QuestionTable;
import java.util.ArrayList;
import java.util.List;

public class DbHelperQuizPhysicsOne extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "QuizPhysicsOne.db";
    private static final int DATABASE_VERSION = 1;
    private static DbHelperQuizPhysicsOne instace;

    private SQLiteDatabase db;

    private DbHelperQuizPhysicsOne(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DbHelperQuizPhysicsOne getInstace(Context context) {
        if (instace == null) {
            instace = new DbHelperQuizPhysicsOne((context.getApplicationContext()));
        }
        return instace;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                CategoriesTable.TABLE_NAME + "( " +
                CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CategoriesTable.COLUMN_NAME + " TEXT " +
                ")";
        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionTable.TABLE_NAME + " ( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionTable.COLUMN_ANSWER_NUM + " INTEGER, " +
                QuestionTable.COLUMN_LEVEL + " TEXT, " +
                QuestionTable.COLUMN_CATEGORY_ID + " INTEGER," +
                "FOREIGN KEY(" + QuestionTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";
        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillCategoriesTable();
        fillQuestionsTable();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // chú ý lệnh sql cần chính xác
        db.execSQL("DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionTable.TABLE_NAME);
        onCreate(db);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
        // cái này nó bắt api cái trên, khi nào lỗi thì cần xem lại !
        /*

        ***********************************


        NHỚ KIỂM TRA LẠI CHỖ NÀY NHÉ !


        ********************************


         */
    }

    //bảng chương học
    private void fillCategoriesTable() {
        CategoryQuizPhysicsOne c1 = new CategoryQuizPhysicsOne("Động học chất điểm");
        insertCategory(c1);
        CategoryQuizPhysicsOne c2 = new CategoryQuizPhysicsOne("Động lực học chất điểm");
        insertCategory(c2);
        CategoryQuizPhysicsOne c3 = new CategoryQuizPhysicsOne("Động lực học vật rắn");
        insertCategory(c3);
    }

    public void addCategory(CategoryQuizPhysicsOne category){
        db = getWritableDatabase();
        insertCategory(category);
    }

    public void addCategories(List<CategoryQuizPhysicsOne> categories ){
        db = getWritableDatabase();
        for(CategoryQuizPhysicsOne category:categories){
            insertCategory(category);
        }
    }

    private void insertCategory(CategoryQuizPhysicsOne category) {

        ContentValues cv = new ContentValues();
        cv.put(CategoriesTable.COLUMN_NAME, category.getName());
        db.insert(CategoriesTable.TABLE_NAME, null, cv);

    }


    // bảng phương án
    private void fillQuestionsTable() {
        QuestionQuizPhysicsOne q1 = new QuestionQuizPhysicsOne("Trường hợp nào dưới đây có thể coi vật chuyển động như một chất điểm ?", "A.Chiếc ô tô trong bến xe.", "B.Mặt Trăng quanh Trái Đất", "C.Con cá trong chậu", 2, QuestionQuizPhysicsOne.LEVEL_EASY, CategoryQuizPhysicsOne.CATEGORY1);
        insertQuestion(q1);
        QuestionQuizPhysicsOne q2 = new QuestionQuizPhysicsOne("Nếu nói \"Trái Đất quay quanh Mặt Trời\" thì trong câu nói này vật nào được chọn làm vật mốc?", "A.Trái Đất", "B.Mặt Trăng", "C.Mặt Trời", 3, QuestionQuizPhysicsOne.LEVEL_EASY, CategoryQuizPhysicsOne.CATEGORY1);
        insertQuestion(q2);
        QuestionQuizPhysicsOne q3 = new QuestionQuizPhysicsOne("Hệ quy chiếu gồm:", "A.Vật làm mốc, hệ toạ độ, mốc thời gian.", "B.Hệ toạ độ, mốc thời gian, đồng hồ.", "C.Vật làm mốc, hệ toạ độ, mốc thời gian và đồng hồ.", 3, QuestionQuizPhysicsOne.LEVEL_EASY, CategoryQuizPhysicsOne.CATEGORY1);
        insertQuestion(q3);
        QuestionQuizPhysicsOne q4 = new QuestionQuizPhysicsOne("Công thức tính vận tốc", "A. v = s/t", "B. v = t/s", "C. v = s*t", 1, QuestionQuizPhysicsOne.LEVEL_EASY, CategoryQuizPhysicsOne.CATEGORY1);
        insertQuestion(q4);
        QuestionQuizPhysicsOne q5 = new QuestionQuizPhysicsOne("Công thức quãng đường đi được của chuyển động thẳng nhanh dần đều là:", "A. s = v0t + at2/2 (a và v0 cùng dấu).", "B. s = v0t + at2/2 (a và v0 trái dấu).", "C. x = x0 + v0t + at2/2 (a và v0 trái dấu).", 1, QuestionQuizPhysicsOne.LEVEL_MEDIUM, CategoryQuizPhysicsOne.CATEGORY1);
        insertQuestion(q5);
        QuestionQuizPhysicsOne q6 = new QuestionQuizPhysicsOne("Tại một nơi nhất định trên Trái Đất và ở gần mặt đất, các vật đều rơi tự do với:", "A.Cùng một gia tốc g.", "B.Gia tốc khác nhau.", "C.Gia tốc bằng 0", 1, QuestionQuizPhysicsOne.LEVEL_MEDIUM, CategoryQuizPhysicsOne.CATEGORY1);
        insertQuestion(q6);

        QuestionQuizPhysicsOne q7 = new QuestionQuizPhysicsOne("Trường hợp nào dưới đây có thể coi vật chuyển động như một chất điểm ?", "A.Chiếc ô tô trong bến xe.", "B.Mặt Trăng quanh Trái Đất", "C.Con cá trong chậu", 2, QuestionQuizPhysicsOne.LEVEL_MEDIUM, CategoryQuizPhysicsOne.CATEGORY1);
        insertQuestion(q7);
        QuestionQuizPhysicsOne q8 = new QuestionQuizPhysicsOne("Nếu nói \"Trái Đất quay quanh Mặt Trời\" thì trong câu nói này vật nào được chọn làm vật mốc?", "A.Trái Đất", "B.Mặt Trăng", "C.Mặt Trời", 3, QuestionQuizPhysicsOne.LEVEL_MEDIUM, CategoryQuizPhysicsOne.CATEGORY1);
        insertQuestion(q8);
        QuestionQuizPhysicsOne q9 = new QuestionQuizPhysicsOne("Hệ quy chiếu gồm:", "A.Vật làm mốc, hệ toạ độ, mốc thời gian.", "B.Hệ toạ độ, mốc thời gian, đồng hồ.", "C.Vật làm mốc, hệ toạ độ, mốc thời gian và đồng hồ.", 3, QuestionQuizPhysicsOne.LEVEL_MEDIUM, CategoryQuizPhysicsOne.CATEGORY1);
        insertQuestion(q9);
        QuestionQuizPhysicsOne q10 = new QuestionQuizPhysicsOne("Công thức tính vận tốc", "A. v = s/t", "B. v = t/s", "C. v = s*t", 1, QuestionQuizPhysicsOne.LEVEL_MEDIUM, CategoryQuizPhysicsOne.CATEGORY1);
        insertQuestion(q10);
        QuestionQuizPhysicsOne q11 = new QuestionQuizPhysicsOne("Công thức quãng đường đi được của chuyển động thẳng nhanh dần đều là:", "A. s = v0t + at2/2 (a và v0 cùng dấu).", "B. s = v0t + at2/2 (a và v0 trái dấu).", "C. x = x0 + v0t + at2/2 (a và v0 trái dấu).", 1, QuestionQuizPhysicsOne.LEVEL_MEDIUM, CategoryQuizPhysicsOne.CATEGORY1);
        insertQuestion(q11);
        QuestionQuizPhysicsOne q12 = new QuestionQuizPhysicsOne("Tại một nơi nhất định trên Trái Đất và ở gần mặt đất, các vật đều rơi tự do với:", "A.Cùng một gia tốc g.", "B.Gia tốc khác nhau.", "C.Gia tốc bằng 0", 1, QuestionQuizPhysicsOne.LEVEL_MEDIUM, CategoryQuizPhysicsOne.CATEGORY1);
        insertQuestion(q12);

        QuestionQuizPhysicsOne q13 = new QuestionQuizPhysicsOne("Trường hợp nào dưới đây có thể coi vật chuyển động như một chất điểm ?", "A.Chiếc ô tô trong bến xe.", "B.Mặt Trăng quanh Trái Đất", "C.Con cá trong chậu", 2, QuestionQuizPhysicsOne.LEVEL_HARD, CategoryQuizPhysicsOne.CATEGORY1);
        insertQuestion(q13);
        QuestionQuizPhysicsOne q14 = new QuestionQuizPhysicsOne("Nếu nói \"Trái Đất quay quanh Mặt Trời\" thì trong câu nói này vật nào được chọn làm vật mốc?", "A.Trái Đất", "B.Mặt Trăng", "C.Mặt Trời", 3, QuestionQuizPhysicsOne.LEVEL_HARD, CategoryQuizPhysicsOne.CATEGORY1);
        insertQuestion(q14);
        QuestionQuizPhysicsOne q15 = new QuestionQuizPhysicsOne("Hệ quy chiếu gồm:", "A.Vật làm mốc, hệ toạ độ, mốc thời gian.", "B.Hệ toạ độ, mốc thời gian, đồng hồ.", "C.Vật làm mốc, hệ toạ độ, mốc thời gian và đồng hồ.", 3, QuestionQuizPhysicsOne.LEVEL_HARD, CategoryQuizPhysicsOne.CATEGORY1);
        insertQuestion(q15);
        QuestionQuizPhysicsOne q16 = new QuestionQuizPhysicsOne("Công thức tính vận tốc", "A. v = s/t", "B. v = t/s", "C. v = s*t", 1, QuestionQuizPhysicsOne.LEVEL_HARD, CategoryQuizPhysicsOne.CATEGORY1);
        insertQuestion(q16);
        QuestionQuizPhysicsOne q17 = new QuestionQuizPhysicsOne("Công thức quãng đường đi được của chuyển động thẳng nhanh dần đều là:", "A. s = v0t + at2/2 (a và v0 cùng dấu).", "B. s = v0t + at2/2 (a và v0 trái dấu).", "C. x = x0 + v0t + at2/2 (a và v0 trái dấu).", 1, QuestionQuizPhysicsOne.LEVEL_HARD, CategoryQuizPhysicsOne.CATEGORY1);
        insertQuestion(q17);
        QuestionQuizPhysicsOne q18 = new QuestionQuizPhysicsOne("Tại một nơi nhất định trên Trái Đất và ở gần mặt đất, các vật đều rơi tự do với:", "A.Cùng một gia tốc g.", "B.Gia tốc khác nhau.", "C.Gia tốc bằng 0", 1, QuestionQuizPhysicsOne.LEVEL_HARD, CategoryQuizPhysicsOne.CATEGORY1);
        insertQuestion(q18);

    }


    public void addQuestion(QuestionQuizPhysicsOne question){
        db = getWritableDatabase();
        insertQuestion(question);
    }

    public void addQuestions(List<QuestionQuizPhysicsOne> questions){
        db = getWritableDatabase();
        for(QuestionQuizPhysicsOne question:questions){
            insertQuestion(question);
        }
    }

    private void insertQuestion(QuestionQuizPhysicsOne question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionTable.COLUMN_ANSWER_NUM, question.getAnswerNum());
        cv.put(QuestionTable.COLUMN_LEVEL, question.getLevel());
        cv.put(QuestionTable.COLUMN_CATEGORY_ID, question.getCategoryId());
        db.insert(QuestionTable.TABLE_NAME, null, cv);
    }

    public List<CategoryQuizPhysicsOne> getAllCategories() {
        List<CategoryQuizPhysicsOne> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + CategoriesTable.TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                CategoryQuizPhysicsOne category = new CategoryQuizPhysicsOne();
                category.setId(c.getInt(c.getColumnIndex(CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);

            } while (c.moveToNext());
        }
        c.close();
        return categoryList;
    }

    // nhập list câu hỏi
    public ArrayList<QuestionQuizPhysicsOne> getAllQuestions() {
        ArrayList<QuestionQuizPhysicsOne> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                QuestionQuizPhysicsOne question = new QuestionQuizPhysicsOne();
                question.setId(c.getInt(c.getColumnIndex(QuestionTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setAnswerNum(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NUM)));
                question.setLevel(c.getString(c.getColumnIndex(QuestionTable.COLUMN_LEVEL)));
                question.setCategoryId(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);

            } while (c.moveToNext());
        }
        c.close();// đừng quên khoá lại !
        return questionList;

    }

    public ArrayList<QuestionQuizPhysicsOne> getQuestions(int categoryId, String difficulty) {
        ArrayList<QuestionQuizPhysicsOne> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = QuestionTable.COLUMN_CATEGORY_ID + " = ? " +
                " AND " + QuestionTable.COLUMN_LEVEL + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryId),difficulty};
        Cursor c = db.query(
                QuestionTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

       /* String[] selectionArgs = new String[]{difficulty};
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionTable.TABLE_NAME +
                " WHERE " + QuestionTable.COLUMN_DIFFICULTY + " = ?", selectionArgs);
*/

        if (c.moveToFirst()) {
            do {
                QuestionQuizPhysicsOne question = new QuestionQuizPhysicsOne();
                question.setId(c.getInt(c.getColumnIndex(QuestionTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setAnswerNum(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NUM)));
                question.setLevel(c.getString(c.getColumnIndex(QuestionTable.COLUMN_LEVEL)));
                question.setCategoryId(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();// đừng quên khoá lại !
        return questionList;

    }

}
