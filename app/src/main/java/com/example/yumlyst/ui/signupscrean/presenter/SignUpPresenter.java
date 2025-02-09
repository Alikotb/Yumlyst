package com.example.yumlyst.ui.signupscrean.presenter;

import com.example.yumlyst.ui.signupscrean.view.ISignView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignUpPresenter {

    private final FirebaseAuth auth;
    private ISignView view;

    public SignUpPresenter(ISignView view) {
        auth = FirebaseAuth.getInstance();
        this.view=view;
    }

    public void signUp(String name, String email, String password) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    FirebaseUser user = authResult.getUser();
                    if (user != null) {
                        // Update Firebase User Profile with Display Name
                        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                .setDisplayName(name)
                                .build();

                        user.updateProfile(profileUpdates)
                                .addOnCompleteListener(task -> {
                                    if (task.isSuccessful()) {
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

}
