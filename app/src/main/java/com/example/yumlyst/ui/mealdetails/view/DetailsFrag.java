package com.example.yumlyst.ui.mealdetails.view;

import android.os.Bundle;
import android.text.TextUtils;
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
import com.example.yumlyst.database.MealRepo;
import com.example.yumlyst.model.MealDTO;
import com.example.yumlyst.network.APICall.RemoteDataSource;
import com.example.yumlyst.ui.adapters.DetailsAdapter;
import com.example.yumlyst.ui.mealdetails.presenter.DetailsPresenter;

public class DetailsFrag extends Fragment implements IDetailsView {

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
    String id;
    DetailsPresenter detailsPresenter;

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
        detailsPresenter = new DetailsPresenter(this, MealRepo.getInstance(RemoteDataSource.getInstance()));

        findById();
        super.onViewCreated(view, savedInstanceState);
         id=DetailsFragArgs.fromBundle(getArguments()).getID();
        detailsPresenter.getMealDetails(id);
//        print_data(meal);
//        adapter = new DetailsAdapter(meal.listIngredients());
//        ingredients.setAdapter(adapter);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
    }

    private void print_data(MealDTO meal) {
        name.setText(meal.getStrMeal());
        categoryArea.setText(meal.getStrCategory() + " | " + meal.getStrArea());
        instructions.setText(meal.getStrInstructions());
        Glide.with(this).load(meal.getStrMealThumb()).into(imageView);
        adapter = new DetailsAdapter(meal.listIngredients());
        ingredients.setAdapter(adapter);
        loadYouTubeVideo(meal.getStrYoutube());
    }


    private void findById() {
        back = getActivity().findViewById(R.id.btnImage);
        imageView = getActivity().findViewById(R.id.mealImage);
        name = getActivity().findViewById(R.id.mealDetailsName);
        categoryArea = getActivity().findViewById(R.id.mealCategoryArea);
        instructions = getActivity().findViewById(R.id.mealInstructions);
        ingredients = getActivity().findViewById(R.id.ingredientsRecyclerView);
        video = getActivity().findViewById(R.id.mealVideoWebView);
        //setContentView(video);

        plan = getActivity().findViewById(R.id.btnPlan);
        favorite = getActivity().findViewById(R.id.btnFavorite);


    }

    private void loadYouTubeVideo(String url) {
        if (!TextUtils.isEmpty(url)) {
            String videoId = url.split("v=")[1];
            String embedUrl = "https://www.youtube.com/embed/" + videoId;
            video.getSettings().setJavaScriptEnabled(true);
            video.getSettings().setPluginState(WebSettings.PluginState.ON);
            video.setWebViewClient(new WebViewClient());
            video.loadUrl(embedUrl);
        }
    }


    @Override
    public void showMealDetails(MealDTO meal) {
        print_data(meal);
    }
}