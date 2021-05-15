package com.example.justmotor.ui.home;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.justmotor.Conexion;
import com.example.justmotor.R;
import com.example.justmotor.ui.BD.Datasource;
import com.example.justmotor.ui.Filtrar.FilterMotoFragment;
import com.example.justmotor.ui.GetSet.Mix_Oferta;
import com.example.justmotor.ui.GetSet.Modelo;
import com.example.justmotor.ui.GetSet.Motor;
import com.example.justmotor.ui.GetSet.Ofertas;
import com.example.justmotor.ui.RegistrarLogin.RegistrarFragment;
import com.google.gson.JsonObject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Ref;
import java.util.ArrayList;
import java.util.Date;

import cz.msebera.android.httpclient.Header;

public class HomeFragment extends Fragment {

    SearchView searchView;
    ListView lv;
    private long idActual;
    private Datasource bd;
    private long Numero_de_Cilindorsbd;
    private long Tiempobd;
    private long Tipo_Motorbd;
    private long Refrigeracionbd;
    private long Encendidobd;
    private long Cambiobd;
    private long Alimentacionbd;
    private long Dimension_Neumatico_Traserobd;
    private long Dimension_Neumatico_Delanterobd;
    private long Motorbd;
    private long Neumaticosbd;
    private long Dimension_Motobd;
    private long Ficha_Tecnicabd;
    private long Modelobd;
    private long Ofertabd;
    SwipeRefreshLayout Referesh;
    private adapterTodoIcon scTasks;
    private AsyncHttpResponseHandler handler;

    /*
    ArrayList<Motor> motor = new ArrayList<Motor>();
    ArrayList<Ofertas> oferta = new ArrayList<Ofertas>();

     */

    private static String[] from = new String[]{
            Datasource.FOTO,
            Datasource.DATA_ENTRADA,
            Datasource.ACTIVA,
            Datasource.MARCA,
            Datasource.PRECIO,
            Datasource.NOMBRE_MODELO,};

    private static int[] to = new int[]{
            R.id.Imagen_moto,
            R.id.Oferta_Data_Entrada,
            R.id.Oferta_Activa,
            R.id.Oferta_Nombre_Marca,
            R.id.Oferta_Precio,
            R.id.Oferta_Nombre_Modelo,};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        Referesh = v.findViewById(R.id.RefreshLayout);

        Pedir_Permisos_Llamada();
        bd = new Datasource(getContext());
        lv =  v.findViewById(R.id.list1);

        Cursor cur = bd.Todo_Oferta();
        if(cur.moveToNext()){

        }else{
            if(!cur.moveToNext()){
                HacerPeticionApi();
            }
        }

        loadTasks(v);
        searchView = v.findViewById(R.id.Comp_Filt_Buscador_Home);

        Referesh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getContext(), "Has echo un refresh", Toast.LENGTH_LONG).show();
                //EliminarCamposSqlite();
                HacerPeticionApi();
                Referesh.setRefreshing(false);
            }
        });



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                return true;
            }
        });

        return v;
    }

    private void EliminarCamposSqlite() {
        bd.Delete_All_Tiempo();
        bd.Delete_All_Numero_Cilindros();
        bd.Delete_All_Tipo_Motor();
        bd.Delete_All_Refrigeracion();
        bd.Delete_All_Encendido();
        bd.Delete_All_Cambio();
        bd.Delete_All_Alimentacion();
        bd.Delete_All_Neum_Traseros();
        bd.Delete_All_Neum_Delanteros();
        bd.Delete_All_Motor();
        bd.Delete_All_Neumatico();
        bd.Delete_All_Dimension();
        bd.Delete_All_Ficha_Tecnica();
        bd.Delete_All_Modelo();
        bd.Delete_All_Oferta();
    }

    private void loadTasks(View v) {
        // Demanem totes les tasques
        Cursor cursorTasks = bd.Todo_Oferta();

        // Now create a simple cursor adapter and set it to display
        scTasks = new adapterTodoIcon(getContext(),
                R.layout.row_oferta,
                cursorTasks,
                from,
                to,
                1, HomeFragment.this);

        lv = (ListView) v.findViewById(R.id.list1);
        lv.setAdapter(scTasks);

        lv.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View view,
                                            int position, long id) {

                        //modifiquem el id
                        MirarOferta(id);
                    }


                }
        );
    }


    private void MirarOferta(long id) {

        Bundle bundle = new Bundle();
        bundle.putLong("id", id);

        NavHostFragment.findNavController(getParentFragment()).navigate(R.id.action_nav_home_to_mirarFichaFragment, bundle);

    }



        private void HacerPeticionApi(){
            AsyncHttpClient client = new AsyncHttpClient();
            client.setMaxRetriesAndTimeout(1, 10000);
            crearHandlerParaPeticon(client);

            String url = Conexion.URL;
            url += "api/ofertas";
            client.get(url, this.handler);

        }


        private void crearHandlerParaPeticon(AsyncHttpClient client){
            handler = new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                    String respnse = new String(responseBody);

                    try {
                        JSONArray jsonArray = new JSONArray(respnse);

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
/*
                            int id = Integer.parseInt(jsonObject.getString("Id_Ofertas"));
                            String Precio = String.valueOf(jsonObject.getString("Precio"));
                            String Foto = jsonObject.getString("Fotos");
                            String Matricula = String.valueOf(jsonObject.getString("Matricula"));
                            String Marca = String.valueOf(jsonObject.getString("Marca"));
                            String Modelo = String.valueOf(jsonObject.getString("Modelo"));
                            String Data_Entrada = jsonObject.getString("Data_entrada");
                            String Data_Final = jsonObject.getString("Data_Final");
                            boolean activa = Boolean.parseBoolean(jsonObject.getString("Activa"));

                            Ofertas of = new Ofertas(id,Precio,Matricula,Marca,Foto,Modelo,Data_Entrada,Data_Final,activa);
                            oferta.add(of);

 */

/*
                            int id = Integer.parseInt(jsonObject.getString("Id_Motor"));
                            int Tipo_de_motor = Integer.parseInt(jsonObject.getString("Tipo_Motor"));
                            String Nombre_Motor = jsonObject.getString("nombre_motor");
                            int Refrigeracion = Integer.parseInt(jsonObject.getString("Refrigeracion"));
                            int Encendidoo = Integer.parseInt(jsonObject.getString("Encendido"));
                            int Cambio = Integer.parseInt(jsonObject.getString("Cambio"));
                            int Alimentacionn = Integer.parseInt(jsonObject.getString("Alimentacion"));
                            int Cilindrada = Integer.parseInt(jsonObject.getString("Cilindrada"));
                            double Potencia = Double.parseDouble(jsonObject.getString("Potencia"));
                            int Relacion_De_Comprersion = Integer.parseInt(jsonObject.getString("Relacion_de_compresion"));
                            String Capacidad_de_aceite = jsonObject.getString("Capacidad_de_aciete");

                            Motor Motor = new Motor(id,Nombre_Motor,Tipo_de_motor,Refrigeracion,Encendidoo,Cambio,Alimentacionn,Cilindrada,Potencia,Relacion_De_Comprersion,Capacidad_de_aceite);
                            motor.add(Motor);

 */

                            //Json de Tiempo
                            int id_Tiempo = Integer.parseInt(jsonObject.getString("id"));
                            String Tipo_Tiempo = jsonObject.getString("tipotiempo");

                            //Json de Numero de cilindros
                            int id_Numero_de_cilindors = Integer.parseInt(jsonObject.getString("id"));
                            String Numero_de_cilindors = jsonObject.getString("numerocilindro");

                            //Json de Tipo motor
                            int id_Tipo_Motor = Integer.parseInt(jsonObject.getString("id"));
                            int Tiempo = Integer.parseInt(jsonObject.getString("tiempo_id"));
                            int Numero_Cilindros = Integer.parseInt(jsonObject.getString("numerocilindro_id"));

                            //Json de refrigeracion
                            int id_Tipo_Refrigeracion = Integer.parseInt(jsonObject.getString("id"));
                            String Tipo_Refrigeracion = jsonObject.getString("tiporefrigeracion");

                            //Json de encendio
                            int id_Encendido = Integer.parseInt(jsonObject.getString("id"));
                            String Tipo_Encendido = jsonObject.getString("tipoencendido");

                            //Json de Cambio
                            int id_cambio = Integer.parseInt(jsonObject.getString("id"));
                            String Tipo_Cambio = jsonObject.getString("tipocambio");

                            //Json de alimentacion
                            int id_Alimentacion = Integer.parseInt(jsonObject.getString("id"));
                            String Tipo_Alimentacion = jsonObject.getString("tipoalimentacion");

                            //Json de dimension neumatico traseo
                            int id_Dim_Neu_Tra = Integer.parseInt(jsonObject.getString("id"));
                            int Ancho_tra = Integer.parseInt(jsonObject.getString("anchotra"));
                            int Perfil_tra = Integer.parseInt(jsonObject.getString("perfiltra"));
                            int Radio_tra = Integer.parseInt(jsonObject.getString("radiotra"));

                            //Json de dimension neumatico delantero
                            int id_Dim_Neu_Del = Integer.parseInt(jsonObject.getString("id"));
                            int Ancho_del = Integer.parseInt(jsonObject.getString("anchodel"));
                            int Perfil_del = Integer.parseInt(jsonObject.getString("perfildel"));
                            int Radio_del = Integer.parseInt(jsonObject.getString("radiodel"));

                            //Json de motor
                            int id = Integer.parseInt(jsonObject.getString("id"));
                            int Tipo_de_motor = Integer.parseInt(jsonObject.getString("tipomotor_id"));
                            String Nombre_Motor = jsonObject.getString("nombremotor");
                            int Refrigeracion = Integer.parseInt(jsonObject.getString("refrigeracion_id"));
                            int Encendido = Integer.parseInt(jsonObject.getString("encendido_id"));
                            int Cambio = Integer.parseInt(jsonObject.getString("cambio_id"));
                            int Alimentacion = Integer.parseInt(jsonObject.getString("alimentacion_id"));
                            int Cilindrada = Integer.parseInt(jsonObject.getString("cilindrada"));
                            double Potencia = Double.parseDouble(jsonObject.getString("potencia"));
                            int Relacion_De_Comprersion = Integer.parseInt(jsonObject.getString("relaciondecompresion"));
                            String Capacidad_de_aceite = jsonObject.getString("capacidadeaceite");


                            //Json de neumatico
                            int id_neumatico = Integer.parseInt(jsonObject.getString("id"));
                            int Dimension_Neumatico_trasero = Integer.parseInt(jsonObject.getString("dimensionesneumaticotrasero_id"));
                            int Dimension_Neumatico_delantero = Integer.parseInt(jsonObject.getString("dimensionesneumaticodelantero_id"));
                            String Marca_Neumaticos = jsonObject.getString("marcaneumatico");
                            String Modelo_Neumaticos = jsonObject.getString("modeloneumatico");

                            //Json de dimension
                            int id_Dimension = Integer.parseInt(jsonObject.getString("id"));
                            float Longitud_Total = Float.parseFloat(jsonObject.getString("longitud"));
                            String Nombre_Dimension = jsonObject.getString("nombredimension");
                            float Ancho_Total = Float.parseFloat(jsonObject.getString("anchototal"));
                            float Altura_Total = Float.parseFloat(jsonObject.getString("alturatotal"));
                            float Distancia_Entre_Ejes = Float.parseFloat(jsonObject.getString("distanciaentrejes"));
                            float Altura_Des_Del_Suelo = Float.parseFloat(jsonObject.getString("alturadesdelsuelo"));
                            float Deposito_De_gasolina = Float.parseFloat(jsonObject.getString("depositodegasolina"));
                            float Peso = Float.parseFloat(jsonObject.getString("peso"));



                            //Json de Ficha tecnica
                            int id_Ficha_Tecnia = Integer.parseInt(jsonObject.getString("id"));
                            int Motor = Integer.parseInt(jsonObject.getString("motor_id"));
                            int Neumaticos = Integer.parseInt(jsonObject.getString("neumatico_id"));
                            int Dimensiones = Integer.parseInt(jsonObject.getString("dimension_id"));
                            String Nombre_Ficha_tecnica = jsonObject.getString("nombreficha");
                            String KM = jsonObject.getString("km");
                            String Año = jsonObject.getString("anyo");
                            String Consumo = jsonObject.getString("consumo");
                            String Marca_Frenos = jsonObject.getString("marcafrenos");
                            String ABS = jsonObject.getString("abs");
                            String Color = jsonObject.getString("color");



                            //Json de Modelo
                            int id_Modelo = Integer.parseInt(jsonObject.getString("id"));
                            int Ficha_Tecnica = Integer.parseInt(jsonObject.getString("fichatecnica_id"));
                            String Nombre_Modelo = jsonObject.getString("nombremodelo");
                            String Tipo_Modelo = jsonObject.getString("tipomodelo");
                            String Descripcion = jsonObject.getString("descripcion");


                            //Json de Oferta
                            int id_Oferta = Integer.parseInt(jsonObject.getString("id"));
                            String Precio = String.valueOf(jsonObject.getString("precio"));
                            String Foto = jsonObject.getString("fotos");
                            String Matricula = String.valueOf(jsonObject.getString("matricula"));
                            String Marca = String.valueOf(jsonObject.getString("marca"));
                            String Modelo = String.valueOf(jsonObject.getString("modelo_id"));
                            String Data_Entrada = jsonObject.getString("dataentrada");
                            String Data_Final = jsonObject.getString("datafinal");
                            boolean activa = Boolean.parseBoolean(jsonObject.getString("activa"));



                            //Añadir a la base de datos.

                            //Tipo tiemo
                            Tiempobd = bd.Crear_Tiempo(Tipo_Tiempo);

                            //Numero de cilindors
                            Numero_de_Cilindorsbd = bd.Crear_Numero_Cilindros(Numero_de_cilindors);

                            //Tipo motor
                            Tipo_Motorbd = bd.Crear_Tipo_Motor(Tiempo, Numero_Cilindros);

                            //Refrigeracion
                            Refrigeracionbd = bd.Crear_Refrigeracion(Tipo_Refrigeracion);

                            //Encendido
                            Encendidobd = bd.Crear_Encendido(Tipo_Encendido);

                            //Cambio
                            Cambiobd = bd.Crear_Cambio(Tipo_Cambio);

                            //Alimentacion
                            Alimentacionbd = bd.Crear_Alimentacion(Tipo_Alimentacion);

                            //Dimensiones Neumatico Trasero
                            Dimension_Neumatico_Traserobd = bd.Crear_Neum_Traseros(Ancho_tra, Perfil_tra, Radio_tra);

                            //Dimensiones Neumatico Delantero
                            Dimension_Neumatico_Delanterobd = bd.Crear_Neum_Delanteros(Ancho_del, Perfil_del, Radio_del);

                            //Motor
                            Motorbd = bd.Crear_Motor(Tipo_de_motor,Nombre_Motor,Refrigeracion, Encendido, Cambio, Alimentacion, Cilindrada, Potencia, Relacion_De_Comprersion, Capacidad_de_aceite);

                            //Neumaticos
                            Neumaticosbd = bd.Crear_Neumatico(Dimension_Neumatico_trasero, Dimension_Neumatico_delantero, Marca_Neumaticos, Modelo_Neumaticos);

                            //Dimensiones
                            Dimension_Motobd = bd.Crear_Dimension(Longitud_Total, Nombre_Dimension, Ancho_Total, Altura_Total, Distancia_Entre_Ejes, Altura_Des_Del_Suelo, Deposito_De_gasolina, Peso);

                            //Ficha tecnica
                            Ficha_Tecnicabd = bd.Crear_Ficha_Tecnica(Motor, Neumaticos, Dimensiones, Nombre_Ficha_tecnica, KM, Año, Consumo, Marca_Frenos, ABS, Color);

                            //Modelo
                            Modelobd = bd.Crear_Modelo(Ficha_Tecnica, Nombre_Modelo, Tipo_Modelo, Descripcion);

                            //Ofertas
                            Ofertabd = bd.Crear_Oferta(Precio, Foto, Matricula, Marca, Modelo, Data_Entrada, Data_Final, activa);

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    /*
                    if(oferta.size() > 0){
                        ArrayAdapter<Ofertas> adapter = new ArrayAdapter<Ofertas>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, oferta );
                        lv.setAdapter(adapter);
                    }
                     */

                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            };
        }


        private void Pedir_Permisos_Llamada () {
            //Pedir permisos para poder llamar.
            final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 0;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if (ContextCompat.checkSelfPermission(getContext(),
                        Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                            Manifest.permission.CALL_PHONE)) {

                    } else {
                        ActivityCompat.requestPermissions(getActivity(),
                                new String[]{Manifest.permission.CALL_PHONE},
                                MY_PERMISSIONS_REQUEST_CALL_PHONE);
                    }
                }
            }
        }


        class adapterTodoIcon extends android.widget.SimpleCursorAdapter {


            private HomeFragment oTodoListIcon;

            public adapterTodoIcon(Context context, int layout, Cursor c, String[] from, int[] to, int flags, HomeFragment HomFra) {
                super(context, layout, c, from, to, flags);
                oTodoListIcon = HomFra;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                View view = super.getView(position, convertView, parent);

                // Agafem l'objecte de la view que es una LINEA DEL CURSOR
                Cursor linia = (Cursor) getItem(position);


                return view;
        }
    }
}

