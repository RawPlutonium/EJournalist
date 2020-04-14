package com.example.ejournalist;


import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity
public class Notes {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String text;
    private String creation_date;
    private int event_id;

    Notes(String text, String creation_date, int event_id) {
        this.text = text;
        this.creation_date = creation_date;
        this.event_id = event_id;
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    String getCreationDate() {
        return creation_date;
    }

    public int getEventId() {
        return event_id;
    }
}

