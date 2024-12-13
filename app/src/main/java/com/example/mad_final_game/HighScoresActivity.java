package com.example.mad_final_game;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class HighScoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);

        ListView highScoresList = findViewById(R.id.high_scores_list);
        Button backToMenuButton = findViewById(R.id.back_to_menu_button);

        HighScoresDatabase dbHelper = new HighScoresDatabase(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT name, score FROM scores ORDER BY score DESC LIMIT 5", null);

        List<String> highScores = new ArrayList<>();
        while (cursor.moveToNext()) {
            String name = cursor.getString(0);
            int score = cursor.getInt(1);
            highScores.add(name + ": " + score);
        }
        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, highScores);
        highScoresList.setAdapter(adapter);

        backToMenuButton.setOnClickListener(v -> {
            Intent intent = new Intent(HighScoresActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
