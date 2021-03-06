package com.epicodus.twitterproject.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.twitterproject.models.Representative;
import com.epicodus.twitterproject.ui.RepresentativeDetailFragment;

import java.util.ArrayList;

public class RepresentativePagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Representative> mRepresentatives;

    public RepresentativePagerAdapter(FragmentManager fm, ArrayList<Representative> representatives) {
        super(fm);
        mRepresentatives = representatives;
    }

    @Override
    public Fragment getItem(int position) {
        return RepresentativeDetailFragment.newInstance(mRepresentatives.get(position));
    }

    @Override
    public int getCount() {
        return mRepresentatives.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mRepresentatives.get(position).getName();
    }
}