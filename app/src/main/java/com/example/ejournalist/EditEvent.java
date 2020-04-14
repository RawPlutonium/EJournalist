package com.example.ejournalist;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class EditEvent extends AppCompatActivity {
    Button deleteEvent, updateEvent;
    EditText editEventName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        final int event_id = intent.getIntExtra("EVENT_ID",0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_event);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        deleteEvent = findViewById(R.id.deleteEventButton);
        updateEvent = findViewById(R.id.saveEditedEventButton);
        editEventName = findViewById(R.id.edit_event_name);
        final AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "production")
                .allowMainThreadQueries()
                .build();
        List<Event> currentEvent = db.eventDao().getEvent(event_id);
        editEventName.setText(currentEvent.get(0).getName());
        deleteEvent.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //here insert the event id from get intent
                        db.eventDao().deleteEvent(event_id);
                        Toast toast = Toast.makeText(getApplicationContext(), "Your Event has been deleted", Toast.LENGTH_LONG);
                        toast.show();
                        Intent intent = new Intent(EditEvent.this, EventActivity.class);
                        startActivity(intent);
                    }
                }
        );
        updateEvent.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db.eventDao().updateEvent(editEventName.getText().toString(),event_id);
                        Toast toast  = Toast.makeText(getApplicationContext(), "Your event has been updated", Toast.LENGTH_LONG);
                        toast.show();
                        startActivity(new Intent(EditEvent.this, EventActivity.class));
                    }
                }
        );
    }

}
