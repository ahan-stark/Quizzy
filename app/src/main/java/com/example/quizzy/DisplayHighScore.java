package com.example.quizzy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizzy.database.AppDatabase;
import com.example.quizzy.entity.Highscore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DisplayHighScore extends AppCompatActivity {
    TextView textViewfordate, textViewforScore;
    RecyclerView recyclerView;
    List<String> data = new ArrayList<>();
    CustomAdapter c;

//    String [] arr = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5", "Item 6", "Item 7", "Item 8", "Item 9"};

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_high_score);
//        textViewfordate = findViewById(R.id.disHighScoreDate);
//        textViewforScore = findViewById(R.id.disHighScore);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        c = new CustomAdapter(data);
        recyclerView.setAdapter(c);
        List<String> list = new ArrayList<>();
        AsyncTask.execute(() -> {
            AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, "user-database").build();
            List<Highscore> contents =  db.scoreDAO().getAll();

            for (Highscore highscore:
                    contents) {
                //converting from millis to date
                DateFormat simple = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
                Date result = new Date(highscore.date);
                String actualDate = simple.format(result);
                //conversion ends here
                data.add(String.format("[%s , %o]", actualDate, highscore.score));
                Log.d("checking",highscore.date+""+highscore.score);
//                Toast.makeText(this,highscore.date+""+highscore.score , Toast.LENGTH_SHORT).show();
//                textViewfordate.setText((new Date(highscore.date).toString()));
//                textViewforScore.setText(""+highscore.score);
//                finalVal= finalVal + (new Date(highscore.date ))+""+highscore.score;

            }
            runOnUiThread(() -> {
                c.notifyDataSetChanged();
            });
//            c.getItemCount();
//            textView.setText(finalVal);
        });



    }
}