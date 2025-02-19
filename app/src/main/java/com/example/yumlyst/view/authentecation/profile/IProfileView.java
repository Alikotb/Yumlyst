package com.example.yumlyst.view.authentecation.profile;

public interface IProfileView {
    void fetchUserData();

    void navigateToHomeScreen();

    void openFacebookApp(String userId);

    void shareApp();
    void contactWithLinkedIn();
    void showGitHup();
    void contactWithEmail();


}
