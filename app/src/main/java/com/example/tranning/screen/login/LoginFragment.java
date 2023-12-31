package com.example.tranning.screen.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.tranning.R;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class LoginFragment extends Fragment {

    private EditText edEmail, edPassword;
    private Button btLogin, btSignUp;
    private ProgressBar progressBar;
    private LoginViewModel loginViewModel;


    public LoginFragment() {

    }


    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        if (loginViewModel.getFirebaseUser()!=null){
            NavHostFragment.findNavController(getActivity().getSupportFragmentManager().
                            findFragmentById(R.id.nav_host_fragment))
                    .navigate(R.id.mainFragment);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edEmail = view.findViewById(R.id.edEmailLogin);
        edPassword = view.findViewById(R.id.edPasswordLogin);
        btLogin = view.findViewById(R.id.btLoginFmLogin);
        btSignUp = view.findViewById(R.id.btSignUpFmLogin);
        progressBar = view.findViewById(R.id.prLoadingLogin);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edEmail.getText().toString().isEmpty() && !edPassword.getText().toString().isEmpty()) {
                    progressBar.setVisibility(View.VISIBLE);

                    loginViewModel.login(edEmail.getText().toString(), edPassword.getText().toString());
                }
            }
        });

        loginViewModel.getLoginResult().observe(getViewLifecycleOwner(), isLoggedIn -> {

            if (isLoggedIn) {
                NavHostFragment.findNavController(getActivity().getSupportFragmentManager().
                                findFragmentById(R.id.nav_host_fragment))
                        .navigate(R.id.mainFragment);
                progressBar.setVisibility(View.GONE);

            } else {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(requireContext(), "Login failed. Please check your credentials.", Toast.LENGTH_SHORT).show();
            }
        });
        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(getActivity().getSupportFragmentManager().
                                findFragmentById(R.id.nav_host_fragment))
                        .navigate(R.id.signUpFragment);
            }
        });


    }
}