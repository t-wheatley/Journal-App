package uk.ac.tees.q5071488.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    Button btnHome, btnAdd, btnList, btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnHome = (Button) findViewById(R.id.buttonHome);
        btnHome.setOnClickListener(this);

        btnAdd = (Button) findViewById(R.id.buttonAdd);
        btnAdd.setOnClickListener(this);

        btnList = (Button) findViewById(R.id.buttonList);
        btnList.setOnClickListener(this);

        btnSearch = (Button) findViewById(R.id.buttonSearch);
        btnSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        // If add button pressed
        if (view== findViewById(R.id.buttonAdd)){
            // Loads the addEntry activity
            Intent intent = new Intent(this, AddEntry.class);
            startActivity(intent);
        }

        // If list button pressed
        if (view== findViewById(R.id.buttonList)){
            // Loads the addEntry activity
            Intent intent = new Intent(this, EntryList.class);
            startActivity(intent);
        }
    }
}
