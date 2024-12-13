package com.example.mad_final_game;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameOverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        int finalScore = getIntent().getIntExtra("FINAL_SCORE", 0);

        TextView finalScoreText = findViewById(R.id.final_score);
        finalScoreText.setText("Your Score: " + finalScore);

        Button restartButton = findViewById(R.id.restart_button);
        restartButton.setOnClickListener(v -> {
            Intent intent = new Intent(GameOverActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        Button viewHighScoresButton = findViewById(R.id.view_high_scores_button);
        viewHighScoresButton.setOnClickListener(v -> {
            Intent intent = new Intent(GameOverActivity.this, HighScoresActivity.class);
            startActivity(intent);
        });
    }
}
