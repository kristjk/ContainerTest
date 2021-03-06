package com.example.notandi.containertest;

import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

public class ContactsActivity extends ListActivity {

    Cursor mCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCursor = getContentResolver().query(
                ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);

        startManagingCursor(mCursor);

        String _id = ContactsContract.Contacts._ID;
        String displayName = ContactsContract.Contacts.DISPLAY_NAME;

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                android.R.layout.two_line_list_item, mCursor,
                new String[]{_id, displayName},
                new int[]{ android.R.id.text1, android.R.id.text2});

        setListAdapter(adapter);
    }
}
