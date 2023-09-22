package com.example.tranning.data;

import com.example.tranning.data.userDataSourceLocal.AppDatabase;
import com.example.tranning.data.userDataSourceLocal.UserDao;
import com.example.tranning.data.userDataSourceLocal.UserEntity;
import com.example.tranning.data.userDataSourceRemote.RetrofitClient;
import com.example.tranning.model.User;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

public class UserRepository  {
    private AppDatabase db;
    private UserDao userDao ;
    private RetrofitClient retrofitClient;



    @Inject
    public UserRepository(AppDatabase db,RetrofitClient retrofitClient) {
        this.db = db;
        this.userDao = db.userDao();
        this.retrofitClient = retrofitClient;
    }

    public List<User> getAllUserLocal(){

        List<User> users =  new ArrayList<>();
        List<UserEntity> userEntities = userDao.getAllUserLocal();
        for (UserEntity u : userEntities){
            User user = new User(u.getName(),u.getRealName(),u.getTeam(),u.getFirstAppearance());
            users.add(user);
        }
        return  users;
    }

    public void addUserLocal(User user){
        UserEntity u = new UserEntity(user.getName(),
                user.getRealName(),user.getTeam(),user.getFirstAppearance());

        userDao.addUserLocal(u);
    }

    public Call<List<User>> getApiListUser() {
        Call<List<User>> call = retrofitClient.getMyApi().getApiListUser();
        return call;

    }
}
