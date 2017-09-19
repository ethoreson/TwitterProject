package com.epicodus.twitterproject.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epicodus.twitterproject.R;
import com.epicodus.twitterproject.models.Representative;
import com.epicodus.twitterproject.ui.RepresentativeDetailActivity;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RepresentativeListAdapter extends RecyclerView.Adapter<RepresentativeListAdapter.RepresentativeViewHolder> {
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
    public void onBindViewHolder(RepresentativeListAdapter.RepresentativeViewHolder holder, int position) {
        holder.bindRepresentative(mRepresentatives.get(position));
    }

    @Override
    public int getItemCount() {
        return mRepresentatives.size();
    }

    public class RepresentativeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.representativeNameTextView) TextView mNameTextView;
        @Bind(R.id.partyTextView) TextView mPartyTextView;
        private Context mContext;

        public RepresentativeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, RepresentativeDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("representatives", Parcels.wrap(mRepresentatives));
            mContext.startActivity(intent);
        }

        public void bindRepresentative(Representative representative) {
            mNameTextView.setText(representative.getName());
            mPartyTextView.setText(representative.getParty());
        }
    }
}
