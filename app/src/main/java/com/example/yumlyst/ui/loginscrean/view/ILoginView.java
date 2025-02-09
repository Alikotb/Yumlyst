package com.example.yumlyst.ui.loginscrean.view;

import android.content.Intent;

public interface ILoginView {
    void showError(String msg);
    void navigateToHome();
    void handleGoogleSignInResult(Intent data);
}
