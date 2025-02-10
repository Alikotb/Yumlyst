package com.example.yumlyst.ui.authentecation.loginscrean.view;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.yumlyst.R;
import com.example.yumlyst.ui.authentecation.loginscrean.presenter.LoginPresenter;

public class Login extends Fragment implements ILoginView {

    private Button loginBtn, signUpBtn, googleBtn;
    private EditText edtEmail, edtPassword;
    private LoginPresenter loginPresenter;

    private final ActivityResultLauncher<Intent> googleSignInLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == getActivity().RESULT_OK && result.getData() != null) {
                    handleGoogleSignInResult(result.getData());
                } else {
                    showError("Google Sign-In failed!");
                }
            }
    );

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginPresenter = new LoginPresenter(this, requireContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViews(view);
        setListeners();
    }

    private void initializeViews(View view) {
        loginBtn = view.findViewById(R.id.login_loginpage_button);
        signUpBtn = view.findViewById(R.id.signUp_loginpage_btn);
        googleBtn = view.findViewById(R.id.loginWithGoogle_button);
        edtEmail = view.findViewById(R.id.edtEmail);
        edtPassword = view.findViewById(R.id.edtpassword);
    }

    private void setListeners() {
        loginBtn.setOnClickListener(v -> loginPresenter.login(
                edtEmail.getText().toString().trim(),
                edtPassword.getText().toString().trim()
        ));

        signUpBtn.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.action_login_to_signUp));

        googleBtn.setOnClickListener(v -> loginPresenter.signInWithGoogle());
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToHome() {
        Navigation.findNavController(requireView()).navigate(R.id.action_login_to_home2);
    }

    @Override
    public void handleGoogleSignInResult(Intent data) {
        loginPresenter.handleGoogleSignInResult(data);
    }

    public ActivityResultLauncher<Intent> getGoogleSignInLauncher() {
        return googleSignInLauncher;
    }
}
