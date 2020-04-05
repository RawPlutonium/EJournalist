package com.example.ejournalist;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Timestamp;

@Entity
public class Notes {
    public Notes(String text) {
        this.text = text;
    }
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name="text")
    private String text;


    @ColumnInfo(name = "creation_date")
    private String creationDate;

    @ColumnInfo(name="event_id")
    private int event_id;

    public int getId() {
        return id;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}

