package com.example.tranning.screen.signUp;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tranning.data.authFirebase.AuthRepository;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SignUpViewModel extends ViewModel {

    private final MutableLiveData<Boolean> signupResult = new MutableLiveData<>();

    private AuthRepository authRepository;

    @Inject
    public SignUpViewModel(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }


    public MutableLiveData<Boolean> getSignupResult() {
        return signupResult;
    }

    public void signup(String email, String password) {
        LiveData<Boolean> result = authRepository.signup(email,password);

        result.observeForever(isLoggedIn -> {
            signupResult.postValue(isLoggedIn);

        });
    }

}
