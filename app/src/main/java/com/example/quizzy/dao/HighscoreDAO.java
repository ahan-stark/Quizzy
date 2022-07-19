package com.example.quizzy.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.quizzy.entity.Highscore;

import java.util.List;

@Dao
public interface HighscoreDAO {


    @Query("Select * from Highscore order by score desc limit 5")
    List<Highscore> getAll();
//    @Query("SELECT * FROM Highscore")
//    List<Highscore> getAll();

//    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
//    List<User> loadAllByIds(int[] userIds);
//
//    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +
//            "last_name LIKE :last LIMIT 1")
//    User findByName(String first, String last);

    @Insert
    void insertAll(Highscore... score);

    @Delete
    void delete(Highscore score);


}
