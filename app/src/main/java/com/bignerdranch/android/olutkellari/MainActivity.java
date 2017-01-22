package com.bignerdranch.android.olutkellari;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button selaaOluita = (Button) findViewById(R.id.selaaOluita);
        Button arvosteleOlut = (Button) findViewById(R.id.arvosteleOlut);
        Button profiili = (Button) findViewById(R.id.profiili);

        selaaOluita.setOnClickListener((View.OnClickListener) this);
        arvosteleOlut.setOnClickListener((View.OnClickListener) this);
        profiili.setOnClickListener((View.OnClickListener) this);

    }

        void onClick(View v){

            final int selaa = R.id.selaaOluita;
            final int arvostele = R.id.arvosteleOlut;
            final int profiili = R.id.profiili;
            int id = v.getId();
            if(id == selaa){

            }else if(id == arvostele){

            }else if(id == profiili){

            }

        }
}
