package com.epicodus.twitterproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

//import android.net.http.RequestQueue;

public class ListOfRepsActivity extends AppCompatActivity {
    private ListView lv;
    public static final String TAG = AboutActivity.class.getSimpleName();

    @Bind(R.id.zipCodeTextView) TextView mZipCodeTextView;
    @Bind(R.id.listView) ListView mListView;

    public ArrayList<Representative> mRepresentatives = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_list_of_reps);
        lv = (ListView) findViewById(R.id.listView);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_reps);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String zipCode = intent.getStringExtra("zipCode");

        List<String> array_list = new ArrayList<String>();
        //ListView listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                array_list );

        lv.setAdapter(arrayAdapter);

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

                        for (Representative representative: mRepresentatives) {
                            Log.d(TAG, "Name: " + representative.getName());
                            Log.d(TAG, "Party: " + representative.getParty());
                        }
                    }
                });
            }
        });
    }
}
