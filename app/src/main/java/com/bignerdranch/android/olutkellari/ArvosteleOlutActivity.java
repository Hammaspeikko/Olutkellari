package com.bignerdranch.android.olutkellari;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class ArvosteleOlutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arvostele_olut);

        //Spinner maat = (Spinner) findViewById();

    }

    private List<String> haeMaat (){
        //Hakee kaikki maat
        Locale[] locales = Locale.getAvailableLocales();
        ArrayList<String> maat = new ArrayList<String>();

        //Lisätään kaikki maat listaan (poislukien tyhjät ja tuplat)
        for(Locale l : locales){
            String maa = l.getDisplayCountry().trim();
            if(!maat.contains(maa) && maa.length() > 0){
                maat.add(maa);
            }
        }

        // Maat aakkosjärjestykse
        Collections.sort(maat);
        return maat;
    }
}
