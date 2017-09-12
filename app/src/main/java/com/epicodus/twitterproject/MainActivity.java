package com.epicodus.twitterproject;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
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

        Typeface droidFont = Typeface.createFromAsset(getAssets(), "fonts/DroidSerifRegular.ttf");
        mAppIntroTextView.setTypeface(droidFont);

        mFindRepsButton.setOnClickListener(this);
        mAboutButton.setOnClickListener(this);
    }
        @Override
        public void onClick(View v) {
            if(v == mFindRepsButton) {
                String zipCode = mZipCodeText.getText().toString();
                Intent intent = new Intent(MainActivity.this, ListOfRepsActivity.class);
                intent.putExtra("zipCode", zipCode);
                startActivity(intent);
            }
            if(v == mAboutButton) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        }
    }
