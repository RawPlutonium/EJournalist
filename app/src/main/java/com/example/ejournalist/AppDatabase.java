package com.example.ejournalist;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Event.class, Notes.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract EventDao eventDao();
    public abstract NotesDao notesDao();
}
