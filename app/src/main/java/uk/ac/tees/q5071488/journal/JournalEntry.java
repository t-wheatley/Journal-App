package uk.ac.tees.q5071488.journal;

import java.util.Date;

/**
 * Created by Tom on 21/02/2017.
 */

public class JournalEntry
{
    // Labels table name
    public static final String TABLE = "JournalEntry";

    // Labels Table Columns names
    public static final String COL_ID = "_id";
    public static final String COL_NOTE = "note_text";
    public static final String COL_CATEGORY = "category";
    public static final String COL_DATETIME = "datetime";
    public static final String COL_LOCLONG = "loc_long";
    public static final String COL_LOCLAT = "loc_lat";

    // Attributes
    public int entry_ID;
    public String note;
    public String category;
    public Double datetime;
    public double longitude;
    public double latitude;

    // Constructor with all parameters
    public JournalEntry(int entry_ID, String note, String category, double datetime, double longitude, double latitude)
    {
        this.entry_ID = entry_ID;
        this.note = note;
        this.category = category;
        this.datetime = datetime;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    // Constructor without id parameter
    public JournalEntry(String note, String category, double datetime, double longitude, double latitude)
    {
        this.note = note;
        this.category = category;
        this.datetime = datetime;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public JournalEntry()
    {

    }

    public int getEntry_ID()
    {
        return entry_ID;
    }

    public void setEntry_ID(int entry_ID)
    {
        this.entry_ID = entry_ID;
    }

    public String getNote()
    {
        return note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public double getDatetime()
    {
        return datetime;
    }

    public void setDatetime(double datetime)
    {
        this.datetime = datetime;
    }

    public double getLongitude()
    {
        return longitude;
    }

    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }

    public double getLatitude()
    {
        return latitude;
    }

    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }
}
