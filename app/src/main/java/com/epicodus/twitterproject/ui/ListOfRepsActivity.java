package com.epicodus.twitterproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.epicodus.twitterproject.R;
import com.epicodus.twitterproject.adapters.RepresentativeListAdapter;
import com.epicodus.twitterproject.models.Representative;
import com.epicodus.twitterproject.services.GoogleService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class ListOfRepsActivity extends AppCompatActivity {
//    private SharedPreferences mSharedPreferences;
//    private String mRecentZipCode;
//    public static final String TAG = AboutActivity.class.getSimpleName();

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private RepresentativeListAdapter mAdapter;

    public ArrayList<Representative> mRepresentatives = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_reps);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String zipCode = intent.getStringExtra("zipCode");

        getRepresentatives(zipCode);

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mRecentZipCode = mSharedPreferences.getString(Constants.PREFERENCES_ZIPCODE_KEY, null);
//
//        if (mRecentZipCode != null) {
//            getRepresentatives(mRecentZipCode);
//        }
        //getRepresentatives(zipCode);
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
                        mAdapter = new RepresentativeListAdapter(getApplicationContext(), mRepresentatives);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(ListOfRepsActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                        String[] representativeNames = new String[mRepresentatives.size()];
//                        for (int i = 0; i < representativeNames.length; i++) {
//                            representativeNames[i] = mRepresentatives.get(i).getName();
//                        }
                    }
                });
            }
        });
    }
}
