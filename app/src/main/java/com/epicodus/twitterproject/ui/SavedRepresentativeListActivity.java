package com.epicodus.twitterproject.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.epicodus.twitterproject.Constants;
import com.epicodus.twitterproject.R;
import com.epicodus.twitterproject.adapters.FirebaseRepresentativeListAdapter;
import com.epicodus.twitterproject.adapters.FirebaseRepresentativeViewHolder;
import com.epicodus.twitterproject.models.Representative;
import com.epicodus.twitterproject.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedRepresentativeListActivity extends AppCompatActivity implements OnStartDragListener {
    private DatabaseReference mRepresentativeReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_of_reps);
        ButterKnife.bind(this);

        //mRepresentativeReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_REPRESENTATIVES);
        setUpFirebaseAdapter();
    }
    private void setUpFirebaseAdapter() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mRepresentativeReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_REPRESENTATIVES)
                .child(uid);
        mFirebaseAdapter = new FirebaseRepresentativeListAdapter(Representative.class, R.layout.representative_list_item, FirebaseRepresentativeViewHolder.class, mRepresentativeReference, this, this);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);

        //ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        //mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
