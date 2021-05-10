package com.example.justmotor.ui.BD;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Datasource {

    public static final String IDGENERAL = "_id";

    ///Tabla TIEMPO
    public static final String TABLE_TIEMPO = "Tiempo";
    public static final String TIPO_TIEMPO = "Tipo_Tiempo";

    //Tabla NUMERO CILINDORS
    public static final String TABLE_NUM_CILINDROS = "Numero_Cilindros";
    public static final String NUMERO_DE_CILINDROS = "Numero_de_cilindros";

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
    public static final String CLILINDRADA = "Cilindrada";
    public static final String CAPACIDAD_DE_ACEITE = "Capacidad_De_Aceite";

    //Table NEUMATICO
    public static final String TABLE_NEUMATICO = "Neumaticos";
    public static final String DIMENSION_NEUMATICO_TRASERO = "Dimension_Neumatico_Trasera";
    public static final String DIMENSION_NEUMATICO_DELANTERO = "Dimension_Neumatico_Delantera";
    public static final String MARCA_NEUMATICO = "Marca_Neumaticos";
    public static final String MODELO_NEUMATICOS = "Modelo_Neumaticos";

    //Table DIMENSION MOTO
    public static final String TABLE_DIM_MOTO = "Dimension_Moto";
    public static final String LONGITUD_TOTAL = "Longitud_total";
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
    public static final String DESCRIPCION = "Descrripcion";


    //Table OFERTA
    public static final String TABLE_OFERTA = "Oferta";
    public static final String USUARIO_CONCESIONARIO = "Usuario_Concesionario";
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

}
