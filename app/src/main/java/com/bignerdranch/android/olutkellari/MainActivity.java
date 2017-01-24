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


        arvosteleOlut.setOnClickListener(this);
        profiili.setOnClickListener(this);

    }

        public void onClick(View v){


            final int arvostele = R.id.arvosteleOlut;
            final int profiili = R.id.profiili;
            int id = v.getId();
          if(id == arvostele){
                Intent intent = new Intent(getApplicationContext(), ArvosteleOlutActivity.class);
                startActivity(intent);
            }else if(id == profiili){
                Intent intent = new Intent(getApplicationContext(), ProfiiliActivity.class);
                startActivity(intent);

            }

        }
}
