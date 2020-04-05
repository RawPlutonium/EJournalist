package com.example.ejournalist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Add_Event extends AppCompatActivity {
    EditText event_name;
    Button addEvent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        event_name = findViewById(R.id.event_name);
        addEvent = findViewById(R.id.saveEvent);
        final AppDatabase db  = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "production")
                .allowMainThreadQueries()
                .build();
        addEvent.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db.eventDao().insertAll(new Event(event_name.getText().toString()));
                        startActivity(new Intent(Add_Event.this, EventActivity.class));
                    }
                }
        );
    }
}
