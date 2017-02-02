package com.bignerdranch.android.olutkellari;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
            Intent intent = new Intent(getApplicationContext(), ViivakoodiActivity.class);
            startActivity(intent);
        }else if (id == ilman){
            Intent intent = new Intent(getApplicationContext(), ArvosteleOlutActivity.class);
            startActivity(intent);
        }

    }
}
