package com.example.hp_pc.events.data;

import android.net.Uri;
import android.provider.BaseColumns;

public final class EventContract {
    //URI specific
    public static final String CONTENT_AUTHORITY = "com.example.hp_pc.events";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_EVENTS = "events";
    public static final String PATH_ATTENDEES = "attendees";
    public static final String PATH_USERS = "users";

    private EventContract() {
    }

    public static class EventEntry implements BaseColumns {
        //content uri for provider
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_EVENTS);
        //constants for table
        public static final String TABLE_NAME = "events";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_EVENT_NAME = "ename";
        public static final String COLUMN_EVENT_DESC = "desc";


    }

    public static class AttendeesEntry implements BaseColumns {
        //content uri for provider
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_ATTENDEES);
        public static final String TABLE_NAME = "attendees";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_ATTENDEES_NAME = "aname";
        public static final String COLUMN_ATTENDEES_GENDER = "gender";
        public static final String COLUMN_ATTENDEES_STATUS = "status";
        public static final String COLUMN_ATTENDEES_EVENT = "event";

    }

    public static class UsersEntry implements BaseColumns {
        //content uri for provider
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_USERS);
        public static final String TABLE_NAME="users";
        public static final String USER_TABLE = "users";
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PASS = "password";
    }

}
