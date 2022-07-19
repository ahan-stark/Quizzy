package com.example.quizzy.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.quizzy.dao.HighscoreDAO;
import com.example.quizzy.dao.PracticeHighScoreDAO;
import com.example.quizzy.entity.Highscore;
import com.example.quizzy.entity.PracticeHighScore;


@Database(entities = {Highscore.class,PracticeHighScore.class}, version = 1)
    public abstract class AppDatabase extends RoomDatabase {
        public abstract HighscoreDAO scoreDAO();
        public abstract PracticeHighScoreDAO practiceDAO();
}
