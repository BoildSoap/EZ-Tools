package com.example.easytools;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddTool extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {

    Spinner spinner;
    EditText toolName, toolDesc;
    String spinnerSelectedText = "none";
    int memoryRatingNum;

    // How to implement a Spinner
    // https://www.tutorialspoint.com/how-to-get-spinner-value-in-android

    // How to style the spinner
    // https://www.youtube.com/watch?v=7tnlh1nVkuE

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tool);

        toolName = findViewById(R.id.memNameEditText);
        toolDesc = findViewById(R.id.memoryDescEditText);


        // this attaches my spinner design (spinner_list.xml) and my array of spinner choices(R.array.memoryRating)
        spinner = findViewById(R.id.memorySpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_list,
                getResources().getStringArray(R.array.toolsList));

        // ADD WHEN MAKE ARRAY!!!!^^^^^


        // this attaches my custom row design (how I want each row to look)
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_row);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinnerSelectedText = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), spinnerSelectedText, Toast.LENGTH_SHORT).show();
    }
    // This method is required, even if empty, for the OnItemSelectedListener to work
    @Override
    public void onNothingSelected(AdapterView<?> parent) { }

    public void addMemoryButtonClicked(View view) {
        String memName = toolName.getText().toString();
        String memDesc = toolDesc.getText().toString();


        //*** If you only have numbers in your string array, then don't include this part! *** //

        //*** IF you have String text in your string array, then use a loop such as this ***//

        // This loop take the option they clicked on and ensure it is a number.
        // My options went from 5 to 1, so that is why I have it adjusted with 6-i
        // I also had an instruction statement as my first line in my string array
        // ADJUST THIS LOOP TO MATCH YOUR CODE!





        // Note the syntax here for how to access an index of a string array within java
        for (int i = 0; i < 6; i++) {
            if (spinnerSelectedText.equals(getResources().getStringArray(R.array.toolsList)[i])) {
                memoryRatingNum = 6-i;
                break;
            }
        }

        ////////BRING BACK ^^^^^ ONCE ADDED ARRAY




        // Everyone needs this part below this comment

        Tool m = new Tool(memoryRatingNum, memName, memDesc);
        SignUpLoginActivity.firebaseHelper.addData(m);

        toolName.setText("");
        toolDesc.setText("");
    }


}

