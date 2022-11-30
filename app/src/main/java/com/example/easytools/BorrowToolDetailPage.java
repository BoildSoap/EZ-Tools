package com.example.easytools;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class BorrowToolDetailPage extends AppCompatActivity {

    public final String TAG = "Denna";
    TextView nameET, descET;
    Tool currentTool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_tool_detail_page);

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
    public void updateAvailability(View v) {
        Log.d(TAG, "updating aval tool " + currentTool.getName());
        currentTool.setOutUID(SignUpLoginActivity.firebaseHelper.getCurrUID());

        SignUpLoginActivity.firebaseHelper.updateAval(currentTool);
    }
    public void goHome(View view) {
        Intent intent = new Intent(BorrowToolDetailPage.this, homepage.class);
        startActivity(intent);
    }

    public void goBack(View v) {
        Log.d(TAG, "go back");
        Intent intent = new Intent(BorrowToolDetailPage.this, backpack.class);
        startActivity(intent);
    }


}