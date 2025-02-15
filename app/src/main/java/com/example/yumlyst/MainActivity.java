package com.example.yumlyst;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;
    LottieAnimationView lottieAnimationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        /*LottieAnimationView lottieAnimationView = findViewById(R.id.lottieAnimationView);
lottieAnimationView.playAnimation(); // Start animation
lottieAnimationView.loop(true); */

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        lottieAnimationView = findViewById(R.id.internetAnimation);


    }



    public void showNavigationBottom() {
        bottomNavigationView.setVisibility(View.VISIBLE);

    }

    public void hideNavigationBottom() {
        bottomNavigationView.setVisibility(View.GONE);
    }
    public void showInternetAnimation() {
        lottieAnimationView.setVisibility(View.VISIBLE);
        lottieAnimationView.playAnimation();
        Log.d("NetworkCheck", "No internet! Showing animation.");

    }
    public void hideInternetAnimation() {
        lottieAnimationView.cancelAnimation();
        lottieAnimationView.setVisibility(View.GONE);    }

}