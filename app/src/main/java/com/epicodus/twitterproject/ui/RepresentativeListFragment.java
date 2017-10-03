//package com.epicodus.twitterproject.ui;
//
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.preference.PreferenceManager;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.epicodus.twitterproject.R;
//import com.epicodus.twitterproject.adapters.RepresentativeListAdapter;
//import com.epicodus.twitterproject.models.Representative;
//import com.epicodus.twitterproject.services.GoogleService;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//import butterknife.Bind;
//import okhttp3.Call;
//import okhttp3.Callback;
//import okhttp3.Response;
//
///**
// * Created by emiliethoreson on 9/29/17.
// */
//
//public class RepresentativeListFragment {
//    @Bind(R.id.recyclerView)
//    RecyclerView mRecyclerView;
//
//    private RepresentativeListAdapter mAdapter;
//    public ArrayList<Representative> mRepresentatives = new ArrayList<>();
//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;
//    private String mRecentAddress;
//    public RepresentativeListFragment() {
//
//    }
//
//    private void getRepresentatives(String zipCode) {
//        final GoogleService googleService = new GoogleService();
//
//        googleService.findRepresentatives(zipCode, new Callback() {
//
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) {
//                mRepresentatives = googleService.processResults(response);
//
//                getActivity().runOnUiThread(new Runnable() {
//
//                    @Override
//                    public void run() {
//
//                        mAdapter = new RepresentativeListAdapter(getActivity(), mRepresentatives);
//                        mRecyclerView.setAdapter(mAdapter);
//                        RecyclerView.LayoutManager layoutManager =
//                                new LinearLayoutManager(getActivity());
//                        mRecyclerView.setLayoutManager(layoutManager);
//                        mRecyclerView.setHasFixedSize(true);
//                    }
//                });
//            }
//        });
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
//        mEditor = mSharedPreferences.edit();
//
//        // Instructs fragment to include menu options:
//        setHasOptionsMenu(true);
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_representative_detail, container, false);
//    }
//}
