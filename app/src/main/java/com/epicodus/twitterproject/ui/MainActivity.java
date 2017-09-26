package com.epicodus.twitterproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.epicodus.twitterproject.R;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.findRepsButton) Button mFindRepsButton;
    @Bind(R.id.savedRepsButton) Button mSavedRepsButton;
    @Bind(R.id.aboutButton) Button mAboutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mFindRepsButton.setOnClickListener(this);
        mSavedRepsButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

        @Override
        public void onClick(View v) {
            if (v == mFindRepsButton) {
                Intent intent = new Intent(MainActivity.this, ListOfRepsActivity.class);
                startActivity(intent);
            }
            if (v == mAboutButton) {
                Intent aboutIntent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(aboutIntent);
            }
            if (v == mSavedRepsButton) {
                Intent intent = new Intent(MainActivity.this, SavedRepresentativeListActivity.class);
                startActivity(intent);
            }
        }

//        public void saveZipCodeToFirebase(String zipCode) {
//            mSearchedZipCodeReference.push().setValue(zipCode);
//        }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mSearchedZipCodeReference.removeEventListener(mSearchedZipCodeReferenceListener);
//    }
}
