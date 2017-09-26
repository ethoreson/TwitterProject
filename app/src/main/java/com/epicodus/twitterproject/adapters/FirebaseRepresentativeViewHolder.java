package com.epicodus.twitterproject.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.epicodus.twitterproject.Constants;
import com.epicodus.twitterproject.R;
import com.epicodus.twitterproject.models.Representative;
import com.epicodus.twitterproject.ui.RepresentativeDetailActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseRepresentativeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    View mView;
    Context mContext;

    public FirebaseRepresentativeViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindRepresentative(Representative representative) {
        TextView nameTextView = (TextView) mView.findViewById(R.id.representativeNameTextView);
        TextView partyTextView = (TextView) mView.findViewById(R.id.partyTextView);
        TextView phoneTextView = (TextView) mView.findViewById(R.id.phoneTextView);
        //TextView channelsTextView = (TextView) mView.findViewById(R.id.channelsTextView);

        nameTextView.setText(representative.getName());
        partyTextView.setText("Party: " + representative.getParty());
        phoneTextView.setText("Phone: " + representative.getPhone());
        //channelsTextView.setText("Social Media: " + representative.getChannels());
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Representative> representatives = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_REPRESENTATIVES);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    representatives.add(snapshot.getValue(Representative.class));
                }
                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, RepresentativeDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("representatives", Parcels.wrap(representatives));

                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

