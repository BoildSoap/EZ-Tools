package com.example.easytools;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class homepage extends AppCompatActivity {
    public void logOutClicked(View view) {
        SignUpLoginActivity.firebaseHelper.logOutUser();
        Intent intent = new Intent(homepage.this, SignUpLoginActivity.class);
        startActivity(intent);
    }
    public void toBackpack(View view) {
        Intent intent = new Intent(homepage.this, backpack.class);
        startActivity(intent);
    }
    public void showTools(View view) {
        Intent intent = new Intent(homepage.this, backpack.class);
        startActivity(intent);
    }
    public void borrowTools(View view) {
        Intent intent = new Intent(homepage.this, BorrowTool.class);
        startActivity(intent);
    }
    public void addTools(View view) {
        Intent intent = new Intent(homepage.this, AddTool.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
    }

}




