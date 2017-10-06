package com.epicodus.twitterproject.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.epicodus.twitterproject.R;
import com.epicodus.twitterproject.adapters.RepresentativePagerAdapter;
import com.epicodus.twitterproject.models.Representative;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RepresentativeDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager)
    ViewPager mViewPager;
    private RepresentativePagerAdapter adapterViewPager;
    ArrayList<Representative> mRepresentatives = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_representative_detail);
        ButterKnife.bind(this);

        mRepresentatives = Parcels.unwrap(getIntent().getParcelableExtra("representatives"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new RepresentativePagerAdapter(getSupportFragmentManager(), mRepresentatives);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
