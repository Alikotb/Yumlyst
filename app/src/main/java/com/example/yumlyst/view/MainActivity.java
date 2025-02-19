package com.example.yumlyst.view;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.airbnb.lottie.LottieAnimationView;
import com.example.yumlyst.R;
import com.example.yumlyst.network.authentecation.NetworkUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        lottieAnimationView = findViewById(R.id.internetAnimation);

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(bottomNavigationView, navController);


    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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

    }

    public void hideInternetAnimation() {
        lottieAnimationView.cancelAnimation();
        lottieAnimationView.setVisibility(View.GONE);
    }


}
