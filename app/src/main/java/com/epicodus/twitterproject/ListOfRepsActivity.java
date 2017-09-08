package com.epicodus.twitterproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListOfRepsActivity extends AppCompatActivity {
    private TextView mZipCodeTextView;
    private ListView mListView;
    private String[] representatives = new String[] {"Jim Ferrell", "Cyrus Habib", "Jay Inslee", "Dave Reichert", "Patty Murray", "Maria Cantwell", "Mike Pence", "Donald Trump"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_reps);

        mListView = (ListView) findViewById(R.id.listView);
        mZipCodeTextView = (TextView) findViewById(R.id.zipCodeTextView);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, representatives);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String representative = ((TextView)view).getText().toString();
                Toast.makeText(ListOfRepsActivity.this, representative, Toast.LENGTH_LONG).show();
            }
        });

        Intent intent = getIntent();
        String zipCode = intent.getStringExtra("zipCode");
        mZipCodeTextView.setText("Your elected representatives, based on your zip code of " + zipCode + " are:");
    }
}
