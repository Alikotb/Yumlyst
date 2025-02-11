package com.example.yumlyst.ui.homescrean.view;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.yumlyst.MainActivity;
import com.example.yumlyst.MealRepo;
import com.example.yumlyst.R;
import com.example.yumlyst.adapters.CategoryAdapter;
import com.example.yumlyst.model.AreaDTO;
import com.example.yumlyst.model.CategoryDTO;
import com.example.yumlyst.model.IngredientDTO;
import com.example.yumlyst.model.MealDTO;
import com.example.yumlyst.network.APICall.RemoteDataSource;
import com.example.yumlyst.ui.OnclickListneres;
import com.example.yumlyst.ui.homescrean.presenter.HomePresenter;
import com.example.yumlyst.ui.homescrean.presenter.IHomePresenter;

import java.util.List;


public class Home extends Fragment implements OnclickListneres, IHomeView {


    ImageButton profile_image;
    SearchView searchView;
    RecyclerView Daily_meal_res;
    RecyclerView categoryResc;
    RecyclerView CountiesResc;
    String searchText;

    IHomePresenter homePresenter;
    public Home() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homePresenter = new HomePresenter(this, MealRepo.getInstance(RemoteDataSource.getInstance()));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findById();
        setListeners();
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.showNavigationBottom();
        getDataFromRemot();

    }

    private void getDataFromRemot() {
        homePresenter.getCategories();
        homePresenter.getAreas();
        homePresenter.getIngredients();
        homePresenter.getRandomMeal();
    }

    @Override
    public void setListeners() {
        profile_image.setOnClickListener(v->{
            Navigation.findNavController(v).navigate(R.id.action_home2_to_profile);
        });
        searchView.setOnClickListener(v->{
             searchText = searchView.getQuery().toString();
        });


    }
    private void findById() {
        profile_image = getActivity().findViewById(R.id.profile_image);
        searchView = getActivity().findViewById(R.id.searchView);
        Daily_meal_res = getActivity().findViewById(R.id.Daily_meal_res);
        categoryResc = getActivity().findViewById(R.id.categoryResc);
        CountiesResc = getActivity().findViewById(R.id.CountiesResc);

    }

    @Override
    public void showCategories(List<CategoryDTO> categories) {
        CategoryAdapter categoryAdapter = new CategoryAdapter(categories);
        categoryResc.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void showAreas(List<AreaDTO> areas) {
//        CategoryAdapter categoryAdapter = new CategoryAdapter(areas);
//        CountiesResc.setAdapter(categoryAdapter);
    }

    @Override
    public void showIngredients(List<IngredientDTO> ingredients) {
//        IngredientAdapter ingredientAdapter = new IngredientAdapter(ingredients, this);
//        Daily_meal_res.setAdapter(ingredientAdapter);

    }

    @Override
    public void showRandomMeal(MealDTO randomMeal) {
//        RandomMealAdapter randomMealAdapter = new RandomMealAdapter(randomMeal, this);
//        Daily_meal_res.setAdapter(randomMealAdapter);


    }

    @Override
    public void showError(String message) {
//        AppCompatTextView textView = getActivity().findViewById(R.id.error_text);
//        textView.setText(message);

    }
}