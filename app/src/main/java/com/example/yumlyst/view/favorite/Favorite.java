package com.example.yumlyst.view.favorite;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.yumlyst.view.MainActivity;
import com.example.yumlyst.R;
import com.example.yumlyst.network.database.UserCashing;
import com.example.yumlyst.network.database.room.LocalDataSource;
import com.example.yumlyst.helper.Constant;
import com.example.yumlyst.model.MealDTO;
import com.example.yumlyst.network.authentecation.NetworkUtils;
import com.example.yumlyst.network.repository.LocalRepo;
import com.example.yumlyst.view.adapters.SearchAdapter;
import com.example.yumlyst.presenters.favorite.FavoritePresenter;
import com.example.yumlyst.presenters.favorite.IFavoritePresenter;

import java.util.ArrayList;
import java.util.List;


public class Favorite extends Fragment implements IFavoriteView {


    private RecyclerView recyclerView;
    private SearchAdapter adapter;
    private IFavoritePresenter presenter;
    private UserCashing userCashing;
    private LottieAnimationView lottieAnimationView;
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
        lottieAnimationView=view.findViewById(R.id.planAnimation);
        adapter = new SearchAdapter(new ArrayList<>(),"");
        recyclerView.setAdapter(adapter);
        presenter=new FavoritePresenter(LocalRepo.getInstance(LocalDataSource.getInstance(requireContext())),this);
        userCashing = UserCashing.getInstance(requireContext());
        MainActivity mainActivity=(MainActivity) getActivity();
        mainActivity.hideInternetAnimation();
        presenter.getMeals(userCashing.getUserId(),Constant.FAVORITE);
        showOffDetails();

        adapter.setOnitemclick(null, meal -> {
            if (NetworkUtils.isConnected(getContext())) {
                presenter.deleteMeal(userCashing.getUserId(), meal);
            }else{
                Toast.makeText(requireContext(), "No Internet", Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public void showMeals(List<MealDTO> meals) {
        if(meals!=null && !meals.isEmpty()){
            lottieAnimationView.setVisibility(View.GONE);
        }else {
            lottieAnimationView.setVisibility(View.VISIBLE);
        }
        adapter.setList(meals, Constant.FAVORITE);
//        adapter.setOnitemclick(null, meal -> {
//            if (NetworkUtils.isConnected(getContext())) {
//                presenter.deleteMeal(userCashing.getUserId(), meal);
//            }else{
//                Toast.makeText(requireContext(), "No Internet", Toast.LENGTH_SHORT).show();
//            }
//        });
    }


    private void showOffDetails() {
        adapter.setForward(meal -> {
            com.example.yumlyst.view.favorite.FavoriteDirections.ActionFavorite2ToOfflineDetails action = com.example.yumlyst.view.favorite.FavoriteDirections.actionFavorite2ToOfflineDetails(meal);
            Navigation.findNavController(requireView()).navigate(action);
        });
    }

    @Override
    public void deleteMeal(MealDTO meal) {
        adapter.removeMeal(meal);
    }
}