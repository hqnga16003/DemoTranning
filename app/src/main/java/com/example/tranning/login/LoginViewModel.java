package com.example.tranning.login;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LoginViewModel extends ViewModel {

    private MutableLiveData<Boolean> isLoginLiveData = new MutableLiveData<>();

    final FirebaseAuth firebaseAuth;


    {
        Log.d("XXX", "LoginViewModel");

    }

    public MutableLiveData<Boolean> getIsLoginLiveData() {
        return isLoginLiveData;
    }


    public FirebaseUser getFirebaseUser() {
        return firebaseAuth.getCurrentUser();
    }

    public void logout() {
        firebaseAuth.signOut();
    }


    @Inject
    public LoginViewModel(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;
    }

    public void login(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            isLoginLiveData.setValue(true);
                        }
                        else
                            isLoginLiveData.setValue(false);
                    }
                });

    }
}
