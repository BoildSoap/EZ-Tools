package com.example.easytools;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SelectAction extends AppCompatActivity {

    public final String TAG = "Denna";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_action);
    }

    public void showTools(View view) {
        Intent intent = new Intent(SelectAction.this, backpack.class);
        startActivity(intent);
    }

    public void addTools(View view) {
        Intent intent = new Intent(SelectAction.this, AddTool.class);
        startActivity(intent);
    }

    public void logOutClicked(View view) {
        SignUpLoginActivity.firebaseHelper.logOutUser();
        Log.i(TAG, "user logged out");
        Intent intent = new Intent(SelectAction.this, SignUpLoginActivity.class);
        startActivity(intent);
    }
}
