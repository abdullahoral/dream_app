package com.furkansalihege.android.dreamapp;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddDreamActivity extends AppCompatActivity {
    private static final String TAG = "AddDreamActivity";

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference dreamBookRef = db.getReference("Dreambook");

    @BindView(R.id.toolbar_add_dream) Toolbar toolbar;
    @BindView(R.id.ck_wolf) CheckBox ckWolf;
    @BindView(R.id.ck_dog) CheckBox ckDog;
    @BindView(R.id.ck_other) CheckBox ckOther;
    @BindView(R.id.ck_nothing) CheckBox ckNothing;
    @BindView(R.id.bt_save) Button btSave;

    DatabaseReference wolfRef = dreamBookRef.child("Wolf");
    DatabaseReference dogRef = dreamBookRef.child("Dog");
    DatabaseReference otherRef = dreamBookRef.child("Other");
    DatabaseReference nothingRef = dreamBookRef.child("Nothing");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dream);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle(R.string.add_dreams);

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ckWolf.isChecked()){
                    rateClicked(wolfRef);
                }
                if (ckDog.isChecked()){
                    rateClicked(dogRef);
                }
                if (ckOther.isChecked()){
                    rateClicked(otherRef);
                }
                if (ckNothing.isChecked()){
                    rateClicked(nothingRef);
                }
                Toast.makeText(AddDreamActivity.this, "Saved!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void rateClicked(DatabaseReference dreambookRef) {
        dreambookRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                Dream d = mutableData.getValue(Dream.class);
                if (d == null) {
                    return Transaction.success(mutableData);
                }
                d.rate = d.rate + 1;
                // Set value and report transaction success
                mutableData.setValue(d);
                return Transaction.success(mutableData);
            }
            @Override
            public void onComplete(DatabaseError databaseError, boolean b,
                                   DataSnapshot dataSnapshot) {
                // Transaction completed
                Log.d(TAG, "dreamTransaction:onComplete:" + databaseError);
            }
        });
    }
}
