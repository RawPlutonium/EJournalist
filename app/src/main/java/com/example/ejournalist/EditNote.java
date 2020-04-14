package com.example.ejournalist;

import android.annotation.SuppressLint;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EditNote extends AppCompatActivity {
    Button saveNoteButton, deleteNoteButton;
    EditText editNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        final long note_id = intent.getLongExtra("NOTE_ID",0);
        final int event_id = intent.getIntExtra("EVENT_ID", 0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        saveNoteButton = findViewById(R.id.saveNoteButton);
        deleteNoteButton = findViewById(R.id.deleteNoteButton);
        editNote  = findViewById(R.id.editNote);
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm");
        final String timeStamp = df.format(c);
        final AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class,"production")
                .allowMainThreadQueries()
                .build();
        List<Notes> currentNote = db.notesDao().getNote(note_id);
        editNote.setText(currentNote.get(0).getText());
        saveNoteButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db.notesDao().editNote(editNote.getText().toString(), timeStamp, note_id);
                        Toast toast = Toast.makeText(getApplicationContext(), "Edit Saved",Toast.LENGTH_SHORT);
                        toast.show();
                        Intent intent = new Intent(EditNote.this, notes.class);
                        intent.putExtra("EVENT_ID", event_id);
                        startActivity(intent);
                    }
                }
        );
        deleteNoteButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db.notesDao().deleteNote(note_id);
                        Intent intent = new Intent(EditNote.this, notes.class);
                        intent.putExtra("EVENT_ID",event_id);
                        startActivity(intent);
                    }
                }
        );


    }

}
