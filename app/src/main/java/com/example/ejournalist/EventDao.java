package com.example.ejournalist;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EventDao {
    @Query("SELECT * FROM event")
    List<Event> getAllEvents();

    @Insert
    void insertAll(Event... events);
}


