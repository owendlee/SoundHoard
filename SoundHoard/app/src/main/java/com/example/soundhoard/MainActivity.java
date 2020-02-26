package com.example.soundhoard;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView myImage;
    private Button soundboardsButton;
    private Button settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myImage = findViewById(R.id.imageView);
        myImage.setImageResource(R.drawable.soundhoard);

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
}