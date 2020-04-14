package com.example.ejournalist;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class EventActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    EventAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.recyclerViewEvent);
        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "production")
                .allowMainThreadQueries()
                .build();
        final List<Event> events = db.eventDao().getAllEvents();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EventAdapter(events);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new EventAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Event event) {
                Intent intent = new Intent(EventActivity.this, notes.class);
                intent.putExtra("EVENT_ID", event.getId());
                startActivity(intent);
            }
        });
    }

}
