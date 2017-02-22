package uk.ac.tees.q5071488.journal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Tom on 22/02/2017.
 */

public class DBHelper extends SQLiteOpenHelper
{
    // DB name
    private static final String DATABASE_NAME = "JournalEntries.db";

    // Constructor
    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        // Generate Create SQL Statement
        String CREATE_ENTRIES_TABLE = "CREATE TABLE " + JournalEntry.TABLE + "("
                + JournalEntry.COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + JournalEntry.COL_NOTE + " TEXT,"
                + JournalEntry.COL_CATEGORY + " TEXT,"
                + JournalEntry.COL_DATETIME + " REAL,"
                + JournalEntry.COL_LOCLONG + " REAL,"
                + JournalEntry.COL_LOCLAT + " REAL" + ")";

        // Execute/run create SQL statement
        db.execSQL(CREATE_ENTRIES_TABLE);

        Log.d("Database", "Database Created.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldNum, int newNum)
    {
        // Drop older table if exists and create fresh (deletes all data)
        db.execSQL(("DROP TABLE IF EXISTS " + JournalEntry.TABLE));
        onCreate(db);
    }
}
