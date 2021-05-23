package com.example.justmotor;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.justmotor.ui.BD.Datasource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ComparadorFinalFragment extends Fragment {

   long[] Ids_Motos = new long[2];
   long Idmoto_1;
   long Idmoto_2;
   private Datasource bd;
   TextView TxtNombreModelo1;
   TextView TxtNombreModelo2;
   ImageView Moto1;
   ImageView Moto2;

   private ExpandableListView expLv;
   private ExpCompAdapter adapter;
   private ArrayList<String> listCategorias;
   private Map<String, ArrayList<String>> mapChild;

    private ExpandableListView expLv2;
    private ExpCompAdapter adapter2;
    private ArrayList<String> listCategorias2;
    private Map<String, ArrayList<String>> mapChild2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_comparador_final, container, false);

        bd = new Datasource(getContext());
        if(getArguments() != null){
           Ids_Motos = getArguments().getLongArray("idz");
           Idmoto_1 = Ids_Motos[0];
           Idmoto_2 = Ids_Motos[1];
        }

        TxtNombreModelo1 = v.findViewById(R.id.TxtNombreModelo1);
        TxtNombreModelo2 = v.findViewById(R.id.TxtNombreModelo2);
        Moto1 = v.findViewById(R.id.ImagenMoto1);
        Moto2 = v.findViewById(R.id.ImagenMoto2);
        expLv = (ExpandableListView) v.findViewById(R.id.GeneralMoto1);
        listCategorias = new ArrayList<>();
        mapChild = new HashMap<>();

        expLv2 = (ExpandableListView) v.findViewById(R.id.GeneralMoto2);
        listCategorias2 = new ArrayList<>();
        mapChild2 = new HashMap<>();
        cargardatos(Idmoto_1,Idmoto_2);

        return v;
    }

    private void cargardatos(long id1, long id2){

        Cursor Cursor_Tiempo1 = bd.MirarTiempo(id1);
        Cursor_Tiempo1.moveToFirst();
        String Ti = Cursor_Tiempo1.getString(Cursor_Tiempo1.getColumnIndex(Datasource.TIPO_TIEMPO));
        Cursor_Tiempo1.close();

        Cursor Cursor_Tiempo2 = bd.MirarTiempo(id2);
        Cursor_Tiempo2.moveToFirst();
        String Ti2 = Cursor_Tiempo2.getString(Cursor_Tiempo2.getColumnIndex(Datasource.TIPO_TIEMPO));
        Cursor_Tiempo2.close();

        Cursor Cursor_Numero_Cilindros1 = bd.MirarNumeroCilindros(id1);
        Cursor_Numero_Cilindros1.moveToFirst();
        String Cilindros1 = Cursor_Numero_Cilindros1.getString(Cursor_Numero_Cilindros1.getColumnIndex(Datasource.NUMERO_DE_CILINDROS));
        Cursor_Numero_Cilindros1.close();


        Cursor Cursor_Numero_Cilindros2 = bd.MirarNumeroCilindros(id2);
        Cursor_Numero_Cilindros2.moveToFirst();
        String Cilindros2 = Cursor_Numero_Cilindros2.getString(Cursor_Numero_Cilindros2.getColumnIndex(Datasource.NUMERO_DE_CILINDROS));
        Cursor_Numero_Cilindros2.close();

        Cursor Cursor_Refrigeracion1 = bd.MirarRefrigeracion(id1);
        Cursor_Refrigeracion1.moveToFirst();
        String Refri1 = Cursor_Refrigeracion1.getString(Cursor_Refrigeracion1.getColumnIndex(Datasource.TIPO_REFRIGERACION));
        Cursor_Refrigeracion1.close();

        Cursor Cursor_Refrigeracion2 = bd.MirarRefrigeracion(id2);
        Cursor_Refrigeracion2.moveToFirst();
        String Refri2 = Cursor_Refrigeracion2.getString(Cursor_Refrigeracion2.getColumnIndex(Datasource.TIPO_REFRIGERACION));
        Cursor_Refrigeracion2.close();

        Cursor Cursor_Encendido1 = bd.MirarEncendido(id1);
        Cursor_Encendido1.moveToFirst();
        String Encendi1 = Cursor_Encendido1.getString(Cursor_Encendido1.getColumnIndex(Datasource.TIPO_ENCENDIDO));
        Cursor_Encendido1.close();

        Cursor Cursor_Encendido2 = bd.MirarEncendido(id2);
        Cursor_Encendido2.moveToFirst();
        String Encendi2 = Cursor_Encendido2.getString(Cursor_Encendido2.getColumnIndex(Datasource.TIPO_ENCENDIDO));
        Cursor_Encendido2.close();

        Cursor Cursor_Cambio1 = bd.MirarCambio(id1);
        Cursor_Cambio1.moveToFirst();
        String Cambio1 = Cursor_Cambio1.getString(Cursor_Cambio1.getColumnIndex(Datasource.TIPO_CAMBIO));
        Cursor_Cambio1.close();

        Cursor Cursor_Cambio2 = bd.MirarCambio(id2);
        Cursor_Cambio2.moveToFirst();
        String Cambio2 = Cursor_Cambio2.getString(Cursor_Cambio2.getColumnIndex(Datasource.TIPO_CAMBIO));
        Cursor_Cambio2.close();

        Cursor Cursor_Alimentacion1 = bd.MirarAlimentacion(id1);
        Cursor_Alimentacion1.moveToFirst();
        String Alimentacion1 = Cursor_Alimentacion1.getString(Cursor_Alimentacion1.getColumnIndex(Datasource.TIPO_ALIMENTACION));
        Cursor_Alimentacion1.close();

        Cursor Cursor_Alimentacion2 = bd.MirarAlimentacion(id2);
        Cursor_Alimentacion2.moveToFirst();
        String Alimentacion2 = Cursor_Alimentacion2.getString(Cursor_Alimentacion2.getColumnIndex(Datasource.TIPO_ALIMENTACION));
        Cursor_Alimentacion2.close();

        Cursor Cursor_NeumaticoTrasero1 = bd.MirarNeumaticoTrasero(id1);
        Cursor_NeumaticoTrasero1.moveToFirst();
        String AnchoTra1 = Cursor_NeumaticoTrasero1.getString(Cursor_NeumaticoTrasero1.getColumnIndex(Datasource.ANCHO_TRA));
        String PerfilTra1 = Cursor_NeumaticoTrasero1.getString(Cursor_NeumaticoTrasero1.getColumnIndex(Datasource.PERFIL_TRA));
        String RadioTra1 = Cursor_NeumaticoTrasero1.getString(Cursor_NeumaticoTrasero1.getColumnIndex(Datasource.RADIO_TRA));
        Cursor_NeumaticoTrasero1.close();

        Cursor Cursor_NeumaticoTrasero2 = bd.MirarNeumaticoTrasero(id2);
        Cursor_NeumaticoTrasero2.moveToFirst();
        String AnchoTra2 = Cursor_NeumaticoTrasero2.getString(Cursor_NeumaticoTrasero2.getColumnIndex(Datasource.ANCHO_TRA));
        String PerfilTra2 = Cursor_NeumaticoTrasero2.getString(Cursor_NeumaticoTrasero2.getColumnIndex(Datasource.PERFIL_TRA));
        String RadioTra2 = Cursor_NeumaticoTrasero2.getString(Cursor_NeumaticoTrasero2.getColumnIndex(Datasource.RADIO_TRA));
        Cursor_NeumaticoTrasero2.close();

        Cursor Cursor_NeumaticoDelantero1 = bd.MirarNeumaticoDelantero(id1);
        Cursor_NeumaticoDelantero1.moveToFirst();
        String AnchoDel1 = Cursor_NeumaticoDelantero1.getString(Cursor_NeumaticoDelantero1.getColumnIndex(Datasource.ANCHO_DEL));
        String PerfilDel1 = Cursor_NeumaticoDelantero1.getString(Cursor_NeumaticoDelantero1.getColumnIndex(Datasource.PERFIL_DEL));
        String RadioTDel1 = Cursor_NeumaticoDelantero1.getString(Cursor_NeumaticoDelantero1.getColumnIndex(Datasource.RADIO_DEL));
        Cursor_NeumaticoDelantero1.close();


        Cursor Cursor_NeumaticoDelantero2 = bd.MirarNeumaticoDelantero(id2);
        Cursor_NeumaticoDelantero2.moveToFirst();
        String AnchoDel2 = Cursor_NeumaticoDelantero2.getString(Cursor_NeumaticoDelantero2.getColumnIndex(Datasource.ANCHO_DEL));
        String PerfilDel2 = Cursor_NeumaticoDelantero2.getString(Cursor_NeumaticoDelantero2.getColumnIndex(Datasource.PERFIL_DEL));
        String RadioTDel2 = Cursor_NeumaticoDelantero2.getString(Cursor_NeumaticoDelantero2.getColumnIndex(Datasource.RADIO_DEL));
        Cursor_NeumaticoDelantero2.close();

        Cursor Cursor_Neumatico1 = bd.MirarNeumatico(id1);
        Cursor_Neumatico1.moveToFirst();
        String ModeloNeumatico1 = Cursor_Neumatico1.getString(Cursor_Neumatico1.getColumnIndex(Datasource.MODELO_NEUMATICOS));
        String MarcaNeumatico1 = Cursor_Neumatico1.getString(Cursor_Neumatico1.getColumnIndex(Datasource.MARCA_NEUMATICO));
        Cursor_Neumatico1.close();

        Cursor Cursor_Neumatico2 = bd.MirarNeumatico(id2);
        Cursor_Neumatico2.moveToFirst();
        String ModeloNeumatico2 = Cursor_Neumatico2.getString(Cursor_Neumatico2.getColumnIndex(Datasource.MODELO_NEUMATICOS));
        String MarcaNeumatico2 = Cursor_Neumatico2.getString(Cursor_Neumatico2.getColumnIndex(Datasource.MARCA_NEUMATICO));
        Cursor_Neumatico2.close();

        Cursor Cursor_Dimensiones1 = bd.MirarDimensiones(id1);
        Cursor_Dimensiones1.moveToFirst();
        String Longitud1 = Cursor_Dimensiones1.getString(Cursor_Dimensiones1.getColumnIndex(Datasource.LONGITUD_TOTAL)) ;
        String AnchoDim1 = Cursor_Dimensiones1.getString(Cursor_Dimensiones1.getColumnIndex(Datasource.ANCHO_TOTAL));
        String Altura1 = Cursor_Dimensiones1.getString(Cursor_Dimensiones1.getColumnIndex(Datasource.ALTURA_TOTAL));
        String DistanciaEntreEjes1 = Cursor_Dimensiones1.getString(Cursor_Dimensiones1.getColumnIndex(Datasource.DISTANCIA_ENTRE_EJES));
        String AlturaDesdeElSuelo1 = Cursor_Dimensiones1.getString(Cursor_Dimensiones1.getColumnIndex(Datasource.ALTURA_DES_DEL_SUELO));
        String DepositoDeGasolina1 = Cursor_Dimensiones1.getString(Cursor_Dimensiones1.getColumnIndex(Datasource.DEPOSITO_DE_GASOLINA));
        String Peso1 = Cursor_Dimensiones1.getString(Cursor_Dimensiones1.getColumnIndex(Datasource.PESO));
        Cursor_Dimensiones1.close();

        Cursor Cursor_Dimensiones2 = bd.MirarDimensiones(id2);
        Cursor_Dimensiones2.moveToFirst();
        String Longitud2 = Cursor_Dimensiones2.getString(Cursor_Dimensiones2.getColumnIndex(Datasource.LONGITUD_TOTAL)) ;
        String AnchoDim2 = Cursor_Dimensiones2.getString(Cursor_Dimensiones2.getColumnIndex(Datasource.ANCHO_TOTAL));
        String Altura2 = Cursor_Dimensiones2.getString(Cursor_Dimensiones2.getColumnIndex(Datasource.ALTURA_TOTAL));
        String DistanciaEntreEjes2 = Cursor_Dimensiones2.getString(Cursor_Dimensiones2.getColumnIndex(Datasource.DISTANCIA_ENTRE_EJES));
        String AlturaDesdeElSuelo2 = Cursor_Dimensiones2.getString(Cursor_Dimensiones2.getColumnIndex(Datasource.ALTURA_DES_DEL_SUELO));
        String DepositoDeGasolina2 = Cursor_Dimensiones2.getString(Cursor_Dimensiones2.getColumnIndex(Datasource.DEPOSITO_DE_GASOLINA));
        String Peso2 = Cursor_Dimensiones2.getString(Cursor_Dimensiones2.getColumnIndex(Datasource.PESO));
        Cursor_Dimensiones2.close();

        Cursor Cursor_Motor1 = bd.MirarMotor(id1);
        Cursor_Motor1.moveToFirst();
        String Nombre1 = Cursor_Motor1.getString(Cursor_Motor1.getColumnIndex(Datasource.NOMBRE_MOTOR));
        String Potencia1 = Cursor_Motor1.getString(Cursor_Motor1.getColumnIndex(Datasource.POTENCIA));
        String Cilindrada1 = Cursor_Motor1.getString(Cursor_Motor1.getColumnIndex(Datasource.CILINDRADA));
        String RDC1 = Cursor_Motor1.getString(Cursor_Motor1.getColumnIndex(Datasource.RELACION_DE_COMPRESION));
        String CapacidadDeAceite1 = Cursor_Motor1.getString(Cursor_Motor1.getColumnIndex(Datasource.CAPACIDAD_DE_ACEITE));
        Cursor_Motor1.close();

        Cursor Cursor_Motor2 = bd.MirarMotor(id2);
        Cursor_Motor2.moveToFirst();
        String Nombre2 = Cursor_Motor2.getString(Cursor_Motor2.getColumnIndex(Datasource.NOMBRE_MOTOR));
        String Potencia2 = Cursor_Motor2.getString(Cursor_Motor2.getColumnIndex(Datasource.POTENCIA));
        String Cilindrada2 = Cursor_Motor2.getString(Cursor_Motor2.getColumnIndex(Datasource.CILINDRADA));
        String RDC2 = Cursor_Motor2.getString(Cursor_Motor2.getColumnIndex(Datasource.RELACION_DE_COMPRESION));
        String CapacidadDeAceite2 = Cursor_Motor2.getString(Cursor_Motor2.getColumnIndex(Datasource.CAPACIDAD_DE_ACEITE));
        Cursor_Motor2.close();

        Cursor Cursor_FichaTecnica1 = bd.MirarFichaTecnica(id1);
        Cursor_FichaTecnica1.moveToFirst();
        String KM1 = Cursor_FichaTecnica1.getString(Cursor_FichaTecnica1.getColumnIndex(Datasource.KM));
        String Año1 = Cursor_FichaTecnica1.getString(Cursor_FichaTecnica1.getColumnIndex(Datasource.AÑO));
        String Consumo1 = Cursor_FichaTecnica1.getString(Cursor_FichaTecnica1.getColumnIndex(Datasource.CONSUMO));
        String MarcaFrenos1 = Cursor_FichaTecnica1.getString(Cursor_FichaTecnica1.getColumnIndex(Datasource.MARCA_FRENOS));

        int ABSs1 = Integer.parseInt(Cursor_FichaTecnica1.getString(Cursor_FichaTecnica1.getColumnIndex(Datasource.ABS)));
        String ABSFinal1 = " ";
        if(ABSs1 == 0 ){
            ABSFinal1 = "Si";
        }else{
            if(ABSs1 == 1){
                ABSFinal1 = "No";
            }
        }


        String Color1 = Cursor_FichaTecnica1.getString(Cursor_FichaTecnica1.getColumnIndex(Datasource.COLOR));
        Cursor_FichaTecnica1.close();

        Cursor Cursor_FichaTecnica2 = bd.MirarFichaTecnica(id2);
        Cursor_FichaTecnica2.moveToFirst();
        String KM2 = Cursor_FichaTecnica2.getString(Cursor_FichaTecnica2.getColumnIndex(Datasource.KM));
        String Año2 = Cursor_FichaTecnica2.getString(Cursor_FichaTecnica2.getColumnIndex(Datasource.AÑO));
        String Consumo2 = Cursor_FichaTecnica2.getString(Cursor_FichaTecnica2.getColumnIndex(Datasource.CONSUMO));
        String MarcaFrenos2 = Cursor_FichaTecnica2.getString(Cursor_FichaTecnica2.getColumnIndex(Datasource.MARCA_FRENOS));

        int ABSs2 = Integer.parseInt(Cursor_FichaTecnica2.getString(Cursor_FichaTecnica2.getColumnIndex(Datasource.ABS)));
        String ABSFinal2 = " ";
        if(ABSs2 == 0 ){
            ABSFinal2 = "Si";
        }else{
            if(ABSs2 == 1){
                ABSFinal2 = "No";
            }
        }


        String Color2 = Cursor_FichaTecnica2.getString(Cursor_FichaTecnica2.getColumnIndex(Datasource.COLOR));
        Cursor_FichaTecnica2.close();

        Cursor Cursor_Modelo1 = bd.MirarModelo(id1);
        Cursor_Modelo1.moveToFirst();
        String Tipo1 = Cursor_Modelo1.getString(Cursor_Modelo1.getColumnIndex(Datasource.TIPO_MODELO));
        String Modelo1 = Cursor_Modelo1.getString(Cursor_Modelo1.getColumnIndex(Datasource.NOMBRE_MODELO));
        String Descripcion1 = Cursor_Modelo1.getString(Cursor_Modelo1.getColumnIndex(Datasource.DESCRIPCION));
        TxtNombreModelo1.setText(Modelo1);
        Cursor_Modelo1.close();

        Cursor Cursor_Modelo2 = bd.MirarModelo(id2);
        Cursor_Modelo2.moveToFirst();
        String Tipo2 = Cursor_Modelo2.getString(Cursor_Modelo2.getColumnIndex(Datasource.TIPO_MODELO));
        String Modelo2 = Cursor_Modelo2.getString(Cursor_Modelo2.getColumnIndex(Datasource.NOMBRE_MODELO));
        String Descripcion2 = Cursor_Modelo2.getString(Cursor_Modelo2.getColumnIndex(Datasource.DESCRIPCION));
        TxtNombreModelo2.setText(Modelo2);
        Cursor_Modelo2.close();


        Cursor Cursor_Oferta1 = bd.MirarOferta(id1);
        Cursor_Oferta1.moveToFirst();
        String Precio1 = Cursor_Oferta1.getString(Cursor_Oferta1.getColumnIndex(Datasource.PRECIO));
        String GuardarFoto1 = Cursor_Oferta1.getString(Cursor_Oferta1.getColumnIndex(Datasource.FOTO));
        Glide.with(getContext()).load(GuardarFoto1).into(Moto1);
        String Marca1 = Cursor_Oferta1.getString(Cursor_Oferta1.getColumnIndex(Datasource.MARCA));
        Cursor_Oferta1.close();

        Cursor Cursor_Oferta2 = bd.MirarOferta(id2);
        Cursor_Oferta2.moveToFirst();
        String Precio2 = Cursor_Oferta2.getString(Cursor_Oferta2.getColumnIndex(Datasource.PRECIO));
        String GuardarFoto2 = Cursor_Oferta2.getString(Cursor_Oferta2.getColumnIndex(Datasource.FOTO));
        Glide.with(getContext()).load(GuardarFoto2).into(Moto2);
        String Marca2 = Cursor_Oferta2.getString(Cursor_Oferta2.getColumnIndex(Datasource.MARCA));
        Cursor_Oferta2.close();












        //Arrays para meter la informacion de las motos
        ArrayList<String> GenralMoto1 = new ArrayList<>();
        ArrayList<String> GeneralMoto2 = new ArrayList<>();
        ArrayList<String> Motor1 = new ArrayList<>();
        ArrayList<String> Motor2 = new ArrayList<>();
        ArrayList<String> Dimensiones1 = new ArrayList<>();
        ArrayList<String> Dimensiones2 = new ArrayList<>();
        ArrayList<String> Neumatico1 = new ArrayList<>();
        ArrayList<String> Neumatico2 = new ArrayList<>();
        ArrayList<String> Tra1 = new ArrayList<>();
        ArrayList<String> Tra2 = new ArrayList<>();
        ArrayList<String> Del1 = new ArrayList<>();
        ArrayList<String> Del2 = new ArrayList<>();

        listCategorias.add("  General " + Modelo1);
        listCategorias2.add("  General " + Modelo2);
        listCategorias.add("  Motor");
        listCategorias2.add("  Motor");
        listCategorias.add("  Dimensiones");
        listCategorias2.add("  Dimensiones");
        listCategorias.add("  Neumatico");
        listCategorias2.add("  Neumatico");
        listCategorias.add("  Neumatico trasero");
        listCategorias2.add("  Neumatico trasero");
        listCategorias.add("  Neumatico delantero");
        listCategorias2.add("  Neumatico delantero");

        //Datos generales para la moto 1
        GenralMoto1.add("Precio: " + Precio1 + " €");
        GenralMoto1.add("Marca: " + Marca1);
        GenralMoto1.add("Modelo: " + Modelo1);
        GenralMoto1.add("Tipo: " + Tipo1);
        GenralMoto1.add("Km: " + KM1);
        GenralMoto1.add("Año: " + Año1);
        GenralMoto1.add("Consumo: " + Consumo1 + "l");
        GenralMoto1.add("Frenos: " + MarcaFrenos1);
        GenralMoto1.add("ABS: " + ABSFinal1);
        GenralMoto1.add("Color: " + Color1);

        //Datos generales para la moto 2
        GeneralMoto2.add("Precio: " + Precio2 + "€");
        GeneralMoto2.add("Marca: " + Marca2);
        GeneralMoto2.add("Modelo: " + Modelo2);
        GeneralMoto2.add("Tipo: " + Tipo2);
        GeneralMoto2.add("Km: " + KM2);
        GeneralMoto2.add("Año: " + Año2);
        GeneralMoto2.add("Consumo: " + Consumo2 + "l");
        GeneralMoto2.add("Frenos: " + MarcaFrenos2);
        GeneralMoto2.add("ABS: " + ABSFinal2);
        GeneralMoto2.add("Color: " + Color2);

        //Datos motor para la moto 1
        Motor1.add("Motor: " + Nombre1);
        Motor1.add("Potencia: " + Potencia1 + " CV");
        Motor1.add("Cilindrada: " + Cilindrada1);
        Motor1.add("Tiempo: " + Ti);
        Motor1.add("Cilindros: " + Cilindros1);
        Motor1.add("Alimentacion: " + Alimentacion1);
        Motor1.add("Cambio: " + Cambio1);
        Motor1.add("Encendido: " + Encendi1);
        Motor1.add("Refrigeracion: " + Refri1);
        Motor1.add("RDC: " + RDC1);
        Motor1.add("CDA: " + CapacidadDeAceite1);

        //Datos motor para la moto 2
        Motor2.add("Motor: " + Nombre2);
        Motor2.add("Potencia: " + Potencia2 + " CV");
        Motor2.add("Cilindrada: " + Cilindrada2);
        Motor2.add("Tiempo: " + Ti2);
        Motor2.add("Cilindros: " + Cilindros2);
        Motor2.add("Alimentacion: " + Alimentacion2);
        Motor2.add("Cambio: " + Cambio2);
        Motor2.add("Encendido: " + Encendi2);
        Motor2.add("Refrigeracion: " + Refri2);
        Motor2.add("RDC: " + RDC2);
        Motor2.add("CDA: " + CapacidadDeAceite2);

        //Datos Dimensiones para la moto 1
        Dimensiones1.add("Loongitud: " + Longitud1);
        Dimensiones1.add("Ancho: " + AnchoDim1 + " mm");
        Dimensiones1.add("Altura: " + Altura1 + " mm");
        Dimensiones1.add("DEEjes: " + DistanciaEntreEjes1 + " mm");
        Dimensiones1.add("ADS: " + AlturaDesdeElSuelo1 + " mm");
        Dimensiones1.add("Deposito: " + DepositoDeGasolina1 + " L");
        Dimensiones1.add("Peso: " + Peso1 + " Kg");


        //Datos Dimensiones para la moto 2
        Dimensiones2.add("Loongitud: " + Longitud2);
        Dimensiones2.add("Ancho: " + AnchoDim2 + " mm");
        Dimensiones2.add("Altura: " + Altura2 + " mm");
        Dimensiones2.add("DEEjes: " + DistanciaEntreEjes2 + " mm");
        Dimensiones2.add("ADS: " + AlturaDesdeElSuelo2 + " mm");
        Dimensiones2.add("Deposito: " + DepositoDeGasolina2 + " L");
        Dimensiones2.add("Peso: " + Peso2 + " Kg");

        //Datos Neumaticos para la moto 1
        Neumatico1.add("Marca: " + MarcaNeumatico1);
        Neumatico1.add("Modelo: " + ModeloNeumatico1);


        //Datos Neumaticos delanteros i traseros para la moto 1
        Del1.add("Ancho: " + AnchoDel1);
        Del1.add("Perfil: " + PerfilDel1);
        Del1.add("Radio: " + RadioTDel1);

        Tra1.add("Ancho: " + AnchoTra1);
        Tra1.add("Prefil: " + PerfilTra1);
        Tra1.add("Radio: " + RadioTra1);


        //Datos Neumaticos para la moto 2
        Neumatico2.add("Marca: " + MarcaNeumatico2);
        Neumatico2.add("Modelo: " + ModeloNeumatico2);


        //Datos Neumaticos delanteros i traseros para la moto 2
        Del2.add("Ancho: " + AnchoDel2);
        Del2.add("Perfil: " + PerfilDel2);
        Del2.add("Radio: " + RadioTDel2);

        Tra2.add("Ancho: " + AnchoTra2);
        Tra2.add("Perfil: " + PerfilTra2);
        Tra2.add("Radio: " + RadioTra2);

        //Datos moto 1
        mapChild.put(listCategorias.get(0), GenralMoto1);
        mapChild.put(listCategorias.get(1), Motor1);
        mapChild.put(listCategorias.get(2), Dimensiones1);
        mapChild.put(listCategorias.get(3), Neumatico1);
        mapChild.put(listCategorias.get(4), Del1);
        mapChild.put(listCategorias.get(5), Tra1);

        adapter = new ExpCompAdapter(getContext(), listCategorias, mapChild);
        expLv.setAdapter(adapter);

        //Datos moto 1
        mapChild2.put(listCategorias2.get(0), GeneralMoto2);
        mapChild2.put(listCategorias2.get(1), Motor2);
        mapChild2.put(listCategorias2.get(2), Dimensiones2);
        mapChild2.put(listCategorias2.get(3), Neumatico2);
        mapChild2.put(listCategorias2.get(4), Del2);
        mapChild2.put(listCategorias2.get(5), Tra2);

        adapter2 = new ExpCompAdapter(getContext(), listCategorias2, mapChild2);
        expLv2.setAdapter(adapter2);




    }

}