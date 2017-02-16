package com.bignerdranch.android.olutkellari;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.accessibility.AccessibilityManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

public class ArvosteluValintaActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arvostelu_valinta);

        Button viivakoodi = (Button) findViewById(R.id.viivakoodiValinta);
        Button eiviivakoodia = (Button) findViewById(R.id.ilmanViivakoodiaValinta);

        viivakoodi.setOnClickListener(this);
        eiviivakoodia.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int viiva = R.id.viivakoodiValinta;
        int ilman = R.id.ilmanViivakoodiaValinta;
        int id = v.getId();
        if(id == viiva){
            if (ActivityCompat.checkSelfPermission(ArvosteluValintaActivity.this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(ArvosteluValintaActivity.this, new String[]{android.Manifest.permission.CAMERA}, 1);
            }else{
                Intent intent = new Intent(this, SkannaaActivity.class);
                startActivityForResult(intent,0);
            }
        }else if (id == ilman){
            Intent intent = new Intent(getApplicationContext(), ArvosteleOlutActivity.class);
            startActivity(intent);
        }

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode==0){

            if(resultCode == CommonStatusCodes.SUCCESS){
                if(data!=null){
                    Barcode barcode = data.getParcelableExtra("barcode");
                    Intent intent = new Intent(getApplicationContext(), ArvosteleOlutActivity.class);
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
                    Intent intent = new Intent(this, ArvosteluValintaActivity.class);
                    Toast.makeText(this,"Viivakoodinluku tarvitsee oikeudet kameraan!",Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
