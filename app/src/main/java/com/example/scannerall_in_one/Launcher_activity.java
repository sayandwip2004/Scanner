package com.example.scannerall_in_one;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Launcher_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        SharedPreferences prefs = getSharedPreferences("my_app_prefs", MODE_PRIVATE);
        boolean isFirstTime = prefs.getBoolean("isFirstLaunch", true);
        if(isFirstTime){
            startActivity(new Intent(this,Flashscreen_activity.class));
        }
        else{
            startActivity(new Intent(this,MainActivity.class));
        }
        finish();

    }
}