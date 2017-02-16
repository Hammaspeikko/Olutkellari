package com.bignerdranch.android.olutkellari;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfiiliActivity extends AppCompatActivity {

    TextView top1Nimi, top1Hinta, top1Alkoholi,top2Nimi, top2Hinta, top2Alkoholi,top3Nimi,
            top3Hinta, top3Alkoholi, tyyppi, keskihinta, kokonaishinta, maa,viivakoodi;

    ArrayList<String> maat = new ArrayList<>();
    ArrayList<String> tyypit = new ArrayList<>();
    BigDecimal hinnatyhteensa = new BigDecimal("0");
    Integer kokonaismaara = 0;
    Integer viivakoodilla = 0;
    BigDecimal keskihintaDouble = new BigDecimal("0");

    Double top1Arvo = 0.0;
    Double top2Arvo = 0.0;
    Double top3Arvo = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiili);

        top1Nimi = (TextView) findViewById(R.id.top1Nimi);
        top1Hinta = (TextView) findViewById(R.id.top1Hinta);
        top1Alkoholi = (TextView) findViewById(R.id.top1Alkoholi);

        top2Nimi = (TextView) findViewById(R.id.top2Nimi);
        top2Hinta = (TextView) findViewById(R.id.top2Hinta);
        top2Alkoholi = (TextView) findViewById(R.id.top2Alkoholi);

        top3Nimi = (TextView) findViewById(R.id.top3Nimi);
        top3Hinta = (TextView) findViewById(R.id.top3Hinta);
        top3Alkoholi = (TextView) findViewById(R.id.top3Alkoholi);

        tyyppi = (TextView) findViewById(R.id.lempiTyyppi);
        keskihinta = (TextView) findViewById(R.id.keskiHinta);
        kokonaishinta = (TextView) findViewById(R.id.kulutettuRaha);
        maa = (TextView) findViewById(R.id.lempiMaa);
        viivakoodi = (TextView) findViewById(R.id.viivakoodilla);

        haeTiedot();

        tyyppi.setText("Suosituin oluttyyppi: "+haeSuosituin(tyypit));
        maa.setText("Suosituin maa: "+haeSuosituin(maat));
        kokonaishinta.setText("Kokonaishinta: " +hinnatyhteensa.toString());
        viivakoodi.setText("Tallennettu viivakoodilla: "+viivakoodilla+"/"+kokonaismaara);
        keskihinta.setText("Keskihinta: "+keskihintaDouble);

    }

    private void haeTiedot(){
        OlutKortti olutKortti;
        List<OlutKortti> olutLista = new ArrayList<OlutKortti>();

        final int id = 0;
        final int nimiInt = 1;
        final int tyyppiInt = 2;
        final int arvosanaInt = 3;
        final int paikkaInt = 4;
        final int hintaInt = 5;
        final int alkoholiInt = 6;
        final int maaInt = 7;

        Context ctx = this;
        DatabaseOperations dop = new DatabaseOperations(ctx);
        Cursor cursor = dop.haeKaikkiOluet(dop);
        cursor.moveToFirst();
        Integer viivalla = 0;
        if(cursor.getCount() > 0){
        do{
            olutKortti  = new OlutKortti();
            String olutID=cursor.getString(id);
            if(olutID != null){
                viivalla = viivalla +1;
            }
            hinnatyhteensa=hinnatyhteensa.add(new BigDecimal((cursor.getDouble(hintaInt))));
            maat.add(cursor.getString(maaInt));
            Double arvosana = cursor.getDouble(arvosanaInt);
            tyypit.add(cursor.getString(tyyppiInt));
            olutLista.add(olutKortti);
            if(arvosana > top1Arvo){
                top1Hinta.setText("Hinta: "+cursor.getDouble(hintaInt));
                top1Nimi.setText("Nimi: " + cursor.getString(nimiInt));
                top1Alkoholi.setText(cursor.getDouble(alkoholiInt)+ "%");
                top1Arvo = arvosana;
            }else if(arvosana > top2Arvo){
                top2Hinta.setText("Hinta: "+cursor.getDouble(hintaInt));
                top2Nimi.setText("Nimi: " + cursor.getString(nimiInt));
                top2Alkoholi.setText(cursor.getDouble(alkoholiInt)+ "%");
                top2Arvo = arvosana;
            }else if(arvosana > top3Arvo){
                top3Hinta.setText("Hinta: "+cursor.getDouble(hintaInt));
                top3Nimi.setText("Nimi: " + cursor.getString(nimiInt));
                top3Alkoholi.setText(cursor.getDouble(alkoholiInt)+ "%");
                top3Arvo = arvosana;
            }
        }while (cursor.moveToNext());
        viivakoodilla = viivalla;
        kokonaismaara = olutLista.size();
        keskihintaDouble = hinnatyhteensa.divide(new BigDecimal(kokonaismaara),2, RoundingMode.HALF_UP);
        }
    }

    private String haeSuosituin(ArrayList<String> lista){

        HashMap<String,Integer> map = new HashMap<String, Integer>();
        String valiaikainenString;
        String isoinString = "Ei lisättyjä oluita";
        Integer isoin = 0;

        for(String index : lista){
            valiaikainenString = index;
            if(map.containsKey(valiaikainenString)){
                map.put(valiaikainenString, map.get(valiaikainenString)+1);
            }else{
                map.put(valiaikainenString,1);
            }
        }
        for (Map.Entry<String, Integer> avain : map.entrySet()){
           int tmpLuku = avain.getValue();
            if(isoin < tmpLuku){
                isoinString = avain.getKey();
            }
        }
        return isoinString;
    }

}
