package com.example.scannerall_in_one;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.camera.core.ImageProxy;
import androidx.camera.lifecycle.ProcessCameraProvider;

import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;

public class barcodeprocess {
    String value;
    int format;

    @SuppressLint("UnsafeOptInUsageError")
    public void scanBarcode(ImageProxy imageProxy, Context context, ProcessCameraProvider cameraProvider) {
        openUpiApp openUpiApp=new openUpiApp();


        @SuppressWarnings("UnsafeOptInUsageError")
        ImageProxy.PlaneProxy[] planes = imageProxy.getPlanes();

        if (planes.length > 0 && imageProxy.getImage() != null) {
            InputImage image = InputImage.fromMediaImage(
                    imageProxy.getImage(),
                    imageProxy.getImageInfo().getRotationDegrees()
            );

            BarcodeScanner scanner = BarcodeScanning.getClient();

            scanner.process(image)
                    .addOnSuccessListener(barcodes -> {
                        if (!barcodes.isEmpty()) {
                            for (Barcode barcode : barcodes) {
                                value = barcode.getRawValue();
                                format = barcode.getFormat();

                            }

                            if (value != null && value.startsWith("upi://pay")) {
                                openUpiApp.openUpi(context, value);
                                cameraProvider.unbindAll();

                            } else if (value.startsWith("http://") || value.startsWith("https://")) {
                                Toast.makeText(context, "link \n"+value, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(value));
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
                                cameraProvider.unbindAll();



                            } else if (format == Barcode.FORMAT_EAN_13 || format == Barcode.FORMAT_CODE_128 || format == Barcode.FORMAT_UPC_A) {
                                Toast.makeText(context, "Scanned Product Code: " + value, Toast.LENGTH_SHORT).show();
                                
                            }
                        }
                        imageProxy.close();
                    })
                    .addOnFailureListener(e -> {
                        imageProxy.close();
                        Log.e("SCANNER", "Scan failed", e);
                    });
        } else {
            imageProxy.close();
        }
    }

}
