package com.bignerdranch.android.olutkellari;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.SparseArray;

import android.view.SurfaceHolder;
import android.view.SurfaceView;


import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class SkannaaActivity extends AppCompatActivity {
    private SurfaceView kameraPrev;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_skannaa);
        kameraPrev = (SurfaceView) findViewById(R.id.kameraPrev);

        createCameraSource();

    }

    private void createCameraSource() {

        BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(this).build();

        final CameraSource cameraSource = new CameraSource.Builder(this, barcodeDetector)
                .setAutoFocusEnabled(true).setRequestedPreviewSize(1600, 1024).build();

        kameraPrev.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    if (ActivityCompat.checkSelfPermission(SkannaaActivity.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(SkannaaActivity.this, new String[]{android.Manifest.permission.CAMERA}, 1);
                        return;
                    }
                    cameraSource.start(kameraPrev.getHolder());

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }


        });


        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcode = detections.getDetectedItems();
                if(barcode.size() > 0){
                    Intent intent = new Intent();
                    intent.putExtra("barcode",barcode.valueAt(0));
                    setResult(CommonStatusCodes.SUCCESS,intent);
                    finish();
                }
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Intent intent = new Intent(this, ArvosteluValintaActivity.class);
                    startActivity(intent);

                }
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
