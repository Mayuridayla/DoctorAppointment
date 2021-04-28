package com.test.myapplication.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.test.myapplication.Onclick;
import com.test.myapplication.R;
import com.test.myapplication.model.Datamodel;
import com.test.myapplication.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder>  implements Filterable {

    private List<Datamodel> resultList;
    private List<Datamodel> resultListFiltered;

 //   private AdapterView.OnItemClickListener mOnItemClickListener;
    Onclick itemClick;



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, specialist, location;
        public  LinearLayout linearLayout;
        public MyViewHolder(View view) {
            super(view);
           name = (TextView) view.findViewById(R.id.txt_name);
            specialist = (TextView) view.findViewById(R.id.txt_specialist);
            location = (TextView) view.findViewById(R.id.txt_location);
            linearLayout = (LinearLayout) view.findViewById(R.id.ll);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClick.onItemClick(v, getAdapterPosition(), 12);
                }
            });

        }
    }



    public UserAdapter(List<Datamodel> resultList, Onclick itemClick) {
        Log.e("resultListSize....",""+resultList.size());

        this.itemClick = itemClick;
        this.resultList = resultList;
        this.resultListFiltered = resultList;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_recycler, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Datamodel datamodel = resultListFiltered.get(position);
        Log.e("getName....",""+datamodel.getTitle());
        holder.name.setText(datamodel.getTitle());
//        holder.specialist.setText(datamodel.getSpecailist());
//        holder.location.setText(datamodel.getLocation());

    }

    @Override
    public int getItemCount() {
        return resultListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    resultListFiltered = resultList;
                } else {
                    List<Datamodel> filteredList = new ArrayList<>();
                    for (Datamodel row : resultList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name match
                        if (row.getTitle().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    resultListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = resultListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                resultListFiltered = (ArrayList<Datamodel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }



}