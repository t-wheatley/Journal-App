package uk.ac.tees.q5071488.journal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JournalEntryRepo repo = new JournalEntryRepo(this);
        repo.removeAll();
        JournalEntry entry = new JournalEntry("test", "test", 1.0, 1.0, 1.0);
        repo.addEntry(entry);
        JournalEntry je1 = repo.getEntry(1);
        String log = "ID: " + je1.getEntry_ID() + " Note: " + je1.getNote() + " Datetime: "
                + je1.getDatetime() + " Longitude: " + je1.getLongitude();
        Log.d("Database", log);

    }
}
