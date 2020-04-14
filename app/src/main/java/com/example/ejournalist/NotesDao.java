package com.example.ejournalist;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NotesDao {

      @Query("SELECT * FROM notes WHERE event_id=:id")
      List<Notes> getNotes(Integer id);

      @Query("SELECT * FROM notes where id=:id")
      List<Notes> getNote(long id);

      @Insert
      void insert(Notes notes);

      @Query("DELETE FROM notes where id=:id")
      void deleteNote(long id);

      @Query("UPDATE notes SET text=:text, creation_date=:creation_date where id=:id")
      void editNote(String text, String creation_date, long id);
    }

