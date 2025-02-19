package com.example.yumlyst.view.authentecation.login;

import android.content.Intent;

public interface ILoginView {
    void showError(String msg);

    void navigateToHome();

    void handleGoogleSignInResult(Intent data);
}
