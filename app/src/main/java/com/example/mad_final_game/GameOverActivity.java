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

        // Final score from the intent
        int finalScore = getIntent().getIntExtra("FINAL_SCORE", 0);

        //final score
        TextView finalScoreText = findViewById(R.id.final_score);
        finalScoreText.setText("Your Score: " + finalScore);

        //Restart button
        Button restartButton = findViewById(R.id.restart_button);
        restartButton.setOnClickListener(v -> {
            //Restart the game
            Intent intent = new Intent(GameOverActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
