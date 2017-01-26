package com.bignerdranch.android.olutkellari;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class SelaaOluita extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selaa_oluita);

        EditText olut = (EditText) findViewById(R.id.olut);

        Context ctx = this;
        DatabaseOperations dop = new DatabaseOperations(ctx);
        Cursor cursor = dop.haeKaikkiOluet(dop);
        cursor.moveToFirst();

        int size = cursor.getColumnCount();
        int id;
        String nimi, tyyppi, paikka, maa;
        Double arvosana, hinta, alkoholi;

        final int idInt = 0;
        final int nimiInt = 1;
        final int tyyppiInt = 2;
        final int arvosanaInt = 3;
        final int paikkaInt = 4;
        final int hintaInt = 5;
        final int alkoholiInt = 6;
        final int maaInt = 7;

        String kaikki = "";

        do{
            id = cursor.getInt(idInt);
            nimi = cursor.getString(nimiInt);
            tyyppi = cursor.getString(tyyppiInt);
            arvosana = cursor.getDouble(arvosanaInt);
            paikka = cursor.getString(paikkaInt);
            hinta = cursor.getDouble(hintaInt);
            alkoholi = cursor.getDouble(alkoholiInt);
            maa = cursor.getString(maaInt);

            kaikki = kaikki + id + nimi + tyyppi+arvosana+paikka+hinta+alkoholi+maa;

        }while (cursor.moveToNext());

        olut.setText(kaikki);

        
    }
}
