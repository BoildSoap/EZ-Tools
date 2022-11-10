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
    Tool currentMemory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tools);

        nameET = findViewById(R.id.memoryNameTV);
        descET = findViewById(R.id.memoryDescTV);

        // gets intent from ViewAllMemoriesActivity and retrieves the selected Memory
        // the viewer wanted to see.
        Intent intent = getIntent();
        currentMemory = intent.getParcelableExtra(backpack.CHOSEN_MEMORY);
        // Sets the name and desc from the chosen memory
        // Right now I don't have any options to edit the rating.
        nameET.setText(currentMemory.getName());
        descET.setText(currentMemory.getDesc());
    }


    public void saveMemoryEdits(View v) {
        Log.d(TAG, "inside saveMemoryEdits method");
        // updates the currentMemory object to have the same name/desc that are on the screen
        // in the event of changes made.
        currentMemory.setName(nameET.getText().toString());
        currentMemory.setDesc(descET.getText().toString());

        // Calls editData with this updated Memory object
        SignUpLoginActivity.firebaseHelper.editData(currentMemory);
    }

    public void deleteMemory(View v) {
        Log.d(TAG, "deleting memory " + currentMemory.getName());
        SignUpLoginActivity.firebaseHelper.deleteData(currentMemory);
    }

    public void goBack(View v) {
        Log.d(TAG, "go back");
        Intent intent = new Intent(EditTools.this, backpack.class);
        startActivity(intent);
    }


}