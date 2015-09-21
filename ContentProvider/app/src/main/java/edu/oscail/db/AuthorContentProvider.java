package edu.oscail.db;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class AuthorContentProvider extends ContentProvider {
    public AuthorContentProvider() {
    }

    public static final Uri CONTENT_URI = Uri
            .parse("content://edu.oscail.db/author_titles_year");
    private static final int ALLROWS = 1;
    private static final int SINGLE_ROW = 2;



    private static final String AUTHOR_DB = "author_db";



    SqlLiteHelper m_helper = null;
    SQLiteDatabase m_db = null;

    UriMatcher uriMatcher = null;
    {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("edu.oscail.db",
                "author_titles_year",ALLROWS );
        uriMatcher.addURI("edu.oscail.db",
                "author_titles_year/#", SINGLE_ROW);

    }



    @Override
    public boolean onCreate() {
        try {
            ContentValues values = null;
            m_helper =
                    new SqlLiteHelper(getContext(), SqlLiteHelper.TABLE_NAME , null, 1);
            m_db = m_helper.getWritableDatabase();
            values = new ContentValues();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Uri insert(Uri ure, ContentValues values) {
        long id = m_db.insert(SqlLiteHelper.TABLE_NAME, "", values);
        return ContentUris.withAppendedId(CONTENT_URI, id);
    }

    @Override
    public int update(Uri uri, ContentValues values,
                      String whereClause, String[] whereArgs) {

        int numOfChangedRows =
                m_db.update(SqlLiteHelper.TABLE_NAME, values, whereClause,
                        whereArgs);

        return numOfChangedRows;
    }

    @Override
    public int delete(Uri uri, String whereClause,
                      String[] whereArgs) {
        int numOfChangedRows =
                m_db.delete(SqlLiteHelper.TABLE_NAME, whereClause, whereArgs);
        return numOfChangedRows;
    }

    @Override
    public Cursor query(Uri uri, String[] columns,
                        String whereClause, String[] whereArgs,
                        String sortOrder) {
        Cursor cursor = null;

        switch (uriMatcher.match(uri)) {
            case SINGLE_ROW :
            {
                String rowID = uri.getPathSegments().get(1);
                cursor =  m_db.query(SqlLiteHelper.TABLE_NAME, columns, SqlLiteHelper._ID + " = " + rowID ,
                        whereArgs, null, null, sortOrder);
            }
            case ALLROWS :
            {
                cursor =
                        m_db.query(SqlLiteHelper.TABLE_NAME, columns, whereClause,
                                whereArgs, null, null, sortOrder);
            }
            default: break;
        }



        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }
}


