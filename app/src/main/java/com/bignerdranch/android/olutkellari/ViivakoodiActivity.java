package com.bignerdranch.android.olutkellari;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

public class ViivakoodiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viivakoodi);

    }

    public void skannaaKoodi(View v){
        Intent intent = new Intent(this, SkannaaActivity.class);
        startActivityForResult(intent,0);
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

                }else{

                }
            }

        }else{
            super.onActivityResult(requestCode,resultCode,data);
        }
    }
}
