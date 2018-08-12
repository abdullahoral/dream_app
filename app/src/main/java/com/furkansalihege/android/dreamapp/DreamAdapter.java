package com.furkansalihege.android.dreamapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DreamAdapter extends FirebaseRecyclerAdapter<Dream, DreamAdapter.DreamHolder> {


    public DreamAdapter(@NonNull FirebaseRecyclerOptions<Dream> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull DreamHolder holder, int position, @NonNull Dream model) {
        holder.tvTitle.setText(model.getTitle());
        holder.tvRate.setText(String.valueOf(model.getRate()));
    }

    @NonNull
    @Override
    public DreamHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_main_item, viewGroup, false );
        return new DreamHolder(v);
    }

    class DreamHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_title) TextView tvTitle;
        @BindView(R.id.tv_rate) TextView tvRate;

        public DreamHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }
}
