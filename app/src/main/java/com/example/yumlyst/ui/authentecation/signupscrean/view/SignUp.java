package com.example.yumlyst.ui.authentecation.signupscrean.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.yumlyst.R;
import com.example.yumlyst.ui.OnclickListneres;
import com.example.yumlyst.ui.authentecation.signupscrean.presenter.SignUpPresenter;

public class SignUp extends Fragment implements OnclickListneres, ISignView {

    private Button signUpBtn;
    private EditText edtEmail, edtPassword, edtUsername, edtConfirmPassword;
    private SignUpPresenter signUpPresenter;

    public SignUp() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signUpPresenter = new SignUpPresenter(this,requireContext());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findById(view);
        setListeners();
    }

    @Override
    public void setListeners() {
        signUpBtn.setOnClickListener(this::handleSignUp);


    }

    private void findById(View view) {
        signUpBtn = view.findViewById(R.id.signUp_button);
        edtEmail = view.findViewById(R.id.edtEmail);
        edtPassword = view.findViewById(R.id.edtpassword);
        edtUsername = view.findViewById(R.id.edtUserName);
        edtConfirmPassword = view.findViewById(R.id.edtconfirmpassword);
    }

    private void handleSignUp(View v) {
        String emailTxt = edtEmail.getText().toString().trim();
        String passwordTxt = edtPassword.getText().toString().trim();
        String usernameTxt = edtUsername.getText().toString().trim();
        String confirmPasswordTxt = edtConfirmPassword.getText().toString().trim();
        signUpPresenter.signUp(usernameTxt, emailTxt, passwordTxt, confirmPasswordTxt);
    }


    @Override
    public void showError(String msg) {
        Toast.makeText(getContext(), "Sign-up failed: " + msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showEmailError(String msg) {
        edtEmail.setError(msg);
    }

    @Override
    public void showUsernameError(String msg) {
        edtUsername.setError(msg);
    }

    @Override
    public void showPassError(String msg) {
        edtPassword.setError(msg);
    }

    @Override
    public void showConfirmPassError(String msg) {
        edtConfirmPassword.setError(msg);
    }

    @Override
    public void navigateToHome() {
        Navigation.findNavController(requireView()).navigate(R.id.action_signUp_to_home22);
    }
}
