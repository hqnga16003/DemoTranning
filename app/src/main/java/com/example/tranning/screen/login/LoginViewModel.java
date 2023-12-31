package com.example.tranning.screen.login;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.tranning.data.authFirebase.AuthRepository;
import com.google.firebase.auth.FirebaseUser;
import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LoginViewModel extends ViewModel {

    private final MutableLiveData<Boolean> loginResult = new MutableLiveData<>();

    private AuthRepository authRepository;

    public LiveData<Boolean> getLoginResult() {
        return loginResult;
    }


    public FirebaseUser getFirebaseUser() {
        return authRepository.getFirebaseUser();
    }

    public void logout() {
        authRepository.logout();
    }


    @Inject
    public LoginViewModel(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public void login(String email, String password) {
        LiveData<Boolean> result = authRepository.login(email, password);

        result.observeForever(isLoggedIn -> {
            loginResult.postValue(isLoggedIn);

        });
    }
}
