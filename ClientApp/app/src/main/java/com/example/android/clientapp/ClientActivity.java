package com.example.android.clientapp;

import android.app.Activity;
import android.os.Bundle;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.widget.TextView;

public class ClientActivity extends Activity {
    TextView m_textView;
    Cursor m_cursor = null;

    ContentResolver m_resolver = null;

    public static final Uri CONTENT_URI = Uri
            .parse("content://edu.oscail.db/author_titles_year");

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        ContentValues values = new ContentValues();

        m_textView = (TextView) findViewById(R.id.textView1);

        // INSERT
        insertAuthors();
        addToTextView(CONTENT_URI);

        values.clear();
        // UPDATE
        values.put("year", "2011");

        m_resolver.update(CONTENT_URI, values, "author='Jasper Fforde'", null);

        addToTextView(CONTENT_URI);

        m_resolver.delete(CONTENT_URI, null, null);

        addToTextView(CONTENT_URI);

    }

    // Insert several artist records
    private void insertAuthors() {
        ContentValues values = new ContentValues();
        m_resolver = getContentResolver();

        values.put("author", "Jane Austen");
        values.put("title", "Mansfield Park");
        values.put("year", 1814);
        m_resolver.insert(CONTENT_URI, values);

        values.clear();

        values.put("author", "Jasper Fforde");
        values.put("title", "The Eyre Affair");
        values.put("year", 2010);
        m_resolver.insert(CONTENT_URI, values);

        values.clear();

    }

    void addToTextView(Uri uri) {
        m_cursor = m_resolver.query(uri, null, "1", null, null);
        if (m_cursor != null && m_cursor.moveToFirst()) {
            m_textView.append("***********************\n");

            String author;
            do {
                String _id = m_cursor.getString(0);
                author = m_cursor.getString(1);
                String title = m_cursor.getString(2);
                int year = m_cursor.getInt(3);
                m_textView.append(_id + " " + author + " " + title + " " + year
                        + "\n");
            } while (m_cursor.moveToNext());
        }
        m_textView.append("***********************\n");
    }

}

