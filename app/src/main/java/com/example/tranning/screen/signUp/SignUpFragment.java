package com.example.tranning.screen.signUp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
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
public class SignUpFragment extends Fragment {
    private EditText edEmail, edPassword;
    private Button  btSignUp;
    private ProgressBar progressBarSignup;
    private SignUpViewModel signUpViewModel;

    public SignUpFragment() {

    }


    public static SignUpFragment newInstance(String param1, String param2) {
        SignUpFragment fragment = new SignUpFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
        signUpViewModel = new ViewModelProvider(this).get(SignUpViewModel.class);



    }

    @Override
    public void onResume() {
        super.onResume();



    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edEmail = view.findViewById(R.id.edEmailSignup);
        edPassword = view.findViewById(R.id.edPasswordSignup);
        btSignUp = view.findViewById(R.id.btSignUpFmSignup);
        progressBarSignup = view.findViewById(R.id.prLoadingSignup);



        btSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!edEmail.getText().toString().isEmpty() && !edPassword.getText().toString().isEmpty()) {
                    progressBarSignup.setVisibility(View.VISIBLE);
                    signUpViewModel.signup(edEmail.getText().toString(), edPassword.getText().toString());
                }
            }
        });

        signUpViewModel.getSignupResult().observe(getViewLifecycleOwner(), isRegistered -> {
            if (isRegistered) {
                progressBarSignup.setVisibility(View.GONE);
                NavHostFragment.findNavController(getActivity().getSupportFragmentManager().
                                findFragmentById(R.id.nav_host_fragment))
                        .navigate(R.id.mainFragment);
            } else {
                progressBarSignup.setVisibility(View.GONE);
                Toast.makeText(requireContext(), "SignUp failed.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}