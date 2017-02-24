package uk.ac.tees.q5071488.journal;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class EntryDetail extends AppCompatActivity implements View.OnClickListener
{
    Button btnCancel,  btnDelete, btnSave;
    EditText editTextNote;
    TextView dateDisplay;
    RadioGroup radioCategoryGroup;
    JournalEntryRepo repo;
    private int _Entry_Id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_detail);

        btnCancel = (Button) findViewById(R.id.buttonCancel);
        btnCancel.setOnClickListener(this);

        btnDelete = (Button) findViewById(R.id.buttonDelete);
        btnDelete.setOnClickListener(this);

        btnSave = (Button) findViewById(R.id.buttonSave);
        btnSave.setOnClickListener(this);

        editTextNote = (EditText) findViewById(R.id.editNote);
        editTextNote.setOnClickListener(this);
        editTextNote.addTextChangedListener(textWatcher);

        dateDisplay = (TextView) findViewById(R.id.dateDisplay);

        radioCategoryGroup = (RadioGroup) findViewById(R.id.radioCategory);

        _Entry_Id =0;
        Intent intent = getIntent();
        _Entry_Id =intent.getIntExtra("entry_Id", 0);
        repo = new JournalEntryRepo(this);
        JournalEntry entry = new JournalEntry();
        entry = repo.getEntry(_Entry_Id);


        editTextNote.setText(entry.getNote());
        dateDisplay.setText("Date" + entry.getDatetime());
        if (entry.getCategory().equals("Work"))
        {
            radioCategoryGroup.check(R.id.radioWork);
        } else
        {
            radioCategoryGroup.check(R.id.radioPersonal);
        }

    }

    @Override
    public void onClick(View view)
    {
        if (view == findViewById(R.id.buttonSave)){
            JournalEntry entry = new JournalEntry();
            entry.note = editTextNote.getText().toString();
            entry.category = ((RadioButton)findViewById(radioCategoryGroup.getCheckedRadioButtonId())).getText().toString().trim();
            entry.entry_ID = _Entry_Id;

            repo.updateEntry(entry);

            finish();
            startActivity(getIntent());

            Toast.makeText(this,"Entry updated!",Toast.LENGTH_SHORT).show();

        }else if (view== findViewById(R.id.buttonDelete)){
            repo.removeEntry(_Entry_Id);
            Toast.makeText(this, "Entry deleted!", Toast.LENGTH_SHORT);
            finish();
        }else if (view== findViewById(R.id.buttonCancel)){
            finish();
        }
    }

    // TextWatcher
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        // When the text in an editText is changed
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Check Fields For Empty Values
            checkFieldsForEmptyValues();
        }

        @Override
        public void afterTextChanged(Editable s) {}
    };

    // Checking if the editText is empty
    void checkFieldsForEmptyValues() {

        // Getting the value of the editText
        String s1 = editTextNote.getText().toString();

        // Checking if athe editText is empty
        if(s1.trim().equals(""))
        {
            // If empty the button is disabled
            btnSave.setEnabled(false);
            btnSave.setBackgroundColor(Color.parseColor("#d3d3d3"));
        } else {
            // Button is enabled
            btnSave.setEnabled(true);
            btnSave.setBackgroundColor(Color.parseColor("#3F51B5"));
        }
    }
}
