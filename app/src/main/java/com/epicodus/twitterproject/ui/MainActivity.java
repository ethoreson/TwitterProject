package com.epicodus.twitterproject.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.twitterproject.Constants;
import com.epicodus.twitterproject.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    //private static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.findRepsButton) Button mFindRepsButton;
    @Bind(R.id.aboutButton) Button mAboutButton;
    @Bind(R.id.zipCodeText) EditText mZipCodeText;
    @Bind(R.id.appIntroTextView) TextView mAppIntroTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //default behavior, don't change
        setContentView(R.layout.activity_main); // use main activity activity_main
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        mFindRepsButton.setOnClickListener(this);
        mAboutButton.setOnClickListener(this);

        Typeface droidFont = Typeface.createFromAsset(getAssets(), "fonts/DroidSerifRegular.ttf");
        mAppIntroTextView.setTypeface(droidFont);
    }

        @Override
        public void onClick(View v) {
            if (v == mFindRepsButton) {
                String zipCode = mZipCodeText.getText().toString();
                if(!(zipCode).equals("")) {
                    addToSharedPreferences(zipCode);
                }
                Intent intent = new Intent(MainActivity.this, ListOfRepsActivity.class);
                startActivity(intent);
            } else {
                Intent aboutIntent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(aboutIntent);
            }
        }
        private void addToSharedPreferences(String zipCode) {
            mEditor.putString(Constants.PREFERENCES_ZIPCODE_KEY, zipCode).apply();
        }
    }
