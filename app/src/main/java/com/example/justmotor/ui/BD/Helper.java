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

        String CREATE_TIEMPO =
                "CREATE TABLE Tiempo ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "Tipo_Tiempo TEXT NOT NULL" + ")";

        String CREATE_NUM_CILINDORS =
                "CREATE TABLE Numero_Cilindros ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "Numero_De_Cilindros TEXT NOT NULL" + ")";

        //Foraneas tipo i numero cilindros
        String CREATE_TIPO_MOTOR =
                "CREATE TABLE Tipo_Motor ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "Tiempo INTEGER NOT NULL," +
                        "Numero_Cilindros INTEGER NOT NULL," +
                        "FOREIGN KEY(Tiempo) REFERENCES Tiempo(_id)," +
                        "FOREIGN KEY(Numero_Cilindros) REFERENCES Numero_Cilindors(_id)" + ")";

        String CREATE_REFRIGERACION =
                "CREATE TABLE Refrigeracion ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "Tipo_Refrigeracion TEXT NOT NULL"  + ")";

        String CREATE_ENCENDIDO =
                "CREATE TABLE Encendido ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "Tipo_Encendido TEXT NOT NULL"  + ")";

        String CREATE_CAMBIO =
                "CREATE TABLE Cambio ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "Tipo_Cambio TEXT NOT NULL"  + ")";

        String CREATE_ALIMENTACION =
                "CREATE TABLE Alimentacion ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "Tipo_Alimentacion TEXT NOT NULL" + ")";



        String CREATE_DIM_NEU_TRA =
                "CREATE TABLE Dimension_Neumatico_Trasero ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "Ancho TEXT NOT NULL," +
                        "Perfil TEXT NOT NULL," +
                        "Radio INTEGER NOT NULL"  + ")";

        String CREATE_DIM_NEU_DEL =
                "CREATE TABLE Dimension_Neumatico_Delantero ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "Ancho TEXT NOT NULL," +
                        "Perfil TEXT NOT NULL," +
                        "Radio INTEGER NOT NULL"  + ")";

        String CREATE_MOTOR =
                "CREATE TABLE MOTOR ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "Tipo_De_Motor INTEGER NOT NULL," +
                        "Nombre_Motor TEXT NOT NULL," +
                        "Refrigeracion INTEGER NOT NULL," +
                        "Encendido INTEGER NOT NULL," +
                        "Cambio INTEGER NOT NULL," +
                        "Alimentacion INTEGER NOT NULL," +
                        "Potencia TEXT UNIQUE NOT NULL," +
                        "Cilindrada TEXT," +
                        "Capacidad_De_Aceite INTEGER NOT NULL," +
                        "FOREIGN KEY(Tipo_De_Motor) REFERENCES Tipo_Motor(_id)," +
                        "FOREIGN KEY(Encendido) REFERENCES Encendido(_id)," +
                        "FOREIGN KEY(Cambio) REFERENCES Cambio(_id)," +
                        "FOREIGN KEY(Alimentacion) REFERENCES Alimentacion(_id)," +
                        "FOREIGN KEY(Refrigeracion) REFERENCES Refrigeracion(_id)" + ")";

        String CREATE_NEUMATICO =
                "CREATE TABLE Neumaticos ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "Dimension_Neumatico_Trasera INTEGER NOT NULL," +
                        "Dimension_Neumatico_Delantera INTEGER NOT NULL," +
                        "Marca_Neumaticos TEXT NOT NULL," +
                        "Modelo_Neumaticos TEXT NOT NULL," +
                        "FOREIGN KEY(Dimension_Neumatico_Trasera) REFERENCES Dimension_Neumatico_Trasero(_id)," +
                        "FOREIGN KEY(Dimension_Neumatico_Delantera) REFERENCES Dimension_Neumatico_Delantero(_id)" + ")";

        String CREATE_DIM_MOTO =
                "CREATE TABLE Dimension_Moto ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "Longitud_Total REAL NOT NULL," +
                        "Nombre_Dimension REAL NOT NULL," +
                        "Ancho_Total REAL NOT NULL," +
                        "Altura_Total REAL NOT NULL," +
                        "Distancia_Entre_Ejes REAL NOT NULL," +
                        "Altura_Des_Del_Suelo TEXT NOT NULL," +
                        "Deposito_De_Gasolina REAL NOT NULL," +
                        "Peso REAL UNIQUE NOT NULL" + ")";

        String CREATE_FICHA_TECNICA =
                "CREATE TABLE Ficha_Tecnica ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "Motor INTEGER NOT NULL," +
                        "Neumaticos INTEGER NOT NULL," +
                        "Dimensiones INTEGER NOT NULL," +
                        "KM TEXT NOT NULL," +
                        "Año TEXT NOT NULL," +
                        "Consumo TEXT NOT NULL," +
                        "Marca_Frenos TEXT NOT NULL," +
                        "ABS TEXT NOT NULL," +
                        "Color TEXT NOT NULL," +
                        "FOREIGN KEY(Motor) REFERENCES Motor(_id)," +
                        "FOREIGN KEY(Neumaticos) REFERENCES Neumaticos(_id)," +
                        "FOREIGN KEY(Dimensiones) REFERENCES Dimension_Moto(_id)"+ ")";

        String CREATE_MODELO =
                "CREATE TABLE Modelo ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "Ficha_Tecnica INTEGER NOT NULL," +
                        "Nombre_Modelo TEXT NOT NULL," +
                        "Tipo_Modelo INTEGER NOT NULL," +
                        "Descripcion TEXT NOT NULL," +
                        "FOREIGN KEY(Ficha_Tecnica) REFERENCES Ficha_Tecnica(_id)" + ")";

        String CREATE_OFERTA =
                "CREATE TABLE Oferta ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "Usuario_Concesionario TEXT NOT NULL," +
                        "Modelo INTEGER NOT NULL," +
                        "FOTO TEXT NOT NULL," +
                        "Data_Entrada DATE NOT NULL," +
                        "Data_Final DATE NOT NULL," +
                        "Activa BOOLEAN," +
                        "Marca INTEGER NOT NULL," +
                        "Precio TEXT  NOT NULL," +
                        "Matricula TEXT ," +
                        "FOREIGN KEY(Modelo) REFERENCES Modelo(_id)" + ")";

        db.execSQL(CREATE_TIEMPO);
        db.execSQL(CREATE_NUM_CILINDORS);
        db.execSQL(CREATE_TIPO_MOTOR);
        db.execSQL(CREATE_REFRIGERACION);
        db.execSQL(CREATE_ENCENDIDO);
        db.execSQL(CREATE_CAMBIO);
        db.execSQL(CREATE_ALIMENTACION);
        db.execSQL(CREATE_DIM_NEU_TRA);
        db.execSQL(CREATE_DIM_NEU_DEL);
        db.execSQL(CREATE_MOTOR);
        db.execSQL(CREATE_NEUMATICO);
        db.execSQL(CREATE_DIM_MOTO);
        db.execSQL(CREATE_FICHA_TECNICA);
        db.execSQL(CREATE_MODELO);
        db.execSQL(CREATE_OFERTA);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
