package com.example.earthquaketrackerclone.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.earthquaketrackerclone.R;
import com.example.earthquaketrackerclone.data.Constants;
import com.example.earthquaketrackerclone.pojo.EarthquakeModel;
import com.example.earthquaketrackerclone.pojo.USGSModel;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapTabFragment extends Fragment implements OnMapReadyCallback, MapTabFragmentView {

    private MapView mapView;
    private GoogleMap googleMap;

    private MapTabFragmentPresenter presenter;

    //New Instance method "This function is very important to get any data from activity"
    public static MapTabFragment newInstance(ArrayList<EarthquakeModel> earthquakes) {
        MapTabFragment fragment = new MapTabFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constants.KEY_EARTHQUAKES, earthquakes);
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
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);
        defineViews(rootView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        MapsInitializer.initialize(getActivity().getApplicationContext());
        mapView.onResume();
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    //define fragment fields override from PagerFragmentParent
    public void defineFields() {
        presenter = new MapTabFragmentPresenter(this);
    }

    private void initializeMap(Bundle savedInstanceState) {

    }

    public void defineViews(View view) {
        mapView = view.findViewById(R.id.map_view__map_fragment);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.googleMap = googleMap;
        presenter.getMapEarthquakes();
    }

    @Override
    public void getMapEarthquakes(USGSModel usgsModel) {
        addMarkers(usgsModel);
    }

    public void addMarkers(USGSModel usgsModel) {
        for (int i = 0; i < usgsModel.getFeatures().size(); i++) {
            float[] coordinates = usgsModel.getFeatures().get(i).getGeometry().getCoordinates();
            googleMap.addMarker(new MarkerOptions().position(new LatLng(coordinates[1], coordinates[0]))
                    .title(usgsModel.getFeatures().get(i).getProperties().getPlace()));
        }
    }

}

