package com.example.soundhoard;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Sound {
    @PrimaryKey(autoGenerate = true)
    public int sound_id;

    @ColumnInfo(name = "sound_name")
    public String soundName;

    @ColumnInfo(name = "sound_uri")
    public String soundUri;

    public Sound() {
        setName("");
        setUri(null);
    }

    public Sound(String name, String uri) {
        setName(name);
        setUri(uri);
    }

    public int getId() {
        return this.sound_id;
    }

    public String getName() {
        return this.soundName;
    }

    public void setName(String name) {
        this.soundName = name;
    }

    public String getUri() {
        return this.soundUri;
    }

    public void setUri(String uri) {
        this.soundUri = uri;
    }
}