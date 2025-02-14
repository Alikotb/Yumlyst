package com.example.yumlyst.ui.homescrean.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yumlyst.MainActivity;
import com.example.yumlyst.R;
import com.example.yumlyst.database.MealRepo;
import com.example.yumlyst.model.AreaDTO;
import com.example.yumlyst.model.CategoryDTO;
import com.example.yumlyst.model.IngredientDTO;
import com.example.yumlyst.model.MealDTO;
import com.example.yumlyst.network.APICall.RemoteDataSource;
import com.example.yumlyst.ui.OnclickListneres;
import com.example.yumlyst.ui.adapters.AreaAdapter;
import com.example.yumlyst.ui.adapters.CategoryAdapter;
import com.example.yumlyst.ui.adapters.DailMealAdapter;
import com.example.yumlyst.ui.adapters.ImgradientAdapter;
import com.example.yumlyst.ui.homescrean.presenter.HomePresenter;
import com.example.yumlyst.ui.homescrean.presenter.IHomePresenter;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.List;


public class Home extends Fragment implements OnclickListneres, IHomeView {


    ShapeableImageView profile_image;
    SearchView searchView;
    RecyclerView Daily_meal_res;
    RecyclerView categoryResc;
    RecyclerView CountiesResc;
    RecyclerView ingredientsResc;
    String searchText;
    DailMealAdapter randomMealAdapter;
    IHomePresenter homePresenter;
    CategoryAdapter categoryAdapter;
    ChipGroup chipsSearchGroup;
    Chip categoriesChip;
    Chip areaChip;
    Chip ingredientChip;
    AreaAdapter areaAdapter;
    ImgradientAdapter ingredientAdapter;

    public Home() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homePresenter = new HomePresenter(this, MealRepo.getInstance(RemoteDataSource.getInstance()));
        randomMealAdapter = new DailMealAdapter(new ArrayList<>());
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
        ingredientAdapter = new ImgradientAdapter(new ArrayList<>());
        areaAdapter = new AreaAdapter(new ArrayList<>());
        categoryAdapter = new CategoryAdapter(new ArrayList<>());
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
        homePresenter.getPhotoUrl();
    }

    @Override
    public void setListeners() {
        profile_image.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_home2_to_profile);
        });
        searchView.setOnClickListener(v -> {
            searchText = searchView.getQuery().toString();
        });
    }

    private void findById() {
        profile_image = getActivity().findViewById(R.id.profile_image);
        searchView = getActivity().findViewById(R.id.searchView);
        Daily_meal_res = getActivity().findViewById(R.id.Daily_meal_res);
        categoryResc = getActivity().findViewById(R.id.categoryResc);
        CountiesResc = getActivity().findViewById(R.id.CountiesResc);
        ingredientsResc = getActivity().findViewById(R.id.ingradientResc);
        chipsSearchGroup = getActivity().findViewById(R.id.chipsSearchGroup);
        categoriesChip = getActivity().findViewById(R.id.categoriesChip);
        areaChip = getActivity().findViewById(R.id.areaChip);
        ingredientChip = getActivity().findViewById(R.id.ingredientChip);
    }

    @Override
    public void showCategories(List<CategoryDTO> categories) {
        categoryAdapter.setList(categories);
        categoryResc.setAdapter(categoryAdapter);
        listenSearch();
        categoryAdapter.setOnitemclick(v -> {
            navigateToSearch("#" + v.getStrCategory());
        });
    }

    @Override
    public void showAreas(List<AreaDTO> areas) {
        areaAdapter.setList(areas);
        CountiesResc.setAdapter(areaAdapter);

        areaAdapter.setOnitemclick(v -> {
            navigateToSearch(v.getStrArea());
        });
    }

    @Override
    public void showIngredients(List<IngredientDTO> ingredients) {
        ingredientAdapter.setList(ingredients);
        ingredientsResc.setAdapter(ingredientAdapter);
        ingredientAdapter.setOnitemclick(v -> {
            navigateToSearch("*" + v.getStrIngredient());
        });
    }

    @Override
    public void showRandomMeal(List<MealDTO> randomMeals) {

        randomMealAdapter = new DailMealAdapter(randomMeals);
        Daily_meal_res.setAdapter(randomMealAdapter);
        randomMealAdapter.setOnitemclick(v -> {
            navigateToMealDetails(v.getIdMeal());
        });
    }

    @Override
    public void showPhotoUrl(String str) {
        if (str != null) {
            Glide.with(this)
                    .load(str)
                    .placeholder(R.drawable.profile)
                    .into(profile_image);
        }
    }

    @Override
    public void filterCategories(List<CategoryDTO> categorys) {
        categoryAdapter.setList(categorys);
    }

    public void filterAreas(List<AreaDTO> areas) {
        areaAdapter.setList(areas);
    }

    public void filterIngresiants(List<IngredientDTO> ingredientDTOS) {
        ingredientAdapter.setList(ingredientDTOS);
    }

    @Override
    public void navigateToMealDetails(String id) {

        HomeDirections.ActionHome2ToDetailsFrag action =
                HomeDirections.actionHome2ToDetailsFrag(id);
        Navigation.findNavController(getView()).navigate(action);
    }

    @Override
    public void navigateToSearch(String type) {
        HomeDirections.ActionHome2ToSearchFrag action =
                HomeDirections.actionHome2ToSearchFrag(type);
        Navigation.findNavController(getView()).navigate(action);
    }

    private void listenSearch() {
        chipsVisibility();
        replaceCheckedChipsState();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (categoriesChip.isChecked()) {
                    homePresenter.searchCategory(newText);
                } else if (areaChip.isChecked()) {

                    homePresenter.searchArea(newText);
                } else if (ingredientChip.isChecked()) {
                    homePresenter.searchIngredient(newText);
                }
                return false;
            }
        });

    }

    private void replaceCheckedChipsState() {
        chipsSearchGroup.setOnCheckedStateChangeListener((group, checkedIds) -> {
            if (checkedIds.size() != 0) {
                if (checkedIds.get(0) == R.id.categoriesChip) {

                    homePresenter.searchArea("");
                    homePresenter.searchIngredient("");
                } else if (checkedIds.get(0) == R.id.areaChip) {
                    homePresenter.searchCategory("");
                    homePresenter.searchIngredient("");
                } else if (checkedIds.get(0) == R.id.ingredientChip) {

                    homePresenter.searchArea("");
                    homePresenter.searchCategory("");
                }

            }
        });
    }

    private void chipsVisibility() {
        searchView.setOnQueryTextFocusChangeListener((v, hasFocus) -> {
            if (hasFocus)
                chipsSearchGroup.setVisibility(View.VISIBLE);
            else
                chipsSearchGroup.setVisibility(View.GONE);

        });
    }
}