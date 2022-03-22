package com.example.flashcards.library;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.flashcards.models.FlashCardModel;
import com.example.flashcards.models.QuestionModel;
import com.example.flashcards.models.QuizModel;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final String DATABASE_NAME = "MathToMoon.db";
    public static final int DATABASE_VERSION = 3;

    //Topics table constraints
    public static final String TOPICS_ID = "Tid";
    public static final String TOPICS_TABLE = "Topics";
    public static final String COLUMN_TOPIC = "Topic";

    //Question Table constraints
    public static final String QUESTIONS_TABLE = "Questions";
    public static final String QUESTION_ID = "Qid";
    public static final String COLUMN_QUESTION = "Question";
    public static final String COLUMN_ANSWER = "Answer";
    public static final String COLUMN_TOPICS_ID = "TopicsId";

    //FlashCard Table constraints
    public static final String FLASHCARDS_TABLE = "FlashCards";
    public static final String FLASHCARDS_ID = "Fid";
    public static final String COLUMN_QUESTIONS_ID = "QuestionId";
    public static final String COLUMN_isATTEMPTED = "isAttempted";

    //Quizes Table constraints
    public static final String QUIZZES_TABLE = "Quizzes";
    public static final String QUIZ_ID = "Quiz_id";

    //Answers Table constraints
    public static final String ANSWERS_TABLE = "Answers";
    public static final String ANSWER_ID = "Aid";
    public static final String COLUMN_TYPE = "Type";
    public static final String COLUMN_USER_ANSWER = "Answer";
    public static final String COLUMN_isCORRECT = "isCorrect";

    public static final String MASTERY_TABLE = "Mastery";
    public static final String MASTERY_ID = "Mid";
    public static final String COLUMN_STATUS = "status";
    public static final int DEFAULT_STATUS = 0;



    private static final String[] topics = {"ADDITION", "SUBTRACTION", "MULTIPLICATION", "DIVISION"};

    //Flash Card Questions and Answers.
    private static final QuestionModel[] questions = {new QuestionModel(0, "10 + 5", "15", "ADDITION"),
                                                      new QuestionModel(0, "18 + 8 + 12", "38", "ADDITION"),
                                                      new QuestionModel(0, "12 + 6", "18", "ADDITION"),
                                                      new QuestionModel(0, "14 + 7 + 21", "42", "ADDITION"),
                                                      new QuestionModel(0, "14 + 20 + 21", "55", "ADDITION"),
                                                      new QuestionModel(0, "(10 - 5)", "5", "SUBTRACTION"),
                                                      new QuestionModel(0, "(19 - 23)", "-4", "SUBTRACTION"),
                                                      new QuestionModel(0, "(53 - 25)", "28", "SUBTRACTION"),
                                                      new QuestionModel(0, "(8 - 4 - 2)", "2", "SUBTRACTION"),
                                                      new QuestionModel(0, "(83 - 50 - 23 - 6)", "4", "SUBTRACTION"),
                                                      new QuestionModel(0, "8 * 2", "16", "MULTIPLICATION"),
                                                      new QuestionModel(0, "7 * 7", "49", "MULTIPLICATION"),
                                                      new QuestionModel(0, "5 * (6 + 4)", "50", "MULTIPLICATION"),
                                                      new QuestionModel(0, "4 * 7 * 9", "252", "MULTIPLICATION"),
                                                      new QuestionModel(0, "8 * (6 - 2)", "32", "MULTIPLICATION"),
                                                      new QuestionModel(0, "15 / 3", "5", "DIVISION"),
                                                      new QuestionModel(0, "42 / 6", "7", "DIVISION"),
                                                      new QuestionModel(0, "50 / (2 + 3)", "10", "DIVISION"),
                                                      new QuestionModel(0, "24 / ( 10 -4)", "4", "DIVISION"),
                                                      new QuestionModel(0, "24 / 6 - 2", "2", "DIVISION"),
                                                      new QuestionModel(0, "9 + 5", "14", "ADDITION"),
                                                      new QuestionModel(0, "20 + 11 + 12", "43", "ADDITION"),
                                                      new QuestionModel(0, "12 + 12", "24", "ADDITION"),
                                                      new QuestionModel(0, "30 + 7 + 40", "77", "ADDITION"),
                                                      new QuestionModel(0, "25 + 8 + 3", "36", "ADDITION"),
                                                      new QuestionModel(0, "(9 - 2)", "7", "SUBTRACTION"),
                                                      new QuestionModel(0, "(36 - 25)", "11", "SUBTRACTION"),
                                                      new QuestionModel(0, "(45 - 23)", "22", "SUBTRACTION"),
                                                      new QuestionModel(0, "(10 - 5 - 1)", "4", "SUBTRACTION"),
                                                      new QuestionModel(0, "(99 - 50 - 32 - 6)", "11", "SUBTRACTION"),
                                                      new QuestionModel(0, "5 * 5", "25", "MULTIPLICATION"),
                                                      new QuestionModel(0, "8 * 6", "48", "MULTIPLICATION"),
                                                      new QuestionModel(0, "4 * (8 + 2)", "40", "MULTIPLICATION"),
                                                      new QuestionModel(0, "4 * 5 * 8", "160", "MULTIPLICATION"),
                                                      new QuestionModel(0, "8 * (2 - 2)", "0", "MULTIPLICATION"),
                                                      new QuestionModel(0, "39 / 3", "13", "DIVISION"),
                                                      new QuestionModel(0, "72 / 6", "12", "DIVISION"),
                                                      new QuestionModel(0, "100 / (2 + 3)", "20", "DIVISION"),
                                                      new QuestionModel(0, "78 / ( 10 -4)", "13", "DIVISION"),
                                                      new QuestionModel(0, "60 / 6 - 2", "15", "DIVISION")};






    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //This function will invoke either on the first run of your app or when someone clears all your app's data
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("Database Tables", "Inside the onCreate function");
        //Creating statement for Topics Table
        String createTopicsTableStatement = "CREATE TABLE " + TOPICS_TABLE + " ("
                + TOPICS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TOPIC + " TEXT UNIQUE)";

        //Creating statement for Questions Table
        String createQuestionsTableStatement = "CREATE TABLE " + QUESTIONS_TABLE + " ("
                + QUESTION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_QUESTION + " TEXT, "
                + COLUMN_ANSWER + " TEXT, "
                + COLUMN_TOPICS_ID + " INTEGER, " +
                "FOREIGN KEY (" + COLUMN_TOPICS_ID + ") " +
                "REFERENCES " + TOPICS_TABLE + "("+ TOPICS_ID +"))";

        //Creating statement for FlashCards Table
        String createFlashCardsTableStatement = "CREATE TABLE " + FLASHCARDS_TABLE + " ("
                + FLASHCARDS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_QUESTIONS_ID + " int, "
                + COLUMN_isATTEMPTED + " BOOL DEFAULT (FALSE), " +
                "FOREIGN KEY (" + COLUMN_QUESTIONS_ID +") " +
                "REFERENCES " + QUESTIONS_TABLE + "(" + QUESTION_ID + "))";

        //Creating statement for Quizes Table
        String createQuizzesTableStatement = "CREATE TABLE " + QUIZZES_TABLE + " ("
                + QUIZ_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_QUESTIONS_ID + " int, "
                + COLUMN_isATTEMPTED + " BOOL DEFAULT (FALSE), " +
                "FOREIGN KEY (" + COLUMN_QUESTIONS_ID +") " +
                "REFERENCES " + QUESTIONS_TABLE + "(" + QUESTION_ID + "))";

        //Creating statement for Answer Table
        String createAnswersTableStatement = "CREATE TABLE " + ANSWERS_TABLE + " ("
                + ANSWER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_QUESTIONS_ID + " int, "
                + COLUMN_USER_ANSWER + " DOUBLE, "
                + COLUMN_TYPE + " TEXT, "
                + COLUMN_isCORRECT + " BOOL , " +
                "FOREIGN KEY (" + COLUMN_QUESTIONS_ID +") " +
                "REFERENCES " + QUESTIONS_TABLE + "(" + QUESTION_ID + "))";

        //Creating statement for Mastery Table
        String createMasteryTableStatement = "CREATE TABLE " + MASTERY_TABLE + " ("
                + MASTERY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_STATUS + " INTEGER )";

        try {
            //Executing Topics Table
            db.execSQL(createTopicsTableStatement);
            //Executing Questions Table
            db.execSQL(createQuestionsTableStatement);
            //Executing FlashCards Table
            db.execSQL(createFlashCardsTableStatement);
            //Executing Quizzes Table
            db.execSQL(createQuizzesTableStatement);
            //Executing Answer Table
            db.execSQL(createAnswersTableStatement);
            //Executing Mastery Table
            db.execSQL(createMasteryTableStatement);
            this.insertTopics(db);
            this.insertQuestions(db);
            this.insertFlashCards(db);
            this.insertQuizzes(db);
            this.insertMastery(db);

        }catch (Exception e){
            Log.d("Error in Database table", "Error:"+e.getMessage());
        }

    }

    //This is called if the database version number changes. It prevents user apps from breaking when you change the database schema
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TOPICS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + MASTERY_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + QUIZZES_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + FLASHCARDS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + QUESTIONS_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ANSWERS_TABLE);
        onCreate(db);

    }

    //This function is to insert initial values Topics Table
    public void insertTopics(SQLiteDatabase db){
        try{
            for(String topic: topics){
                ContentValues contentValues = new ContentValues();
                contentValues.put(COLUMN_TOPIC, topic);
                db.insert(TOPICS_TABLE,null,contentValues);
            }
            Log.d("Topics Table", "Initial Topics have successfully inserted");
        }catch (Exception e){
            Log.d("Topics Table", "There was some error while inserting Topics - "+e.getMessage());
        }

    }

    public void insertQuestions(SQLiteDatabase db){
        try{
            for(QuestionModel question: questions){
                ContentValues contentValues = new ContentValues();
                contentValues.put(COLUMN_QUESTION, question.getQuestion());
                contentValues.put(COLUMN_ANSWER, question.getAnswer());
                contentValues.put(COLUMN_TOPICS_ID, this.getTopicId(db, question.getTopic()));
                db.insert(QUESTIONS_TABLE,null,contentValues);
            }
            Log.d("Questions Table", "Initial Questions have successfully inserted");
        }catch (Exception e){
            Log.d("Questions Table", "There was some error while inserting Questions - "+e.getMessage());
        }
    }

    //This function is to insert initial values of flashcards
    public void insertFlashCards(SQLiteDatabase db){
        int questionIds[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};       //Since we know what questionId will be, just inserting values manually.
        try{
            for(int questionId: questionIds){
                ContentValues contentValues = new ContentValues();
                contentValues.put(COLUMN_QUESTIONS_ID, questionId);
                db.insert(FLASHCARDS_TABLE,null,contentValues);
            }
            Log.d("FlashCards Table", "Initial FlashCards have successfully inserted");
        }catch (Exception e){
            Log.d("FlashCards Table", "There was some error while inserting FlashCard - "+e.getMessage());
        }
    }

    //This function is to insert initial values of Quizzes
    public void insertQuizzes(SQLiteDatabase db){
        //db = this.getWritableDatabase();
        int questionIds[] = {21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40};       //Since we know what questionId will be, just inserting values manually.
        try{
            for(int questionId: questionIds){
                ContentValues contentValues = new ContentValues();
                contentValues.put(COLUMN_QUESTIONS_ID, questionId);
                db.insert(QUIZZES_TABLE,null,contentValues);
            }
            Log.d("Quizzes Table", "Initial Quizzes have successfully inserted");
        }catch (Exception e){
            Log.d("Quizzes Table", "There was some error while inserting Quizzes - "+e.getMessage());
        }
    }

    //This function is to insert initial values Mastery Table
    public void insertMastery(SQLiteDatabase db){
        try{
                ContentValues contentValues = new ContentValues();
                contentValues.put(COLUMN_STATUS, DEFAULT_STATUS);
                db.insert(MASTERY_TABLE,null,contentValues);
            Log.d("Mastery Table", "Initial status have successfully inserted");
        }catch (Exception e){
            Log.d("Topics Table", "There was some error while inserting status - "+e.getMessage());
        }

    }

    //Get id of topic.
    public int getTopicId(SQLiteDatabase db, String topic){
        int topicId = 0;
        List<FlashCardModel> returnList = new ArrayList<>();
        String query = "SELECT "+ TOPICS_ID + " FROM " + TOPICS_TABLE + " WHERE "+ COLUMN_TOPIC+ " = '"+topic+"'";
        try {
            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                topicId = cursor.getInt(0);
                Log.d("Topic Table", "Topic Id is fetched");
            } else {
                //If table is empty
                Log.d("Topic Table", "Topic Id is not fetched");
            }
        }catch (Exception e){
            Log.d("Topic Table", "Error : "+e.getMessage());
        }
        return topicId;
    }

    //Get all the flash cards.
    public List<FlashCardModel> getAllFlashCards(){
        List<FlashCardModel> returnList = new ArrayList<>();
        String query = "SELECT f." +FLASHCARDS_ID+ ", f." +COLUMN_isATTEMPTED+ ", q." +QUESTION_ID+ ", " +
                "q." +COLUMN_QUESTION+ ", q." +COLUMN_ANSWER+ ", t." +COLUMN_TOPIC+ " FROM " +FLASHCARDS_TABLE+
                " AS f JOIN " +QUESTIONS_TABLE+ " AS q ON f." +COLUMN_QUESTIONS_ID+ " = q." +QUESTION_ID+
                " JOIN " +TOPICS_TABLE+ " as t ON q." +COLUMN_TOPICS_ID+ " = t." +TOPICS_ID;
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {

                    FlashCardModel flashCard = new FlashCardModel();
                    QuestionModel question = new QuestionModel();
                    flashCard.setId(cursor.getInt(0));
                    flashCard.setFlashCardAttempted(cursor.getInt(1) == 1 ? true : false);
                    question.setId(cursor.getInt(2));
                    question.setQuestion(cursor.getString(3));
                    question.setAnswer(cursor.getString(4));
                    question.setTopic(cursor.getString(5));
                    flashCard.setQuestions(question);
                    returnList.add(flashCard);
                } while (cursor.moveToNext());
                Log.d("Display Flash Card", "FlashCard Table is fetched");
            } else {
                //If flashCard table is empty
                Log.d("Display Flash Card", "FlashCard Table is empty");
            }
        }catch (Exception e){
            Log.d("Display Flash Card", "Error : "+e.getMessage());
        }finally {
            db.close();
        }
        return returnList;
    }

    //Get all the flashCard with given Topic.
    public List<FlashCardModel> getAllFlashCardsWithTopic(String topic){
        List<FlashCardModel> returnList = new ArrayList<>();
        boolean valid = false;
        for(String tp: topics){
            if(tp.equals(topic)){
                valid = true;
            }
        }
        if(valid) {
            String query = "SELECT f." +FLASHCARDS_ID+ ", f." +COLUMN_isATTEMPTED+ ", q." +QUESTION_ID+ ", " +
                    "q." +COLUMN_QUESTION+ ", q." +COLUMN_ANSWER+ " FROM " +FLASHCARDS_TABLE+
                    " AS f JOIN " +QUESTIONS_TABLE+ " AS q ON f." +COLUMN_QUESTIONS_ID+ " = q." +QUESTION_ID+
                    " AND q." +COLUMN_TOPICS_ID+ " = (SELECT " +TOPICS_ID+ " from " +TOPICS_TABLE+
                    " where " +COLUMN_TOPIC+ " = \"" +topic+ "\")";

            Log.d("*String query*", query);
            SQLiteDatabase db = this.getReadableDatabase();
            try {
                Cursor cursor = db.rawQuery(query, null);

                if (cursor.moveToFirst()) {
                    do {

                        FlashCardModel flashCard = new FlashCardModel();
                        QuestionModel question = new QuestionModel();
                        flashCard.setId(cursor.getInt(0));
                        flashCard.setFlashCardAttempted(cursor.getInt(1) == 1 ? true : false);
                        question.setId(cursor.getInt(2));
                        question.setQuestion(cursor.getString(3));
                        question.setAnswer(cursor.getString(4));
                        question.setTopic(topic);
                        flashCard.setQuestions(question);
                        returnList.add(flashCard);
                    } while (cursor.moveToNext());
                    Log.d("Display Flash Card", "FlashCard Table is fetched");
                } else {
                    //If flashCard table is empty
                    Log.d("Display Flash Card", "FlashCard Table is empty");
                }
            }catch (Exception e){
                Log.d("Display Flash Card", "Error while fetching details with topic : "+ e.getMessage());
            }finally {
                db.close();
            }
        }else{
            Log.d("*Display FlashCard*", "There is no questions based on "+topic);
        }
        return returnList;
    }

    //Get all the flash cards Not Attempted.
    public List<FlashCardModel> getAllFlashCardsNotAttempted(){
        List<FlashCardModel> returnList = new ArrayList<>();
        String query = "SELECT f." +FLASHCARDS_ID+ ", f." +COLUMN_isATTEMPTED+ ", q." +QUESTION_ID+ ", " +
                "q." +COLUMN_QUESTION+ ", q." +COLUMN_ANSWER+ ", t." +COLUMN_TOPIC+ " FROM " +FLASHCARDS_TABLE+
                " AS f JOIN " +QUESTIONS_TABLE+ " AS q ON f." +COLUMN_QUESTIONS_ID+ " = q." +QUESTION_ID+
                " JOIN " +TOPICS_TABLE+ " as t ON q." +COLUMN_TOPICS_ID+ " = t." +TOPICS_ID+ " AND f."+COLUMN_isATTEMPTED+ " = 0 ";
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {

                    FlashCardModel flashCard = new FlashCardModel();
                    QuestionModel question = new QuestionModel();
                    flashCard.setId(cursor.getInt(0));
                    flashCard.setFlashCardAttempted(cursor.getInt(1) == 1 ? true : false);
                    question.setId(cursor.getInt(2));
                    question.setQuestion(cursor.getString(3));
                    question.setAnswer(cursor.getString(4));
                    question.setTopic(cursor.getString(5));
                    flashCard.setQuestions(question);
                    returnList.add(flashCard);
                } while (cursor.moveToNext());
                Log.d("Display Flash Card", "FlashCard Table is fetched");
            } else {
                //If flashCard table is empty
                Log.d("Display Flash Card", "FlashCard Table is empty");
            }
        }catch (Exception e){
            Log.d("Display Flash Card", "Error : "+e.getMessage());
        }finally {
            db.close();
        }
        return returnList;
    }

    //Get all the flashCard NotAttempted with given Topic.
    public List<FlashCardModel> getAllFlashCardsWithTopicNotAttempted(String topic){
        List<FlashCardModel> returnList = new ArrayList<>();
        boolean valid = false;
        for(String tp: topics){
            if(tp.equals(topic)){
                valid = true;
            }
        }
        if(valid) {
            String query = "SELECT f." +FLASHCARDS_ID+ ", f." +COLUMN_isATTEMPTED+ ", q." +QUESTION_ID+ ", " +
                    "q." +COLUMN_QUESTION+ ", q." +COLUMN_ANSWER+ " FROM " +FLASHCARDS_TABLE+
                    " AS f JOIN " +QUESTIONS_TABLE+ " AS q ON f." +COLUMN_QUESTIONS_ID+ " = q." +QUESTION_ID+
                    " AND q." +COLUMN_TOPICS_ID+ " = (SELECT " +TOPICS_ID+ " from " +TOPICS_TABLE+
                    " where " +COLUMN_TOPIC+ " = \"" +topic+ "\") AND f." +COLUMN_isATTEMPTED+ " = 0";

            Log.d("*String query*", query);
            SQLiteDatabase db = this.getReadableDatabase();
            try {
                Cursor cursor = db.rawQuery(query, null);

                if (cursor.moveToFirst()) {
                    do {

                        FlashCardModel flashCard = new FlashCardModel();
                        QuestionModel question = new QuestionModel();
                        flashCard.setId(cursor.getInt(0));
                        flashCard.setFlashCardAttempted(cursor.getInt(1) == 1 ? true : false);
                        question.setId(cursor.getInt(2));
                        question.setQuestion(cursor.getString(3));
                        question.setAnswer(cursor.getString(4));
                        question.setTopic(topic);
                        flashCard.setQuestions(question);
                        returnList.add(flashCard);
                    } while (cursor.moveToNext());
                    Log.d("Display Flash Card", "FlashCard Table is fetched");
                } else {
                    //If flashCard table is empty
                    Log.d("Display Flash Card", "FlashCard Table is empty");
                }
            }catch (Exception e){
                Log.d("Display Flash Card", "Error while fetching details with topic : "+ e.getMessage());
            }finally {
                db.close();
            }
        }else{
            Log.d("*Display FlashCard*", "There is no questions based on "+topic);
        }
        return returnList;
    }

    //Get all the quizzes.
    public List<QuizModel> getAllQuizzes(){
        List<QuizModel> returnList = new ArrayList<>();
        String query = "SELECT quiz." +QUIZ_ID+ ", quiz." +COLUMN_isATTEMPTED+ ", q." +QUESTION_ID+ ", " +
                "q." +COLUMN_QUESTION+ ", q." +COLUMN_ANSWER+ ", t." +COLUMN_TOPIC+ " FROM " +QUIZZES_TABLE+
                " AS quiz JOIN " +QUESTIONS_TABLE+ " AS q ON quiz." +COLUMN_QUESTIONS_ID+ " = q." +QUESTION_ID+
                " JOIN " +TOPICS_TABLE+ " as t ON q." +COLUMN_TOPICS_ID+ " = t." +TOPICS_ID;
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {

                    QuizModel quiz = new QuizModel();
                    QuestionModel question = new QuestionModel();
                    quiz.setId(cursor.getInt(0));
                    quiz.setQuizAttempted(cursor.getInt(1) == 1 ? true : false);
                    question.setId(cursor.getInt(2));
                    question.setQuestion(cursor.getString(3));
                    question.setAnswer(cursor.getString(4));
                    question.setTopic(cursor.getString(5));
                    quiz.setQuestions(question);
                    returnList.add(quiz);
                } while (cursor.moveToNext());
                Log.d("Display Quizzes", "Quizzes Table is fetched");
            } else {
                //If flashCard table is empty
                Log.d("Display Quizzes", "Quizzes Table is empty");
            }
        }catch (Exception e){
            Log.d("Display Quizzes", "Error : "+e.getMessage());
        }finally {
            db.close();
        }
        return returnList;
    }

    //Get all the quizzes with given Topic.
    public List<QuizModel> getAllQuizzesWithTopic(String topic){
        List<QuizModel> returnList = new ArrayList<>();
        boolean valid = false;
        for(String tp: topics){
            if(tp.equals(topic)){
                valid = true;
            }
        }
        if(valid) {
            String query = "SELECT quiz." + QUIZ_ID + ", quiz." + COLUMN_isATTEMPTED + ", q." + QUESTION_ID + ", " +
                    "q." + COLUMN_QUESTION + ", q." + COLUMN_ANSWER + " FROM " + QUIZZES_TABLE +
                    " AS quiz JOIN " + QUESTIONS_TABLE + " AS q ON quiz." + COLUMN_QUESTIONS_ID + " = q." + QUESTION_ID +
                    " AND q." +COLUMN_TOPICS_ID+ " = (SELECT " +TOPICS_ID+ " from " +TOPICS_TABLE+
                    " where " +COLUMN_TOPIC+ " = \"" +topic+ "\")";
            Log.d("*String query*", query);
            SQLiteDatabase db = this.getReadableDatabase();
            try {
                Cursor cursor = db.rawQuery(query, null);

                if (cursor.moveToFirst()) {
                    do {

                        QuizModel quiz = new QuizModel();
                        QuestionModel question = new QuestionModel();
                        quiz.setId(cursor.getInt(0));
                        quiz.setQuizAttempted(cursor.getInt(1) == 1 ? true : false);
                        question.setId(cursor.getInt(2));
                        question.setQuestion(cursor.getString(3));
                        question.setAnswer(cursor.getString(4));
                        question.setTopic(topic);
                        quiz.setQuestions(question);
                        returnList.add(quiz);
                    } while (cursor.moveToNext());
                    Log.d("Display Quizzes", "Quiz Table is fetched");
                } else {
                    //If flashCard table is empty
                    Log.d("Display Quizzes", "Quiz Table is empty");
                }
            }catch (Exception e){
                Log.d("Display Quizzes", "Error : "+e.getMessage());
            }finally {
                db.close();
            }
        }else{
            Log.d("*Display FlashCard*", "There is no questions based on "+topic);
        }
        return returnList;
    }

    //Get all the quizzes NotAttempted.
    public List<QuizModel> getAllQuizzesNotAttempted(){
        List<QuizModel> returnList = new ArrayList<>();
        String query = "SELECT quiz." +QUIZ_ID+ ", quiz." +COLUMN_isATTEMPTED+ ", q." +QUESTION_ID+ ", " +
                "q." +COLUMN_QUESTION+ ", q." +COLUMN_ANSWER+ ", t." +COLUMN_TOPIC+ " FROM " +QUIZZES_TABLE+
                " AS quiz JOIN " +QUESTIONS_TABLE+ " AS q ON quiz." +COLUMN_QUESTIONS_ID+ " = q." +QUESTION_ID+
                " JOIN " +TOPICS_TABLE+ " as t ON q." +COLUMN_TOPICS_ID+ " = t." +TOPICS_ID+ " AND quiz."+COLUMN_isATTEMPTED+ " = 0 ";
        SQLiteDatabase db = this.getReadableDatabase();
        try {
            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                do {

                    QuizModel quiz = new QuizModel();
                    QuestionModel question = new QuestionModel();
                    quiz.setId(cursor.getInt(0));
                    quiz.setQuizAttempted(cursor.getInt(1) == 1 ? true : false);
                    question.setId(cursor.getInt(2));
                    question.setQuestion(cursor.getString(3));
                    question.setAnswer(cursor.getString(4));
                    question.setTopic(cursor.getString(5));
                    quiz.setQuestions(question);
                    returnList.add(quiz);
                } while (cursor.moveToNext());
                Log.d("Display Quizzes", "Quizzes Table is fetched");
            } else {
                //If flashCard table is empty
                Log.d("Display Quizzes", "Quizzes Table is empty");
            }
        }catch (Exception e){
            Log.d("Display Quizzes", "Error : "+e.getMessage());
        }finally {
            db.close();
        }
        return returnList;
    }

    //Get all the quizzes NotAttempted with given Topic.
    public List<QuizModel> getAllQuizzesWithTopicNotAttempted (String topic){
        List<QuizModel> returnList = new ArrayList<>();
        boolean valid = false;
        for(String tp: topics){
            if(tp.equals(topic)){
                valid = true;
            }
        }
        if(valid) {
            String query = "SELECT quiz." + QUIZ_ID + ", quiz." + COLUMN_isATTEMPTED + ", q." + QUESTION_ID + ", " +
                    "q." + COLUMN_QUESTION + ", q." + COLUMN_ANSWER + " FROM " + QUIZZES_TABLE +
                    " AS quiz JOIN " + QUESTIONS_TABLE + " AS q ON quiz." + COLUMN_QUESTIONS_ID + " = q." + QUESTION_ID +
                    " AND q." +COLUMN_TOPICS_ID+ " = (SELECT " +TOPICS_ID+ " from " +TOPICS_TABLE+
                    " where " +COLUMN_TOPIC+ " = \"" +topic+ "\") AND quiz." +COLUMN_isATTEMPTED+ " = 0 ";
            Log.d("*String query*", query);
            SQLiteDatabase db = this.getReadableDatabase();
            try {
                Cursor cursor = db.rawQuery(query, null);

                if (cursor.moveToFirst()) {
                    do {

                        QuizModel quiz = new QuizModel();
                        QuestionModel question = new QuestionModel();
                        quiz.setId(cursor.getInt(0));
                        quiz.setQuizAttempted(cursor.getInt(1) == 1 ? true : false);
                        question.setId(cursor.getInt(2));
                        question.setQuestion(cursor.getString(3));
                        question.setAnswer(cursor.getString(4));
                        question.setTopic(topic);
                        quiz.setQuestions(question);
                        returnList.add(quiz);
                    } while (cursor.moveToNext());
                    Log.d("Display Quizzes", "Quiz Table is fetched");
                } else {
                    //If flashCard table is empty
                    Log.d("Display Quizzes", "Quiz Table is empty");
                }
            }catch (Exception e){
                Log.d("Display Quizzes", "Error : "+e.getMessage());
            }finally {
                db.close();
            }
        }else{
            Log.d("*Display FlashCard*", "There is no questions based on "+topic);
        }
        return returnList;
    }


    //When user has attempted a flashcard, call this function to update the isATTEMPTED.
    public Boolean updateFlashCardAttempted(int flashCardID,boolean isFlashCardAttempted){
        boolean value = false;
        String condition = FLASHCARDS_ID+" = ?";
        String querySelect = "SELECT * FROM "+FLASHCARDS_TABLE+" WHERE "+condition;
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_isATTEMPTED, isFlashCardAttempted);
        try {
            Cursor cursor = db.rawQuery(querySelect, new String[]{String.valueOf(flashCardID)});
            if (cursor.getCount() > 0) {
                long result = db.update(FLASHCARDS_TABLE, contentValues, condition, new String[]{String.valueOf(flashCardID)});
                if (result == -1) {
                    Log.d("Update FC isAttempted", "Update has failed");
                    value = false;
                } else {
                    Log.d("Update FC isAttempted", "Update has Successful");
                    value =  true;
                }
            } else {
                Log.d("Update FC isAttempted", "Invalid FC_ID");
                value = false;
            }
        }catch (Exception e){
            Log.d("Update FC isAttempted", "Error : "+e.getMessage());
        }
        finally {
            db.close();
            return value;
        }
    }

    //When user has attempted a quiz, call this function to update the isATTEMPTED.
    public Boolean updateQuizAttempted(int quizID,boolean isQuizAttempted){
        boolean value = false;
        String condition = QUIZ_ID+" = ?";
        String querySelect = "SELECT * FROM "+QUIZZES_TABLE+" WHERE "+condition;
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_isATTEMPTED, isQuizAttempted);
        try {
            Cursor cursor = db.rawQuery(querySelect, new String[]{String.valueOf(quizID)});
            if (cursor.getCount() > 0) {
                long result = db.update(QUIZZES_TABLE, contentValues, condition, new String[]{String.valueOf(quizID)});
                if (result == -1) {
                    Log.d("Update Quiz isAttempted", "Update has failed");
                    value = false;
                } else {
                    Log.d("Update Quiz isAttempted", "Update has Successful");
                    value = true;
                }
            } else {
                Log.d("Update Quiz isAttempted", "Invalid Quiz ID");
                value = false;
            }
        }catch (Exception e){
            Log.d("Update FC isAttempted", "Error : "+e.getMessage());
        }
        finally {
            db.close();
            return value;
        }
    }

    //Get Mastery Table
    public int getMastery(){
        SQLiteDatabase db = this.getReadableDatabase();
        int status = 0;
        String query = "SELECT "+ COLUMN_STATUS + " FROM " + MASTERY_TABLE + " WHERE " + MASTERY_ID + " = 1";
        try {
            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                status = cursor.getInt(0);
                Log.d("Mastery Table", COLUMN_STATUS+" is fetched");
            } else {
                //If table is empty
                Log.d("Mastery Table", COLUMN_STATUS+" is fetched");
            }
        }catch (Exception e){
            Log.d("Mastery Table", "Error : "+e.getMessage());
        }
        return status;
    }

    //function to update the mastery value
    public Boolean updateMastery(int status){
        int masteryID = 1;
        boolean value = false;
        String condition = MASTERY_ID+" = ?";
        String querySelect = "SELECT * FROM "+MASTERY_TABLE+" WHERE "+condition;
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_STATUS, status);
        try {
            Cursor cursor = db.rawQuery(querySelect, new String[]{String.valueOf(masteryID)});
            if (cursor.getCount() > 0) {
                long result = db.update(MASTERY_TABLE, contentValues, condition, new String[]{String.valueOf(masteryID)});
                if (result == -1) {
                    Log.d("Update Mastery position", "Update has failed");
                    value = false;
                } else {
                    Log.d("Update Mastery position", "Update has Successful");
                    value = true;
                }
            } else {
                Log.d("Update Mastery position", "Invalid Quiz ID");
                value = false;
            }
        }catch (Exception e){
            Log.d("Update Mastery position", "Error : "+e.getMessage());
        }
        finally {
            db.close();
            return value;
        }
    }

    //function to Insert users answer to the database
    public void insertUserAnswer(double userAnswer, int questionId, String questionType, boolean isCorrect){
        SQLiteDatabase db = this.getWritableDatabase();
        try{
            ContentValues contentValues = new ContentValues();
            contentValues.put(QUESTION_ID, questionId);
            contentValues.put(COLUMN_USER_ANSWER, userAnswer);
            contentValues.put(COLUMN_TYPE, questionType);
            contentValues.put(COLUMN_isCORRECT, isCorrect);
            db.insert(ANSWERS_TABLE,null,contentValues);
            Log.d("Answers Table", "Initial status have successfully inserted");
        }catch (Exception e){
            Log.d("Answers Table", "There was some error while inserting status - "+e.getMessage());
        }
    }

}

/**
 * Here are some helpfull links for Android SQLite
 *
 * -> https://developer.android.com/training/data-storage/sqlite#java
 * -> https://www.youtube.com/watch?v=312RhjfetP8&t=1947s
 */

