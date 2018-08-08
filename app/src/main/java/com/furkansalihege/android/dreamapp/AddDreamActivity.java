package com.furkansalihege.android.dreamapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Source;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddDreamActivity extends AppCompatActivity {
    private static final String TAG = "AddDreamActivity";

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @BindView(R.id.toolbar_add_dream) Toolbar toolbar;
    @BindView(R.id.ck_wolf) CheckBox ckWolf;
    @BindView(R.id.ck_dog) CheckBox ckDog;
    @BindView(R.id.ck_other) CheckBox ckOther;
    @BindView(R.id.ck_nothing) CheckBox ckNothing;
    @BindView(R.id.bt_save) Button btSave;
    int wolfRate;
    int dogRate;
    int otherRate;
    int nothingRate;
    Source source = Source.SERVER;
    DocumentReference wolfRef = db.collection("Dreambook").document("Wolf");
    DocumentReference dogRef = db.collection("Dreambook").document("Dog");
    DocumentReference otherRef = db.collection("Dreambook").document("Other");
    DocumentReference nothingRef = db.collection("Dreambook").document("Nothing");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dream);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle(R.string.add_dreams);
        getAllRates();

        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ckWolf.isChecked()){
                    wolfRef.update("rate", (wolfRate + 1) )
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "DocumentSnapshot successfully updated!");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error updating document", e);
                                }
                            });
                }

                if (ckDog.isChecked()){
                    dogRef.update("rate", (dogRate + 1) )
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "DocumentSnapshot successfully updated!");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error updating document", e);
                                }
                            });
                }

                if (ckOther.isChecked()){
                    otherRef.update("rate", (otherRate + 1) )
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "DocumentSnapshot successfully updated!");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error updating document", e);
                                }
                            });
                }

                if (ckNothing.isChecked()){
                    nothingRef.update("rate", (nothingRate + 1) )
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "DocumentSnapshot successfully updated!");
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error updating document", e);
                                }
                            });
                }

                Toast.makeText(AddDreamActivity.this, "Saved!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void getWolfRate() {
        wolfRef.get(source).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Dream wolf = documentSnapshot.toObject(Dream.class);
                wolfRate = wolf.getRate();
            }
        });
    }

    public void getDogRate(){
        dogRef.get(source).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Dream dog = documentSnapshot.toObject(Dream.class);
                dogRate = dog.getRate();
            }
        });
    }
    public void getOtherRate(){
        otherRef.get(source).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Dream other = documentSnapshot.toObject(Dream.class);
                otherRate = other.getRate();
            }
        });
    }
    public void getNothingRate(){
        nothingRef.get(source).addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Dream nothing = documentSnapshot.toObject(Dream.class);
                nothingRate = nothing.getRate();
            }
        });
    }

    public void getAllRates () {
        getWolfRate();
        getDogRate();
        getOtherRate();
        getNothingRate();
    }
}
