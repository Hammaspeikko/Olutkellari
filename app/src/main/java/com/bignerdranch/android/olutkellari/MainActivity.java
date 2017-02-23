package com.bignerdranch.android.olutkellari;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button arvosteleOlut = (Button) findViewById(R.id.arvosteleOlut);
        Button profiili = (Button) findViewById(R.id.profiili);
        Button selaaOluita = (Button) findViewById(R.id.selaaOluita);
        Button tietosuoja = (Button) findViewById(R.id.tietosuoja);


        arvosteleOlut.setOnClickListener(this);
        profiili.setOnClickListener(this);
        selaaOluita.setOnClickListener(this);
        tietosuoja.setOnClickListener(this);

        builder = new AlertDialog.Builder(this);
        builder.setMessage("1. Tietojen kerääminen\n\n" +
                "Emme kerää sovelluksella mitään tietoja omaan käyttöön, eikä sovellus lähetä mitään tietoja ulkopuolisille. Sovellus kerää kamera kuvasta viivakoodin ID numeron, mitä sovellus käyttää olen tallentamisessa ja hakemiseen tietokannasta.\n\n" +
                "2. Tietojen käyttäminen\n\n" +
                "Sovellus käyttää puhelimesi kameraa tunnistaakseen oluen viivakoodin. Kameran kuvaamaa kuvaa ei tallenneta mihinkään. Kameran ottamasta viivakoodista etsitään viivakoodin ID, joka tallennetaan puhelimen omaan muistiin. Tätä tietoa ei lähetetä mihinkään puhelimen ulkopuolelle\n\n" +
                "3. Tietojen poistaminen\n\n" +
                "Jos haluat poistaa kaikki sovelluksen tallentamat tiedot puhelimestasi mene profiilit sivulle ja paina tyhjennä kanta. Tämän jälkeen voit poistaa sovelluksen.\n\n" +
                "4. Suostumus\n\n" +
                "Lataamalla ja käyttämällä sovelluksen hyväksyt nämä tietosuojakäytännöt.\n\n")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });

    }

        public void onClick(View v){


            final int arvostele = R.id.arvosteleOlut;
            final int profiili = R.id.profiili;
            final int selaa = R.id.selaaOluita;
            final int tieto = R.id.tietosuoja;
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
          }else if (id == tieto){
              AlertDialog alert = builder.create();
              alert.show();
          }

        }
}
