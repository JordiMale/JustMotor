package com.example.justmotor.ui.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.MediaStore;

public class Datasource {

    public static final String IDGENERAL = "_id";

    ///Tabla TIEMPO
    public static final String TABLE_TIEMPO = "Tiempo";
    public static final String TIPO_TIEMPO = "Tipo_Tiempo";

    //Tabla NUMERO CILINDORS
    public static final String TABLE_NUM_CILINDROS = "Numero_Cilindros";
    public static final String NUMERO_DE_CILINDROS = "Numero_De_Cilindros";

    //Tabla TIPO MOTOR
    public static final String TABLE_TIPO_MOTOR = "Tipo_Motor";
    public static final String TIEMPO = "Tiempo";
    public static final String NUMERO_CILINDROS = "Numero_Cilindros";

    //Tabla REFRIGERACION
    public static final String TABLE_REFRIGERACION = "Refrigeracion";
    public static final String TIPO_REFRIGERACION = "Tipo_Refrigeracion";

    //Tabla ENCENDIDO
    public static final String TABLE_ENCENDIDO = "Encendido";
    public static final String TIPO_ENCENDIDO = "Tipo_Encendido";

    //Table CAMBIO
    public static final String TABLE_CAMBIO = "Cambio";
    public static final String TIPO_CAMBIO = "Tipo_Cambio";

    //Table ALIMENTACION
    public static final String TABLE_ALIMENTACION = "Alimentacion";
    public static final String TIPO_ALIMENTACION = "Tipo_Alimentacion";

    //Table DIM_NEU_TRA
    public static final String TABLE_DIM_NEU_TRA = "Dimension_Neumatico_Trasero";
    public static final String ANCHO_TRA = "Ancho";
    public static final String PERFIL_TRA = "Perfil";
    public static final String RADIO_TRA = "Radio";

    //Table DIM_NEU_DEL
    public static final String TABLE_DIM_NEU_DEL = "Dimension_Neumatico_Delantero";
    public static final String ANCHO_DEL = "Ancho";
    public static final String PERFIL_DEL = "Perfil";
    public static final String RADIO_DEL = "Radio";


    //Table MOTOR
    public static final String TABLE_MOTOR = "Motor";
    public static final String TIPO_DE_MOTOR = "Tipo_de_motor";
    public static final String NOMBRE_MOTOR = "Nombre_Motor";
    public static final String REFRIGERACION = "Refrigeracion";
    public static final String ENCENDIDO = "Encendido";
    public static final String CAMBIO = "Cambio";
    public static final String ALIMENTACION = "Alimentacion";
    public static final String POTENCIA = "Potencia";
    public static final String CILINDRADA = "Cilindrada";
    public static final String RELACION_DE_COMPRESION = "Relacion_De_Compresion";
    public static final String CAPACIDAD_DE_ACEITE = "Capacidad_De_Aceite";

    //Table NEUMATICO
    public static final String TABLE_NEUMATICO = "Neumaticos";
    public static final String DIMENSION_NEUMATICO_TRASERO = "Dimension_Neumatico_Trasera";
    public static final String DIMENSION_NEUMATICO_DELANTERO = "Dimension_Neumatico_Delantera";
    public static final String MARCA_NEUMATICO = "Marca_Neumaticos";
    public static final String MODELO_NEUMATICOS = "Modelo_Neumaticos";

    //Table DIMENSION MOTO
    public static final String TABLE_DIM_MOTO = "Dimension_Moto";
    public static final String LONGITUD_TOTAL = "Longitud_Total";
    public static final String NOMBRE_DIMENSION = "Nombre_Dimension";
    public static final String ANCHO_TOTAL = "Ancho_Total";
    public static final String ALTURA_TOTAL = "Altura_Total";
    public static final String DISTANCIA_ENTRE_EJES = "Distancia_Entre_Ejes";
    public static final String ALTURA_DES_DEL_SUELO = "Altura_Des_Del_Suelo";
    public static final String DEPOSITO_DE_GASOLINA = "Deposito_De_Gasolina";
    public static final String PESO = "Peso";

    //Table FICHA TECNICA
    public static final String TABLE_FICHA_TECNICA = "Ficha_Tecnica";
    public static final String MOTOR = "Motor";
    public static final String NEUMATICOS = "Neumaticos";
    public static final String DIMENSIONES = "Dimensiones";
    public static final String NOMBRE_FICHA_TECNICA = "Nombre_Ficha_Tecnica";
    public static final String KM = "KM";
    public static final String AÑO = "Año";
    public static final String CONSUMO = "Consumo";
    public static final String MARCA_FRENOS = "Marca_Frenos";
    public static final String ABS = "ABS";
    public static final String COLOR = "Color";

    //Table MODELO
    public static final String TABLE_MODELO = "Modelo";
    public static final String FICHA_TECNICA = "Ficha_Tecnica";
    public static final String NOMBRE_MODELO = "Nombre_Modelo";
    public static final String TIPO_MODELO = "Tipo_Modelo";
    public static final String DESCRIPCION = "Descripcion";


    //Table OFERTA
    public static final String TABLE_OFERTA = "Oferta";
    public static final String MODELO = "Modelo";
    public static final String FOTO = "FOTO";
    public static final String DATA_ENTRADA = "Data_Entrada";
    public static final String DATA_FINAL = "Data_Final";
    public static final String ACTIVA = "Activa";
    public static final String MARCA = "Marca";
    public static final String PRECIO = "Precio";
    public static final String MATRICULA = "Matricula";


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

    //Crear la mototo////

    //Insert de tipo tiempo
    public long Crear_Tiempo(String Tipo_Tiempo) {
        ContentValues values = new ContentValues();
        values.put(TIPO_TIEMPO, Tipo_Tiempo);
        return dbW.insert(TABLE_TIEMPO, null, values);
    }

    //Eliminar all los datos de la tabala tiempo
    public void Delete_All_Tiempo() {
        dbW.execSQL("delete from " + TABLE_TIEMPO);
    }

    //Insert de numero cilindros
    public long Crear_Numero_Cilindros(String Numero_Cilindros) {
        ContentValues values = new ContentValues();
        values.put(NUMERO_DE_CILINDROS, Numero_Cilindros);
        return dbW.insert(TABLE_NUM_CILINDROS, null, values);
    }

    //Elimina all los datos de la tabala Numero de cilindros
    public void Delete_All_Numero_Cilindros() {
        dbW.execSQL("delete from " + TABLE_NUM_CILINDROS);
    }

    //Insert de Tipo motor
    public long Crear_Tipo_Motor(int Tiempo, int Numero_Cilindros) {
        ContentValues values = new ContentValues();
        values.put(TIEMPO, Tiempo);
        values.put(NUMERO_CILINDROS, Numero_Cilindros);
        return dbW.insert(TABLE_TIPO_MOTOR, null, values);
    }

    //Elimina all los datos de la tabala Tipo motor
    public void Delete_All_Tipo_Motor() {
        dbW.execSQL("delete from " + TABLE_TIPO_MOTOR);
    }

    //Insert de refrigeracion
    public long Crear_Refrigeracion(String Refrigeracion) {
        ContentValues values = new ContentValues();
        values.put(TIPO_REFRIGERACION, Refrigeracion);
        return dbW.insert(TABLE_REFRIGERACION, null, values);
    }

    //Elimina all los datos de la tabala refrigeracion
    public void Delete_All_Refrigeracion() {
        dbW.execSQL("delete from " + TABLE_REFRIGERACION);
    }

    //Insert de encendio
    public long Crear_Encendido(String Encendido) {
        ContentValues values = new ContentValues();
        values.put(TIPO_ENCENDIDO, Encendido);
        return dbW.insert(TABLE_ENCENDIDO, null, values);
    }

    //Elimina all los datos de la tabala encendio
    public void Delete_All_Encendido() {
        dbW.execSQL("delete from " + TABLE_ENCENDIDO);
    }

    //Insert de Cambio
    public long Crear_Cambio(String Cambio) {
        ContentValues values = new ContentValues();
        values.put(TIPO_CAMBIO, Cambio);
        return dbW.insert(TABLE_CAMBIO, null, values);
    }

    //Elimina all los datos de la tabala Cambio
    public void Delete_All_Cambio() {
        dbW.execSQL("delete from " + TABLE_CAMBIO);
    }

    //Insert de alimentacion
    public long Crear_Alimentacion(String Alimentacion) {
        ContentValues values = new ContentValues();
        values.put(TIPO_ALIMENTACION, Alimentacion);
        return dbW.insert(TABLE_ALIMENTACION, null, values);
    }

    //Elimina all los datos de la tabala alimentacion
    public void Delete_All_Alimentacion() {
        dbW.execSQL("delete from " + TABLE_ALIMENTACION);
    }

    //Insert de dimension neumatico trasero
    public long Crear_Neum_Traseros(int AnchoTra, int PerfilTra, int RadioTra) {
        ContentValues values = new ContentValues();
        values.put(ANCHO_TRA, AnchoTra);
        values.put(PERFIL_TRA, PerfilTra);
        values.put(RADIO_TRA, RadioTra);
        return dbW.insert(TABLE_DIM_NEU_TRA, null, values);
    }

    //Elimina all los datos de la tabala neumatico trasero
    public void Delete_All_Neum_Traseros() {
        dbW.execSQL("delete from " + TABLE_DIM_NEU_TRA);
    }

    //Insert de dimension neumatico delantero
    public long Crear_Neum_Delanteros(int AnchoDel, int PerfilDel, int RadioDel) {
        ContentValues values = new ContentValues();
        values.put(ANCHO_DEL, AnchoDel);
        values.put(PERFIL_DEL, PerfilDel);
        values.put(RADIO_DEL, RadioDel);
        return dbW.insert(TABLE_DIM_NEU_DEL, null, values);
    }

    //Elimina all los datos de la tabala neumatico delantero
    public void Delete_All_Neum_Delanteros() {
        dbW.execSQL("delete from " + TABLE_DIM_NEU_DEL);
    }

    //Insert de motor
    public long Crear_Motor(int Tipo_de_Motor, String Nombre_Motor, int Refrigeracion, int Encendido, int Cambio, int Alimentacion, int Cilindrada, Double Potencia, int Relacion_De_Compresion, String Capacidad_de_Aceite) {
        ContentValues values = new ContentValues();
        values.put(TIPO_DE_MOTOR, Tipo_de_Motor);
        values.put(NOMBRE_MOTOR, Nombre_Motor);
        values.put(REFRIGERACION, Refrigeracion);
        values.put(ENCENDIDO, Encendido);
        values.put(CAMBIO, Cambio);
        values.put(ALIMENTACION, Alimentacion);
        values.put(CILINDRADA, Cilindrada);
        values.put(POTENCIA, Potencia);
        values.put(RELACION_DE_COMPRESION, Relacion_De_Compresion);
        values.put(CAPACIDAD_DE_ACEITE, Capacidad_de_Aceite);
        return dbW.insert(TABLE_MOTOR, null, values);
    }

    //Elimina all los datos de la tabala motor
    public void Delete_All_Motor() {
        dbW.execSQL("delete  from " + TABLE_MOTOR);
    }

    //Insert de Neumatico
    public long Crear_Neumatico(int Dimension_Neumatico_trasero, int Dimension_Neumatico_delantero, String Marca_Neumaticos, String Modelo_Neumaticos) {
        ContentValues values = new ContentValues();
        values.put(DIMENSION_NEUMATICO_TRASERO, Dimension_Neumatico_trasero);
        values.put(DIMENSION_NEUMATICO_DELANTERO, Dimension_Neumatico_delantero);
        values.put(MARCA_NEUMATICO, Marca_Neumaticos);
        values.put(MODELO_NEUMATICOS, Modelo_Neumaticos);
        return dbW.insert(TABLE_NEUMATICO, null, values);
    }

    //Elimina all los datos de la tabala Neumatico
    public void Delete_All_Neumatico() {
        dbW.execSQL("delete  from " + TABLE_NEUMATICO);
    }

    //Insert de Dimensiones
    public long Crear_Dimension(Float Longitud_Total, String Nombre_Dimension, Float Ancho_Total, Float Altura_Total, Float Distancia_Entre_Ejes, Float Altura_Des_Del_Suelo, Float Deposito_De_gasolina, Float Peso) {
        ContentValues values = new ContentValues();
        values.put(LONGITUD_TOTAL, Longitud_Total);
        values.put(NOMBRE_DIMENSION, Nombre_Dimension);
        values.put(ANCHO_TOTAL, Ancho_Total);
        values.put(ALTURA_TOTAL, Altura_Total);
        values.put(DISTANCIA_ENTRE_EJES, Distancia_Entre_Ejes);
        values.put(ALTURA_DES_DEL_SUELO, Altura_Des_Del_Suelo);
        values.put(DEPOSITO_DE_GASOLINA, Deposito_De_gasolina);
        values.put(PESO, Peso);
        return dbW.insert(TABLE_DIM_MOTO, null, values);
    }

    //Elimina all los datos de la tabala Dimension
    public void Delete_All_Dimension() {
        dbW.execSQL("delete  from " + TABLE_DIM_MOTO);
    }

    //Insert de Ficha tecnica
    public long Crear_Ficha_Tecnica(int Motor, int Neumaticos, int Dimensiones, String Nombre_Ficha_Tecnica, String Km, String Año, String Consumo, String Marca_Frenos, String Abs, String Color) {
        ContentValues values = new ContentValues();
        values.put(MOTOR, Motor);
        values.put(NEUMATICOS, Neumaticos);
        values.put(DIMENSIONES, Dimensiones);
        values.put(NOMBRE_FICHA_TECNICA, Nombre_Ficha_Tecnica);
        values.put(KM, Km);
        values.put(AÑO, Año);
        values.put(CONSUMO, Consumo);
        values.put(MARCA_FRENOS, Marca_Frenos);
        values.put(ABS, Abs);
        values.put(COLOR, Color);
        return dbW.insert(TABLE_FICHA_TECNICA, null, values);
    }

    //Elimina all los datos de la tabala Ficha Tecnica
    public void Delete_All_Ficha_Tecnica() {
        dbW.execSQL("delete  from " + TABLE_FICHA_TECNICA);
    }

    //Insert de Modelo
    public long Crear_Modelo(int Ficha_Tecnica, String Nombre_Modelo, String Tipo_Modelo, String Descripcion) {
        ContentValues values = new ContentValues();
        values.put(FICHA_TECNICA, Ficha_Tecnica);
        values.put(NOMBRE_MODELO, Nombre_Modelo);
        values.put(TIPO_MODELO, Tipo_Modelo);
        values.put(DESCRIPCION, Descripcion);
        return dbW.insert(TABLE_MODELO, null, values);
    }

    //Elimina all los datos de la tabala Modelo
    public void Delete_All_Modelo() {
        dbW.execSQL("delete  from " + TABLE_MODELO);
    }

    //Insert de Oferta
    public long Crear_Oferta(String Precio, String Foto, String Matricula, String Marca, String Modelo, String Data_Entrada, String Data_Final, Boolean activa) {
        ContentValues values = new ContentValues();
        values.put(MODELO, Modelo);
        values.put(FOTO, Foto);
        values.put(DATA_ENTRADA, Data_Entrada);
        values.put(DATA_FINAL, Data_Final);
        values.put(ACTIVA, activa);
        values.put(MARCA, Marca);
        values.put(PRECIO, Precio);
        values.put(MATRICULA, Matricula);
        return dbW.insert(TABLE_OFERTA, null, values);
    }

    //Elimina all los datos de la tabala Oferta
    public void Delete_All_Oferta() {
        dbW.execSQL("delete  from " + TABLE_OFERTA);
    }

    //Per poder mostrar la oferta en el listview
    public Cursor Todo_Oferta() {
        final String MY_QUERY = "SELECT Ofer._id, Precio, FOTO, Matricula, Marca, Data_Entrada, Data_Final, Activa, Mod.Nombre_Modelo " +
                "FROM Oferta AS Ofer INNER JOIN Modelo AS Mod ON Ofer.Modelo = Mod._id ORDER BY Mod.Nombre_Modelo";

        return dbR.rawQuery(MY_QUERY, null);


    }


    //Per mirar la ficha tecnica sencera

    //Per poder mirar el temps
    public Cursor MirarTiempo(long id) {
        return dbR.query(TABLE_TIEMPO, new String[]{IDGENERAL, TIPO_TIEMPO},
                IDGENERAL + "=?", new String[]{String.valueOf(id)},
                null, null, null);
    }

    //Per poder mirar el numero de cilindres
    public Cursor MirarNumeroCilindros(long id) {
        return dbR.query(TABLE_NUM_CILINDROS, new String[]{IDGENERAL, NUMERO_DE_CILINDROS},
                IDGENERAL + "=?", new String[]{String.valueOf(id)},
                null, null, null);
    }

    //Per poder mirar la refrigeracio
    public Cursor MirarRefrigeracion(long id) {
        return dbR.query(TABLE_REFRIGERACION, new String[]{IDGENERAL, TIPO_REFRIGERACION},
                IDGENERAL + "=?", new String[]{String.valueOf(id)},
                null, null, null);
    }

    //Per poder mirar el encendido
    public Cursor MirarEncendido(long id) {
        return dbR.query(TABLE_ENCENDIDO, new String[]{IDGENERAL, TIPO_ENCENDIDO},
                IDGENERAL + "=?", new String[]{String.valueOf(id)},
                null, null, null);
    }

    //Per poder mirar el Camibo
    public Cursor MirarCambio(long id) {
        return dbR.query(TABLE_CAMBIO, new String[]{IDGENERAL, TIPO_CAMBIO},
                IDGENERAL + "=?", new String[]{String.valueOf(id)},
                null, null, null);
    }

    //Per poder mirar la alimentacio
    public Cursor MirarAlimentacion(long id) {
        return dbR.query(TABLE_ALIMENTACION, new String[]{IDGENERAL, TIPO_ALIMENTACION},
                IDGENERAL + "=?", new String[]{String.valueOf(id)},
                null, null, null);
    }

    //Per poder mirar el Neumatico trasero
    public Cursor MirarNeumaticoTrasero(long id) {
        return dbR.query(TABLE_DIM_NEU_TRA, new String[]{IDGENERAL, ANCHO_TRA, PERFIL_TRA, RADIO_TRA},
                IDGENERAL + "=?", new String[]{String.valueOf(id)},
                null, null, null);
    }

    //Per poder mirar el Neumatico delantero
    public Cursor MirarNeumaticoDelantero(long id) {
        return dbR.query(TABLE_DIM_NEU_DEL, new String[]{IDGENERAL, ANCHO_DEL, PERFIL_DEL, RADIO_DEL},
                IDGENERAL + "=?", new String[]{String.valueOf(id)},
                null, null, null);
    }

    //Per poder mirar el motor
    public Cursor MirarMotor(long id) {
        return dbR.query(TABLE_MOTOR, new String[]{IDGENERAL, NOMBRE_MOTOR, CILINDRADA, POTENCIA, RELACION_DE_COMPRESION, CAPACIDAD_DE_ACEITE},
                IDGENERAL + "=?", new String[]{String.valueOf(id)},
                null, null, null);
    }

    //Per poder mirar el Neumatico
    public Cursor MirarNeumatico(long id) {
        return dbR.query(TABLE_NEUMATICO, new String[]{IDGENERAL, MARCA_NEUMATICO, MODELO_NEUMATICOS},
                IDGENERAL + "=?", new String[]{String.valueOf(id)},
                null, null, null);
    }

    //Per poder mirar el Dimensiones
    public Cursor MirarDimensiones(long id) {
        return dbR.query(TABLE_DIM_MOTO, new String[]{IDGENERAL, ANCHO_TOTAL, LONGITUD_TOTAL, ALTURA_TOTAL, DISTANCIA_ENTRE_EJES, ALTURA_DES_DEL_SUELO, DEPOSITO_DE_GASOLINA, PESO},
                IDGENERAL + "=?", new String[]{String.valueOf(id)},
                null, null, null);
    }

    //Per poder mirar el Ficha Tecnica
    public Cursor MirarFichaTecnica(long id) {
        return dbR.query(TABLE_FICHA_TECNICA, new String[]{IDGENERAL, KM, AÑO, CONSUMO, MARCA_FRENOS, ABS, COLOR},
                IDGENERAL + "=?", new String[]{String.valueOf(id)},
                null, null, null);
    }

    //Per poder mirar el Modelo
    public Cursor MirarModelo(long id) {
        return dbR.query(TABLE_MODELO, new String[]{IDGENERAL, NOMBRE_MODELO, TIPO_MODELO, DESCRIPCION},
                IDGENERAL + "=?", new String[]{String.valueOf(id)},
                null, null, null);
    }

    //Per poder mirar el Oferta
    public Cursor MirarOferta(long id) {
        return dbR.query(TABLE_OFERTA, new String[]{IDGENERAL, FOTO, MARCA, PRECIO},
                IDGENERAL + "=?", new String[]{String.valueOf(id)},
                null, null, null);
    }

    //Filtrar motos por el nombre del modelo
    public Cursor FiltrarNombreModelo(String nums) {
        final String MY_QUERY = "SELECT Ofer._id, Precio, FOTO, Matricula, Marca, Data_Entrada, Data_Final, Activa, Mod.Nombre_Modelo " +
                "FROM Oferta AS Ofer INNER JOIN Modelo AS Mod ON Ofer.Modelo = Mod._id WHERE Mod.Nombre_Modelo LIKE '%" + nums + "%'";

        return dbR.rawQuery(MY_QUERY, null);

    }



    //Cursores para el comparador de las fichas tecnicas.

    //Cursor para ver los datos generales de la primera moto.
    //Per poder mostrar la oferta en el listview
    public Cursor MirarMoto1(long id) {
        final String MY_QUERY = "SELECT Ofer._id, Precio, Marca, Mod.Nombre_Modelo, Mod.Tipo_Modelo, Fich.KM, Fich.Año, Fich.Consumo, Fich.Marca_Frenos, Fich.ABS, Fich.Color " +
                "FROM Oferta AS Ofer INNER JOIN Modelo AS Mod ON Ofer.Modelo = Mod._id INNER JOIN Ficha_Tecnica AS Fich ON Mod.Ficha_Tecnica = Fich._id LIKE '%" + id + "%'";

        return dbR.rawQuery(MY_QUERY, null);


    }


    //Per poder mostrar la oferta en el listview de favoritos
    public Cursor FavOfertas(long id) {
        final String MY_QUERY = "SELECT Ofer._id, Precio, FOTO, Matricula, Marca, Data_Entrada, Data_Final, Activa, Mod.Nombre_Modelo " +
                "FROM Oferta AS Ofer INNER JOIN Modelo AS Mod ON Ofer.Modelo = Mod._id LIKE '%" + id + "%' ORDER BY Mod.Nombre_Modelo";

        return dbR.rawQuery(MY_QUERY, null);


    }





}
