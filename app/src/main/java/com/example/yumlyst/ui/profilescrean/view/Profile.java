package com.example.yumlyst.ui.profilescrean.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.yumlyst.R;
import com.example.yumlyst.ui.OnclickListneres;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;


public class Profile extends Fragment implements OnclickListneres {

    ImageButton back_button;
    ShapeableImageView profile_image;
    MaterialAutoCompleteTextView username;
    MaterialAutoCompleteTextView email;
    MaterialButton Favourites_button;
    MaterialButton logout_button;

    public Profile() {
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
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findById();
        setListeners();
    }

    @Override
    public void setListeners() {
        back_button.setOnClickListener(v->{
            Navigation.findNavController(v).navigate(R.id.action_profile_to_home2);
        });
        Favourites_button.setOnClickListener(v->{
            Navigation.findNavController(v).navigate(R.id.action_profile_to_favorite);
        });
        logout_button.setOnClickListener(v-> {
        });

    }
    private void findById() {
        back_button = getActivity().findViewById(R.id.back_button);
        profile_image = getActivity().findViewById(R.id.profile_profilepage_image);
        username = getActivity().findViewById(R.id.username);
        email = getActivity().findViewById(R.id.email);
        Favourites_button = getActivity().findViewById(R.id.Favourites_button);
        logout_button = getActivity().findViewById(R.id.logout_button);

    }
}