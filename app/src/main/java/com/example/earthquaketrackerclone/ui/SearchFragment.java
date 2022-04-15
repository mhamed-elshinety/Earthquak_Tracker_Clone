package com.example.earthquaketrackerclone.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.earthquaketrackerclone.R;
import com.example.earthquaketrackerclone.data.Constants;
import com.example.earthquaketrackerclone.pojo.EarthquakeModel;
import com.example.earthquaketrackerclone.pojo.USGSModel;
import java.util.ArrayList;
import java.util.Locale;

public class SearchFragment extends Fragment implements SearchFragmentView, View.OnClickListener {

    private SearchFragmentPresenter presenter;
    private AutoCompleteTextView minimumMagnitudeAutocompleteTv;
    private AutoCompleteTextView defaultDistanceAutocompleteTv;
    private AutoCompleteTextView sortingAutocompleteTv;
    private TextView searchTv;
    private ProgressBar progressIndicator;

    private NavController navController;

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
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
        return inflater.inflate(R.layout.fragment_search, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        defineViews(view);
        searchTv.setOnClickListener(this);
    }

    private Bundle getQueryBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.START_TIME, "2022-01-01");
        bundle.putString(Constants.END_TIME, "2022-04-06");
        bundle.putFloat(Constants.MIN_LATITUDE, -90);
        bundle.putFloat(Constants.MIN_LONGITUDE, 0);
        bundle.putFloat(Constants.MAX_LATITUDE, 90);
        bundle.putFloat(Constants.MAX_LONGITUDE, 180);
        bundle.putInt(Constants.LIMIT, 120);
        bundle.putFloat(Constants.MIN_MAGNITUDE, Integer.parseInt(minimumMagnitudeAutocompleteTv.getText().toString()));
        bundle.putString(Constants.ORDER_BY, sortingAutocompleteTv.getText().toString().toLowerCase(Locale.ROOT));
        return bundle;
    }

    //define fragment fields override from PagerFragmentParent
    public void defineFields() {
        presenter = new SearchFragmentPresenter(this);
    }

    public void defineViews(View view) {
        navController = Navigation.findNavController(view);

        searchTv = requireActivity().findViewById(R.id.search_tv);
        progressIndicator = requireActivity().findViewById(R.id.progress_indicator);

        minimumMagnitudeAutocompleteTv = requireActivity().findViewById(R.id.minimum_magnitude_auto_tv);
        minimumMagnitudeAutocompleteTv.setAdapter(new ArrayAdapter<>
                (requireContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.magnitudes_list)));

        defaultDistanceAutocompleteTv = requireActivity().findViewById(R.id.default_distance_auto_tv);
        defaultDistanceAutocompleteTv.setAdapter(new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.default_distance_list)));


        sortingAutocompleteTv = requireActivity().findViewById(R.id.order_by_auto_tv);
        sortingAutocompleteTv.setAdapter(new ArrayAdapter<>
                (requireContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.sort_by_list)));
    }

    @Override
    public void onGetEarthquakes(USGSModel usgsModel) {
        ArrayList<USGSModel.Feature> features = usgsModel.getFeatures();
        progressIndicator.setVisibility(View.INVISIBLE);
        if (features.size() > 0) {
            Log.d(Constants.LOG_KEY_MY_APP, "onGetEarthquakes: " +
                    features.get(0).getProperties().toString());
            Bundle args = new Bundle();
            args.putSerializable(Constants.KEY_EARTHQUAKES,getEarthquakes(usgsModel));
            navController
                    .navigate(R.id.action_searchFragment_to_searchResultFragment2,args);

        }
        else
            Log.d(Constants.LOG_KEY_MY_APP, "onGetEarthquakes: " +
                    features.size());
    }

    private ArrayList<EarthquakeModel> getEarthquakes(USGSModel usgsModel) {
        ArrayList<EarthquakeModel> earthquakes = new ArrayList<>();
        for(int i=0; i<usgsModel.getFeatures().size(); i++){
            earthquakes.add(usgsModel.getFeatures().get(i).getProperties());
        }
        return earthquakes;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.search_tv) {
            if (isDataCompleted()) {
                presenter.getEarthquakes(getQueryBundle());
                progressIndicator.setVisibility(View.VISIBLE);
            } else
                Toast.makeText(getContext(), getResources().getString(R.string.complete_date), Toast.LENGTH_LONG).show();
        }
    }

    private boolean isDataCompleted() {
        return minimumMagnitudeAutocompleteTv.getText().toString().length() > 0 &&
                sortingAutocompleteTv.getText().toString().length() > 0 &&
                defaultDistanceAutocompleteTv.getText().toString().length() > 0;
    }
}
