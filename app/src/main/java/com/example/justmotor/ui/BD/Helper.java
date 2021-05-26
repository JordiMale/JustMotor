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
                "CREATE TABLE Tiempo (id INTEGER PRIMARY KEY," +
                        "_id INTEGER, " +
                        "Tipo_Tiempo TEXT NOT NULL)" ;

        String CREATE_NUM_CILINDORS =
                "CREATE TABLE Numero_Cilindros (id INTEGER PRIMARY KEY," +
                        "_id INTEGER, " +
                        "Numero_De_Cilindros TEXT NOT NULL" + ")";

        //Foraneas tipo i numero cilindros
        String CREATE_TIPO_MOTOR =
                "CREATE TABLE Tipo_Motor (id INTEGER PRIMARY KEY," +
                        "_id INTEGER, " +
                        "Tiempooo INTEGER NOT NULL," +
                        "Numero_Cilindroos INTEGER NOT NULL," +
                        "FOREIGN KEY(Tiempooo) REFERENCES Tiempo(_id)," +
                        "FOREIGN KEY(Numero_Cilindroos) REFERENCES Numero_Cilindors(_id)" + ")";

        String CREATE_REFRIGERACION =
                "CREATE TABLE Refrigeracion (id INTEGER PRIMARY KEY," +
                        "_id INTEGER, " +
                        "Tipo_Refrigeracion TEXT NOT NULL"  + ")";

        String CREATE_ENCENDIDO =
                "CREATE TABLE Encendido (id INTEGER PRIMARY KEY," +
                        "_id INTEGER, " +
                        "Tipo_Encendido TEXT NOT NULL"  + ")";

        String CREATE_CAMBIO =
                "CREATE TABLE Cambio (id INTEGER PRIMARY KEY," +
                        "_id INTEGER, " +
                        "Tipo_Cambio TEXT NOT NULL"  + ")";

        String CREATE_ALIMENTACION =
                "CREATE TABLE Alimentacion (id INTEGER PRIMARY KEY," +
                        "_id INTEGER, " +
                        "Tipo_Alimentacion TEXT NOT NULL" + ")";



        String CREATE_DIM_NEU_TRA =
                "CREATE TABLE Dimension_Neumatico_Trasero (id INTEGER PRIMARY KEY," +
                        "_id INTEGER, " +
                        "Ancho INTEGER NOT NULL," +
                        "Perfil INTEGER NOT NULL," +
                        "Radio INTEGER NOT NULL"  + ")";

        String CREATE_DIM_NEU_DEL =
                "CREATE TABLE Dimension_Neumatico_Delantero (id INTEGER PRIMARY KEY," +
                        "_id INTEGER, " +
                        "Ancho INTEGER NOT NULL," +
                        "Perfil INTEGER NOT NULL," +
                        "Radio INTEGER NOT NULL"  + ")";

        String CREATE_MOTOR =
                "CREATE TABLE MOTOR ( id INTEGER PRIMARY KEY," +
                        "_id INTEGER, " +
                        "Tipo_De_Motor INTEGER NOT NULL," +
                        "Nombre_Motor TEXT NOT NULL," +
                        "Refrigeracion INTEGER NOT NULL," +
                        "Encendido INTEGER NOT NULL," +
                        "Cambio INTEGER NOT NULL," +
                        "Alimentacion INTEGER NOT NULL," +
                        "Potencia TEXT NOT NULL," +
                        "Cilindrada TEXT NOT NULL," +
                        "Relacion_De_Compresion INTEGER NOT NULL," +
                        "Capacidad_De_Aceite INTEGER NOT NULL," +
                        "FOREIGN KEY(Tipo_De_Motor) REFERENCES Tipo_Motor(_id)," +
                        "FOREIGN KEY(Encendido) REFERENCES Encendido(_id)," +
                        "FOREIGN KEY(Cambio) REFERENCES Cambio(_id)," +
                        "FOREIGN KEY(Alimentacion) REFERENCES Alimentacion(_id)," +
                        "FOREIGN KEY(Refrigeracion) REFERENCES Refrigeracion(_id)" + ")";

        String CREATE_NEUMATICO =
                "CREATE TABLE Neumaticos ( id INTEGER PRIMARY KEY, " +
                        "_id INTEGER, " +
                        "Dimension_Neumatico_Trasera INTEGER NOT NULL," +
                        "Dimension_Neumatico_Delantera INTEGER NOT NULL," +
                        "Marca_Neumaticos TEXT NOT NULL," +
                        "Modelo_Neumaticos TEXT NOT NULL," +
                        "FOREIGN KEY(Dimension_Neumatico_Trasera) REFERENCES Dimension_Neumatico_Trasero(_id)," +
                        "FOREIGN KEY(Dimension_Neumatico_Delantera) REFERENCES Dimension_Neumatico_Delantero(_id)" + ")";

        String CREATE_DIM_MOTO =
                "CREATE TABLE Dimension_Moto ( id INTEGER PRIMARY KEY," +
                        "_id INTEGER, " +
                        "Longitud_Total REAL NOT NULL," +
                        "Nombre_Dimension REAL NOT NULL," +
                        "Ancho_Total REAL NOT NULL," +
                        "Altura_Total REAL NOT NULL," +
                        "Distancia_Entre_Ejes REAL NOT NULL," +
                        "Altura_Des_Del_Suelo TEXT NOT NULL," +
                        "Deposito_De_Gasolina REAL NOT NULL," +
                        "Peso REAL NOT NULL" + ")";

        String CREATE_FICHA_TECNICA =
                "CREATE TABLE Ficha_Tecnica (id INTEGER PRIMARY KEY, " +
                        "_id INTEGER , " +
                        "Motor INTEGER NOT NULL," +
                        "Neumaticos INTEGER NOT NULL," +
                        "Dimensiones INTEGER NOT NULL," +
                        "Nombre_Ficha_Tecnica TEXT NOT NULL," +
                        "KM TEXT NOT NULL," +
                        "Año TEXT NOT NULL," +
                        "Consumo TEXT NOT NULL," +
                        "Marca_Frenos TEXT NOT NULL," +
                        "ABS BOOLEAN NOT NULL," +
                        "Color TEXT NOT NULL," +
                        "FOREIGN KEY(Motor) REFERENCES Motor(_id)," +
                        "FOREIGN KEY(Neumaticos) REFERENCES Neumaticos(_id)," +
                        "FOREIGN KEY(Dimensiones) REFERENCES Dimension_Moto(_id)"+ ")";

        String CREATE_MODELO =
                "CREATE TABLE Modelo ( id INTEGER PRIMARY KEY, " +
                        "_id INTEGER , " +
                        "Ficha_Tecnica INTEGER NOT NULL," +
                        "Nombre_Modelo TEXT NOT NULL," +
                        "Tipo_Modelo INTEGER NOT NULL," +
                        "Descripcion TEXT NOT NULL," +
                        "FOREIGN KEY(Ficha_Tecnica) REFERENCES Ficha_Tecnica(_id)" + ")";

        String CREATE_OFERTA =
                "CREATE TABLE Oferta ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "Modelo INTEGER NOT NULL," +
                        "FOTO TEXT NOT NULL," +
                        "Data_Entrada DATE NOT NULL," +
                        "Data_Final DATE NOT NULL," +
                        "Activa BOOLEAN NOT NULL," +
                        "Marca TEXT NOT NULL," +
                        "Precio TEXT  NOT NULL," +
                        "Matricula TEXT ," +
                        "FOREIGN KEY(Modelo) REFERENCES Modelo(_id)" + ")";

        String CREATE_OFERTA_FAV =
                "CREATE TABLE Oferta_Fav ( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "Fotoo TEXT NOT NULL," +
                        "Data_Entradaa DATE NOT NULL," +
                        "Activaa BOOLEAN NOT NULL," +
                        "Marcaa TEXT NOT NULL," +
                        "Precioo TEXT  NOT NULL," +
                        "Nombre_Modeloo TEXT NOT NULL " + ")";


        db.execSQL(CREATE_OFERTA_FAV);
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
