package com.example.yumlyst.ui.firstscrean.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.yumlyst.MainActivity;
import com.example.yumlyst.R;
import com.example.yumlyst.ui.OnclickListneres;


public class FirstScreen extends Fragment implements OnclickListneres {


    Button loginBtn;
    Button skipBtn;

    public FirstScreen() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.hideNavigationBottom();
        findById(view);
        setListeners();
    }

    public void setListeners() {
        loginBtn.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_firstScreen_to_login);
        });
        skipBtn.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_firstScreen_to_home2);
        });
    }

    private void findById(@NonNull View view) {
        loginBtn = view.findViewById(R.id.login_button);
        skipBtn = view.findViewById(R.id.skip_button);
    }
}