package com.example.ejournalist;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

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
        if(events.isEmpty()){
            Toast toast = Toast.makeText(getApplicationContext(), "No Event's yet! Please go back and add one to continue",Toast.LENGTH_LONG);
            toast.show();
        }
        adapter = new EventAdapter(events);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new EventAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Event event) {
                Intent intent = new Intent(EventActivity.this, PreEvent.class);
                intent.putExtra("EVENT_ID", event.getId());
                startActivity(intent);
            }
        });

    }

}
