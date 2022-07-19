package com.example.quizzy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizzy.database.AppDatabase;
import com.example.quizzy.entity.Highscore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
//        String dateTime = intent.getExtras().getString("date");
        Long currentDate = intent.getExtras().getLong("date");
        finalScoreView.setText(finalScore);

        int finalScoreInInt = Integer.parseInt(finalScore);
//        currentDate.setText(dateTime);
//        AsyncTask.execute(() -> {
//            AppDatabase db = Room.databaseBuilder(getApplicationContext(),
//                    AppDatabase.class, "user-database").build();
//            db.scoreDAO().insertAll(new Highscore(currentDate, finalScoreInInt));
//
//        });

        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent playAgainIntent = new Intent(displayResult.this,startgame.class);
                startActivity(playAgainIntent);
            }
        });
    }
}