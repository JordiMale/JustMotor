package com.example.justmotor.ui.MirarFicha;

import android.database.Cursor;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.justmotor.R;
import com.example.justmotor.ui.BD.Datasource;


public class MirarFichaFragment extends Fragment {

    private Datasource bd;
    private long idTask;

    //General
    TextView Marca;
    ImageView Foto;
    TextView Modelo;
    TextView Año;
    TextView Tipo;
    TextView Precio;
    TextView KM;
    TextView Consumo;
    TextView MarcaFrenos;
    TextView ABS;
    TextView Color;
    TextView Descripcion;
    //

    //Motor
    TextView Nombre;
    TextView Potencia;
    TextView Cilindrada;
    TextView Tiempo;
    TextView Cilindros;
    TextView Alimentacion;
    TextView Cambio;
    TextView Encendido;
    TextView Refrigeracion;
    TextView RDC;
    TextView CapacidadDeAceite;
    //

    //Dimensiones
    TextView Longitud;
    TextView AnchoDim;
    TextView Altura;
    TextView DistanciaEntreEjes;
    TextView AlturaDesdeElSuelo;
    TextView DepositoDeGasolina;
    TextView Peso;
    //

    //Neumaticos
    TextView MarcaNeu;
    TextView ModeloNeu;
        //Neumatico Trasero
        TextView AnchoTra;
        TextView PerfilTra;
        TextView RadioTra;
        //
        //Neumatico Delantero
        TextView AnchoDel;
        TextView PerfilDel;
        TextView RadioDel;
        //
    //


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_mirar_ficha, container, false);

        //General
         Marca = v.findViewById(R.id.TxtNombreMarca);
         Foto = v.findViewById(R.id.ImagenMoto);
         Modelo  = v.findViewById(R.id.TxtNombreModelo);
         Año  = v.findViewById(R.id.TxtAño);
         Tipo  = v.findViewById(R.id.TxtTipoModelo);
         Precio  = v.findViewById(R.id.TxtPrecio);
         KM  = v.findViewById(R.id.TxtKm);
         Consumo  = v.findViewById(R.id.TxtConsumo);
         MarcaFrenos  = v.findViewById(R.id.TxtMarcaFrenos);
         ABS  = v.findViewById(R.id.TxtABS);
         Color  = v.findViewById(R.id.TxtColor);
         Descripcion  = v.findViewById(R.id.TxtDescripcion);
        //

        //Motor
         Nombre = v.findViewById(R.id.TxtNombrMotor);
         Potencia = v.findViewById(R.id.TxtPotencia);
         Cilindrada = v.findViewById(R.id.TxtCilindrada);
         Tiempo = v.findViewById(R.id.TxtTipotiempo);
         Cilindros = v.findViewById(R.id.TxtNumeroCilindros);
         Alimentacion = v.findViewById(R.id.TxtTipoAlimentacion);
         Cambio = v.findViewById(R.id.TxtTipoCambio);
         Encendido = v.findViewById(R.id.TxtEncendido);
         Refrigeracion = v.findViewById(R.id.TxtRefrigeracion);
         RDC = v.findViewById(R.id.TxtRelacionDeCompresion);
         CapacidadDeAceite = v.findViewById(R.id.TxtCapacidadDeAceite);
        //

        //Dimensiones
         Longitud = v.findViewById(R.id.TxtLongitud);
         AnchoDim = v.findViewById(R.id.TxtAncho);
         Altura = v.findViewById(R.id.TxtAltura);
         DistanciaEntreEjes = v.findViewById(R.id.TxtDistanciaEntreEjes);
         AlturaDesdeElSuelo = v.findViewById(R.id.TxtAlturaDesdeElSuelo);
         DepositoDeGasolina = v.findViewById(R.id.TxtDepositoDeChofa);
         Peso = v.findViewById(R.id.TxtPeso);
        //

        //Neumaticos
         MarcaNeu = v.findViewById(R.id.TxtMarcaNeumaticos);
         ModeloNeu = v.findViewById(R.id.TxtModeloNeumaticos);
            //Neumatico Trasero
             AnchoTra = v.findViewById(R.id.TxtAnchoTra);
             PerfilTra = v.findViewById(R.id.TxtPerfilTra);
             RadioTra = v.findViewById(R.id.TxtRadioTra);
            //
            //Neumatico Delantero
             AnchoDel = v.findViewById(R.id.TxtAnchoDel);
             PerfilDel = v.findViewById(R.id.TxtPerfilDel);
             RadioDel = v.findViewById(R.id.TxtRadioDel);
            //
        //
        bd = new Datasource(getContext());
        idTask = getArguments().getLong("id");
        cargarDatos();
        return v;
    }

    private void cargarDatos() {

        Cursor Cursor_Tiempo = bd.MirarTiempo(idTask);
        Cursor_Tiempo.moveToFirst();
        Tiempo.setText(Cursor_Tiempo.getString(Cursor_Tiempo.getColumnIndex(Datasource.TIPO_TIEMPO)));
        String Ti = String.valueOf(Tiempo);
        Cursor_Tiempo.close();


        Cursor Cursor_Numero_Cilindros = bd.MirarNumeroCilindros(idTask);
        Cursor_Numero_Cilindros.moveToFirst();
        Cilindros.setText(Cursor_Numero_Cilindros.getString(Cursor_Numero_Cilindros.getColumnIndex(Datasource.NUMERO_DE_CILINDROS)));
        Cursor_Numero_Cilindros.close();



        Cursor Cursor_Refrigeracion = bd.MirarRefrigeracion(idTask);
        Cursor_Refrigeracion.moveToFirst();
        Refrigeracion.setText(Cursor_Refrigeracion.getString(Cursor_Refrigeracion.getColumnIndex(Datasource.TIPO_REFRIGERACION)));
        Cursor_Refrigeracion.close();

        Cursor Cursor_Encendido = bd.MirarEncendido(idTask);
        Cursor_Encendido.moveToFirst();
        Encendido.setText(Cursor_Encendido.getString(Cursor_Encendido.getColumnIndex(Datasource.TIPO_ENCENDIDO)));
        Cursor_Encendido.close();

        Cursor Cursor_Cambio = bd.MirarCambio(idTask);
        Cursor_Cambio.moveToFirst();
        Cambio.setText(Cursor_Cambio.getString(Cursor_Cambio.getColumnIndex(Datasource.TIPO_CAMBIO)));
        Cursor_Cambio.close();

        Cursor Cursor_Alimentacion = bd.MirarAlimentacion(idTask);
        Cursor_Alimentacion.moveToFirst();
        Alimentacion.setText(Cursor_Alimentacion.getString(Cursor_Alimentacion.getColumnIndex(Datasource.TIPO_ALIMENTACION)));
        Cursor_Alimentacion.close();

        Cursor Cursor_NeumaticoTrasero = bd.MirarNeumaticoTrasero(idTask);
        Cursor_NeumaticoTrasero.moveToFirst();
        AnchoTra.setText(Cursor_NeumaticoTrasero.getString(Cursor_NeumaticoTrasero.getColumnIndex(Datasource.ANCHO_TRA)));
        PerfilTra.setText(Cursor_NeumaticoTrasero.getString(Cursor_NeumaticoTrasero.getColumnIndex(Datasource.PERFIL_TRA)));
        RadioTra.setText(Cursor_NeumaticoTrasero.getString(Cursor_NeumaticoTrasero.getColumnIndex(Datasource.RADIO_TRA)));
        Cursor_NeumaticoTrasero.close();

        Cursor Cursor_NeumaticoDelantero = bd.MirarNeumaticoDelantero(idTask);
        Cursor_NeumaticoDelantero.moveToFirst();
        AnchoDel.setText(Cursor_NeumaticoDelantero.getString(Cursor_NeumaticoDelantero.getColumnIndex(Datasource.ANCHO_DEL)));
        PerfilDel.setText(Cursor_NeumaticoDelantero.getString(Cursor_NeumaticoDelantero.getColumnIndex(Datasource.PERFIL_DEL)));
        RadioDel.setText(Cursor_NeumaticoDelantero.getString(Cursor_NeumaticoDelantero.getColumnIndex(Datasource.RADIO_DEL)));
        Cursor_NeumaticoDelantero.close();

        Cursor Cursor_Neumatico = bd.MirarNeumatico(idTask);
        Cursor_Neumatico.moveToFirst();
        MarcaNeu.setText(Cursor_Neumatico.getString(Cursor_Neumatico.getColumnIndex(Datasource.MARCA_NEUMATICO)));
        ModeloNeu.setText(Cursor_Neumatico.getString(Cursor_Neumatico.getColumnIndex(Datasource.MODELO_NEUMATICOS)));
        Cursor_Neumatico.close();


        Cursor Cursor_Dimensiones = bd.MirarDimensiones(idTask);
        Cursor_Dimensiones.moveToFirst();
        Longitud.setText(Cursor_Dimensiones.getString(Cursor_Dimensiones.getColumnIndex(Datasource.LONGITUD_TOTAL)));
        AnchoDim.setText(Cursor_Dimensiones.getString(Cursor_Dimensiones.getColumnIndex(Datasource.ANCHO_TOTAL)));
        Altura.setText(Cursor_Dimensiones.getString(Cursor_Dimensiones.getColumnIndex(Datasource.ALTURA_TOTAL)));
        DistanciaEntreEjes.setText(Cursor_Dimensiones.getString(Cursor_Dimensiones.getColumnIndex(Datasource.DISTANCIA_ENTRE_EJES)));
        AlturaDesdeElSuelo.setText(Cursor_Dimensiones.getString(Cursor_Dimensiones.getColumnIndex(Datasource.ALTURA_DES_DEL_SUELO)));
        DepositoDeGasolina.setText(Cursor_Dimensiones.getString(Cursor_Dimensiones.getColumnIndex(Datasource.DEPOSITO_DE_GASOLINA)));
        Peso.setText(Cursor_Dimensiones.getString(Cursor_Dimensiones.getColumnIndex(Datasource.PESO)));
        Cursor_Dimensiones.close();

        Cursor Cursor_Moto = bd.MirarMotor(idTask);
        Cursor_Moto.moveToFirst();
        Nombre.setText(Cursor_Moto.getString(Cursor_Moto.getColumnIndex(Datasource.NOMBRE_MOTOR)));
        Potencia.setText(Cursor_Moto.getString(Cursor_Moto.getColumnIndex(Datasource.POTENCIA)));
        Cilindrada.setText(Cursor_Moto.getString(Cursor_Moto.getColumnIndex(Datasource.CILINDRADA)));
        RDC.setText(Cursor_Moto.getString(Cursor_Moto.getColumnIndex(Datasource.RELACION_DE_COMPRESION)));
        CapacidadDeAceite.setText(Cursor_Moto.getString(Cursor_Moto.getColumnIndex(Datasource.CAPACIDAD_DE_ACEITE)));
        Cursor_Moto.close();



        Cursor Cursor_FichaTecnica = bd.MirarFichaTecnica(idTask);
        Cursor_FichaTecnica.moveToFirst();
        KM.setText(Cursor_FichaTecnica.getString(Cursor_FichaTecnica.getColumnIndex(Datasource.KM)));
        Año.setText(Cursor_FichaTecnica.getString(Cursor_FichaTecnica.getColumnIndex(Datasource.AÑO)));
        Consumo.setText(Cursor_FichaTecnica.getString(Cursor_FichaTecnica.getColumnIndex(Datasource.CONSUMO)));
        MarcaFrenos.setText(Cursor_FichaTecnica.getString(Cursor_FichaTecnica.getColumnIndex(Datasource.MARCA_FRENOS)));
        ABS.setText(Cursor_FichaTecnica.getString(Cursor_FichaTecnica.getColumnIndex(Datasource.ABS)));
        Color.setText(Cursor_FichaTecnica.getString(Cursor_FichaTecnica.getColumnIndex(Datasource.COLOR)));
        Cursor_FichaTecnica.close();

        Cursor Cursor_Modelo = bd.MirarModelo(idTask);
        Cursor_Modelo.moveToFirst();
        Tipo.setText(Cursor_Modelo.getString(Cursor_Modelo.getColumnIndex(Datasource.TIPO_MODELO)));
        Modelo.setText(Cursor_Modelo.getString(Cursor_Modelo.getColumnIndex(Datasource.NOMBRE_MODELO)));
        Descripcion.setText(Cursor_Modelo.getString(Cursor_Modelo.getColumnIndex(Datasource.DESCRIPCION)));
        Cursor_Modelo.close();

        Cursor Cursor_Oferta = bd.MirarOferta(idTask);
        Cursor_Oferta.moveToFirst();
        Precio.setText(Cursor_Oferta.getString(Cursor_Oferta.getColumnIndex(Datasource.PRECIO)));
        //Foto.setText(Cursor_Oferta.getString(Cursor_Oferta.getColumnIndex(Datasource.NOMBRE_MODELO)));
        Marca.setText(Cursor_Oferta.getString(Cursor_Oferta.getColumnIndex(Datasource.MARCA)));
        Cursor_Oferta.close();

    }
}