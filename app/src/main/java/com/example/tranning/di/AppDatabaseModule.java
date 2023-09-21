package com.example.tranning.di;

import android.app.Application;

import androidx.room.Room;

import com.example.tranning.data.AppDatabase;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
@Module
@InstallIn(SingletonComponent.class)
public class AppDatabaseModule {

    @Provides
    @Singleton
    public AppDatabase provideAppDatabase(Application applicationContext) {
        return Room.databaseBuilder(
                applicationContext,
                AppDatabase.class, "user-name"
        ).allowMainThreadQueries().build();
    }


}