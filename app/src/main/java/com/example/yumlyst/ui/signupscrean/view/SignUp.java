package com.example.yumlyst.ui.signupscrean.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.yumlyst.R;
import com.example.yumlyst.ui.OnclickListneres;


public class SignUp extends Fragment implements OnclickListneres {

    Button signUpBtn;
    Button googleBtn;
    Button loginBtn;
    EditText email;
    EditText password;
    EditText username;
    EditText confirmPassword;
    String emailTxt,passwordTxt,usernameTxt,confirmPasswordTxt;

    public SignUp() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findById();
        setListeners();
    }

    @Override
    public void setListeners() {
        signUpBtn.setOnClickListener(v->{
            emailTxt = email.getText().toString();
            passwordTxt = password.getText().toString();
            usernameTxt = username.getText().toString();
            confirmPasswordTxt = confirmPassword.getText().toString();

            Navigation.findNavController(v).navigate(R.id.action_signUp_to_login);
        });
        googleBtn.setOnClickListener(v->{
            Navigation.findNavController(v).navigate(R.id.action_signUp_to_home22);
        });
        loginBtn.setOnClickListener(v->{
            Navigation.findNavController(v).navigate(R.id.action_signUp_to_login);
        });

    }
    private void findById(){
        signUpBtn = getActivity().findViewById(R.id.signUp_button);
        googleBtn = getActivity().findViewById(R.id.loginWithGoogle_signupPage_button);
        loginBtn = getActivity().findViewById(R.id.have_account_button);
        email = getActivity().findViewById(R.id.edtEmail);
        password = getActivity().findViewById(R.id.edtpassword);
        username = getActivity().findViewById(R.id.edtUserName);
        confirmPassword = getActivity().findViewById(R.id.edtconfirmpassword);

    }
}