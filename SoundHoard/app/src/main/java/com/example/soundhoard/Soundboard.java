package com.example.soundhoard;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Soundboard {
    @PrimaryKey(autoGenerate = true)
    public int soundboard_id;

    @ColumnInfo(name = "soundboard_name")
    public String soundboardName;

    public Soundboard() {
        setName("Example");
    }

    public String getName() {
        return this.soundboardName;
    }

    public void setName(String name) {
        this.soundboardName = name;
    }
}