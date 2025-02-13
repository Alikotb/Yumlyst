package com.example.yumlyst.ui.mealdetails.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yumlyst.R;
import com.example.yumlyst.model.MealDTO;
import com.example.yumlyst.ui.adapters.DetailsAdapter;

public class DetailsFrag extends Fragment {

    MealDTO meal;
    ImageButton back;
    ImageView imageView;
    TextView name;
    TextView categoryArea;
    TextView instructions;
    RecyclerView ingredients;
    WebView video;
    Button plan;
    Button favorite;
    DetailsAdapter adapter;

    public DetailsFrag() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        findById();
        super.onViewCreated(view, savedInstanceState);
        meal = DetailsFragArgs.fromBundle(getArguments()).getMealDetails();
        print_data();
        adapter = new DetailsAdapter(meal.listIngredients());
        ingredients.setAdapter(adapter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    private void print_data() {
        name.setText(meal.getStrMeal());
        categoryArea.setText(meal.getStrCategory() + " | " + meal.getStrArea());
        instructions.setText(meal.getStrInstructions());
        Glide.with(this).load(meal.getStrMealThumb()).into(imageView);
        //video.loadUrl(meal.getStrYoutube());

        WebSettings webSettings = video.getSettings();

        webSettings.setDomStorageEnabled(true);

        video.getSettings().setJavaScriptEnabled(true);

        video.setWebViewClient(new WebViewClient());
        //video.loadUrl(meal.getStrYoutube());
        String ifram = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/RIrgCGbIYWo?si=0dIOcHB5SrkQvvZS\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
        video.loadData(ifram, "text/html", "UTF-8");
        video.setContentDescription("");
    }


    private void findById() {
        back = getActivity().findViewById(R.id.btnImage);
        imageView = getActivity().findViewById(R.id.mealImage);
        name = getActivity().findViewById(R.id.mealName);
        categoryArea = getActivity().findViewById(R.id.mealCategoryArea);
        instructions = getActivity().findViewById(R.id.mealInstructions);
        ingredients = getActivity().findViewById(R.id.ingredientsRecyclerView);
        video = getActivity().findViewById(R.id.mealVideoWebView);
        //setContentView(video);

        plan = getActivity().findViewById(R.id.btnPlan);
        favorite = getActivity().findViewById(R.id.btnFavorite);


    }
}