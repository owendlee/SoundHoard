package com.example.soundhoard;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SoundDao {
    @Query("SELECT * FROM Sound")
    List<Sound> getAll();

    @Query("SELECT * FROM Sound WHERE sound_id = (:id) LIMIT 1")
    Sound loadById(int id);

    @Query("SELECT * FROM Sound WHERE sound_id IN (:soundIds)")
    List<Sound> loadAllByIds(int[] soundIds);

    @Query("SELECT * FROM Sound WHERE sound_name LIKE (:soundName) LIMIT 1")
    Sound findByName(String soundName);

    @Query("UPDATE Sound SET sound_name = (:newSoundName) WHERE sound_id = (:id)")
    void updateNameById(String newSoundName, int id);

    @Query("DELETE FROM Sound WHERE sound_id = (:id)")
    void deleteById(int id);

    @Insert
    long insert(Sound sound);

    @Delete
    void delete(Sound sound);
}