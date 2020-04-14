package com.example.ejournalist;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EventDao {
    @Query("SELECT * FROM event")
    List<Event> getAllEvents();

    @Query("SELECT * FROM Event WHERE id=:id")
    List<Event> getEvent(int id);

    @Insert
    void insertAll(Event... events);

    @Query("UPDATE Event SET name=:name WHERE id=:id")
    void updateEvent(String name, int id);

    @Query("DELETE FROM Event WHERE id=:id")
    void deleteEvent(int id);
}


