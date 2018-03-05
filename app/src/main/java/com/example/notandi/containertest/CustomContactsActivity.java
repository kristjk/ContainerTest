package com.example.notandi.containertest;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.widget.SimpleCursorAdapter;

public class CustomContactsActivity extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    final static int CONTACTS_LOADER = 1;
    SimpleCursorAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLoaderManager().initLoader(CONTACTS_LOADER, null, this);

        String _id = CustomContentProvider._ID;
        String displayName = CustomContentProvider.DISPLAY_NAME;

        mAdapter = new SimpleCursorAdapter(this,
                android.R.layout.two_line_list_item, null,
                new String[]{_id, displayName},
                new int[]{ android.R.id.text1, android.R.id.text2});

        setListAdapter(mAdapter);

        Handler handler = new Handler();

        // Bæta við færslu eftir skilgreindan tíma og sjá hana poppa upp á skjánum
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                ContentValues values = new ContentValues();
                values.put(CustomContentProvider.DISPLAY_NAME, "Nýburi KKson " + System.currentTimeMillis());
                getContentResolver().insert(CustomContentProvider.CUSTOM_CONTACTS_URI, values);
            }
        };

        handler.postDelayed(runnable, 5000);
        handler.postDelayed(runnable, 10000);
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
        switch (id){
            case CONTACTS_LOADER:
                return new CursorLoader(
                        getApplicationContext(),
                        CustomContentProvider.CUSTOM_CONTACTS_URI,
                        null, null, null, null);
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        switch (loader.getId()){
            case CONTACTS_LOADER:
                mAdapter.swapCursor(cursor);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        switch (loader.getId()){
            case CONTACTS_LOADER:
                mAdapter.swapCursor(null);
        }
    }
}
