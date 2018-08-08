package com.furkansalihege.android.dreamapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference dreambookRef =  db.collection("Dreambook");

    private DreamAdapter dreamAdapter;

    @BindView(R.id.toolbar_main) Toolbar toolbar;
    @BindView(R.id.rv_main) RecyclerView recyclerView;
    @BindView(R.id.fab_add_dream) FloatingActionButton fabAddDream;
    @BindView(R.id.adView) AdView mAdView;


    private static final String SAMPLE_ADMOB_APP_ID = "ca-app-pub-3940256099942544~3347511713";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setUpRecyclerView();
        MobileAds.initialize(this, SAMPLE_ADMOB_APP_ID);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        fabAddDream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddDreamActivity.class));
            }
        });
    }

    private void setUpRecyclerView() {
        Query query = dreambookRef.orderBy("rate", Query.Direction.DESCENDING );

        FirestoreRecyclerOptions<Dream> options = new FirestoreRecyclerOptions.Builder<Dream>()
                .setQuery(query, Dream.class)
                .build();

        dreamAdapter = new DreamAdapter(options);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(dreamAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        dreamAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        dreamAdapter.stopListening();
    }
}
