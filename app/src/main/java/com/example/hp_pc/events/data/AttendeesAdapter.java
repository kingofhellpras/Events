package com.example.hp_pc.events.data;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.hp_pc.events.R;

/**
 * Created by HP-PC on 3/18/2017.
 */

public class AttendeesAdapter extends CursorAdapter {
    public AttendeesAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.att_list_item, parent, false);

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView attName = (TextView) view.findViewById(R.id.att_name);
        attName.setText(cursor.getString(cursor.getColumnIndex(EventContract.AttendeesEntry.COLUMN_ATTENDEES_NAME)));
        view.setTag(attName.getText().toString().trim());
        TextView status = (TextView) view.findViewById(R.id.att_status);
        status.setText(cursor.getString(cursor.getColumnIndex(EventContract.AttendeesEntry.COLUMN_ATTENDEES_STATUS)));

    }
}
