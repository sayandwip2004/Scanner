package com.example.scannerall_in_one;

import android.app.Activity;
import android.content.Context;

import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.Preview;
import androidx.camera.core.UseCase;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

public class CameraHelper {
    private final PreviewView previewView;
    private final ExecutorService cameraExecutor;
    private ProcessCameraProvider cameraProvider;
    public CameraHelper(PreviewView previewView, ExecutorService cameraExecutor) {
        this.previewView = previewView;
        this.cameraExecutor = cameraExecutor;
    }

    public void startCamera(Activity activity, Boolean scanned) {
        barcodeprocess barcodeprocess=new barcodeprocess();
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture =
                ProcessCameraProvider.getInstance(activity);
        cameraProviderFuture.addListener(new Runnable() {
            @Override
            public void run() {
                try {
                    cameraProvider = cameraProviderFuture.get();
                    cameraProvider.unbindAll();


                    Preview preview = new Preview.Builder().build();
                    preview.setSurfaceProvider(previewView.getSurfaceProvider());

                    ImageAnalysis imageAnalysis = new ImageAnalysis.Builder()
                            .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                            .build();


                    imageAnalysis.setAnalyzer(cameraExecutor, image -> {
                        if (!scanned) {
                            barcodeprocess.scanBarcode(image,activity,cameraProvider);


                        } else {
                            image.close();
                        }
                    });

                    CameraSelector cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA;
                    cameraProvider.bindToLifecycle((LifecycleOwner) activity, cameraSelector, preview, imageAnalysis);

                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, ContextCompat.getMainExecutor(activity));

    }
}
