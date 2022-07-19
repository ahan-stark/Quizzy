package com.example.quizzy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.quizzy.database.AppDatabase;
import com.example.quizzy.entity.Highscore;

import java.util.Date;
import java.util.List;

public class DisplayHighScore extends AppCompatActivity {
    TextView textViewfordate, textViewforScore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_high_score);
        textViewfordate = findViewById(R.id.disHighScoreDate);
        textViewforScore = findViewById(R.id.disHighScore);
        AsyncTask.execute(() -> {
            AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, "user-database").build();
            List<Highscore> contents =  db.scoreDAO().getAll();
            String finalVal = "";
            for (Highscore highscore:
                    contents) {
                Log.d("checking",highscore.date+""+highscore.score);
                textViewfordate.setText((new Date(highscore.date).toString()));
                textViewforScore.setText(""+highscore.score);
//                finalVal= finalVal + (new Date(highscore.date ))+""+highscore.score;

            }
//            textView.setText(finalVal);
        });

    }
}