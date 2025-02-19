package com.example.yumlyst.view.authentecation.signup;

public interface ISignView {
    void showError(String msg);

    void showEmailError(String msg);

    void showUsernameError(String msg);

    void showPassError(String msg);

    void showConfirmPassError(String msg);

    void navigateToHome();
}
