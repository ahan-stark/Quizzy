package com.example.quizzy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {
    TextView firstNum, secondNum, disResult, userRank;
    EditText enteredAnswer;
    Button buttonClick;
    int userFinalScore;
    AtomicInteger attemptTime = new AtomicInteger();
    Random random = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstNum =  findViewById(R.id.firstNumber);
        secondNum = findViewById(R.id.secondNumber);
        generateRandomNumbers();
        disResult = findViewById(R.id.finalResult);
        userRank = findViewById(R.id.userScore);
        buttonClick = findViewById(R.id.checkButton);
        firstNum =  findViewById(R.id.firstNumber);
        secondNum = findViewById(R.id.secondNumber);
        enteredAnswer =(EditText)findViewById(R.id.enterAnswer);
        buttonClick.setOnClickListener(view -> {
            String userAnswer = enteredAnswer.getText().toString();
            attemptTime.set(attemptTime.get() + 1);
            checkResults(userAnswer);

        });
    }
    private void checkResults(String userAnswer) {
       String stringRandomNumber1 = firstNum.getText().toString();
       String stringRandomNumber2 = secondNum.getText().toString();
        int actualValue = Integer.parseInt(stringRandomNumber1) +  Integer.parseInt(stringRandomNumber2);
        int userValueInt = Integer.parseInt(userAnswer);
        try {
            if(actualValue == userValueInt)
            {
                userFinalScore = userFinalScore + 1;
                disResult.setText("you are right!");
                userRank.setText(""+ userFinalScore);
                if(Integer.parseInt(userRank.getText().toString()) == 3){
                    Toast.makeText(this, "You have scored 10 points out of "+attemptTime.get()+"attempts", Toast.LENGTH_SHORT).show();
                    userFinalScore = 0;
                    userRank.setText(""+userFinalScore);
                    attemptTime.set(0);
                    generateRandomNumbers();
                }
                else {
                    generateRandomNumbers();
                    enteredAnswer.getText().clear();
                }
            }
            else {
                disResult.setText("oops! try again");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    private void generateRandomNumbers(){
        int randomFirstNumber =  random.nextInt(10);
        int randomSecondNumber =  random.nextInt(10);
        String stringRandomNumber1 = String.valueOf(randomFirstNumber);
        String stringRandomNumber2 = String.valueOf(randomSecondNumber);
        firstNum.setText(stringRandomNumber1);
        secondNum.setText(stringRandomNumber2);
    }
}