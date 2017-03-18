package com.example.hp_pc.events.data;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.hp_pc.events.R;

public class EventAdapter extends CursorAdapter{

    public EventAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.event_list_item, parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView eventName = (TextView) view.findViewById(R.id.event_name);
        eventName.setText(cursor.getString(cursor.getColumnIndex(EventContract.EventEntry.COLUMN_EVENT_NAME)));
        view.setTag(eventName.getText().toString().trim());
        TextView breed = (TextView) view.findViewById(R.id.event_desc);
        breed.setText(cursor.getString(cursor.getColumnIndex(EventContract.EventEntry.COLUMN_EVENT_DESC)));
    }
}
