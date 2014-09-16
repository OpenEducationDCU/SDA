package com.example.simpleauthordatabase;


import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class DatabaseActivity extends Activity {

	
	  TextView m_textView;
	  Cursor m_cursor = null;
	  SqlLiteHelper m_helper = null;
	  SQLiteDatabase m_db = null;
	  

	  @Override
	  public void onCreate(Bundle savedInstanceState) 
	  {
		ContentValues values = null;
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_database);

	    m_textView = (TextView) findViewById(R.id.textView1);

	    m_helper = new SqlLiteHelper(this, "author_db", null, 1);
	   
	    m_db = m_helper.getWritableDatabase();
	    m_db.execSQL("delete from  authortable; ");
	  
	    insertAuthors();
	    addToTextView();
	  }
	  
		// Insert several artist records
		private void insertAuthors() {

			ContentValues values = new ContentValues();
			 values = new ContentValues();

			    values.put(SqlLiteHelper.AUTHOR_NAME, "Jane Austen");
			    values.put(SqlLiteHelper.BOOK_TITLE, "Mansfield Park");
			    values.put(SqlLiteHelper.YEAR, 1814);
			    m_db.insert(SqlLiteHelper.TABLE_NAME, null, values);

			    values.clear();
			    
			    values.put(SqlLiteHelper.AUTHOR_NAME, "Jasper Fforde");
			    values.put(SqlLiteHelper.BOOK_TITLE, "The Eyre Affair");
			    values.put(SqlLiteHelper.YEAR, 2010);
			    m_db.insert(SqlLiteHelper.TABLE_NAME, null, values);
			    
			 

			    values.clear();
  
		}

	  @Override
	  public void onDestroy() {
	    super.onDestroy();
	    m_helper.close();
	  }

	  void addToTextView() {
	    m_cursor =
	        m_db.rawQuery("SELECT * FROM authortable;", null);

	    if (m_cursor != null && m_cursor.moveToFirst()) {
	    m_textView.append("***********************\n");
	  //  m_textView.append(_id + " " + "Author_Name" + " " + "Book_Title + " " + "Year"
	          //  + "\n");
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

