package com.example.earthquaketrackerclone.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.earthquaketrackerclone.R;
import com.example.earthquaketrackerclone.data.Assistant;
import com.example.earthquaketrackerclone.pojo.EarthquakeModel;
import com.example.earthquaketrackerclone.ui.FeaturedFragment;
import com.example.earthquaketrackerclone.ui.LatestTabFragment;
import com.example.earthquaketrackerclone.ui.MapTabFragment;
import com.example.earthquaketrackerclone.ui.SearchFragment;

import java.util.ArrayList;

public class MainPagerAdapter extends FragmentStatePagerAdapter {

    private ArrayList<EarthquakeModel> earthquakes;

    public MainPagerAdapter(@NonNull FragmentManager fm, ArrayList<EarthquakeModel>earthquakes) {
        super(fm);
        this.earthquakes = earthquakes;
    }

    //Override method that returns needed fragments
    @NonNull
    @Override
    public Fragment getItem(int position) {
         switch (position) {
            case 0:

                return LatestTabFragment.newInstance();
            case 1:
                return MapTabFragment.newInstance(earthquakes);
            case 2:
                return FeaturedFragment.newInstance(earthquakes);
            case 3:
                return SearchFragment.newInstance();
            default:
                return null;
        }

    }

    //Override method that returns number of fragments is view pager
    @Override
    public int getCount() {
        return 4;
    }

    //Override method that returns each page title
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return Assistant.getStringResource(R.string.latest);
            case 1:
                return Assistant.getStringResource(R.string.map);
            case 2:
                return Assistant.getStringResource(R.string.featured);
            case 3:
                return Assistant.getStringResource(R.string.search);
            default:
                return super.getPageTitle(position);
        }
    }

}
