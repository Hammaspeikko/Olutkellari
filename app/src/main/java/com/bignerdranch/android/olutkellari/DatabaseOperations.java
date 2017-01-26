package com.bignerdranch.android.olutkellari;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Sami on 24.1.2017.
 */

public class DatabaseOperations extends SQLiteOpenHelper {
    public static final int databaseVersion = 1;
    public String CREATE_QUERY = "CREATE TABLE IF NOT EXISTS "+ Olut.OlutInfo.TABLE_NAME+ "("+ Olut.OlutInfo.Olut_ID +
            " INTEGER,"+ Olut.OlutInfo.nimi+" TEXT,"+ Olut.OlutInfo.tyyppi +
            " TEXT," + Olut.OlutInfo.arvosana + " INTEGER," + Olut.OlutInfo.paikka + " TEXT,"
            + Olut.OlutInfo.hinta + " NUMERIC," + Olut.OlutInfo.alkoholi +" NUMERIC," + Olut.OlutInfo.maa+" TEXT);";

    public DatabaseOperations(Context context) {
        super(context, Olut.OlutInfo.DATABASE_NAME, null, databaseVersion);
        Log.e("DatabaseOperations:", "Database luotu");

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        Log.e("DatabaseOperations:", "Table luotu");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void taytaKanta(DatabaseOperations dop,Integer OlutId, String nimi, String tyyppi, Double arvosana, String paikka, Double hinta, String maa, Double alkoholi){

        //Luodaan tarvittavat oliot
        SQLiteDatabase SQLdb = dop.getWritableDatabase();
        SQLdb.execSQL(CREATE_QUERY);
        Log.e("DatabaseOperations:", "Table luotu");
        ContentValues cv = new ContentValues();

        if(OlutId == null){
            OlutId = 0;
        }

        //Asetetaan lähetettävät arvot
        cv.put(Olut.OlutInfo.Olut_ID, OlutId);
        cv.put(Olut.OlutInfo.nimi,nimi);
        cv.put(Olut.OlutInfo.tyyppi, tyyppi);
        cv.put(Olut.OlutInfo.arvosana,arvosana);
        cv.put(Olut.OlutInfo.paikka, paikka);
        cv.put(Olut.OlutInfo.hinta, hinta);
        cv.put(Olut.OlutInfo.maa, maa);
        cv.put(Olut.OlutInfo.alkoholi,alkoholi);


        Long result = SQLdb.insert(Olut.OlutInfo.TABLE_NAME,null,cv);
        String resultString = result.toString();
        Log.e("Insert: ", resultString);

    }

    public Cursor haeKaikkiOluet(DatabaseOperations dop){
        SQLiteDatabase SQdb = dop.getReadableDatabase();
        String[] kentat = {Olut.OlutInfo.Olut_ID, Olut.OlutInfo.nimi, Olut.OlutInfo.tyyppi,
                Olut.OlutInfo.arvosana, Olut.OlutInfo.paikka, Olut.OlutInfo.hinta,
                Olut.OlutInfo.hinta, Olut.OlutInfo.maa, Olut.OlutInfo.alkoholi};

        Cursor cursor = SQdb.query(Olut.OlutInfo.TABLE_NAME, kentat, null,null,null,null,null );
        return cursor;
    }
}
