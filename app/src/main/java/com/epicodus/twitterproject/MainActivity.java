package com.epicodus.twitterproject;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Button mFindRepsButton;
    private EditText mZipCodeEditText;
    private TextView mAppIntroTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //default behavior, don't change
        setContentView(R.layout.layout); // use main activity layout
        mAppIntroTextView = (TextView) findViewById(R.id.appIntroTextView);
        Typeface droidFont = Typeface.createFromAsset(getAssets(), "fonts/DroidSerifRegular.ttf");
        mAppIntroTextView.setTypeface(droidFont);
        mZipCodeEditText = (EditText) findViewById(R.id.zipCodeText);
        mFindRepsButton = (Button) findViewById(R.id.findRepsButton);
        mFindRepsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String zipCode = mZipCodeEditText.getText().toString();
                Intent intent = new Intent(MainActivity.this, ListOfRepsActivity.class); //new instance of intent class
                intent.putExtra("zipCode", zipCode);
                startActivity(intent); //start activity method, takes line above
            }
        });
    }
}
