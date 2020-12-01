package com.yng.appmascotas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MyBioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bio);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}