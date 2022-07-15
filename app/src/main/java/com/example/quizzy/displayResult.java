package com.example.quizzy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class displayResult extends AppCompatActivity {
    Button playAgainButton;
    TextView finalScoreView, currentDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_result);
        finalScoreView = findViewById(R.id.displayFinalScore);
        currentDate = findViewById(R.id.disDate);
        playAgainButton = findViewById(R.id.playAgain);
        Intent intent = getIntent();
        String finalScore = intent.getExtras().getString("finalscore");
        String dateTime = intent.getExtras().getString("date");
        finalScoreView.setText(finalScore);
        currentDate.setText(dateTime);

        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent playAgainIntent = new Intent(displayResult.this,startgame.class);
                startActivity(playAgainIntent);
            }
        });
    }
}