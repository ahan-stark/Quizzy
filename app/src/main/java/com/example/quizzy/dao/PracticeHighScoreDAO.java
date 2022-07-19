package com.example.quizzy.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.quizzy.entity.PracticeHighScore;


@Dao
public interface PracticeHighScoreDAO {
    @Insert
    void insert(PracticeHighScore... practicescore);
}
