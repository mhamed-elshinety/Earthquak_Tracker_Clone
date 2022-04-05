package com.example.earthquaketrackerclone.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.earthquaketrackerclone.R;
import com.example.earthquaketrackerclone.pojo.EarthquakeModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ViewPager viewPager;
    public TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        defineFields();
        initializeViewPager();
    }


    // define views
    public void defineFields(){
        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
    }

    // adding an adapter to view pager and adjusting the TabLayout to work with it
    public void initializeViewPager(){
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager(),new ArrayList<EarthquakeModel>());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}