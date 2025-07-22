package com.example.scannerall_in_one;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.camera.view.PreviewView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class openUpiApp {
    Boolean value2=false;
    private ExecutorService cameraExecutor;
    private PreviewView previewView;
    public void openUpi(Context context, String upiUri) {
        cameraExecutor = Executors.newSingleThreadExecutor();

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose UPI App");

        String[] apps = {"Google Pay", "PhonePe", "Paytm"};
        builder.setItems(apps, (dialog, which) -> {
            String packageName = "";
            switch (which) {
                case 0:
                    packageName = "com.google.android.apps.nbu.paisa.user";
                    break;
                case 1:
                    packageName = "com.phonepe.app";
                    break;
                case 2:
                    packageName = "net.one97.paytm";
                    break;
            }

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(upiUri));
            intent.setPackage(packageName);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    try {
                        context.startActivity(intent);
                    }catch (Exception e){
                        Toast.makeText(context, "Not found", Toast.LENGTH_SHORT).show();

                    }
                }
            },1000);
            dialog.dismiss();
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

//                CameraHelper cameraHelper=new CameraHelper(previewView,cameraExecutor);
//                cameraHelper.startCamera((Activity) context,value2);


            }
        });
        builder.setCancelable(true);

        AlertDialog dialog = builder.create();
        dialog.show();


    }
}
