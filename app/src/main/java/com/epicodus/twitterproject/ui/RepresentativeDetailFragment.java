package com.epicodus.twitterproject.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.twitterproject.Constants;

import com.epicodus.twitterproject.models.Representative;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RepresentativeDetailFragment extends Fragment implements View.OnClickListener {
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;

    @Bind(R.id.representativeNameTextView) TextView mNameLabel;
    @Bind(R.id.partyTextView) TextView mPartyLabel;
    @Bind(R.id.phoneTextView) TextView mPhoneLabel;
    @Bind(R.id.channelTextView) TextView mChannelsLabel;
    @Bind(R.id.photoTextView) ImageView mPhotoLabel;
    @Bind(R.id.saveRepresentativeButton) TextView mSaveRepresentativeButton;

        private Representative mRepresentative;

        public static RepresentativeDetailFragment newInstance(Representative representative) {
        RepresentativeDetailFragment representativeDetailFragment = new RepresentativeDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("representative", Parcels.wrap(representative));
        representativeDetailFragment.setArguments(args);
        return representativeDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRepresentative = Parcels.unwrap(getArguments().getParcelable("representative"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_representative_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.with(view.getContext())
                .load(mRepresentative.getPhotoUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(mPhotoLabel);

        mNameLabel.setText(mRepresentative.getName());
        mPartyLabel.setText(mRepresentative.getParty());
        mPhoneLabel.setText(android.text.TextUtils.join("Phone number: ", mRepresentative.getPhones()));
        mChannelsLabel.setText(android.text.TextUtils.join("Contact: ", mRepresentative.getChannels()));
        //mPhotolabel.setText(mRepresentative.getPhotoUrl());

        mNameLabel.setOnClickListener(this);
        mPartyLabel.setOnClickListener(this);
        mPhoneLabel.setOnClickListener(this);
        mChannelsLabel.setOnClickListener(this);
        //mPhotoLLabel.setOnClickListener(this);

        mSaveRepresentativeButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mNameLabel) {
            Intent nameIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mRepresentative.getName()));
            startActivity(nameIntent);
        }
        if (v == mPartyLabel) {
            Intent phoneIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("party:" + mRepresentative.getParty()));
            startActivity(phoneIntent);
        }
        if (v == mPhoneLabel) {
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel:" + mRepresentative.getPhones()));
            startActivity(phoneIntent);
        }
        if (v == mSaveRepresentativeButton) {
            DatabaseReference representativeRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_REPRESENTATIVES);
            representativeRef.push().setValue(mRepresentative);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }
};
