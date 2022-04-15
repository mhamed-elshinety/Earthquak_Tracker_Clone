package com.example.earthquaketrackerclone.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.earthquaketrackerclone.R;
import com.example.earthquaketrackerclone.adapters.MainPagerAdapter;
import com.example.earthquaketrackerclone.pojo.EarthquakeModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavHostFragment hostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.navigation_host_fragment);
        navController = hostFragment.getNavController();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }


    // define views
    public void defineFields(){

    }

    // adding an adapter to view pager and adjusting the TabLayout to work with it
    public void initializeNavigationGraph(){
    }
}