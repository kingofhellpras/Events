package com.example.hp_pc.events;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.example.hp_pc.events.data.AttendeesAdapter;
import com.example.hp_pc.events.data.DbHelper;
import com.example.hp_pc.events.data.EventContract;

public class Attendees extends AppCompatActivity {
    String tag="something";
    private DbHelper mDbHelper;
    String[] selectionArgs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendees);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent i = getIntent();

        tag = i.getStringExtra("event name");
        selectionArgs = new String[]{tag};

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent attDetails = new Intent(Attendees.this, AttendeesDetails.class);
                attDetails.putExtra("event name", tag);
                attDetails.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(attDetails);
            }
        });
        ListView petListView = (ListView) findViewById(R.id.list_attendees);
        View emptyView = findViewById(R.id.a_empty_view);
        petListView.setEmptyView(emptyView);
        mDbHelper = new DbHelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
       dispAttendees();
    }

    private void dispAttendees() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String[] projection = {
                EventContract.AttendeesEntry._ID,
                EventContract.AttendeesEntry.COLUMN_ATTENDEES_NAME,
                EventContract.AttendeesEntry.COLUMN_ATTENDEES_EVENT,
                EventContract.AttendeesEntry.COLUMN_ATTENDEES_GENDER,
                EventContract.AttendeesEntry.COLUMN_ATTENDEES_STATUS
        };

        String selection = EventContract.AttendeesEntry.COLUMN_ATTENDEES_EVENT + "=?";


        Cursor c = getContentResolver().query(EventContract.AttendeesEntry.CONTENT_URI, projection, selection, selectionArgs, null);
        ListView lv = (ListView) findViewById(R.id.list_attendees);
        AttendeesAdapter mAdapter = new AttendeesAdapter(this, c);
        lv.setAdapter(mAdapter);
    }
}
