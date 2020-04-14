package com.example.ejournalist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class notes extends AppCompatActivity {
    RecyclerView recyclerView;
    notesAdapter adapter;
    EditText note;
    Button saveNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        final int event_id = intent.getIntExtra("EVENT_ID", 0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        note = findViewById(R.id.note);
        saveNote = findViewById(R.id.saveNoteButton);
        recyclerView = findViewById(R.id.recyclerViewNotes);
        final AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class,"production")
                .allowMainThreadQueries()
                .build();
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm");
        final String timeStamp = df.format(c);
        saveNote.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast toast = Toast.makeText(getApplicationContext(),"I have "+note.getText().toString(),Toast.LENGTH_SHORT);
                        toast.show();
                        db.notesDao().insert(new Notes(note.getText().toString(), timeStamp, event_id));
                        finish();
                        startActivity(getIntent());
                    }
                }
        );
        final List<Notes> notes = db.notesDao().getNotes(event_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new notesAdapter(notes);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(
                new notesAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Notes notes) {
                        Intent intent = new Intent(notes.this, EditNote.class);
                        intent.putExtra("NOTE_ID", notes.getId());
                        intent.putExtra("EVENT_ID", event_id);
                        startActivity(intent);
                    }
                }
        );

    }

}
