package com.example.ejournalist;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

public class PreEvent extends AppCompatActivity {
    Button editEvent, goToNotes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        final int event_id = intent.getIntExtra("EVENT_ID", 0 );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_event);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        editEvent = findViewById(R.id.editEvent);
        goToNotes = findViewById(R.id.goToNotesBtn);
        editEvent.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PreEvent.this, EditEvent.class);
                        intent.putExtra("EVENT_ID", event_id);
                        startActivity(intent);
                    }
                }
        );
        goToNotes.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(PreEvent.this, NotesActivity.class);
                        intent.putExtra("EVENT_ID", event_id);
                        startActivity(intent);
                    }
                }
        );
    }

}
