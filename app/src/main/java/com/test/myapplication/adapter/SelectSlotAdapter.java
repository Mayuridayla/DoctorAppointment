package com.test.myapplication.adapter;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.myapplication.Onclick;
import com.test.myapplication.R;
import com.test.myapplication.model.Datamodel;
import com.test.myapplication.model.User;

import java.util.ArrayList;
import java.util.List;

public class SelectSlotAdapter extends RecyclerView.Adapter<SelectSlotAdapter.MyViewHolder> {

    private List<Datamodel> resultList;
    private List<Datamodel> resultListFiltered;
    private LinearLayout linearLayout;
    Onclick itemClick;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, specialist, join;
        private ProgressBar progress;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.txt_name);
            specialist = (TextView) view.findViewById(R.id.txt_specialist);
            linearLayout = (LinearLayout) view.findViewById(R.id.ll);
            join = (TextView) view.findViewById(R.id.txt_join);
            progress = (ProgressBar) view.findViewById(R.id.progress);
            join.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClick.onItemClick(v, getAdapterPosition(), 12);
                }
            });
        }
    }


    public SelectSlotAdapter(List<Datamodel> resultList, Onclick itemClick) {

        this.itemClick = itemClick;
        this.resultList = resultList;
        this.resultListFiltered = resultList;
    }


    @NonNull
    @Override
    public SelectSlotAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.slot_data_list, parent, false);

        return new SelectSlotAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SelectSlotAdapter.MyViewHolder holder, int position) {
        Datamodel datamodel = resultListFiltered.get(position);
        Log.e("getName....", "" + datamodel.getTitle());
        holder.name.setText(datamodel.getTitle());
        holder.specialist.setText(datamodel.getSpecailist());

        if (datamodel.getSpecailist() == "Available") {
            holder.progress.setProgress(20);
            holder.progress.getProgressDrawable().setColorFilter(
                    Color.GREEN, android.graphics.PorterDuff.Mode.SRC_IN);

        } else if (datamodel.getSpecailist() == "Almost Full") {
            holder.progress.setProgress(80);
            holder.progress.getProgressDrawable().setColorFilter(
                    Color.RED, android.graphics.PorterDuff.Mode.SRC_IN);
        } else {
            holder.progress.setProgress(50);
            holder.progress.getProgressDrawable().setColorFilter(
                    Color.YELLOW, android.graphics.PorterDuff.Mode.SRC_IN);
        }

    }

    @Override
    public int getItemCount() {
        return resultListFiltered.size();
    }


}