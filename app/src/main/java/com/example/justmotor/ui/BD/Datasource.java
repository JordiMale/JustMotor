package com.example.justmotor.ui.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Datasource {

    public static final String IDGENERAL = "_id";



    private Helper dbHelper;
    private SQLiteDatabase dbW, dbR;


    public Datasource(Context ctx) {
        // En el constructor directament obro la comunicació amb la base de dades
        dbHelper = new Helper(ctx);
        // amés també construeixo dos databases un per llegir i l'altre per alterar
        open();
    }

    // DESTRUCTOR
    protected void finalize() {
        // Cerramos los databases
        dbW.close();
        dbR.close();
    }

    private void open() {
        dbW = dbHelper.getWritableDatabase();
        dbR = dbHelper.getReadableDatabase();
    }

}
