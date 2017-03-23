package com.example.hp_pc.events;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hp_pc.events.data.DbHelper;
import com.example.hp_pc.events.data.EventAdapter;
import com.example.hp_pc.events.data.EventContract;
import com.example.hp_pc.events.login.LoginActivity;
import com.example.hp_pc.events.login.Session;
import com.example.hp_pc.events.login.UserProfile;

public class Events extends AppCompatActivity {
    private DbHelper mDbHelper;
    private Session session;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent i = getIntent();
        user = i.getStringExtra("email");
        session = new Session(this);
        if (!session.loggedin()) {
            logout();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Events.this, EventDetails.class);
                startActivity(i);
            }
        });
        ListView petListView = (ListView) findViewById(R.id.list);
        View emptyView = findViewById(R.id.empty_view);
        petListView.setEmptyView(emptyView);
        mDbHelper = new DbHelper(this);
    }

    private void logout() {
        session.setLoggedin(false);
        finish();
        Intent n=new Intent(Events.this, LoginActivity.class);
        n.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(n);
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    private void displayDatabaseInfo() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        String[] projection = {
                EventContract.EventEntry._ID,
                EventContract.EventEntry.COLUMN_EVENT_NAME,
                EventContract.EventEntry.COLUMN_EVENT_DESC,
        };

        Cursor cursor = db.query(
                EventContract.EventEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);

        ListView lv = (ListView) findViewById(R.id.list);
        EventAdapter mAdapter = new EventAdapter(this, cursor);
        lv.setAdapter(mAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String tag = view.getTag().toString();
                Toast.makeText(Events.this, "Event name : " + tag, Toast.LENGTH_SHORT).show();
                Intent eventDetails = new Intent(Events.this, Attendees.class);
                eventDetails.putExtra("event name", tag);
                eventDetails.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(eventDetails);
            }
        });
    }

    private void insertEvent() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(EventContract.EventEntry.COLUMN_EVENT_NAME, "Cultural");
        values.put(EventContract.EventEntry.COLUMN_EVENT_DESC, "This is a cultural event");
        long newRowId = db.insert(EventContract.EventEntry.TABLE_NAME, null, values);
        if (newRowId > 0)
            Toast.makeText(this, "Fake data added!!", Toast.LENGTH_SHORT).show();
        else Toast.makeText(this, "Insertion unsuccessfull", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_events, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         int id = item.getItemId();

        if (id == R.id.dummy) {
            insertEvent();
            displayDatabaseInfo();
            return true;
        }
        if (id == R.id.action_logout) {
            logout();
        }
        if (id == R.id.view_profile) {
            Intent i = new Intent(Events.this, UserProfile.class);
            i.putExtra("user",user);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}
