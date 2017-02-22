package uk.ac.tees.q5071488.journal;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class AddEntry extends AppCompatActivity implements View.OnClickListener
{
    Button btnCancel, btnSave;
    EditText editTextNote;
    RadioGroup radioCategoryGroup;
    JournalEntryRepo repo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        btnCancel = (Button) findViewById(R.id.buttonCancel);
        btnCancel.setOnClickListener(this);

        btnSave = (Button) findViewById(R.id.buttonSave);
        btnSave.setOnClickListener(this);

        editTextNote = (EditText) findViewById(R.id.editNote);
        editTextNote.setOnClickListener(this);
        editTextNote.addTextChangedListener(textWatcher);

        radioCategoryGroup = (RadioGroup) findViewById(R.id.radioCategory);

        // Check Fields For Empty Values
        checkFieldsForEmptyValues();

        repo = new JournalEntryRepo(this);
    }

    @Override
    public void onClick(View view)
    {
        // If save button pressed
        if (view== findViewById(R.id.buttonSave))
        {
            JournalEntry entry = new JournalEntry();

            // Getting string from editText
            String note = editTextNote.getText().toString().trim();
            // Getting string from selected radioButton
            String category = ((RadioButton)findViewById(radioCategoryGroup.getCheckedRadioButtonId())).getText().toString().trim();

            // Adding the new entry to the database
            entry.setNote(note);
            entry.setCategory(category);
            repo.addEntry(entry);

            // Toast to inform user the entry has been made
            Toast.makeText(getApplicationContext(), "Entry added to your journal!", Toast.LENGTH_SHORT).show();
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
