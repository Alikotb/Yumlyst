package com.example.yumlyst.ui.authentecation.signupscrean.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Patterns;

import com.example.yumlyst.ui.authentecation.UserCashing;
import com.example.yumlyst.ui.authentecation.signupscrean.view.ISignView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignUpPresenter {

    private final FirebaseAuth auth;
    private ISignView view;
    SharedPreferences sharedPrefUser;
    private static UserCashing userCashing;


    public SignUpPresenter(ISignView view , Context context) {
        auth = FirebaseAuth.getInstance();
        this.view=view;
        userCashing = UserCashing.getInstance(context);;

    }

    public void signUp(String name, String email, String password, String confirmPassword) {
        if (!validateInputs(name, email, password, confirmPassword)) {
            return;
        }
        auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    FirebaseUser user = authResult.getUser();
                    if (user != null) {
                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                .setDisplayName(name)
                                .build();

                        user.updateProfile(profileUpdates)
                                .addOnCompleteListener(task -> {
                                    if (task.isSuccessful()) {
                                        //cahes user in shared pref
                                        userCashing.cacheUser(name, email);
                                        view.navigateToHome();
                                    } else {
                                        view.showError(task.getException().getMessage());
                                    }
                                });
                    } else {
                        view.showError("User creation failed.");
                    }
                })
                .addOnFailureListener(e -> view.showError(e.getMessage()));
    }
    private boolean validateInputs(String username, String email, String password, String confirmPassword) {

        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            view.showEmailError("Enter a valid email!");
            return false;
        }

        if (TextUtils.isEmpty(username)) {
            view.showUsernameError("Username is required!");
            return false;
        }


        if (TextUtils.isEmpty(password) || password.length() < 6) {
            view.showPassError("Password must be at least 6 characters!");
            return false;
        }

        if (!password.equals(confirmPassword)) {
            view.showConfirmPassError("Passwords do not match!");
            return false;
        }

        return true;
    }



}
