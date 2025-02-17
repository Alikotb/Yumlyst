package com.example.yumlyst.ui.planscrean.view;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yumlyst.MainActivity;
import com.example.yumlyst.R;
import com.example.yumlyst.database.UserCashing;
import com.example.yumlyst.database.room.LocalDataSource;
import com.example.yumlyst.helper.Calender;
import com.example.yumlyst.helper.Constant;
import com.example.yumlyst.model.MealDTO;
import com.example.yumlyst.network.authentecation.NetworkUtils;
import com.example.yumlyst.repository.LocalRepo;
import com.example.yumlyst.ui.adapters.PlanAdapter;
import com.example.yumlyst.ui.planscrean.presenter.IPlanPresenter;
import com.example.yumlyst.ui.planscrean.presenter.PlanPresenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Plan extends Fragment implements IPlanView {

    private RecyclerView planRecycler;
    private DatePicker planCalendar;
    private PlanAdapter adapter;
    private IPlanPresenter presenter;
    private UserCashing userCashing;
    private String selectedDay;

    public Plan() {
        // Default constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_plan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainActivity mainActivity = (MainActivity) getActivity();
        if (mainActivity != null) {
            mainActivity.hideInternetAnimation();
        }

        // Initialize components
        adapter = new PlanAdapter(new ArrayList<>());
        presenter = new PlanPresenter( this,requireContext());
        userCashing = UserCashing.getInstance(requireContext());

        findViews(view);
        setListeners();
        deleteElement();
    }

    private void setListeners() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            planCalendar.setOnDateChangedListener((view, year, monthOfYear, dayOfMonth) -> {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                Calendar selectedCal = Calendar.getInstance();
                selectedCal.set(year, monthOfYear, dayOfMonth);
                String day = sdf.format(selectedCal.getTime());
                fetchMealsForDay(day);
            });
        }
    }

    private void fetchMealsForDay(String day) {
        if (userCashing.getUserId() != null) {
            selectedDay = day;
            presenter.getAllPlanByDay(userCashing.getUserId(), day, Constant.PLAN);
        }
    }

    private void findViews(@NonNull View view) {
        planRecycler = view.findViewById(R.id.planRecycler);
        planCalendar = view.findViewById(R.id.planClaender);

        if (planCalendar != null) {
            planCalendar.setMinDate(System.currentTimeMillis());
        }

        planRecycler.setAdapter(adapter);
    }

    @Override
    public void showMeals(List<MealDTO> meals) {
        if (meals != null) {
            adapter.setList(meals);
        }
    }

    private void deleteElement() {
        adapter.setOnitemclick(meal -> {
            if (NetworkUtils.isConnected(getContext())) {
                presenter.deleteFromPlan(userCashing.getUserId(), meal, selectedDay);

                adapter.removeMeal(meal);
            } else {
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
