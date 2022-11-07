package com.example.easytools;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ToolDetails extends AppCompatActivity {
    Spinner spinner;
    EditText memoryName, memoryDesc;
    String spinnerSelectedText = "none";
    int memoryRatingNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_details);

        memoryName = findViewById(R.id.name);
        memoryDesc = findViewById(R.id.desc);
    }

    public void addMemoryButtonClicked(View view) {
        String memName = memoryName.getText().toString();
        String memDesc = memoryDesc.getText().toString();

        for (int i = 1; i < 6; i++) {
            if (spinnerSelectedText.equals(getResources().getStringArray(R.array.memoryRating)[i])) {
                memoryRatingNum = 6-i;
                break;
            }
        }

        // Everyone needs this part below this comment

        Tool m = new Tool(memoryRatingNum, memName, memDesc);
        SignUpLoginActivity.firebaseHelper.addData(m);

        memoryName.setText("");
        memoryDesc.setText("");

    }
}