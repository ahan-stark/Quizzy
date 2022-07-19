package com.example.quizzy.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Highscore {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "date")
    public Long date;

    @ColumnInfo(name="score")
    public int score;

    public Highscore(@NonNull Long date, int score) {
        this.date = date;
        this.score = score;
    }
}