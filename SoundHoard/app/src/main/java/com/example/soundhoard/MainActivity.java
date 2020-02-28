package com.example.soundhoard;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView myImage;
    private Button soundboardsButton;
    private Button settingsButton;
    ConstraintLayout rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myImage = findViewById(R.id.imageView);
        myImage.setImageResource(R.drawable.soundhoard);
        chooseRandomBackground();

        soundboardsButton = findViewById(R.id.soundboardsButton);
        soundboardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(MainActivity.this, Soundboards.class);
            }
        });

        settingsButton = findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(MainActivity.this, Settings.class);
            }
        });
    }

    public void openActivity(Context context, Class<?> thisClass) {
        Intent intent = new Intent(context, thisClass);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    public void chooseRandomBackground() {
        rootLayout = findViewById(R.id.rootLayout);

        // int randomNumber = (int)(Math.random() * (largestNumberOfBackground - 1)) + 1;
        // upper bound must be updated when additional app backgrounds are added
        int randomNumber = (int)(Math.random() * (5 - 1)) + 1;
        switch(randomNumber) {
            case 1:
                rootLayout.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.background1));
                return;
            case 2:
                rootLayout.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.background2));
                return;
            case 3:
                rootLayout.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.background3));
                return;
            case 4:
                rootLayout.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.background4));
                return;
            case 5:
                rootLayout.setBackground(ContextCompat.getDrawable(MainActivity.this, R.drawable.background5));
                return;
        }
    }
}