package com.example.soundhoard;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setTitle("Settings");

        executeExplodeTransition();
    }

    public void executeExplodeTransition() {
        Explode explode = new Explode();
        explode.setDuration(1000);
        getWindow().setEnterTransition(explode);
    }
}
