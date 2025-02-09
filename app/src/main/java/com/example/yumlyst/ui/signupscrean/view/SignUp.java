package com.example.yumlyst.ui.signupscrean.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
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
import com.example.yumlyst.ui.signupscrean.presenter.SignUpPresenter;

public class SignUp extends Fragment implements OnclickListneres,ISignView {

    private Button signUpBtn,loginBtn;
    private EditText edtEmail, edtPassword, edtUsername, edtConfirmPassword;
    private SignUpPresenter signUpPresenter;

    public SignUp() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signUpPresenter = new SignUpPresenter(this);
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

        loginBtn.setOnClickListener(v ->
                Navigation.findNavController(v).navigate(R.id.action_signUp_to_login)
        );
    }

    private void findById(View view) {
        signUpBtn = view.findViewById(R.id.signUp_button);
        loginBtn = view.findViewById(R.id.have_account_button);
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

        if (!validateInputs(usernameTxt, emailTxt, passwordTxt, confirmPasswordTxt)) {
            return;
        }

        signUpPresenter.signUp(usernameTxt, emailTxt, passwordTxt);
    }

    private boolean validateInputs(String username, String email, String password, String confirmPassword) {

        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtEmail.setError("Enter a valid email!");
            return false;
        }

        if (TextUtils.isEmpty(username)) {
            edtUsername.setError("Username is required!");
            return false;
        }


        if (TextUtils.isEmpty(password) || password.length() < 6) {
            edtPassword.setError("Password must be at least 6 characters!");
            return false;
        }

        if (!password.equals(confirmPassword)) {
            edtConfirmPassword.setError("Passwords do not match!");
            return false;
        }

        return true;
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(getContext(), "Sign-up failed: " + msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToHome() {
        Navigation.findNavController(requireView()).navigate(R.id.action_signUp_to_home22);
    }
}
