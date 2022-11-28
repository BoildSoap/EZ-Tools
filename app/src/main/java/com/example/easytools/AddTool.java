package com.example.easytools;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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


    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinnerSelectedText = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), spinnerSelectedText, Toast.LENGTH_SHORT).show();
    }
    // This method is required, even if empty, for the OnItemSelectedListener to work
    @Override
    public void onNothingSelected(AdapterView<?> parent) { }

    public void addToolButtonClicked(View view) {
        String name = toolName.getText().toString();
        String desc = toolDesc.getText().toString();
        String myUid = SignUpLoginActivity.firebaseHelper.getmAuth().getUid();

        /*
        The code is adding, however it isn't saving the UID.  Check the code in Tool constructor
        and also check the code when teh docID is being saved.
         */

        Tool t = new Tool(name, desc, myUid);
        Log.d("Denna", "" + t.toString());
        SignUpLoginActivity.firebaseHelper.addData(t);

        toolName.setText("");
        toolDesc.setText("");
    }

    public void goHome(View view) {
        Intent intent = new Intent(AddTool.this, homepage.class);
        startActivity(intent);
    }
    public void goBack(View view) {
        Intent intent = new Intent(AddTool.this, backpack.class);
        startActivity(intent);
    }
}

