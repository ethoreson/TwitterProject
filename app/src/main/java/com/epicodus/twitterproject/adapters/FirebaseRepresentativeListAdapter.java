package com.epicodus.twitterproject.adapters;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.epicodus.twitterproject.models.Representative;
import com.epicodus.twitterproject.util.ItemTouchHelperAdapter;
import com.epicodus.twitterproject.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class FirebaseRepresentativeListAdapter extends FirebaseRecyclerAdapter<Representative, FirebaseRepresentativeViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;

    public FirebaseRepresentativeListAdapter(Class<Representative> modelClass, int modelLayout,
                                             Class<FirebaseRepresentativeViewHolder> viewHolderClass,
                                             Query ref, OnStartDragListener onStartDragListener, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;
    }

    @Override
    protected void populateViewHolder(FirebaseRepresentativeViewHolder viewHolder, Representative model, int position) {
        viewHolder.bindRepresentative(model);

        viewHolder.mRepresentativeNameTextView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        return false;
    }

    @Override
    public void onItemDismiss(int position) {

    }
}