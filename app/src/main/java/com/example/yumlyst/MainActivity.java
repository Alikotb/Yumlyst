package com.example.yumlyst;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.yumlyst.model.responsemodel.CategoriesResponse;
import com.example.yumlyst.network.APICall.RemoteDataSource;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView=findViewById(R.id.bottom_navigation);
        RemoteDataSource.getInstance().getCategories(new RemoteDataSource.NetworkCallback<CategoriesResponse>() {
            @Override
            public void onSuccess(CategoriesResponse response) {
                Log.i("test", "Categories received: " + response.getCategories().size());
            }

            @Override
            public void onFailure(String message) {
                Log.e("test", "API Error: " + message);
            }
        });

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(bottomNavigationView,navController);


    }

    public void showNavigationBottom(){
        bottomNavigationView.setVisibility(View.VISIBLE);

    }
    public void hideNavigationBottom(){
        bottomNavigationView.setVisibility(View.GONE);
    }
}