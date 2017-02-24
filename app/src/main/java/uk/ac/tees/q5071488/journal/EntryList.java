package uk.ac.tees.q5071488.journal;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class EntryList extends ListActivity implements android.view.View.OnClickListener
{
    Button btnBack, btnGetAll;
    TextView entry_Id;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_list);

        btnBack = (Button) findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(this);

        btnGetAll = (Button) findViewById(R.id.buttonGetAll);
        btnGetAll.setOnClickListener(this);
    }

    public void onResume()
    {
        super.onResume();

        btnGetAll.performClick();
    }

    public void onClick(View view)
    {
        // If back button pressed
        if (view== findViewById(R.id.buttonBack))
        {
            finish();
        } else
        {
            JournalEntryRepo repo = new JournalEntryRepo(this);

            ArrayList<HashMap<String, String>> entryList =  repo.getEntryList();
            if(entryList.size()!=0) {
                ListView lv = getListView();
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        entry_Id = (TextView) view.findViewById(R.id.entry_Id);
                        String entryId = entry_Id.getText().toString();
                        Intent objIndent = new Intent(getApplicationContext(),EntryDetail.class);
                        objIndent.putExtra("entry_Id", Integer.parseInt( entryId));
                        startActivity(objIndent);
                    }
                });
                Collections.sort(entryList, new DateComparator("datetime"));
                Collections.reverse(entryList);
                ListAdapter adapter = new SimpleAdapter( this, entryList, R.layout.view_journal_entry, new String[] { "id","note", "datetime"}, new int[] {R.id.entry_Id, R.id.entry_note, R.id.entry_datetime});
                setListAdapter(adapter);
            }else{
                Toast.makeText(this,"No entries!",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
