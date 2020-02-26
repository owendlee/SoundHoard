package com.example.soundhoard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SoundboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soundboard);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}