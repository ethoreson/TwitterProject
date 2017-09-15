package com.epicodus.twitterproject.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epicodus.twitterproject.R;
import com.epicodus.twitterproject.models.Representative;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public abstract class RepresentativeListAdapter extends RecyclerView.Adapter<RepresentativeListAdapter.RepresentativeViewHolder> {
    private ArrayList<Representative> mRepresentatives = new ArrayList<>();
    private Context mContext;

    public RepresentativeListAdapter(Context context, ArrayList<Representative> representatives) {
        mContext = context;
        mRepresentatives = representatives;
    }

    @Override
    public RepresentativeListAdapter.RepresentativeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.representative_list_item, parent, false);
        RepresentativeViewHolder viewHolder = new RepresentativeViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return mRepresentatives.size();
    }

    public class RepresentativeViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.representativeNameTextView) TextView mNameTextView;
        @Bind(R.id.partyTextView) TextView mPartyTextView;
        private Context mContext;

        public RepresentativeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindRepresentative(Representative representative) {
            mNameTextView.setText(representative.getName());
            mPartyTextView.setText(representative.getParty());
        }
    }
}
