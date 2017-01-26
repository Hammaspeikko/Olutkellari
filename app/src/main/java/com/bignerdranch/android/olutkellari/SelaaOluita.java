package com.bignerdranch.android.olutkellari;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class SelaaOluita extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selaa_oluita);

        final int idInt = 0;
        final int nimiInt = 1;
        final int tyyppiInt = 2;
        final int arvosanaInt = 3;
        final int paikkaInt = 4;
        final int hintaInt = 5;
        final int alkoholiInt = 6;
        final int maaInt = 7;

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        OlutKortti olutKortti;
        List<OlutKortti> olutLista = new ArrayList<OlutKortti>();

        Context ctx = this;
        DatabaseOperations dop = new DatabaseOperations(ctx);
        Cursor cursor = dop.haeKaikkiOluet(dop);
        cursor.moveToFirst();

        int size = cursor.getColumnCount();
        int id;
        String nimi, tyyppi, paikka, maa;
        Double arvosana, hinta, alkoholi;


        do{
            olutKortti  = new OlutKortti();
            olutKortti.setNimi(cursor.getString(nimiInt));
            olutKortti.setHinta(cursor.getDouble(hintaInt));
            olutKortti.setMaa(cursor.getString(maaInt));
            olutKortti.setAlkoholi(cursor.getDouble(alkoholiInt));
            olutKortti.setArvosana(cursor.getDouble(arvosanaInt));
            olutKortti.setPaikka(cursor.getString(paikkaInt));
            olutKortti.setTyyppi(cursor.getString(tyyppiInt));
            olutLista.add(olutKortti);
        }while (cursor.moveToNext());

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new OlutAdapter(olutLista);
        mRecyclerView.setAdapter(mAdapter);
        
    }
}
