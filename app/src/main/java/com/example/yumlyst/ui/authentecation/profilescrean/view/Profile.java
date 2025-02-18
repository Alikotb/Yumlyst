package com.example.yumlyst.ui.authentecation.profilescrean.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.example.yumlyst.MainActivity;
import com.example.yumlyst.R;
import com.example.yumlyst.helper.Constant;
import com.example.yumlyst.ui.OnclickListneres;
import com.example.yumlyst.ui.authentecation.profilescrean.presenter.ProfilePresenter;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;

public class Profile extends Fragment implements OnclickListneres, IProfileView {

    private ShapeableImageView profileImage;
    private MaterialAutoCompleteTextView username;
    private MaterialAutoCompleteTextView email;
    private MaterialButton logoutButton;
    private ProfilePresenter profilePresenter;
    private ImageButton withGmail;
    private ImageButton withLinkedIn;
    private ImageButton withGithub;
    private MaterialButton shareAppButton;

    public Profile() {
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
        MainActivity mainActivity = (MainActivity) getActivity();
        mainActivity.showNavigationBottom();
        findViews(view);
        setListeners();
        profilePresenter.handleFetchUserData();
    }

    private void findViews(View view) {
        profileImage = view.findViewById(R.id.profile_profilepage_image);
        username = view.findViewById(R.id.username);
        email = view.findViewById(R.id.email);
        logoutButton = view.findViewById(R.id.logout_button);
        withGmail = view.findViewById(R.id.withGmail);
        withLinkedIn = view.findViewById(R.id.withLinkedIn);
        withGithub = view.findViewById(R.id.withGithub);
        shareAppButton = view.findViewById(R.id.share_app_button);

    }

    @Override
    public void setListeners() {
        logoutButton.setOnClickListener(v -> profilePresenter.handleLogout());
        withGmail.setOnClickListener(v -> contactWithEmail());
        withLinkedIn.setOnClickListener(v -> contactWithLinkedIn());
        withGithub.setOnClickListener(v -> showGitHup());
        shareAppButton.setOnClickListener(v -> shareApp());
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
    public void navigateToHomeScreen() {
        Navigation.findNavController(requireView()).navigate(R.id.action_profile_to_firstScreen);
    }

    @Override
    public void openFacebookApp(String userId) {

            Intent intent;
            try {
                requireActivity().getPackageManager().getPackageInfo("com.facebook.katana", 0);

                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/" + userId));
            } catch (Exception e) {
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/" + userId));
            }
            startActivity(intent);
        }
        @Override
        public void shareApp() {
        String appLink = Constant.APP_LINK;
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, "YUMMLYST is a food app planner: " + appLink);
        shareIntent.setType("text/plain");

        if (shareIntent.resolveActivity(requireActivity().getPackageManager()) != null) {
            startActivity(Intent.createChooser(shareIntent, "Share app via"));
        } else {
            Toast.makeText(requireContext(), "can't share", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void contactWithLinkedIn() {
        Intent intent;
        try {
            getContext().getPackageManager().getPackageInfo("com.linkedin.android", 0);
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("linkedin://in/" + Constant.LINKEDIN_ID));
        } catch (Exception e) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/" +  Constant.LINKEDIN_ID));
        }
        startActivity(intent);

    }

    @Override
    public void showGitHup() {
        Intent intent;
        try {
            requireActivity().getPackageManager().getPackageInfo("com.github.android", 0);
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("github://user?username=" + Constant.GITHUB));
        } catch (Exception e) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/" + Constant.GITHUB));
        }
        startActivity(intent);
    }

    @Override
    public void contactWithEmail() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + Constant.EMAIL));
        try {
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(requireContext(), "No email app found", Toast.LENGTH_SHORT).show();
        }

    }


}
