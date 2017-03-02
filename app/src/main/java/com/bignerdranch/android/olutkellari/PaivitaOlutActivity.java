package com.bignerdranch.android.olutkellari;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PaivitaOlutActivity extends AppCompatActivity implements View.OnClickListener {

    private final String lisatty = "Olut päivitetty!";

    private EditText nimi;
    private EditText paikka;
    private EditText alkoholi;
    private EditText hinta;
    private Spinner maatSpinner;
    private Spinner tyyppiSpinner;
    private RatingBar arvosana;
    private String orginalName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paivita_olut);

       OlutKortti kortti = (OlutKortti) getIntent().getSerializableExtra("Kortti");


        //Tekstikentät
        nimi = (EditText) findViewById(R.id.nimiTextPaivita);
        nimi.setText(kortti.getNimi());
        orginalName = kortti.getNimi();

        paikka = (EditText) findViewById(R.id.paikkaTextPaivita);
        paikka.setText(kortti.getPaikka());

        //Arvosana
        arvosana = (RatingBar) findViewById(R.id.arvosteluPaivita);
        double arvosanat = kortti.getArvosana();
        arvosana.setRating((float) arvosanat);

        //Numerot
        alkoholi = (EditText) findViewById(R.id.alkoholiPaivita);
        alkoholi.setText(kortti.getAlkoholi().toString());
        hinta = (EditText) findViewById(R.id.hintaPaivita);
        hinta.setText(kortti.getHinta().toString());

        //Spinnerit
        maatSpinner = (Spinner) findViewById(R.id.maaPaivita);
        List<String> maat = haeMaat();
        ArrayAdapter<String> adapterMaat = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, maat);
        maatSpinner.setAdapter(adapterMaat);

        tyyppiSpinner = (Spinner) findViewById(R.id.tyyppiPaivita);
        List<String> tyypit = haeOlutTyypit();
        ArrayAdapter<String> adapterTyypit = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tyypit);
        tyyppiSpinner.setAdapter(adapterTyypit);

        for(int i = 0; i < tyypit.size(); i ++){
            String tyyppi =tyypit.get(i);
            if(tyyppi.equals(kortti.getTyyppi())){
                tyyppiSpinner.setSelection(i);
            }
        }

        for(int i = 0; i < maat.size(); i ++){
            String maa =maat.get(i);
            if(maa.equals(kortti.getMaa())){
                maatSpinner.setSelection(i);
            }
        }


        Button tallennaButton = (Button) findViewById(R.id.paivita);
        tallennaButton.setOnClickListener(this);

        hinta.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(5,2)});
        alkoholi.setFilters(new InputFilter[] {new DecimalDigitsInputFilter(3,2)});



    }

    private List<String> haeMaat (){
        //Hakee kaikki maat
        Locale[] locales = Locale.getAvailableLocales();
        ArrayList<String> maat = new ArrayList<>();

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

    private class DecimalDigitsInputFilter implements InputFilter {

        Pattern mPattern;

        public DecimalDigitsInputFilter(int digitsBeforeZero,int digitsAfterZero) {
            mPattern=Pattern.compile("[0-9]{0," + (digitsBeforeZero-1) + "}+((\\.[0-9]{0," + (digitsAfterZero-1) + "})?)||(\\.)?");
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

            Matcher matcher=mPattern.matcher(dest);
            if(!matcher.matches())
                return "";
            return null;
        }

    }

    private List<String> haeOlutTyypit(){
        List<String> tyypit = new ArrayList<>();
        tyypit.add("Vehnäolut"); tyypit.add("Berliner Weisse"); tyypit.add("Lambic"); tyypit.add("Stout");
        tyypit.add("Portteri"); tyypit.add("Ale"); tyypit.add("Bitter"); tyypit.add("Trappist");
        tyypit.add("Alt"); tyypit.add("Kölsch");  tyypit.add("Sahti");
        tyypit.add("Lager"); tyypit.add("Light"); tyypit.add("Pils");
        tyypit.add("Rauchbier"); tyypit.add("Dortmunder"); tyypit.add("Wieniläinen, wiener");
        tyypit.add("Märzen"); tyypit.add("Münchener-olut"); tyypit.add("Bock");


        Collections.sort(tyypit);
        return tyypit;
    }

    @Override
    public void onClick(View v) {

        String nimiS, paikkaS, maaS, tyyppiS;
        Double hintaD = null;
        Double alkoholiD = null;
        Double arvosanaD;
        Context cxt = this;

        boolean kaikkiTaytetty = true;
        boolean numerovaarin = false;
        nimiS = nimi.getText().toString();
        if(nimiS.equals("") || nimiS == null){
            kaikkiTaytetty = false;
        }
        paikkaS = paikka.getText().toString();
        if(paikkaS.equals("") || paikkaS == null){
            kaikkiTaytetty = false;
        }
        maaS = maatSpinner.getSelectedItem().toString();
        tyyppiS = tyyppiSpinner.getSelectedItem().toString();
        String hintaString = hinta.getText().toString();

        if(hintaString.equals("")  || hintaString == null){
            kaikkiTaytetty = false;
        }else{
            if(hintaString.equals(".")){
                numerovaarin = true;
            }else{
                hintaD = Double.parseDouble(hintaString);
            }
        }
        String alkoholiString = alkoholi.getText().toString();
        if(alkoholiString.equals("") || alkoholiString.equals(",") || alkoholiString == null){
            kaikkiTaytetty = false;
        }else{
            if(alkoholiString.equals(".")){
                numerovaarin = true;
            }else{
                alkoholiD = Double.parseDouble(alkoholiString);
            }

        }
        arvosanaD = Double.valueOf(arvosana.getRating());
        if(arvosanaD.equals("") || arvosanaD == null){
            kaikkiTaytetty = false;
        }

        if(kaikkiTaytetty){
            if(numerovaarin){
                Toast.makeText(getBaseContext(),"Täytä numerot oikein!", Toast.LENGTH_LONG).show();

            }else{

                OlutKortti olutkortti = new OlutKortti();

                olutkortti.setAlkoholi(alkoholiD);
                olutkortti.setNimi(nimiS);
                olutkortti.setArvosana(arvosanaD);
                olutkortti.setHinta(hintaD);
                olutkortti.setMaa(maaS);
                olutkortti.setTyyppi(tyyppiS);
                olutkortti.setPaikka(paikkaS);


                DatabaseOperations dbo = new DatabaseOperations(cxt);

                dbo.updateOlut(dbo,olutkortti,orginalName);

                Toast.makeText(getBaseContext(),lisatty, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, MainActivity.class);
                this.startActivity(intent);
            }

        }else{
            Toast.makeText(getBaseContext(),"Täytä kaikki kentät!", Toast.LENGTH_LONG).show();
        }

    }
}
