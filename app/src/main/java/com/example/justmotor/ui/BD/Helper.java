package com.example.justmotor.ui.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Helper extends SQLiteOpenHelper {
    // database version
    private static final int database_VERSION = 2;

    private static final String database_NAME = "DataBaseJustMotor";

    public Helper(Context context) {
        super(context, database_NAME, null, database_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_OFERTA =
                "CREATE TABLE Oferta ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "Usuario_Concesionario TEXT NOT NULL," +
                        "Modelo INTEGER NOT NULL," +
                        "CP INTEGER NOT NULL," +
                        "Data_Entrada DATE NOT NULL," +
                        "Data_Final DATE NOT NULL," +
                        "Activa BOOLEAN," +
                        "Marca INTEGER NOT NULL," +
                        "Precio TEXT  NOT NULL," +
                        "Matricula TEXT ," +
                        "FOREIGN KEY(Modelo) REFERENCES Ficha_Modelo(_id)," +
                        "FOREIGN KEY(Marca) REFERENCES Marca(_id)" + ")";

        String CREATE_MARCA =
                "CREATE TABLE Marca ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "Nom_Client TEXT NOT NULL," +
                        "Adreça TEXT NOT NULL," +
                        "CP INTEGER NOT NULL," +
                        "Poblacio TEXT NOT NULL," +
                        "Telefon TEXT," +
                        "Gmail TEXT," +
                        "NumeSerie TEXT UNIQUE NOT NULL," +
                        "Data TEXT," +
                        "Tipo INTEGER NOT NULL," +
                        "Zones INTEGER  NOT NULL," +
                        "FOREIGN KEY(Tipo) REFERENCES Tipo_Maquines(_id)," +
                        "FOREIGN KEY(Zones) REFERENCES ZonesM(_id)" + ")";

        String CREATE_FICHA_TECNICA =
                "CREATE TABLE Ficha_Tecnica ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "Nom_Client TEXT NOT NULL," +
                        "Adreça TEXT NOT NULL," +
                        "CP INTEGER NOT NULL," +
                        "Poblacio TEXT NOT NULL," +
                        "Telefon TEXT," +
                        "Gmail TEXT," +
                        "NumeSerie TEXT UNIQUE NOT NULL," +
                        "Data TEXT," +
                        "Tipo INTEGER NOT NULL," +
                        "Zones INTEGER  NOT NULL," +
                        "FOREIGN KEY(Tipo) REFERENCES Tipo_Maquines(_id)," +
                        "FOREIGN KEY(Zones) REFERENCES ZonesM(_id)" + ")";

        String CREATE_MODELO =
                "CREATE TABLE Ficha_Modelo ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "Nom_Client TEXT NOT NULL," +
                        "Adreça TEXT NOT NULL," +
                        "CP INTEGER NOT NULL," +
                        "Poblacio TEXT NOT NULL," +
                        "Telefon TEXT," +
                        "Gmail TEXT," +
                        "NumeSerie TEXT UNIQUE NOT NULL," +
                        "Data TEXT," +
                        "Tipo INTEGER NOT NULL," +
                        "Zones INTEGER  NOT NULL," +
                        "FOREIGN KEY(Tipo) REFERENCES Tipo_Maquines(_id)," +
                        "FOREIGN KEY(Zones) REFERENCES ZonesM(_id)" + ")";


        db.execSQL(CREATE_FICHA_TECNICA);
        db.execSQL(CREATE_MODELO);
        db.execSQL(CREATE_MARCA);
        db.execSQL(CREATE_OFERTA);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
