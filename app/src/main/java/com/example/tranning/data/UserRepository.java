package com.example.tranning.data;

import com.example.tranning.data.userDataSourceLocal.UserDao;
import com.example.tranning.data.userDataSourceLocal.UserEntity;
import com.example.tranning.model.User;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class UserRepository  {
    private AppDatabase db;
    private UserDao userDao ;


    @Inject
    public UserRepository(AppDatabase db) {
        this.db = db;
        this.userDao = db.userDao();
    }

    public List<User> getAll(){

        List<User> users =  new ArrayList<>();
        List<UserEntity> userEntities = userDao.getAll();
        for (UserEntity u : userEntities){
            User user = new User(u.getName(),u.getRealName(),u.getTeam(),u.getFirstAppearance());
            users.add(user);
        }
        return  users;
    }

    public void addUser(User user){
        UserEntity u = new UserEntity(user.getName(),
                user.getRealName(),user.getTeam(),user.getFirstAppearance());

        userDao.insertUser(u);

    }
}
