package com.example.yumlyst.ui.search.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yumlyst.R;
import com.example.yumlyst.database.MealRepo;
import com.example.yumlyst.model.MealDTO;
import com.example.yumlyst.network.APICall.RemoteDataSource;
import com.example.yumlyst.ui.adapters.SearchAdapter;
import com.example.yumlyst.ui.search.presenter.ISearchPresenter;
import com.example.yumlyst.ui.search.presenter.SearchPresenter;

import java.util.List;

public class SearchFrag extends Fragment implements ISearchView {


    RecyclerView searchResycle;
    ISearchPresenter searchPresenter;
    SearchAdapter searchAdapter;
    String category;

    public SearchFrag() {
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
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        category = SearchFragArgs.fromBundle(getArguments()).getType();
        searchPresenter = new SearchPresenter(this, MealRepo.getInstance(RemoteDataSource.getInstance()));
        Log.d("ali", "category from searchfrag : " + category);
        findById();
        if (category.startsWith("#")) {
            category = category.substring(1);
            searchPresenter.getMealByCategory(category);
        } else if (category.startsWith("*")) {
            category = category.substring(1);
            searchPresenter.getMealByIngredient(category);
        } else {
            searchPresenter.getMealByArea(category);
        }

    }

    private void findById() {
        searchResycle = getActivity().findViewById(R.id.searchResycle);
    }

    @Override
    public void showMealsByCategory(List<MealDTO> meals) {
        searchAdapter = new SearchAdapter(meals);
        searchResycle.setAdapter(searchAdapter);
        searchAdapter.setOnitemclick((id) -> {
            navigateToMealDetails(id);

        });

    }

    @Override
    public void showMealsByArea(List<MealDTO> meals) {
        searchAdapter = new SearchAdapter(meals);
        searchResycle.setAdapter(searchAdapter);
        searchAdapter.setOnitemclick((id) -> {
            navigateToMealDetails(id);

        });

    }

    @Override
    public void showMealsByIngredient(List<MealDTO> meals) {
        searchAdapter = new SearchAdapter(meals);
        searchResycle.setAdapter(searchAdapter);
        searchAdapter.setOnitemclick((id) -> {
            navigateToMealDetails(id);

        });
    }


    @Override
    public void navigateToMealDetails(String id) {
        //action_searchFrag_to_detailsFrag
       /*SearchFragDirections.actionSearchFragToDetailsFrag action =
                SearchFragDirections.actionHome2ToSearchFrag(type);
        Navigation.findNavController(getView()).navigate(action);*/
        SearchFragDirections.ActionSearchFragToDetailsFrag action =
                SearchFragDirections.actionSearchFragToDetailsFrag(id);
        Navigation.findNavController(getView()).navigate(action);


    }
}