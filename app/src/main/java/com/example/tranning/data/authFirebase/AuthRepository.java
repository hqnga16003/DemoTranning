package com.example.tranning.data.authFirebase;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

public class AuthRepository {
    private FirebaseAuth firebaseAuth;


    @Inject
    public AuthRepository(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;
    }

    public LiveData<Boolean> login(String email, String password) {
        MutableLiveData<Boolean> loginResult = new MutableLiveData<>();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            loginResult.postValue(true);
                        }
                        else{
                            loginResult.postValue(false);
                        }

                    }
                });
        return loginResult;
    }

    public LiveData<Boolean> signup(String email, String password) {
        MutableLiveData<Boolean> signupResult = new MutableLiveData<>();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            signupResult.postValue(true);
                        }
                        else{
                            signupResult.postValue(false);
                        }

                    }
                });
        return signupResult;
    }

    public void logout() {
        firebaseAuth.signOut();
    }

    public FirebaseUser getFirebaseUser() {
        return firebaseAuth.getCurrentUser();
    }
}
