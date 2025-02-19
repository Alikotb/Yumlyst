package com.example.yumlyst.view.details;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yumlyst.R;
import com.example.yumlyst.helper.BitmapTypeConverter;
import com.example.yumlyst.model.MealDTO;
import com.example.yumlyst.view.adapters.DetailsAdapter;


public class OfflineDetails extends Fragment {

    MealDTO meal;
    ImageButton back;
    ImageView imageView;
    TextView name;
    TextView categoryArea;
    TextView instructions;
    RecyclerView ingredients;
    DetailsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_offline_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findById();
        meal= OfflineDetailsArgs.fromBundle(getArguments()).getMeal();
        print_data(meal);
        back.setOnClickListener(view1 -> navigateToHome());


    }
    private void findById() {
        back = getActivity().findViewById(R.id.btnImage);
        imageView = getActivity().findViewById(R.id.mealImage);
        name = getActivity().findViewById(R.id.mealDetailsName);
        categoryArea = getActivity().findViewById(R.id.mealCategoryArea);
        instructions = getActivity().findViewById(R.id.mealInstructions);
        ingredients = getActivity().findViewById(R.id.ingredientsRecyclerView);
        //setContentView(video);
    }
    private void print_data(MealDTO meall) {
        this.meal = meall;
        name.setText(meal.getStrMeal());
        categoryArea.setText(meal.getStrCategory() + " | " + meal.getStrArea());
        instructions.setText(meal.getStrInstructions());
        imageView.setImageBitmap(BitmapTypeConverter.toBitmap(meall.getBitmap()));


        adapter = new DetailsAdapter(meal.listIngredients());
        ingredients.setAdapter(adapter);
    }
    void navigateToHome(){
        getActivity().onBackPressed();
    }
}