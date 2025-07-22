package com.example.scannerall_in_one;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.LottieAnimationView;

public class Flashscreen_activity extends AppCompatActivity {
    LottieAnimationView lottieAnimationView;
    private static final int time=4000;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashscreen);

        lottieAnimationView=findViewById(R.id.lottie);
        lottieAnimationView.setAnimation(R.raw.animation);
        lottieAnimationView.playAnimation();
        lottieAnimationView.loop(true);

        textView=findViewById(R.id.text);
        Animation bounce = AnimationUtils.loadAnimation(this, R.anim.bouncetext);
        textView.startAnimation(bounce);

        SharedPreferences prefs = getSharedPreferences("my_app_prefs", MODE_PRIVATE);
        prefs.edit().putBoolean("isFirstLaunch", false).apply();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(Flashscreen_activity.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        },time);
    }
}