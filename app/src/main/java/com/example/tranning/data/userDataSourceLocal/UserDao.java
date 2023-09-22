package com.example.tranning.data.userDataSourceLocal;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM UserEntity")
    List<UserEntity> getAllUserLocal();

    @Insert
    void addUserLocal(UserEntity user);


}
