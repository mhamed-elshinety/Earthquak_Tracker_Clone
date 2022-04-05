package com.example.earthquaketrackerclone.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.earthquaketrackerclone.R;
import com.example.earthquaketrackerclone.data.Constants;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        startSplashScreenTimeout();
        getSupportActionBar().hide();
    }

    //finishing splash screen and opening main activity after SPLASH_TIME_OUT
    public void startSplashScreenTimeout (){
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, Constants.SPLASH_TIME_OUT);
    }
}