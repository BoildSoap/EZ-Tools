package com.example.easytools;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class backpack extends AppCompatActivity {
    private ListView listView;
    public static final String CHOSEN_TOOL = "chosen tool";
    public String myUserName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backpack);

        // find listView in xml
        // get ArrayList of data from firebase



        ArrayList<Tool> myList = SignUpLoginActivity.firebaseHelper.getMyTools();

        // bind data to the ArrayAdapter (this is a default adapter
        // The text shown is based on the Memory class toString

        ArrayAdapter<Tool> listAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, myList);
        // attaches the listAdapter to my listView
        toolAdapter myToolAdapter = new toolAdapter(this, myList);


        // This finds the listView and then adds the adapter to bind the data to this view
        ListView listView = (ListView) findViewById(R.id.allToolsListView);

        listView.setAdapter(myToolAdapter);
        // if did custom array set up, use this one

        // Create listener to listen for when a Food from the specific Category list is clicked on
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if(myList.get(position).getMyUserName().equals(SignUpLoginActivity.firebaseHelper.getUserEmail()) && (myList.get(position).isAval() == true)) {
                    // Creates an intent to go from the Specific Category to the specific Detail
                    Intent intent = new Intent(backpack.this, EditTools.class);
                    // Sends the specific object at index i to the Detail activity
                    // In this case, it is sending the particular Food object
                    intent.putExtra(CHOSEN_TOOL, myList.get(position));
                    startActivity(intent);
                }else if((myList.get(position).getMyUserName().equals(SignUpLoginActivity.firebaseHelper.getUserEmail()) && !(myList.get(position).isAval() == true))) {
                    Toast.makeText(getApplicationContext(), "Tool is Lent out. Cannot Edit", Toast.LENGTH_SHORT).show();
                }else {
                    // Creates an intent to go from the Specific Category to the specific Detail
                    Intent intent = new Intent(backpack.this, ReturnTool.class);
                    // Sends the specific object at index i to the Detail activity
                    // In this case, it is sending the particular Food object
                    intent.putExtra(CHOSEN_TOOL, myList.get(position));
                    startActivity(intent);
                }
            }
        });

    }
    public void goHome(View view) {
        Intent intent = new Intent(backpack.this, homepage.class);
        startActivity(intent);
    }
    public void goToSelectOptions(View view) {
        Intent intent = new Intent(backpack.this, SelectAction.class);
        startActivity(intent);
    }
}