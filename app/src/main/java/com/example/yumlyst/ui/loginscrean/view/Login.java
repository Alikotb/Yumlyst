package com.example.yumlyst.ui.loginscrean.view;

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


public class Login extends Fragment implements OnclickListneres {

    Button loginBtn;
    Button signUpBtn;
    Button googleBtn;
    EditText email;
    EditText password;
    String emailTxt,passwordTxt;

    public Login() {
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
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findById();
        setListeners();
    }

    @Override
    public void setListeners() {
        loginBtn.setOnClickListener(v->{
             emailTxt = email.getText().toString();
             passwordTxt = password.getText().toString();
            Navigation.findNavController(v).navigate(R.id.action_login_to_home2);

        });
        signUpBtn.setOnClickListener(v->{
            Navigation.findNavController(v).navigate(R.id.action_login_to_signUp);
        });

        googleBtn.setOnClickListener(v->{
            Navigation.findNavController(v).navigate(R.id.action_login_to_home2);
        });

    }
    private void findById(){
        loginBtn = getActivity().findViewById(R.id.login_loginpage_button);
        signUpBtn = getActivity().findViewById(R.id.signUp_loginpage_btn);
        googleBtn = getActivity().findViewById(R.id.loginWithGoogle_button);
        email = getActivity().findViewById(R.id.edtEmail);
        password = getActivity().findViewById(R.id.edtpassword);

    }
}