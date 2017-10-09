package com.epicodus.twitterproject.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.epicodus.twitterproject.Constants;
import com.epicodus.twitterproject.R;
import com.epicodus.twitterproject.adapters.FirebaseRepresentativeViewHolder;
import com.epicodus.twitterproject.models.Representative;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedRepresentativeListActivity extends AppCompatActivity {
    private DatabaseReference mRepresentativeReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;
    //private ItemTouchHelper mItemTouchHelper;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_of_reps);
        ButterKnife.bind(this);

        //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        //String uid = user.getUid();

        mRepresentativeReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_REPRESENTATIVES);
        setUpFirebaseAdapter();
    }
    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Representative, FirebaseRepresentativeViewHolder>
                (Representative.class, R.layout.representative_list_item_drag, FirebaseRepresentativeViewHolder.class, mRepresentativeReference) {

            @Override
            protected void populateViewHolder(FirebaseRepresentativeViewHolder viewHolder,
                                              Representative model, int position) {
                viewHolder.bindRepresentative(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}

//package com.epicodus.twitterproject.ui;
//
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//
//import com.epicodus.twitterproject.Constants;
//import com.epicodus.twitterproject.R;
//import com.epicodus.twitterproject.adapters.FirebaseRepresentativeViewHolder;
//import com.epicodus.twitterproject.models.Representative;
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//
//import butterknife.Bind;
//import butterknife.ButterKnife;
//
//public class SavedRepresentativeListActivity extends AppCompatActivity {
//    private DatabaseReference mRepresentativeReference;
//    private FirebaseRecyclerAdapter mFirebaseAdapter;
//
//    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.activity_list_of_reps);
//        ButterKnife.bind(this);
//
//        mRepresentativeReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_REPRESENTATIVES);
//        setUpFirebaseAdapter();
//    }
//    private void setUpFirebaseAdapter() {
//        mFirebaseAdapter = new FirebaseRecyclerAdapter<Representative, FirebaseRepresentativeViewHolder>
//                (Representative.class, R.layout.representative_list_item_drag, FirebaseRepresentativeViewHolder.class, mRepresentativeReference) {
//
//            @Override
//            protected void populateViewHolder(FirebaseRepresentativeViewHolder viewHolder,
//                                              Representative model, int position) {
//                viewHolder.bindRepresentative(model);
//            }
//        };
//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        mRecyclerView.setAdapter(mFirebaseAdapter);
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mFirebaseAdapter.cleanup();
//    }
//}
