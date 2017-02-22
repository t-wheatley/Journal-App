package uk.ac.tees.q5071488.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Tom on 22/02/2017.
 */

public class JournalEntryRepo
{
    private DBHelper dbHelper;

    // Constructor
    public JournalEntryRepo(Context context)
    {
        dbHelper = new DBHelper(context);
    }

    public int addEntry(JournalEntry journalEntry)
    {
        // Connect to the database to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Creating value pairs to be inserted
        ContentValues values = new ContentValues();
        // ID not needed as auto-incremented
        values.put(JournalEntry.COL_NOTE, journalEntry.getNote());
        values.put(JournalEntry.COL_CATEGORY, journalEntry.getCategory());
        // Julian datetime added using SQL update below
        values.put(JournalEntry.COL_LOCLONG, journalEntry.getLongitude());
        values.put(JournalEntry.COL_LOCLAT, journalEntry.getLatitude());

        // Add new record to the database and return the id
        long entry_id = db.insert(journalEntry.TABLE, null, values);

        // Query to add Julia Datetime
        String datetimeQuery =  "UPDATE " + JournalEntry.TABLE +
                " SET " +
                JournalEntry.COL_DATETIME + " = (julianday('now'))" +
                " WHERE " +
                JournalEntry.COL_ID + " = " + (int)entry_id;

        db.execSQL(datetimeQuery);

        db.close();
        Log.d("Database", "Entry added to database.");
        return (int) entry_id;
    }

    public JournalEntry getEntry(int id)
    {
        Log.d("Database", "Getting an entry from the database.");
        // Connect to the database to read data
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String selectQuery =  "SELECT  " +
                JournalEntry.COL_ID + "," +
                JournalEntry.COL_NOTE + "," +
                JournalEntry.COL_CATEGORY + "," +
                JournalEntry.COL_DATETIME + "," +
                JournalEntry.COL_LOCLONG + "," +
                JournalEntry.COL_LOCLAT +
                " FROM " + JournalEntry.TABLE
                + " WHERE " +
                JournalEntry.COL_ID + "=?";

        int iCount = 0;
        JournalEntry entry = new JournalEntry();

        Cursor cursor = db.rawQuery(selectQuery, new String[] {String.valueOf(id)});

        if (cursor.moveToFirst())
        {
            do
            {
                entry.entry_ID = cursor.getInt(cursor.getColumnIndex(JournalEntry.COL_ID));
                entry.note = cursor.getString(cursor.getColumnIndex(JournalEntry.COL_NOTE));
                entry.category = cursor.getString(cursor.getColumnIndex(JournalEntry.COL_CATEGORY));
                entry.datetime = cursor.getDouble(cursor.getColumnIndex(JournalEntry.COL_DATETIME));
                entry.longitude = cursor.getDouble(cursor.getColumnIndex(JournalEntry.COL_LOCLONG));
                entry.latitude = cursor.getDouble(cursor.getColumnIndex(JournalEntry.COL_LOCLONG));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return entry;
    }



    public void removeAll()
    {
        Log.d("Database", "Removing all entries.");

        // Connect to the tables
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Execute delete (drop) table SQL command
        db.execSQL("DROP TABLE IF EXISTS " + JournalEntry.TABLE);

        // call create method to re-generate the table
        dbHelper.onCreate(db);

        db.close();
    }

}
