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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
    }

}




