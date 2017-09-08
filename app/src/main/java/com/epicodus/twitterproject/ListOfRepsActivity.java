package com.epicodus.twitterproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ListOfRepsActivity extends AppCompatActivity {
    private String[] representatives = new String[] {"Jim Ferrell", "Cyrus Habib", "Jay Inslee", "Dave Reichert", "Patty Murray", "Maria Cantwell", "Mike Pence", "Donald Trump"};
    private TextView mZipCodeTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_reps);

        mZipCodeTextView = (TextView) findViewById(R.id.zipCodeTextView);

        Intent intent = getIntent();
        String zipCode = intent.getStringExtra("zipCode");
        mZipCodeTextView.setText("Your elected representatives, based on your zip code of " + zipCode + " are:");
    }
}
