package com.example.simpleauthordatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlLiteHelper extends SQLiteOpenHelper {
	
	final static String TABLE_NAME = "authortable";
	final static String AUTHOR_NAME = "author";
	final static String BOOK_TITLE = "title";
	final static String YEAR = "year";
	final static String _ID = "_id";

  public SqlLiteHelper(Context context, String dbName,
      SQLiteDatabase.CursorFactory factory, int version) {
    super(context, dbName, factory, version);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    String createString =
        "CREATE TABLE IF NOT EXISTS authortable "
            + "( _id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "author TEXT NOT NULL, "
            + "title TEXT NOT NULL, "
            + "year INTEGER NOT NULL);";
    db.execSQL(createString);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db,
      int oldVersion, int newVersion) {
    String dropString =
        "DROP TABLE IF EXISTS authortable;";
    db.execSQL(dropString);
    onCreate(db);
  }
}