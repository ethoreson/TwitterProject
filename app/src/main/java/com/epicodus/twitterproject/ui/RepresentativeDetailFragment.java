package com.epicodus.twitterproject.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epicodus.twitterproject.R;
import com.epicodus.twitterproject.models.Representative;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RepresentativeDetailFragment extends Fragment implements View.OnClickListener {
    private static final int MAX_WIDTH = 400;
    private static final int MAX_HEIGHT = 300;

    @Bind(R.id.representativeNameTextView) TextView mNameLabel;
    @Bind(R.id.partyTextView) TextView mPartyLabel;

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

        mNameLabel.setText(mRepresentative.getName());
        mPartyLabel.setText(mRepresentative.getParty());

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mNameLabel) {
            Intent nameIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mRepresentative.getName()));
            startActivity(nameIntent);
        }
        if (v == mPartyLabel) {
            Intent partyIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mRepresentative.getParty()));
            startActivity(partyIntent);
        }
    }
};
