package com.example.earthquaketrackerclone.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.earthquaketrackerclone.R;
import com.example.earthquaketrackerclone.data.Constants;
import com.example.earthquaketrackerclone.pojo.EarthquakeModel;
import com.example.earthquaketrackerclone.pojo.USGSModel;

import java.util.ArrayList;
import java.util.Random;

public class SearchFragment extends Fragment implements SearchFragmentView{

    private SearchFragmentPresenter presenter;
    private AutoCompleteTextView autoCompleteTextView;

    public static SearchFragment newInstance(){
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
        return inflater.inflate(R.layout.fragment_search,container,false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        defineViews();
        presenter.getEarthquakes(getQueryBundle());


    }

    private Bundle getQueryBundle() {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.START_TIME,"2022-04-05");
        bundle.putString(Constants.END_TIME,"2022-04-06");
        bundle.putFloat(Constants.MIN_LATITUDE,0);
        bundle.putFloat(Constants.MIN_LONGITUDE,0);
        bundle.putFloat(Constants.MAX_LATITUDE,50);
        bundle.putFloat(Constants.MAX_LONGITUDE,50);
        bundle.putInt(Constants.LIMIT,20);
        bundle.putFloat(Constants.MIN_MAGNITUDE,2);
        bundle.putString(Constants.ORDER_BY,"time");
        return bundle;
    }

    //define fragment fields override from PagerFragmentParent
    public void defineFields() {
        presenter = new SearchFragmentPresenter(this);
    }

    public void defineViews() {
        autoCompleteTextView = getActivity().findViewById(R.id.autoCompleteTv);
        autoCompleteTextView.setAdapter(new ArrayAdapter<String>
                (requireContext(), android.R.layout.simple_list_item_1,getMags()));
    }

    private ArrayList<String> getMags() {
        ArrayList<String> arrayList =  new ArrayList<>();
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
        arrayList.add("5");
        arrayList.add("6");
        return arrayList;
    }

    @Override
    public void onGetEarthquakes(USGSModel usgsModel) {
        Log.d(Constants.LOG_KEY_MY_APP, "onGetEarthquakes: " + usgsModel.getFeatures().get(0).getProperties().toString());
    }
}
