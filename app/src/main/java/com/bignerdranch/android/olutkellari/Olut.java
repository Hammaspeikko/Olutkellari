package com.bignerdranch.android.olutkellari;

import android.provider.BaseColumns;

/**
 * Created by Sami on 24.1.2017.
 */

class Olut {

    public Olut() {
    }

    public static abstract class OlutInfo implements BaseColumns{
        public static final String Olut_ID = "olut_id";
        public static final String nimi = "nimi";
        public static final String tyyppi = "tyyppi";
        public static final String arvosana = "arvosana";
        public static final String paikka = "paikka";
        public static final String hinta = "hinta";
        public static final String alkoholi = "alkoholi";
        public static final String maa = "maa";

        public static final String DATABASE_NAME = "olut_db";
        public static final String TABLE_NAME = "olut_info";
    }

}
