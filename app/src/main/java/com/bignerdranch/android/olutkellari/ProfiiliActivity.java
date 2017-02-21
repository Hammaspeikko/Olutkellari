package com.bignerdranch.android.olutkellari;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ProfiiliActivity extends AppCompatActivity implements View.OnClickListener {

    TextView top1Nimi, top1Hinta, top1Alkoholi,top2Nimi, top2Hinta, top2Alkoholi,top3Nimi,
            top3Hinta, top3Alkoholi, tyyppi, keskihinta, kokonaishinta, maa,viivakoodi;

    ArrayList<String> maat = new ArrayList<>();
    ArrayList<String> tyypit = new ArrayList<>();
    BigDecimal hinnatyhteensa = new BigDecimal("0");
    Integer kokonaismaara = 0;
    Integer viivakoodilla = 0;
    BigDecimal keskihintaDouble = new BigDecimal("0");
    Switch topSwitch;
    Button tyhjennaKanta;

    OlutKortti olutKortti;
    List<OlutKortti> olutLista = new ArrayList<OlutKortti>();


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
        topSwitch = (Switch) findViewById(R.id.toplistaSwitch);
        tyhjennaKanta = (Button) findViewById(R.id.poistaKaikki);

        haeTiedot();
        topSwitch.setChecked(true);
        topSwitch.setText("Top 3");
        topSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    topSwitch.setText("Top 3");
                    haeTop3(olutLista,false);
                }else{
                    topSwitch.setText("Bottom 3");
                    haeTop3(olutLista,true);
                }

            }
        });

        tyhjennaKanta.setOnClickListener(this);
        tyyppi.setText("Suosituin oluttyyppi: "+haeSuosituin(tyypit));
        maa.setText("Suosituin maa: "+haeSuosituin(maat));
        kokonaishinta.setText("Kokonaishinta: " +hinnatyhteensa.toString());
        viivakoodi.setText("Tallennettu viivakoodilla: "+viivakoodilla+"/"+kokonaismaara);
        keskihinta.setText("Keskihinta: "+keskihintaDouble);

    }

    private void haeTiedot(){
               final int id = 0;
        final int nimiInt = 1;
        final int tyyppiInt = 2;
        final int arvosanaInt = 3;
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
            olutKortti.setArvosana(cursor.getDouble(arvosanaInt));
            tyypit.add(cursor.getString(tyyppiInt));
            olutKortti.setHinta(cursor.getDouble(hintaInt));
            olutKortti.setAlkoholi(cursor.getDouble(alkoholiInt));
            olutKortti.setNimi(cursor.getString(nimiInt));
            olutLista.add(olutKortti);
        }while (cursor.moveToNext());

            haeTop3(olutLista,false);
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

    private void haeTop3(List<OlutKortti> lista, Boolean valittu){
        Collections.sort(lista, new Comparator<OlutKortti>() {
            @Override
            public int compare(OlutKortti o1, OlutKortti o2) {
                return o1.getArvosana().compareTo(o2.getArvosana());
            }
        });
        if(!valittu){
            Collections.reverse(lista);
        }
        int koko = lista.size();
        if(koko >= 1){
            top1Hinta.setText("Hinta: "+lista.get(0).getHinta());
            top1Nimi.setText("Nimi: " + lista.get(0).getNimi());
            top1Alkoholi.setText(lista.get(0).getAlkoholi()+ "%");
        }
        if(koko >= 2){
            top2Hinta.setText("Hinta: "+lista.get(1).getHinta());
            top2Nimi.setText("Nimi: " + lista.get(1).getNimi());
            top2Alkoholi.setText(lista.get(1).getAlkoholi()+ "%");
        }
        if(koko >= 3){
            top3Hinta.setText("Hinta: "+lista.get(2).getHinta());
            top3Nimi.setText("Nimi: " + lista.get(2).getNimi());
            top3Alkoholi.setText(lista.get(2).getAlkoholi()+ "%");
        }

    }


    @Override
    public void onClick(View v) {
        new AlertDialog.Builder(this)
                .setTitle("Poista kaikki oluet kannasta")
                .setMessage("Oletko varrma, että haluat poistaa kaikki oluet kannasta?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Kyllä", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        toinenVarmistus();
                    }})
                .setNegativeButton("Ei", null).show();
    }

    private void toinenVarmistus(){
        new AlertDialog.Builder(this)
                .setTitle("Poista kaikki oluet kannasta")
                .setMessage("Tätä toimintoa ei voi peruuttaa. Haluatko yhä jatkaa?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton("Kyllä", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        poistaKaikkiOluet();
                    }}).setNegativeButton("Ei", null).show();
    }

    private void poistaKaikkiOluet(){
        Context ctx = this;
        DatabaseOperations dop = new DatabaseOperations(ctx);
        dop.dropTable(dop);
        Toast.makeText(this, "Kaikki oluet on nyt poistettu!",Toast.LENGTH_LONG ).show();
    }
}
