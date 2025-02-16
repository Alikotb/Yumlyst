package com.example.yumlyst.ui.mealdetails.view;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.yumlyst.R;
import com.example.yumlyst.database.UserCashing;
import com.example.yumlyst.database.room.LocalDataSource;
import com.example.yumlyst.helper.BitmapTypeConverter;
import com.example.yumlyst.helper.Calender;
import com.example.yumlyst.helper.Constant;
import com.example.yumlyst.model.LocalDTO;
import com.example.yumlyst.repository.LocalRepo;
import com.example.yumlyst.repository.RemoteMealRepo;
import com.example.yumlyst.model.MealDTO;
import com.example.yumlyst.network.APICall.RemoteDataSource;
import com.example.yumlyst.ui.adapters.DetailsAdapter;
import com.example.yumlyst.ui.mealdetails.presenter.DetailsPresenter;
import com.example.yumlyst.ui.mealdetails.presenter.IDetailsPresenter;

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
    IDetailsPresenter detailsPresenter;
    UserCashing userCashing;

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
        detailsPresenter = new DetailsPresenter(this, RemoteMealRepo.getInstance(RemoteDataSource.getInstance()), LocalRepo.getInstance(LocalDataSource.getInstance(requireContext())));
        findById();
        super.onViewCreated(view, savedInstanceState);
         id=DetailsFragArgs.fromBundle(getArguments()).getID();
        detailsPresenter.getMealDetails(id);
        back.setOnClickListener(view1 -> getActivity().onBackPressed());
        setListeners();
         userCashing = UserCashing.getInstance(requireContext());
    }

    public void setListeners() {

            plan.setOnClickListener(view -> {
                if(userCashing.isUserLoggedIn() ) {
                    Calender.showDate(requireContext(), selectedDate -> {
                        detailsPresenter.insert(new LocalDTO(Calender.getDay(selectedDate), userCashing.getUserId(), meal, Constant.PLAN));
                        Log.d("hhh", "setListeners: "+userCashing.getUserId() +" day :"+selectedDate);
                        Toast.makeText(requireContext(), "added successfully", Toast.LENGTH_SHORT).show();
                    });
                }else{
                    Toast.makeText(requireContext(), "loginFirst", Toast.LENGTH_SHORT).show();
                }
            });
            favorite.setOnClickListener(view -> {
                if(userCashing.isUserLoggedIn()) {
                detailsPresenter.insert(new LocalDTO(Calender.getMinDate(), userCashing.getUserId(), meal, Constant.FAVORITE));
                    Toast.makeText(requireContext(), "added successfully", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(requireContext(), "loginFirst", Toast.LENGTH_SHORT).show();
                }
            });

    }

    private void print_data(MealDTO meall) {
         Bitmap mealBitmap; // Declare the local variable
        this.meal=meall;
        name.setText(meal.getStrMeal());
        categoryArea.setText(meal.getStrCategory() + " | " + meal.getStrArea());
        instructions.setText(meal.getStrInstructions());
//        Glide.with(this).load(meal.getStrMealThumb()).into(imageView);
        Glide.with(this)
            .asBitmap()
                .load(meal.getStrMealThumb()) // Load image URL
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        imageView.setImageBitmap(resource);
                        meal.setBitmap(BitmapTypeConverter.fromBitmap(resource));
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                        // Handle cleanup if needed
                    }
                });

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