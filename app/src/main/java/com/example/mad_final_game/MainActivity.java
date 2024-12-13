package com.example.mad_final_game;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the Play button
        Button playButton = findViewById(R.id.play_button);

        // Set up the click listener for the Play button
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the SequenceActivity
                Intent intent = new Intent(MainActivity.this, SequenceActivity.class);
                startActivity(intent);
            }
        });
    }
}
