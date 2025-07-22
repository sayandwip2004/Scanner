package com.example.scannerall_in_one;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;




public class Scanner extends AppCompatActivity {
    private PreviewView previewView;
    private ExecutorService cameraExecutor;
    private boolean scanned = false;

    private ProcessCameraProvider cameraProvider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_scanner);

//   scanner laser
        previewView = findViewById(R.id.previewView);
        View laser = findViewById(R.id.laser);
        previewView.post(() -> {
            float previewHeight = previewView.getHeight();

            ObjectAnimator animator = ObjectAnimator.ofFloat(
                    laser,
                    "translationY",
                    0f,
                    previewHeight
            );

            animator.setRepeatMode(ValueAnimator.REVERSE);
            animator.setRepeatCount(ValueAnimator.INFINITE);
            animator.setDuration(2000); // 2 seconds for one full pass
            animator.setInterpolator(new LinearInterpolator());
            animator.start();
        });
        cameraExecutor = Executors.newSingleThreadExecutor();
        CameraHelper cameraHelper=new CameraHelper(previewView,cameraExecutor);
        cameraHelper.startCamera(this,scanned);

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        cameraExecutor.shutdown();
    }

    @Override
    protected void onResume() {
        super.onResume();
        scanned = false;
        CameraHelper cameraHelper=new CameraHelper(previewView,cameraExecutor);
        cameraHelper.startCamera(this,scanned);
    }








}