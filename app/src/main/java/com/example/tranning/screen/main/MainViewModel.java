package com.example.tranning.screen.main;

import androidx.lifecycle.ViewModel;

import com.example.tranning.data.UserRepository;
import com.example.tranning.model.User;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Call;

@HiltViewModel
public class MainViewModel extends ViewModel {

    private final UserRepository repository;



    @Inject
    public MainViewModel( UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAll() {
        return repository.getAllUserLocal();
    }

    public void addUser(User user){
         repository.addUserLocal(user);
    }

    public Call<List<User>> getApiListUser(){
        return repository.getApiListUser();
    }
}
