package com.epicodus.twitterproject.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.epicodus.twitterproject.models.Representative;
import com.epicodus.twitterproject.ui.RepresentativeDetailActivity;
import com.epicodus.twitterproject.util.ItemTouchHelperAdapter;
import com.epicodus.twitterproject.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collections;

public class FirebaseRepresentativeListAdapter extends FirebaseRecyclerAdapter<Representative, FirebaseRepresentativeViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;
    private ChildEventListener mChildEfventListener;
    private ArrayList<Representative> mRepresentatives = new ArrayList<>();

    public FirebaseRepresentativeListAdapter(Class<Representative> modelClass, int modelLayout, Class<FirebaseRepresentativeViewHolder> viewHolderClass, Query ref, OnStartDragListener onStartDragListener, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;
        mChildEfventListener = mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mRepresentatives.add(dataSnapshot.getValue(Representative.class));

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void populateViewHolder(final FirebaseRepresentativeViewHolder viewHolder, Representative model, int position) {
        viewHolder.bindRepresentative(model);

        viewHolder.mRepresentativeNameView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, RepresentativeDetailActivity.class);
                intent.putExtra("position", viewHolder.getAdapterPosition());
                intent.putExtra("representatives", Parcels.wrap(mRepresentatives));
                mContext.startActivity(intent);
            }
        });
    }


    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mRepresentatives, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        mRepresentatives.remove(position);
        getRef(position).removeValue();
    }

    private void setIndexInFirebase() {
        for (Representative representative : mRepresentatives) {
            int index = mRepresentatives.indexOf(representative);
            DatabaseReference ref = getRef(index);
            representative.setIndex(Integer.toString(index));
            ref.setValue(representative);
        }
    }

    @Override
    public void cleanup() {
        super.cleanup();
        setIndexInFirebase();
        mRef.removeEventListener(mChildEfventListener);
    }
}