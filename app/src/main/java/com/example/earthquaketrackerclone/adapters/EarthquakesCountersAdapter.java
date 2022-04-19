package com.example.earthquaketrackerclone.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.earthquaketrackerclone.R;

import java.util.ArrayList;

public class EarthquakesCountersAdapter extends RecyclerView.Adapter<EarthquakesCountersAdapter.ViewHolder> {

    private ArrayList<Integer> counters;
    private Context context;

    public EarthquakesCountersAdapter(ArrayList<Integer> counters, Context context){
        this.counters = counters;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_featured_earthquakes_counter,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.noEarthquakes.setText(counters.get(position) + "");
        setComment(holder,position);
    }

    private void setComment(ViewHolder holder, int position) {
        switch (position){
            case 0:
                holder.commentTv.setText(context.getString(R.string.earthquakes_in_past_24_hours));
                break;
            case 1:
                holder.commentTv.setText(context.getString(R.string.earthquakes_in_past_7_days));
                break;
            default:
                holder.commentTv.setText(context.getString(R.string.earthquakes_in_past_30_days));
                break;
              }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView noEarthquakes;
        TextView commentTv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            noEarthquakes = itemView.findViewById(R.id.earthquake_magnitude_tv);
            commentTv = itemView.findViewById(R.id.comment_tv);
        }
    }
}
