package com.epicodus.twitterproject.ui;


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

/**
 * A simple {@link Fragment} subclass.
 */
public class RepresentativeDetailFragment extends Fragment {
        @Bind(R.id.representativeNameTextView) TextView mName;
        @Bind(R.id.partyTextView) TextView mParty;

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
    public View onCreateView(LayoutInflater infalter, ViewGroup container, Bundle savedInstanceState) {
        View view = infalter.inflate(R.layout.fragment_representative_detail, container, false);
        ButterKnife.bind(this, view);

        mName.setText(mRepresentative.getName());
        mParty.setText(mRepresentative.getParty());

        return view;
    }
};
