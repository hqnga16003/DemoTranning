package com.example.tranning.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.tranning.data.userDataSource.UserDao;
import com.example.tranning.data.userDataSource.UserEntity;

@Database(entities = {UserEntity.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
