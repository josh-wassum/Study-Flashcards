package com.example.flashcards.library;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.flashcards.models.FlashCardModel;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    public static final String DATABASE_NAME = "StudyFlashCards.db";
    public static final int DATABASE_VERSION = 1;
    public static final String FLASHCARDS_TABLE = "FLASHCARDS";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_FC_QUESTION = "FC_QUESTION";
    public static final String COLUMN_FC_ANSWER = "FC_ANSWER";
    public static final String COLUMN_FC_ATTEMPTED = "FC_ATTEMPTED";
    //Flash Card Questions and Answers.
    private static final FlashCardModel[] flashCardModels = {new FlashCardModel(0, "10 + 5", "15", false),
                                                            new FlashCardModel(0, "20 + 5", "25", false)};

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //This function will invoke either on the first run of your app or when someone clears all your app's data
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creating table for FLASHCARDS at the beginning of the application
        String createTableStatement = "CREATE TABLE " + FLASHCARDS_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_FC_QUESTION + " TEXT," +
                " " + COLUMN_FC_ANSWER + " TEXT, " + COLUMN_FC_ATTEMPTED + " BOOL)";
        db.execSQL(createTableStatement);
        this.insertFlashCards(db);

    }

    //This is called if the database version number changes. It prevents user apps from breaking when you change the database schema
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //This function is to insert initial values of flashcards
    public void insertFlashCards(SQLiteDatabase db){
        //db = this.getWritableDatabase();
        try {

            for (FlashCardModel flashCardModel : flashCardModels) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(COLUMN_FC_QUESTION, flashCardModel.getFlashCardQuestion());
                contentValues.put(COLUMN_FC_ANSWER, flashCardModel.getFlashCardAnswer());
                contentValues.put(COLUMN_FC_ATTEMPTED, flashCardModel.isFlashCardAttempted());
                db.insert(FLASHCARDS_TABLE, null, contentValues);
            }
            Log.d("FlashCard Table", "Initial FlashCards have successfully inserted");
        }catch(Exception e){
            Log.d("FlashCard Table", "There was some error while inserting FlashCards"+e.getMessage());
        }
    }

    //Get all the flash cards.
    public List<FlashCardModel> getAllFlashCards(){
        List<FlashCardModel> returnList = new ArrayList<>();
        String query = "SELECT * FROM " + FLASHCARDS_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                int fc_Id = cursor.getInt(0);
                String fc_Question = cursor.getString(1);
                String fc_Answer = cursor.getString(2);
                boolean fc_Attempted = cursor.getInt(3) == 1? true: false;
                FlashCardModel flashCardModel = new FlashCardModel(fc_Id,fc_Question,fc_Answer,fc_Attempted);
                returnList.add(flashCardModel);
            }while(cursor.moveToNext());
            Log.d("Display Flash Card", "FlashCard Table is fetched");
        }else{
            //If flashCard table is empty
            Log.d("Display Flash Card", "FlashCard Table is empty");
        }
        return returnList;
    }

    //When user has attempted a flashcard, call this function to update the FC_ATTEMPTED.
    public Boolean updateFlashCardAttempted(int flashCardID,boolean isFlashCardAttempted){

        String condition = COLUMN_ID+" = ?";
        String querySelect = "SELECT * FROM "+FLASHCARDS_TABLE+" WHERE "+condition;
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_FC_ATTEMPTED, isFlashCardAttempted);
        Cursor cursor = db.rawQuery(querySelect, new String[]{String.valueOf(flashCardID)});
        if(cursor.getCount()>0){
            long result = db.update(FLASHCARDS_TABLE, contentValues,condition, new String[]{String.valueOf(flashCardID)});
            if(result == -1){
                Log.d("Update FC_Attempted", "Update has failed");
                return false;
            }else{
                Log.d("Update FC_Attempted", "Update has Successful");
                return true;
            }
        }else {
            Log.d("Update FC_Attempted", "Invalid FC_ID");
            return false;
        }
    }

}

    /**
     * Here are some helpfull links for Android SQLite
     *
     * -> https://developer.android.com/training/data-storage/sqlite#java
     */

