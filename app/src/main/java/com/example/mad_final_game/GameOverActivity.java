package com.example.mad_final_game;

import android.content.ContentValues;
import android.content.Intent; // Add this line
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        int finalScore = getIntent().getIntExtra("FINAL_SCORE", 0);

        TextView finalScoreText = findViewById(R.id.final_score);
        finalScoreText.setText("Your Score: " + finalScore);

        HighScoresDatabase dbHelper = new HighScoresDatabase(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", "Player");
        values.put("score", finalScore);
        db.insert("scores", null, values);

        ListView highScoresList = findViewById(R.id.high_scores_list);
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

        Button restartButton = findViewById(R.id.restart_button);
        restartButton.setOnClickListener(v -> {
            startActivity(new Intent(GameOverActivity.this, MainActivity.class));
            finish();
        });
    }
}
