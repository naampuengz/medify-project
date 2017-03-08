package com.example.airy.medifyseniorproject;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StartingActivity extends AppCompatActivity {

    private static int TIME_OUT = 1000;
    private Handler loginHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);




        loginHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Intent loginIntent = new Intent(getApplicationContext(), AuthenticationActivity.class);
                Intent loginIntent = new Intent(getApplicationContext(), GoogleSignInActivity.class);

                startActivity(loginIntent);
            }
        }, TIME_OUT);
    }
}
