package com.example.soundhoard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class SoundboardActivity extends AppCompatActivity implements SoundboardDialog.SoundboardDialogListener {
    AppDatabase database;
    Soundboard soundboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soundboard);

        Intent intent = getIntent();
        soundboard = (Soundboard)intent.getSerializableExtra("soundboard");

        getSupportActionBar().setTitle(soundboard.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_soundboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home: // setDisplayHomeAsUpEnabled action
                onBackPressed();
                return true;
            case R.id.editSoundboardName:
                openDialog();
            case R.id.deleteSoundboard:
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void applyText(String name) {
        int id = soundboard.getId();
        database = database.getInstance(this);

        database.soundboardDao().updateNameById(name, id);
        soundboard = database.soundboardDao().loadById(id);
        getSupportActionBar().setTitle(soundboard.getName());
        Toast.makeText(SoundboardActivity.this, R.string.soundboard_activity_name_edited, Toast.LENGTH_SHORT).show();
    }

    public void openDialog() {
        SoundboardDialog soundboardDialog = new SoundboardDialog(SoundboardDialog.UPDATE_DIALOG);
        soundboardDialog.show(getSupportFragmentManager(), "dialog");
    }
}