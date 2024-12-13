package com.example.mad_final_game;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HighScoresDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "highscores.db";
    private static final int DATABASE_VERSION = 1;

    public HighScoresDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE scores (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, score INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS scores");
        onCreate(db);
    }
}
