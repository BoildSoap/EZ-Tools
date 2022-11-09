package com.example.easytools;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ToolDetails extends AppCompatActivity {
    Spinner spinner;
    EditText toolName, toolDesc;
    String spinnerSelectedText = "none";
    int memoryRatingNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_details);

        toolName = findViewById(R.id.name);
        
        toolDesc = findViewById(R.id.desc);
    }

    public void addMemoryButtonClicked(View view) {
        String memName = toolName.getText().toString();
        String memDesc = toolDesc.getText().toString();

        for (int i = 1; i < 6; i++) {
            if (spinnerSelectedText.equals(getResources().getStringArray(R.array.toolsList)[i])) {
                memoryRatingNum = 6-i;
                break;
            }
        }

        // Everyone needs this part below this comment

        Tool m = new Tool(memoryRatingNum, memName, memDesc);
        SignUpLoginActivity.firebaseHelper.addData(m);

        toolName.setText("");
        toolDesc.setText("");

    }
}