package com.example.earthquaketrackerclone.adapters;

import android.annotation.SuppressLint;
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

public class BiggestEarthquakesAdapter extends RecyclerView.Adapter<BiggestEarthquakesAdapter.ViewHolder> {

    private ArrayList<USGSModel> usgsModels;
    private Context context;

    public BiggestEarthquakesAdapter(ArrayList<USGSModel> usgsModels, Context context) {
        this.usgsModels = usgsModels;
        this.context = context;
    }

    @NonNull
    @Override
    public BiggestEarthquakesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_featured_biggest_earthquake, parent, false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull BiggestEarthquakesAdapter.ViewHolder holder, int position) {
        EarthquakeModel currEarthquake = getCurrentEarthquake(position);
        holder.earthquakeMagnitudeTv.setText(currEarthquake.getMag() + "");
        holder.locationTv.setText(currEarthquake.getPlace());
        holder.dateTv.setText(Assistant.getFormattedDate(new Date(currEarthquake.getTime()), "dd MMM, yyyy hh:mm:ss a"));
        setDaysInWeek(holder, position);
    }

    private void setDaysInWeek(ViewHolder holder, int position) {
        if (position == 0)
            holder.days_in_weeks.setText(context.getString(R.string.today));
        else
            holder.days_in_weeks.setText(context.getString(R.string.this_week));
    }

    private EarthquakeModel getCurrentEarthquake(int position) {
        return usgsModels.get(position).getFeatures().get(0).getProperties();
    }

    @Override
    public int getItemCount() {
        return usgsModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView earthquakeMagnitudeTv;
        TextView locationTv;
        TextView days_in_weeks;
        TextView dateTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            earthquakeMagnitudeTv = itemView.findViewById(R.id.earthquake_magnitude_tv);
            locationTv = itemView.findViewById(R.id.location_tv);
            days_in_weeks = itemView.findViewById(R.id.title);
            dateTv = itemView.findViewById(R.id.date_tv);
        }
    }
}
