package com.epicodus.twitterproject.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.twitterproject.Constants;
import com.epicodus.twitterproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;
    private DatabaseReference mSearchedZipCodeReference;
    private ValueEventListener mSearchedZipCodeReferenceListener;
    //private static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.findRepsButton) Button mFindRepsButton;
    @Bind(R.id.aboutButton) Button mAboutButton;
    @Bind(R.id.zipCodeText) EditText mZipCodeText;
    @Bind(R.id.appIntroTextView) TextView mAppIntroTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSearchedZipCodeReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_ZIPCODE);

        mSearchedZipCodeReference = mSearchedZipCodeReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot zipCodeSnapshot : dataSnapshot.getChildren()) {
                    String zipCode = zipCodeSnapshot.getValue().toString();
                    Log.d("zip codes updated", "zipCode: " + zipCode);
                }
            }

            @Override
           public void onCancelled(DatabaseError databaseError) {
            }


        });

        super.onCreate(savedInstanceState); //default behavior, don't change
        setContentView(R.layout.activity_main); // use main activity activity_main
        ButterKnife.bind(this);

        Typeface droidFont = Typeface.createFromAsset(getAssets(), "fonts/DroidSerifRegular.ttf");
        mAppIntroTextView.setTypeface(droidFont);

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();

        mFindRepsButton.setOnClickListener(this);
        //mAboutButton.setOnClickListener(this);


    }

        @Override
        public void onClick(View v) {
            if (v == mFindRepsButton) {
                String zipCode = mZipCodeText.getText().toString();

                saveZipCodeToFirebase(zipCode);
//                if(!(zipCode).equals("")) {
//                    addToSharedPreferences(zipCode);
//                }
                Intent intent = new Intent(MainActivity.this, ListOfRepsActivity.class);
                intent.putExtra("zipCode", zipCode);
                startActivity(intent);
            } else if (v == mAboutButton) {
                Intent aboutIntent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(aboutIntent);
            }
        }

        public void saveZipCodeToFirebase(String zipCode) {
            mSearchedZipCodeReference.push().setValue(zipCode);
        }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSearchedZipCodeReference.removeEventListener(mSearchedZipCodeReferenceListener);
    }
//        private void addToSharedPreferences(String zipCode) {
//            mEditor.putString(Constants.PREFERENCES_ZIPCODE_KEY, zipCode).apply();
//        }
    }
