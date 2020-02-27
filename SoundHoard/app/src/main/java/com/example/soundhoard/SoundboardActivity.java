package com.example.soundhoard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SoundboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soundboard);

        Intent intent = getIntent();
        String soundboardName = intent.getStringExtra("soundboardName");
        getSupportActionBar().setTitle(soundboardName);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}