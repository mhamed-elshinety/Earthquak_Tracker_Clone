package com.example.earthquaketrackerclone.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.earthquaketrackerclone.R;
import com.example.earthquaketrackerclone.adapters.EarthquakeHorAdapter;
import com.example.earthquaketrackerclone.data.Assistant;
import com.example.earthquaketrackerclone.data.ImageDownloader;
import com.example.earthquaketrackerclone.listeners.OnImageDownloadListener;
import com.example.earthquaketrackerclone.pojo.EarthquakeModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class LatestTabFragment extends Fragment implements LatestTabFragmentView, OnImageDownloadListener {

    private RecyclerView nearYouRecV;
    private RecyclerView mostRecentRecV;

    private TextView magTxv;
    private TextView disTxv;
    private TextView townTxv;
    private TextView dateTimeTxv;
    private TextView durDaysTxv;
    private ImageView flagImv;

    private ArrayList<EarthquakeModel> nearByEarthquakes;
    private LatestTabFragmentPresenter presenter;

    //New Instance method "This function is very important to get any data from activity"
    public static LatestTabFragment newInstance() {
        LatestTabFragment fragment = new LatestTabFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        defineFields();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_latest, container, false);  //inflating latest fragment layout
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        defineViews();

        //starting loading data
        presenter.getNearYouEarthquakes();
        presenter.getMostRecentEarthquakes();
        presenter.getSignificantRecentlyEarthquake();

    }


    @Override
    public void getNearYouEarthquakes(ArrayList<EarthquakeModel> earthquakes) {
        nearYouRecV.setAdapter(new EarthquakeHorAdapter(getContext(), earthquakes, LinearLayout.HORIZONTAL));
        nearYouRecV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    @Override
    public void getMostRecentEarthquakes(ArrayList<EarthquakeModel> earthquakes) {
        mostRecentRecV.setAdapter(new EarthquakeHorAdapter(getContext(), earthquakes,LinearLayout.HORIZONTAL));
        mostRecentRecV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));    }

    @Override
    public void getSignificantRecentlyEarthquake(EarthquakeModel earthquakeModel) {
        setMag(earthquakeModel.getMag());
        setPlace(earthquakeModel.getPlace());
        setTime(earthquakeModel.getTime());
        setDurDays(earthquakeModel.getTime());
        ImageDownloader downloader = new ImageDownloader(this);
        String [] stringSplits = earthquakeModel.getPlace().split(" ");
        downloader.execute("https://countryflagsapi.com/png/" + stringSplits[stringSplits.length-1]);
    }


    //define fragment fields "override from PagerFragmentParent"
    public void defineFields() {
        this.presenter = new LatestTabFragmentPresenter(this);
    }

    //define fragment views fields "override from PagerFragmentParent"
    public void defineViews() {
        nearYouRecV = getActivity().findViewById(R.id.new_you_recv);
        mostRecentRecV = getActivity().findViewById(R.id.most_recent_recv);
        magTxv = getActivity().findViewById(R.id.mag_txv);
        disTxv = getActivity().findViewById(R.id.dis_txv);
        townTxv = getActivity().findViewById(R.id.town_txv);
        dateTimeTxv = getActivity().findViewById(R.id.date_time_txv);
        durDaysTxv = getActivity().findViewById(R.id.dur_days_txv);
        flagImv = getActivity().findViewById(R.id.flag_iv);
    }

    //setting time
    private void setTime(long time) {
        dateTimeTxv.setText(new SimpleDateFormat("MMM dd, yyyy hh:mm:ss").format(new Date(time)));
    }

    //splitting place into 2 splits to set them in dis and town TVs
    private void setPlace(String place) {
        String[] placeSplits = place.split("of", 2);
        if (placeSplits.length == 2) {
            disTxv.setText(placeSplits[0]);
            townTxv.setText(placeSplits[1]);
        } else {
            disTxv.setText(getContext().getResources().getString(R.string.near_to));
            townTxv.setText(place);
        }
    }

    //setting magnitude TV
    private void setMag(double magnitude) {
        magTxv.setText(magnitude + "");
    }

    private void setDurDays(long time){
        long days = Assistant.subtractDays(new Date(time));
        if(days==0)
            durDaysTxv.setText(getResources().getString(R.string.today));
        else
            durDaysTxv.setText(days + " Days ago");


    }

    @Override
    public void onImageDownload(Bitmap bitmap) {
        flagImv.setImageBitmap(bitmap);
    }
}