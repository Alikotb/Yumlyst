package com.example.yumlyst.ui.authentecation.loginscrean.presenter;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.example.yumlyst.R;
import com.example.yumlyst.database.UserCashing;
import com.example.yumlyst.ui.authentecation.loginscrean.view.ILoginView;
import com.example.yumlyst.ui.authentecation.loginscrean.view.Login;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginPresenter {

    private static UserCashing userCashing;
    private final FirebaseAuth auth;
    private final ILoginView view;
    private final GoogleSignInClient googleSignInClient;

    public LoginPresenter(@NonNull ILoginView view, @NonNull Context context) {
        this.auth = FirebaseAuth.getInstance();
        this.view = view;
        userCashing = UserCashing.getInstance(context);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(context, gso);
    }

    public void login(String email, String password) {
        if (!validateInputs(email, password)) return;

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        userCashing.cacheUser(email, auth.getCurrentUser().getDisplayName());
                        view.navigateToHome();
                    } else {
                        view.showError(task.getException() != null ? task.getException().getMessage() : "Login failed.");
                    }
                });
    }

    public void signInWithGoogle() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        if (view instanceof Login) {
            ((Login) view).getGoogleSignInLauncher().launch(signInIntent);
        }
    }

    public void handleGoogleSignInResult(Intent data) {
        GoogleSignIn.getSignedInAccountFromIntent(data)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        String idToken = task.getResult().getIdToken();
                        firebaseAuthWithGoogle(idToken);
                        //cahes user in shared pref

                    } else {
                        view.showError("Google Sign-In failed!");
                    }
                });
    }

    public void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        auth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        userCashing.cacheUser(auth.getCurrentUser().getDisplayName(), auth.getCurrentUser().getEmail());
                        view.navigateToHome();
                    } else {
                        view.showError(task.getException() != null ? task.getException().getMessage() : "Google Sign-In failed.");
                    }
                });
    }

    private boolean validateInputs(String email, String password) {
        if (email == null || email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            view.showError("Enter a valid email!");
            return false;
        }
        if (password == null || password.length() < 6) {
            view.showError("Password must be at least 6 characters!");
            return false;
        }
        return true;
    }


}
