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

import com.bumptech.glide.Glide;
import com.example.yumlyst.R;
import com.example.yumlyst.ui.OnclickListneres;
import com.example.yumlyst.ui.profilescrean.presenter.ProfilePresenter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;

public class Profile extends Fragment implements OnclickListneres, IProfileView {

    private ImageButton backButton;
    private ShapeableImageView profileImage;
    private MaterialAutoCompleteTextView username;
    private MaterialAutoCompleteTextView email;
    private MaterialButton favouritesButton;
    private MaterialButton logoutButton;
    private ProfilePresenter profilePresenter;

    public Profile() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profilePresenter = new ProfilePresenter(this, requireContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        setListeners();
        profilePresenter.handleFetchUserData();
    }

    private void findViews(View view) {
        backButton = view.findViewById(R.id.back_button);
        profileImage = view.findViewById(R.id.profile_profilepage_image);
        username = view.findViewById(R.id.username);
        email = view.findViewById(R.id.email);
        favouritesButton = view.findViewById(R.id.Favourites_button);
        logoutButton = view.findViewById(R.id.logout_button);
    }

    @Override
    public void setListeners() {
        backButton.setOnClickListener(v -> navigateToHome());
        favouritesButton.setOnClickListener(v -> navigateFavorit());
        logoutButton.setOnClickListener(v -> profilePresenter.handleLogout());
    }

    @Override
    public void fetchUserData() {
        if (profilePresenter.isLoggedIn()) {
            username.setText(profilePresenter.getUsername());
            email.setText(profilePresenter.getEmail());
            if (profilePresenter.getPhotoUrl() != null) {
                Glide.with(this)
                        .load(profilePresenter.getPhotoUrl())
                        .placeholder(R.drawable.profile) // Optional: Placeholder image
                        .into(profileImage);
            }
        }
    }

    @Override
    public void navigateToHome() {
        Navigation.findNavController(requireView()).navigate(R.id.action_profile_to_home2);
    }

    @Override
    public void navigateFavorit() {
        Navigation.findNavController(requireView()).navigate(R.id.action_profile_to_favorite);
    }
}
