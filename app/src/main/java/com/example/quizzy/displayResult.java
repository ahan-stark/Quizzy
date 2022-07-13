package com.example.quizzy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class displayResult extends AppCompatActivity {
    Button playAgainButton;
    TextView finalScoreView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_result);
        finalScoreView = findViewById(R.id.displayFinalScore);
        playAgainButton = findViewById(R.id.playAgain);
        Intent intent = getIntent();
        String finalScore = intent.getExtras().getString("finalscore");
        finalScoreView.setText(finalScore);

        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent playAgainIntent = new Intent(displayResult.this,startgame.class);
                startActivity(playAgainIntent);
            }
        });
    }
}