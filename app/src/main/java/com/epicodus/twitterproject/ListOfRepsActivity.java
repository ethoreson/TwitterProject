package com.epicodus.twitterproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListOfRepsActivity extends AppCompatActivity {
    private TextView mZipCodeTextView;
    private ListView mListView;
    private String[] representatives = new String[] {"Jim Ferrell", "Cyrus Habib", "Jay Inslee", "Dave Reichert", "Patty Murray", "Maria Cantwell", "Mike Pence", "Donald Trump"};
    private String[] details = new String[] {"Mayor - D - @WAFederalWay", "Lieutenant Governor - D - @cyrushabib", "Governor - D - @GovInslee", "Congress Representative - R - @davereichert", "Senator - D - @PattyMurray", "Senator - D - @SenatorCantwell", "Vice President - R - @mike_pence", "President - R - @realDonaldTrump"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_reps);

        mListView = (ListView) findViewById(R.id.listView);
        mZipCodeTextView = (TextView) findViewById(R.id.zipCodeTextView);

        MyRepsArrayAdapter adapter = new MyRepsArrayAdapter(this, android.R.layout.simple_list_item_1, representatives, details);
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
