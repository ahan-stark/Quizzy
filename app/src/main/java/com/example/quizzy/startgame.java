package com.example.quizzy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class startgame extends AppCompatActivity {
    Button practiceGame, timeMode, highScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startgame);
        practiceGame = findViewById(R.id.practiceGame);
        timeMode = findViewById(R.id.timeMode);
        highScore = findViewById(R.id.highScore);

        practiceGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToPracticeGame = new Intent(startgame.this, MainActivity.class);
                startActivity(goToPracticeGame);
            }
        });
        timeMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToTimeMode = new Intent(startgame.this, timeMode.class);
                startActivity(goToTimeMode);
            }
        });
        highScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent highScoreIntent = new Intent(startgame.this,DisplayHighScore.class);
                startActivity(highScoreIntent);
            }
        });
    }
}