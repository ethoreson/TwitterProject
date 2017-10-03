package com.epicodus.twitterproject.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;

import com.epicodus.twitterproject.Constants;
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
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentZipCode;

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

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentZipCode = mSharedPreferences.getString(Constants.PREFERENCES_ZIPCODE_KEY, null);

        if (mRecentZipCode != null) {
            getRepresentatives(mRecentZipCode);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                addToSharedPreferences(query);
                getRepresentatives(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
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
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListOfRepsActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }

    private void addToSharedPreferences(String zipCode) {
        mEditor.putString(Constants.PREFERENCES_ZIPCODE_KEY, zipCode).apply();
    }

}