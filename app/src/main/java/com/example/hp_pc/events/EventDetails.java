package com.example.hp_pc.events;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hp_pc.events.data.DbHelper;
import com.example.hp_pc.events.data.EventContract;

public class EventDetails extends Activity {

    private DbHelper mDbHelper;
    EditText eName, eDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_details);
        mDbHelper = new DbHelper(this);
        eDesc = (EditText) findViewById(R.id.editText2);
        eName = (EditText) findViewById(R.id.name_text);
    }

    public void cancel(View v) {
        finish();
    }

    public void save(View v) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        String eventName, eventDesc;
        eventDesc = eDesc.getText().toString().trim();
        eventName = eName.getText().toString().trim();
        if (!TextUtils.isEmpty(eventDesc)&& !TextUtils.isEmpty(eventName))
        {values.put(EventContract.EventEntry.COLUMN_EVENT_NAME, eventName);
        values.put(EventContract.EventEntry.COLUMN_EVENT_DESC, eventDesc);
        getContentResolver().insert(EventContract.EventEntry.CONTENT_URI, values);
        Intent i = new Intent(EventDetails.this, Events.class);
        startActivity(i);}
        else Toast.makeText(this, "Name/Desc can't be empty!!", Toast.LENGTH_SHORT).show();
    }

}
