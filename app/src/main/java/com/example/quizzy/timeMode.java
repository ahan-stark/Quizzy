package com.example.quizzy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class timeMode extends AppCompatActivity {
    TextView firstNum, secondNum, disResult, userRank, lastScore, timerView;
    EditText enteredAnswer;
    Button buttonClick;
    int userFinalScore;
    AtomicInteger attemptTime = new AtomicInteger();
    Random random = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_mode);
        SharedPreferences sharedPreferences = getSharedPreferences("MySharePref", Context.MODE_PRIVATE);
        lastScore = findViewById(R.id.prevScore);
        firstNum = findViewById(R.id.firstNumber);
        secondNum = findViewById(R.id.secondNumber);
        timerView = findViewById(R.id.timerView);
        attemptTime.set(0);
        generateRandomNumbers();
        disResult = findViewById(R.id.finalResult);
        userRank = findViewById(R.id.userScore);
        buttonClick = findViewById(R.id.checkButton);
        firstNum = findViewById(R.id.firstNumber);
        secondNum = findViewById(R.id.secondNumber);
        enteredAnswer = (EditText) findViewById(R.id.enterAnswer);
        //starting the timer
        new CountDownTimer(20000, 1000) {
            public void onTick(long millisUntilFinished) {
                // Used for formatting digit to be in 2 digits only
                NumberFormat f = new DecimalFormat("00");
                long hour = (millisUntilFinished / 3600000) % 24;
                long min = (millisUntilFinished / 60000) % 60;
                long sec = (millisUntilFinished / 1000) % 60;
                timerView.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
            }
            // When the task is over it will print 00:00:00 there
            public void onFinish() {
                timerView.setText("00:00:00");
                Intent gotoWinnerPage = new Intent(timeMode.this,displayResult.class);
//                SharedPreferences sharedPreferences = getSharedPreferences("MySharePref", Context.MODE_PRIVATE);
                int finalPreviousAttempt = attemptTime.get();
                //userFinalScore = 0;
                //inserting previous score to sharedPreferences
//                sharedPreferences.edit().putInt("prevScore", finalPreviousAttempt).commit();
//                int sc = sharedPreferences.getInt("prevScore", 0);
//                lastScore.setText("" + sc);
                userRank.setText("" + userFinalScore);
                String userFinalScoreInString = Integer.toString(userFinalScore);
//
//                    attemptTime.set(0);
//                    generateRandomNumbers();
                gotoWinnerPage.putExtra("finalscore",userFinalScoreInString);
                startActivity(gotoWinnerPage);
            }
        }.start();
        //fetching previous score from sharedPreference file
//        int sc = sharedPreferences.getInt("prevScore", 0);
//        lastScore.setText("" + sc);
        buttonClick.setOnClickListener(view -> {
            String userAnswer = enteredAnswer.getText().toString();
            attemptTime.set(attemptTime.get() + 1);
            checkResults(userAnswer);

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
                enteredAnswer.getText().clear();
                generateRandomNumbers();
            }
//                if (Integer.parseInt(userRank.getText().toString()) == 3) {
//                    SharedPreferences sharedPreferences = getSharedPreferences("MySharePref", Context.MODE_PRIVATE);
//                    int finalPreviousAttempt = attemptTime.get();
//                    Toast.makeText(this, "You have scored 10 points out of " + attemptTime.get() + "attempts", Toast.LENGTH_SHORT).show();
//                    userFinalScore = 0;
//                    //inserting previous score to sharedPreferences
//                    sharedPreferences.edit().putInt("prevScore", finalPreviousAttempt).commit();
//                    int sc = sharedPreferences.getInt("prevScore", 0);
//                    lastScore.setText("" + sc);
//                    userRank.setText("" + userFinalScore);
//
//                    attemptTime.set(0);
//                    generateRandomNumbers();
//                } else {
//                    generateRandomNumbers();
//                    enteredAnswer.getText().clear();
//                }
//            }
            else {
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