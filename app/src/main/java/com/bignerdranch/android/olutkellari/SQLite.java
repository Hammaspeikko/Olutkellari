
package com.bignerdranch.android.olutkellari;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.sql.ResultSet;

public class SQLite extends AppCompatActivity {

    SQLiteDatabase mydatabase = openOrCreateDatabase("Olut",MODE_PRIVATE,null);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Open or create database
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS Olut(Olut_ID INTEGER,Nimi TEXT, Tyyppi TEXT," +
                " Arvosana INTEGER,Paikka TEXT,Hinta NUMERIC,Maa TEXT);");


    }
    public void lisaaOlut(){
        mydatabase.beginTransaction();
        //TODO lisää lause
        Cursor resultSet = mydatabase.rawQuery("Select * from Olut",null);
        mydatabase.endTransaction();
    }

    public Olut haeKaikkiOluet(){
        mydatabase.beginTransaction();
        //TODO lisää lause
        Cursor resultSet = mydatabase.rawQuery("Select * from Olut",null);
        Olut olut = lissaOlioon(resultSet);
        mydatabase.endTransaction();
        return olut;
    }

    public Olut haeTiettyOlut(){
        mydatabase.beginTransaction();
        //TODO lisää lause
        Cursor resultSet = mydatabase.rawQuery("Select * from Olut",null);
        Olut olut = lissaOlioon(resultSet);
        mydatabase.endTransaction();
        return olut;
    }

    private Olut lissaOlioon(Cursor resultSet){
        Olut olut = new Olut();

        resultSet.moveToFirst();
        olut.setOlutID(resultSet.getInt(1));
        olut.setNimi(resultSet.getString(2));
        olut.setTyyppi(resultSet.getString(3));
        olut.setArvosana(resultSet.getInt(4));
        olut.setPaikka(resultSet.getString(5));
        olut.setHinta(resultSet.getDouble(6));
        olut.setMaa(resultSet.getString(7));

        return olut;
    }
}
