package com.bignerdranch.android.olutkellari;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class ArvosteleOlutActivity extends AppCompatActivity implements View.OnClickListener{
    final String lisatty = "Olut lisätty!";

    EditText nimi, paikka,alkoholi,hinta;
    Spinner maatSpinner, tyyppiSpinner;
    RatingBar arvosana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arvostele_olut);

        //Tekstikentät
        nimi = (EditText) findViewById(R.id.nimiText);
        paikka = (EditText) findViewById(R.id.paikkaText);

        //Arvosana
        arvosana = (RatingBar) findViewById(R.id.arvostelu);

        //Numerot
        alkoholi = (EditText) findViewById(R.id.alkoholi);
        hinta = (EditText) findViewById(R.id.hinta);

        //Spinnerit
        maatSpinner = (Spinner) findViewById(R.id.maa);
        List<String> maat = haeMaat();
        ArrayAdapter<String> adapterMaat = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, maat);
        maatSpinner.setAdapter(adapterMaat);

        tyyppiSpinner = (Spinner) findViewById(R.id.tyyppi);
        List<String> tyypit = haeOlutTyypit();
        ArrayAdapter<String> adapterTyypit = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, tyypit);
        tyyppiSpinner.setAdapter(adapterTyypit);

        Button tallennaButton = (Button) findViewById(R.id.tallenna);
        tallennaButton.setOnClickListener(this);



    }

    private List<String> haeMaat (){
        //Hakee kaikki maat
        Locale[] locales = Locale.getAvailableLocales();
        ArrayList<String> maat = new ArrayList<String>();

        //Lisätään kaikki maat listaan (poislukien tyhjät ja tuplat)
        for(Locale l : locales){
            String maa = l.getDisplayCountry().trim();
            if(!maat.contains(maa) && maa.length() > 0 && !maa.equals("maailma")){
                maat.add(maa);
            }
        }

        // Maat aakkosjärjestykse
        Collections.sort(maat);
        return maat;
    }

    private List<String> haeOlutTyypit(){
        List<String> tyypit = new ArrayList<String>();
        tyypit.add("Altbier"); tyypit.add("Amber ale"); tyypit.add("Barley wine"); tyypit.add("Berliner Weisse");
        tyypit.add("Bière de Garde"); tyypit.add("Bitter"); tyypit.add("Blonde Ale"); tyypit.add("Bock");
        tyypit.add("Brown ale"); tyypit.add("California Common/Steam Beer");  tyypit.add("Cream Ale");
        tyypit.add("Dortmunder Export"); tyypit.add("Doppelbock"); tyypit.add("Dunkel");
        tyypit.add("Dunkelweizen"); tyypit.add("Eisbock"); tyypit.add("Flanders red ale");
        tyypit.add("Golden/Summer ale"); tyypit.add("Gose"); tyypit.add("Gueuze");
        tyypit.add("Hefeweizen");  tyypit.add("Helles"); tyypit.add("India pale ale");
        tyypit.add("Kölsch"); tyypit.add("Lambic"); tyypit.add("Light ale");
        tyypit.add("Maibock/Helles bock"); tyypit.add("Malt liquor");
        tyypit.add("Mild"); tyypit.add("Oktoberfestbier/Märzenbier"); tyypit.add("Old ale");
        tyypit.add("Oud bruin"); tyypit.add("Pale ale"); tyypit.add("Pilsener/Pilsner/Pils");
        tyypit.add("Porter"); tyypit.add("Red ale"); tyypit.add("Roggenbier"); tyypit.add("Saison");
        tyypit.add("Scotch ale"); tyypit.add("Stout"); tyypit.add("Schwarzbier"); tyypit.add("Vienna lager");
        tyypit.add("Witbier"); tyypit.add("Weissbier"); tyypit.add("Weizenbock");

        Collections.sort(tyypit);
        return tyypit;
    }

    public void onClick(View v){
        String nimiS, paikkaS, maaS, tyyppiS;
        Double hintaD;
        Double alkoholiD;
        Double arvosanaD;
        Context cxt = this;

        nimiS = nimi.getText().toString();
        paikkaS = paikka.getText().toString();
        maaS = maatSpinner.getSelectedItem().toString();
        tyyppiS = tyyppiSpinner.getSelectedItem().toString();
        hintaD = Double.parseDouble(hinta.getText().toString());
        alkoholiD = Double.parseDouble(alkoholi.getText().toString());
        arvosanaD = Double.valueOf(arvosana.getRating());

        DatabaseOperations dbo = new DatabaseOperations(cxt);
        dbo.taytaKanta(dbo,null,nimiS,tyyppiS,arvosanaD,paikkaS,hintaD,maaS,alkoholiD);

        Toast.makeText(getBaseContext(),lisatty, Toast.LENGTH_LONG).show();
        finish();

    }
}
