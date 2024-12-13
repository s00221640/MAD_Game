package com.example.mad_final_game;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SequenceActivity extends AppCompatActivity {

    private LinearLayout sequenceContainer;
    private List<Integer> colorSequence = new ArrayList<>();
    private List<Integer> userInput = new ArrayList<>();
    private final int[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};
    private final Handler handler = new Handler();
    private int currentStep = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sequence);

        sequenceContainer = findViewById(R.id.sequence_container);

        Button buttonRed = findViewById(R.id.button_red);
        Button buttonBlue = findViewById(R.id.button_blue);
        Button buttonGreen = findViewById(R.id.button_green);
        Button buttonYellow = findViewById(R.id.button_yellow);

        buttonRed.setOnClickListener(v -> handleUserInput(Color.RED));
        buttonBlue.setOnClickListener(v -> handleUserInput(Color.BLUE));
        buttonGreen.setOnClickListener(v -> handleUserInput(Color.GREEN));
        buttonYellow.setOnClickListener(v -> handleUserInput(Color.YELLOW));

        generateSequence(4);
        displaySequence();
    }

    private void generateSequence(int length) {
        Random random = new Random();
        colorSequence.clear();
        for (int i = 0; i < length; i++) {
            colorSequence.add(colors[random.nextInt(colors.length)]);
        }
    }

    private void displaySequence() {
        sequenceContainer.removeAllViews();
        userInput.clear();
        currentStep = 0;

        for (int i = 0; i < colorSequence.size(); i++) {
            final View colorView = new View(this);
            colorView.setLayoutParams(new LinearLayout.LayoutParams(100, 100));
            colorView.setBackgroundColor(colorSequence.get(i));
            colorView.setVisibility(View.INVISIBLE);

            sequenceContainer.addView(colorView);

            handler.postDelayed(() -> colorView.setVisibility(View.VISIBLE), i * 1000L);
            handler.postDelayed(() -> colorView.setVisibility(View.INVISIBLE), (i * 1000L) + 500);
        }
    }

    private void handleUserInput(int color) {
        userInput.add(color);

        if (userInput.get(currentStep).equals(colorSequence.get(currentStep))) {
            currentStep++;
            if (currentStep == colorSequence.size()) {
                Toast.makeText(this, "Correct! Next Round!", Toast.LENGTH_SHORT).show();
                generateSequence(colorSequence.size() + 2);
                handler.postDelayed(this::displaySequence, 1000);
            }
        } else {
            Intent intent = new Intent(SequenceActivity.this, GameOverActivity.class);
            intent.putExtra("FINAL_SCORE", colorSequence.size() - 2);
            startActivity(intent);
            finish();
        }
    }
}
