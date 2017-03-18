package com.example.hp_pc.events;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp_pc.events.data.DbHelper;
import com.example.hp_pc.events.data.EventContract;

public class AttendeesDetails extends Activity {
    String eventName, genderSelection, statusSelection;
    EditText name;
    Spinner gender, status;
    TextView e;
    DbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendees_details);
        Intent tagIntent = getIntent();
        eventName = tagIntent.getStringExtra("event name");
        mDbHelper = new DbHelper(this);
        name = (EditText) findViewById(R.id.editText);
        gender = (Spinner) findViewById(R.id.spinner2);
        status = (Spinner) findViewById(R.id.spinner);
        e = (TextView) findViewById(R.id.event_n);
        e.setText(eventName);
        setupSpinner();
    }

    private void setupSpinner() {
        //setting Gender spinner elements here
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_gender_options, android.R.layout.simple_spinner_item);
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        gender.setAdapter(genderSpinnerAdapter);
        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                genderSelection = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                genderSelection = "Unknown";

            }
        });

        //setting status spinner elements here
        ArrayAdapter statusSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_status_options, android.R.layout.simple_spinner_item);
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        status.setAdapter(statusSpinnerAdapter);
        status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                statusSelection = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                statusSelection = "Absent";
            }
        });
    }

    public void cancel_save(View v) {
        finish();
    }

    public void save_to_event(View v) {
        if (!TextUtils.isEmpty(name.getText().toString()) && !TextUtils.isEmpty(e.getText().toString().trim())) {
            SQLiteDatabase db = mDbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(EventContract.AttendeesEntry.COLUMN_ATTENDEES_NAME, name.getText().toString().trim());
            values.put(EventContract.AttendeesEntry.COLUMN_ATTENDEES_EVENT, e.getText().toString().trim());
            values.put(EventContract.AttendeesEntry.COLUMN_ATTENDEES_GENDER, genderSelection);
            values.put(EventContract.AttendeesEntry.COLUMN_ATTENDEES_STATUS, statusSelection);
            getContentResolver().insert(EventContract.AttendeesEntry.CONTENT_URI, values);
            finish();
        }
        else Toast.makeText(this, "Attendees name empty!!", Toast.LENGTH_SHORT).show();

    }

}
