package com.example.easytools;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class EditTools extends AppCompatActivity {

    public final String TAG = "Denna";
    EditText nameET, descET;
    Tool currentTool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tools);

        nameET = findViewById(R.id.toolNameTV);
        descET = findViewById(R.id.toolDescTV);

        // gets intent from ViewAllMemoriesActivity and retrieves the selected Memory
        // the viewer wanted to see.
        Intent intent = getIntent();
        currentTool = intent.getParcelableExtra(backpack.CHOSEN_TOOL);
        // Sets the name and desc from the chosen memory
        // Right now I don't have any options to edit the rating.
        nameET.setText(currentTool.getName());
        descET.setText(currentTool.getDesc());
    }
    public void goHome(View view) {
        Intent intent = new Intent(EditTools.this, homepage.class);
        startActivity(intent);
    }

    public void saveToolEdits(View v) {
        Log.d(TAG, "inside saveMemoryEdits method");
        // updates the currentMemory object to have the same name/desc that are on the screen
        // in the event of changes made.
        currentTool.setName(nameET.getText().toString());
        currentTool.setDesc(descET.getText().toString());

        // Calls editData with this updated Memory object
        SignUpLoginActivity.firebaseHelper.editData(currentTool);
    }

    public void deleteData(View v) {
        Log.d(TAG, "deleting tool " + currentTool.getName());
        SignUpLoginActivity.firebaseHelper.deleteData(currentTool);
        SignUpLoginActivity.firebaseHelper.attachReadDataToUser();
    }

    public void goBack(View v) {
        Log.d(TAG, "go back");
        Intent intent = new Intent(EditTools.this, backpack.class);
        startActivity(intent);
    }


}