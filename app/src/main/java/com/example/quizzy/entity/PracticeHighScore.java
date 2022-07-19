package com.example.quizzy.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PracticeHighScore {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "practiceDate")
    public Long practiceDate;

    @ColumnInfo(name="practiceScore")
    public int practiceScore;

    public PracticeHighScore(@NonNull Long practiceDate, int practiceScore) {
        this.practiceDate = practiceDate;
        this.practiceScore = practiceScore;
    }
}
