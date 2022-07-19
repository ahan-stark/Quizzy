package com.example.quizzy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.quizzy.database.AppDatabase;
import com.example.quizzy.entity.Highscore;
import com.example.quizzy.entity.PracticeHighScore;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {
    TextView firstNum, secondNum, disResult, userRank, lastScore;
    EditText enteredAnswer;
    Button buttonClick, doneForNow;
    int userFinalScore = 0;
    AtomicInteger attemptTime = new AtomicInteger();
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        SharedPreferences sharedPreferences = getSharedPreferences("MySharePref", Context.MODE_PRIVATE);
        lastScore = findViewById(R.id.prevScore);
        firstNum = findViewById(R.id.firstNumber);
        secondNum = findViewById(R.id.secondNumber);
        generateRandomNumbers();
        disResult = findViewById(R.id.finalResult);
        userRank = findViewById(R.id.userScore);
        buttonClick = findViewById(R.id.checkButton);
        firstNum = findViewById(R.id.firstNumber);
        secondNum = findViewById(R.id.secondNumber);
        doneForNow = findViewById(R.id.doneForNow);
        enteredAnswer = (EditText) findViewById(R.id.enterAnswer);
        //fetching previous score from sharedPreference file
//        int sc = sharedPreferences.getInt("prevScore", 0);
//        lastScore.setText("" + sc);
        buttonClick.setOnClickListener(view -> {
            String userAnswer = enteredAnswer.getText().toString();
            attemptTime.set(attemptTime.get() + 1);
            checkResults(userAnswer);

        });
        doneForNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Long currentDate = System.currentTimeMillis() ;
                AsyncTask.execute(() -> {
                    AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                            AppDatabase.class, "user-database").build();
                    db.practiceDAO().insert(new PracticeHighScore(currentDate, userFinalScore));

                });

                Intent doneForNowintent = new Intent(MainActivity.this,displayResult.class);
                String userFinalScoreInString = String.valueOf(userFinalScore);
                doneForNowintent.putExtra("finalscore",userFinalScoreInString);
                doneForNowintent.putExtra("date",System.currentTimeMillis());
                startActivity(doneForNowintent);
            }
        });
    }

    private void checkResults(String userAnswer) {
        String stringRandomNumber1 = firstNum.getText().toString();
        String stringRandomNumber2 = secondNum.getText().toString();
        int actualValue = Integer.parseInt(stringRandomNumber1) + Integer.parseInt(stringRandomNumber2);
        int userValueInt = Integer.parseInt(userAnswer);
        try {
            if (actualValue == userValueInt) {
                userFinalScore = userFinalScore + 1;
                disResult.setText("you are right!");
                userRank.setText("" + userFinalScore);
//                if (Integer.parseInt(userRank.getText().toString()) == 3) {
//                    SharedPreferences sharedPreferences = getSharedPreferences("MySharePref", Context.MODE_PRIVATE);
//                    int finalPreviousAttempt = attemptTime.get();
//                    Toast.makeText(this, "You have scored 10 points out of " + attemptTime.get() + "attempts", Toast.LENGTH_SHORT).show();
//                    userFinalScore = 0;
                    //inserting previous score to sharedPreferences
//                    sharedPreferences.edit().putInt("prevScore", finalPreviousAttempt).commit();
//                    int sc = sharedPreferences.getInt("prevScore", 0);
//                    lastScore.setText("" + sc);
//                    userRank.setText("" + userFinalScore);

//                    attemptTime.set(0);
                    generateRandomNumbers();
                enteredAnswer.getText().clear();
//                } else {
//                    generateRandomNumbers();

//                }
            } else {
                disResult.setText("oops! try again");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void generateRandomNumbers() {
        int randomFirstNumber = random.nextInt(10);
        int randomSecondNumber = random.nextInt(10);
        String stringRandomNumber1 = String.valueOf(randomFirstNumber);
        String stringRandomNumber2 = String.valueOf(randomSecondNumber);
        firstNum.setText(stringRandomNumber1);
        secondNum.setText(stringRandomNumber2);
    }

}