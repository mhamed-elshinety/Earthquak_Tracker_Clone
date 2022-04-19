package com.example.earthquaketrackerclone.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.earthquaketrackerclone.R;
import com.example.earthquaketrackerclone.data.Assistant;
import com.example.earthquaketrackerclone.pojo.EarthquakeModel;
import com.example.earthquaketrackerclone.pojo.USGSModel;

import java.util.ArrayList;
import java.util.Date;

public class NearestEarthquakesAdapter extends RecyclerView.Adapter<NearestEarthquakesAdapter.ViewHolder> {

    private ArrayList<USGSModel> usgsModels;
    private Context context;

    public NearestEarthquakesAdapter(ArrayList<USGSModel> usgsModels, Context context){
        this.usgsModels = usgsModels;
        this.context = context;
    }

    @NonNull
    @Override
    public NearestEarthquakesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_featured_nearest_earthquake,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NearestEarthquakesAdapter.ViewHolder holder, int position) {
        EarthquakeModel currEarthquake = getCurrEarthquake(position);
        holder.earthquakeMagnitudeTv.setText(currEarthquake.getMag() + "");
        holder.earthquakeLocationTv.setText(currEarthquake.getPlace());
        holder.dateTv.setText(Assistant.getFormattedDate(new Date(currEarthquake.getTime()),"dd MMM, yyyy hh:mm:ss a"));
        setTitle(holder,position);
    }

    private void setTitle(ViewHolder holder, int position) {
        if (position == 0)
            holder.title.setText(context.getString(R.string.most_significant_earthquake_near_you));
        else
            holder.title.setText(context.getString(R.string.closest_to_you_today));
    }

    private EarthquakeModel getCurrEarthquake(int position) {
        return usgsModels.get(position).getFeatures().get(0).getProperties();
    }

    @Override
    public int getItemCount() {
        return usgsModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView earthquakeMagnitudeTv;
        TextView earthquakeLocationTv;
        TextView title;
        TextView dateTv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            earthquakeMagnitudeTv = itemView.findViewById(R.id.earthquake_magnitude_tv);
            earthquakeLocationTv = itemView.findViewById(R.id.location_tv);
            title = itemView.findViewById(R.id.title);
            dateTv = itemView.findViewById(R.id.date_tv);
        }
    }
}
