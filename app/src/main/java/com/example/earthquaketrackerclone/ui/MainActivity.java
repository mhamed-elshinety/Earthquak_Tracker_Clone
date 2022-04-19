package com.example.earthquaketrackerclone.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.earthquaketrackerclone.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        defineFields();
        initializeNavigationGraph();
    }


    // define views
    public void defineFields(){
        bottomNavigationView = findViewById(R.id.bottom_nav_view);
        navController = ((NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.navigation_host_fragment)).getNavController();
    }

    //initializing navigation graph;
    public void initializeNavigationGraph(){
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }
}