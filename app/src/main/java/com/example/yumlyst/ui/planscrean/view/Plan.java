package com.example.yumlyst.ui.planscrean.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yumlyst.MainActivity;
import com.example.yumlyst.R;
import com.example.yumlyst.database.UserCashing;
import com.example.yumlyst.database.room.LocalDataSource;
import com.example.yumlyst.helper.Constant;
import com.example.yumlyst.model.MealDTO;
import com.example.yumlyst.network.authentecation.NetworkUtils;
import com.example.yumlyst.repository.LocalRepo;
import com.example.yumlyst.ui.adapters.PlanAdapter;
import com.example.yumlyst.ui.planscrean.presenter.IPlanPresenter;
import com.example.yumlyst.ui.planscrean.presenter.PlanPresenter;

import java.util.ArrayList;
import java.util.List;


public class Plan extends Fragment implements IPlanView {

    RecyclerView saturdayResycle;
    RecyclerView sundayRecycle;
    RecyclerView mondayRecycle;
    RecyclerView tuesdayRecycle;
    RecyclerView wednesdayRecycle;
    RecyclerView thursRecycle;
    RecyclerView fridayRecycle;
    TextView saturday;
    TextView sunday;
    TextView monday;
    TextView tuesday;
    TextView wednesday;
    TextView thurs;
    TextView friday;


    private PlanAdapter adapter;
    private IPlanPresenter presenter;
    private UserCashing userCashing;
    private String selectedDay;

    public Plan() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_plan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.hideInternetAnimation();
        adapter = new PlanAdapter(new ArrayList<>());

        presenter = new PlanPresenter(LocalRepo.getInstance(LocalDataSource.getInstance(requireContext())), this);
        userCashing = UserCashing.getInstance(requireContext());

        findById(view);
        setListner();
    }

    private void setListner() {
        saturday.setOnClickListener(v -> fetchMealsForDay(Constant.SATURDAY, saturdayResycle));
        sunday.setOnClickListener(v -> fetchMealsForDay(Constant.SUNDAY, sundayRecycle));
        monday.setOnClickListener(v -> fetchMealsForDay(Constant.MONDAY, mondayRecycle));
        tuesday.setOnClickListener(v -> fetchMealsForDay(Constant.TUESDAY, tuesdayRecycle));
        wednesday.setOnClickListener(v -> fetchMealsForDay(Constant.WEDNESDAY, wednesdayRecycle));
        thurs.setOnClickListener(v -> fetchMealsForDay(Constant.THURSDAY, thursRecycle));
        friday.setOnClickListener(v -> fetchMealsForDay(Constant.FRIDAY, fridayRecycle));
    }

    private void fetchMealsForDay(String day, RecyclerView recyclerView) {
        if (userCashing.getUserId() != null) {
            selectedDay = day; //
            presenter.getAllPlanByDay(userCashing.getUserId(), day, Constant.PLAN);
        }
    }

    private void findById(@NonNull View view) {
        saturdayResycle = view.findViewById(R.id.saturdayResycle);
        sundayRecycle = view.findViewById(R.id.sundayRecycle);
        mondayRecycle = view.findViewById(R.id.mondayRecycle);
        tuesdayRecycle = view.findViewById(R.id.tuesdayRecycle);
        wednesdayRecycle = view.findViewById(R.id.wednesdayRecycle);
        thursRecycle = view.findViewById(R.id.thursRecycle);
        fridayRecycle = view.findViewById(R.id.fridayRecycle);
        saturday = view.findViewById(R.id.saturdaytxt);
        sunday = view.findViewById(R.id.sundaytxt);
        monday = view.findViewById(R.id.mondaytxt);
        tuesday = view.findViewById(R.id.tuesdaytxt);
        wednesday = view.findViewById(R.id.wednesdaytxt);
        thurs = view.findViewById(R.id.thursdtxt);
        friday = view.findViewById(R.id.fridaytxt);
    }

    @Override
    public void showMeals(List<MealDTO> meals) {
        if (selectedDay == null) {
            return;
        }
        switch (selectedDay) {
            case Constant.SATURDAY: {
                PlanAdapter sat = new PlanAdapter(meals);
                saturdayResycle.setAdapter(sat);
                deleteElement(sat,Constant.SATURDAY);
                break;

            }
            case Constant.SUNDAY: {
                PlanAdapter sat = new PlanAdapter(meals);
                sundayRecycle.setAdapter(sat);
                deleteElement(sat,Constant.SUNDAY);
                break;
            }
            case Constant.MONDAY: {
                PlanAdapter sat = new PlanAdapter(meals);
                mondayRecycle.setAdapter(sat);
                deleteElement(sat,Constant.MONDAY);
                break;
            }
            case Constant.TUESDAY: {
                PlanAdapter sat = new PlanAdapter(meals);
                tuesdayRecycle.setAdapter(sat);
                deleteElement(sat,Constant.TUESDAY);
                break;
            }
            case Constant.WEDNESDAY: {
                PlanAdapter sat = new PlanAdapter(meals);
                wednesdayRecycle.setAdapter(sat);
                deleteElement(sat,Constant.WEDNESDAY);
                break;
            }
            case Constant.THURSDAY: {
                PlanAdapter sat = new PlanAdapter(meals);
                thursRecycle.setAdapter(sat);
                deleteElement(sat,Constant.THURSDAY);
                break;
            }
            case Constant.FRIDAY: {
                PlanAdapter sat = new PlanAdapter(meals);
                fridayRecycle.setAdapter(sat);
                deleteElement(sat,Constant.FRIDAY);
                break;
            }
        }
    }

    private void deleteElement(PlanAdapter sat,String d) {
        sat.setOnitemclick(meal -> {
            if (NetworkUtils.isConnected(getContext())) {
                presenter.deleteFromPlan(userCashing.getUserId(), meal,d);
                sat.removeMeal(meal);
            }
            else{
                Toast.makeText(requireContext(), "No Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void deleteMeal(MealDTO meal) {
        if (meal != null) {
            adapter.removeMeal(meal);
        }
    }

}