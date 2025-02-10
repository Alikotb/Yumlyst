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
import com.example.yumlyst.R;
import com.example.yumlyst.ui.OnclickListneres;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class Home extends Fragment implements OnclickListneres {


    ImageButton profile_image;
    SearchView searchView;
    RecyclerView Daily_meal_res;
    RecyclerView categoryResc;
    RecyclerView CountiesResc;
    String searchText;
    BottomNavigationView bottom_navigation;

    public Home() {
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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findById();
        setListeners();
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.showNavigationBottom();

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
}