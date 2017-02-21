package com.bignerdranch.android.olutkellari;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

public class HaeViivakoodillaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, SkannaaActivity.class);
        if (ActivityCompat.checkSelfPermission(HaeViivakoodillaActivity.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(HaeViivakoodillaActivity.this, new String[]{android.Manifest.permission.CAMERA}, 1);
        }else{
            startActivityForResult(intent,0);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode==0){

            if(resultCode == CommonStatusCodes.SUCCESS){
                if(data!=null){
                    Barcode barcode = data.getParcelableExtra("barcode");
                    Intent intent = new Intent(HaeViivakoodillaActivity.this, SelaaOluita.class);

                    String barcodeValue = barcode.rawValue;
                    boolean onkoInteger = barcodeValue.matches("\\d+");
                    if(onkoInteger){

                        intent.putExtra("barcodeValue",barcodeValue );
                        startActivity(intent);

                    }else{
                        Toast.makeText(this,"Viivakoodin luku epäonnistui! Yritä uudelleen", Toast.LENGTH_LONG).show();
                    }

                }
            }

        }else{
            super.onActivityResult(requestCode,resultCode,data);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(this, SkannaaActivity.class);
                    startActivityForResult(intent,0);
                } else {
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(this,"Viivakoodinluku tarvitsee oikeudet kameraan!",Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
