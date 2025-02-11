package com.example.yumlyst.ui.authentecation.profilescrean.presenter;

import android.content.Context;

import com.example.yumlyst.R;
import com.example.yumlyst.ui.authentecation.UserCashing;
import com.example.yumlyst.ui.authentecation.profilescrean.view.IProfileView;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfilePresenter {
    private final FirebaseAuth auth;
    private IProfileView view;
    private final GoogleSignInClient googleSignInClient;
    UserCashing userCashing;

    public ProfilePresenter(IProfileView view , Context context) {
        auth = FirebaseAuth.getInstance();
        this.view=view;
        userCashing = UserCashing.getInstance(context);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(context, gso);
    }


    public void logout() {
        userCashing.clearCache();
        auth.signOut();
        googleSignInClient.signOut();

    }
    public boolean isLoggedIn() {
        return auth.getCurrentUser() != null;
    }
    public String getUsername() {
        FirebaseUser user = auth.getCurrentUser();
        return (user != null && user.getDisplayName() != null) ? user.getDisplayName() : "Guest";
    }

    public String getEmail() {
        FirebaseUser user = auth.getCurrentUser();
        return (user != null && user.getEmail() != null) ? user.getEmail() : "No email provided";
    }

    public String getPhotoUrl() {
        FirebaseUser user = auth.getCurrentUser();
        return (user != null && user.getPhotoUrl() != null) ? user.getPhotoUrl().toString() : null;
    }

    public void handleLogout() {
        logout();
        view.navigateToHomeScreen();


    }
    public void handleFetchUserData() {
        view.fetchUserData();
    }
}


