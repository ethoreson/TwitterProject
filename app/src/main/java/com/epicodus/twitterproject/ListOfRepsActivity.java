package com.epicodus.twitterproject;

import android.content.Intent;
//import android.net.http.RequestQueue;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;


import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ListOfRepsActivity extends AppCompatActivity {
    public static final String TAG = AboutActivity.class.getSimpleName();

    @Bind(R.id.zipCodeTextView) TextView mZipCodeTextView;
    @Bind(R.id.listView) ListView mListView;

    public ArrayList<Representative> mRepresentatives = new ArrayList<>();

//    private String[] representatives = new String[] {"Jim Ferrell", "Cyrus Habib", "Jay Inslee", "Dave Reichert", "Patty Murray", "Maria Cantwell", "Mike Pence", "Donald Trump"};
//    private String[] details = new String[] {"Mayor - D - @WAFederalWay", "Lieutenant Governor - D - @cyrushabib", "Governor - D - @GovInslee", "Congress Representative - R - @davereichert", "Senator - D - @PattyMurray", "Senator - D - @SenatorCantwell", "Vice President - R - @mike_pence", "President - R - @realDonaldTrump"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_reps);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String zipCode = intent.getStringExtra("zipCode");



//        MyRepsArrayAdapter adapter = new MyRepsArrayAdapter(this, android.R.layout.simple_list_item_1, representatives, details);
//        mListView.setAdapter(adapter);

//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String representative = ((TextView)view).getText().toString();
//                Toast.makeText(ListOfRepsActivity.this, representative, Toast.LENGTH_LONG).show();
//            }
//        });


        mZipCodeTextView.setText("Your elected representatives, based on your address of " + zipCode + " are:");

        getRepresentatives(zipCode);
    }

    private void getRepresentatives(String zipCode) {
        final GoogleService googleService = new GoogleService();

        googleService.findRepresentatives(zipCode, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mRepresentatives = googleService.processResults(response);

                ListOfRepsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        String[] representativeNames = new String[mRepresentatives.size()];
                        for (int i = 0; i < representativeNames.length; i++) {
                            representativeNames[i] = mRepresentatives.get(i).getName();
                        }

                        ArrayAdapter adapter = new ArrayAdapter(ListOfRepsActivity.this,
                                android.R.layout.simple_list_item_1, representativeNames);
                        mListView.setAdapter(adapter);

                        for (Representative representative: mRepresentatives) {
                            Log.d(TAG, "Name: " + representative.getName());
                            Log.d(TAG, "Party: " + representative.getParty());
                            Log.d(TAG, "Channels: " + representative.getChannels().toString());
                        }
                    }
                });
//                try {
//                    String jsonData = response.body().string();
//                    if (response.isSuccessful()) {
//                        Log.v(TAG, jsonData);
//                        mRepresentatives = googleService.processResults(response);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
        });
    }
}
