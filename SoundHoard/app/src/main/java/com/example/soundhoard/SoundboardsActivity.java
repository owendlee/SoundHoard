package com.example.soundhoard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.transition.Explode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class SoundboardsActivity extends AppCompatActivity implements SoundboardDialog.SoundboardDialogListener {
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private MyAdapter mAdapter;
    private AppDatabase database;
    private ArrayList<Soundboard> soundboards;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soundboards);
        getSupportActionBar().setTitle(R.string.soundboards_activity_actionbar_title);

        executeExplodeTransition();

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        database = AppDatabase.getInstance(getApplicationContext());
        soundboards = (ArrayList<Soundboard>)database.soundboardDao().getAll();

        mAdapter = new MyAdapter(soundboards);
        recyclerView.setAdapter(mAdapter);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private ArrayList<Soundboard> mDataset;

        public MyAdapter(ArrayList<Soundboard> myDataset) {
            mDataset = myDataset;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextView;

            public ViewHolder(TextView v) {
                super(v);
                mTextView = v;
            }
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.soundboard_item, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            final Soundboard data = mDataset.get(position);
            holder.mTextView.setText(data.getName());
            holder.mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(SoundboardsActivity.this, SoundboardActivity.class);
                    intent.putExtra("soundboardName", data.getName());
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mDataset.size();
        }
    }

    @Override
    public void applyText(String name) {
        if(name.isEmpty()) {
            database.soundboardDao().insertAll(new Soundboard());
        } else {
            database.soundboardDao().insertAll(new Soundboard(name));
        }
        mAdapter.mDataset = (ArrayList<Soundboard>)database.soundboardDao().getAll();
        mAdapter.notifyDataSetChanged();
        Toast.makeText(SoundboardsActivity.this, R.string.soundboards_activity_soundboard_created_toast, Toast.LENGTH_SHORT).show();
    }

    public void executeExplodeTransition() {
        Explode explode = new Explode();
        explode.setDuration(500);
        getWindow().setEnterTransition(explode);
    }

    public void openDialog() {
        SoundboardDialog soundboardDialog = new SoundboardDialog();
        soundboardDialog.show(getSupportFragmentManager(), "dialog");
    }
}
