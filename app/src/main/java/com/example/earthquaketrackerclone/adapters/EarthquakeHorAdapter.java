package com.example.earthquaketrackerclone.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.earthquaketrackerclone.R;
import com.example.earthquaketrackerclone.data.Assistant;
import com.example.earthquaketrackerclone.pojo.EarthquakeModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeHorAdapter extends RecyclerView.Adapter<EarthquakeHorAdapter.ViewHolder> {

    private Context context;
    private ArrayList<EarthquakeModel> earthquakes;

    public EarthquakeHorAdapter (Context context){
        this(context,null);
    }

    public EarthquakeHorAdapter(Context context, ArrayList<EarthquakeModel> earthquakes) {
        setEarthquakes(earthquakes);
        setContext(context);
    }

    private void setContext(Context context) {
        this.context = context;
    }

    private void setEarthquakes(ArrayList<EarthquakeModel> earthquakes) {
        this.earthquakes = earthquakes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_earthquake_hor_rec,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setValuesInViews(holder,earthquakes.get(position));
    }

    //this method used to setting view values from EarthquakeModel object
    private void setValuesInViews(ViewHolder holder, EarthquakeModel earthquakeModel) {
        setMag(holder, earthquakeModel.getMag());
        setPlace(holder,earthquakeModel.getPlace());
        setTime(holder,earthquakeModel.getTime());
        setDurDays(holder,earthquakeModel.getTime());
    }


    //setting duration
    private void setDurDays(ViewHolder holder, long time) {
        long days = Assistant.subtractDays(new Date(time));

        if(days==0)
            holder.durDaysTxv.setText(context.getResources().getString(R.string.today));
        else
            holder.durDaysTxv.setText(days + " " + context.getResources().getString(R.string.days_ago));
    }

    //setting time
    private void setTime(ViewHolder holder, long time) {
        holder.dateTimeTxv.setText(new SimpleDateFormat("MMM dd, yyyy hh:mm a").format(new Date(time)));
    }

    //splitting place into 2 splits to set them in dis and town TVs
    private void setPlace(ViewHolder holder, String place) {
        if(place!=null) {
            String[] placeSplits = place.split("of", 2);
            if (placeSplits.length == 2) {
                holder.disTxv.setText(placeSplits[0]);
                holder.townTxv.setText(placeSplits[1]);
            } else {
                holder.disTxv.setText(context.getResources().getString(R.string.near_to));
                holder.townTxv.setText(place);
            }
        }
    }

    //setting magnitude TV and adjusting colors depends on magnitude
    private void setMag(ViewHolder holder, double magnitude) {
        if(magnitude>0 && magnitude<=2)
            holder.magTxv.setTextColor(context.getResources().getColor(R.color.fair_klein_blue));
        else if(magnitude>2 && magnitude<=4)
            holder.magTxv.setTextColor(context.getResources().getColor(R.color.low_teal));
        else if(magnitude>4 && magnitude<=5)
            holder.magTxv.setTextColor(context.getResources().getColor(R.color.moderate_raw_umber));
        else if(magnitude>5)
            holder.magTxv.setTextColor(context.getResources().getColor(R.color.high_rust));
        holder.magTxv.setText(magnitude+"");
    }

    private void setFlagBitmap(ViewHolder holder, Bitmap countryFlag) {
        if(countryFlag!=null)
            holder.flagImv.setImageBitmap(countryFlag);
        else
            holder.flagImv.setImageResource(R.mipmap.united_nations);
    }


    @Override
    public int getItemCount() {
        return earthquakes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView magTxv;
        TextView disTxv;
        TextView townTxv;
        TextView dateTimeTxv;
        TextView durDaysTxv;
        ImageView flagImv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            magTxv = itemView.findViewById(R.id.mag_txv);
            disTxv = itemView.findViewById(R.id.dis_txv);
            townTxv = itemView.findViewById(R.id.town_txv);
            dateTimeTxv = itemView.findViewById(R.id.date_time_txv);
            durDaysTxv = itemView.findViewById(R.id.dur_days_txv);
            flagImv = itemView.findViewById(R.id.flag_iv);
        }
    }

}
