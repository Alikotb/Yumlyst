package com.example.yumlyst.ui.favoritescrean.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.yumlyst.MainActivity;
import com.example.yumlyst.R;
import com.example.yumlyst.database.UserCashing;
import com.example.yumlyst.database.room.LocalDataSource;
import com.example.yumlyst.helper.Constant;
import com.example.yumlyst.model.MealDTO;
import com.example.yumlyst.network.authentecation.NetworkUtils;
import com.example.yumlyst.repository.LocalRepo;
import com.example.yumlyst.ui.adapters.SearchAdapter;
import com.example.yumlyst.ui.favoritescrean.presenter.FavoritePresenter;
import com.example.yumlyst.ui.favoritescrean.presenter.IFavoritePresenter;

import java.util.List;


public class Favorite extends Fragment implements IFavoriteView {


    private RecyclerView recyclerView;
    private SearchAdapter adapter;
    private IFavoritePresenter presenter;
    private UserCashing userCashing;
    public Favorite() {
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.favoriteRecycle);
        presenter=new FavoritePresenter(LocalRepo.getInstance(LocalDataSource.getInstance(requireContext())),this);
        userCashing = UserCashing.getInstance(requireContext());
        presenter.getMeals(userCashing.getUserId(),Constant.FAVORITE);
        MainActivity mainActivity=(MainActivity) getActivity();
        mainActivity.hideInternetAnimation();
    }
    @Override
    public void showMeals(List<MealDTO> meals) {
        adapter = new SearchAdapter(meals, Constant.FAVORITE);
        recyclerView.setAdapter(adapter);

        adapter.setOnitemclick(null, meal -> {
            if (NetworkUtils.isConnected(getContext())) {
                presenter.deleteMeal(userCashing.getUserId(), meal);
                adapter.removeMeal(meal);
            }else{
                Toast.makeText(requireContext(), "No Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public void deleteMeal(MealDTO meal) {

    }
}