package com.bignerdranch.android.olutkellari;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button arvosteleOlut = (Button) findViewById(R.id.arvosteleOlut);
        Button profiili = (Button) findViewById(R.id.profiili);
        Button selaaOluita = (Button) findViewById(R.id.selaaOluita);


        arvosteleOlut.setOnClickListener(this);
        profiili.setOnClickListener(this);
        selaaOluita.setOnClickListener(this);

    }

        public void onClick(View v){


            final int arvostele = R.id.arvosteleOlut;
            final int profiili = R.id.profiili;
            final int selaa = R.id.selaaOluita;
            int id = v.getId();
          if(id == arvostele){
                Intent intent = new Intent(getApplicationContext(), ArvosteluValintaActivity.class);
                startActivity(intent);
            }else if(id == profiili){
                Intent intent = new Intent(getApplicationContext(), ProfiiliActivity.class);
                startActivity(intent);

            }else if (id == selaa){
              Intent intent = new Intent(getApplicationContext(), SelaaOluita.class);
              startActivity(intent);
          }

        }
}
