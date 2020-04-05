package com.example.ejournalist;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

public class Event_Interface extends AppCompatActivity {
    Button newEventBtn;
    Button myEventsBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_interface);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        newEventBtn = findViewById(R.id.newEventBtn);
        myEventsBtn = findViewById(R.id.goToEventsBtn);
        newEventBtn.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Event_Interface.this, Add_Event.class));
                    }
                }
        );
        myEventsBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Event_Interface.this, EventActivity.class));
                    }
                }
        );
    }

}
