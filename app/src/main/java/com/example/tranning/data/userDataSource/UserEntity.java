package com.example.tranning.data.userDataSource;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class UserEntity {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "name")

    private String name;
    @ColumnInfo(name = "realName")

    private String realName;
    @ColumnInfo(name = "team")

    private String team;
    @ColumnInfo(name = "firstAppearance")

    private String firstAppearance;

    public UserEntity( String name, String realName, String team, String firstAppearance) {
        this.name = name;
        this.realName = realName;
        this.team = team;
        this.firstAppearance = firstAppearance;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getFirstAppearance() {
        return firstAppearance;
    }

    public void setFirstAppearance(String firstAppearance) {
        this.firstAppearance = firstAppearance;
    }
}
