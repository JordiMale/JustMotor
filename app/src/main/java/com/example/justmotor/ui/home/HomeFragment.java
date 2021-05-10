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

import com.example.justmotor.Conexion;
import com.example.justmotor.R;
import com.example.justmotor.ui.BD.Datasource;
import com.example.justmotor.ui.Filtrar.FilterMotoFragment;
import com.example.justmotor.ui.GetSet.Mix_Oferta;
import com.example.justmotor.ui.GetSet.Modelo;
import com.example.justmotor.ui.RegistrarLogin.RegistrarFragment;
import com.google.gson.JsonObject;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

import cz.msebera.android.httpclient.Header;

public class HomeFragment extends Fragment {

    SearchView searchView;
    ListView lv;

    private Datasource bd;
    private long idActual;
    private adapterTodoIcon scTasks;
    private AsyncHttpResponseHandler handler;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        Pedir_Permisos_Llamada();
        bd = new Datasource(getContext());

        //loadTasks(v);
        //HacerPeticionApi();
        searchView = v.findViewById(R.id.Comp_Filt_Buscador_Home);



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
/*
    private void loadTasks(View v) {
        // Demanem totes les tasques
        Cursor cursorTasks = bd.Todo_Maquina();

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

                        // modifiquem el id
                        MirarOferta(id);
                    }


                }
        );
    }


 */
    private void MirarOferta(long id) {

        Bundle bundle = new Bundle();
        bundle.putLong("id", id);

        idActual = id;


        /* HAcerlo en una activity
        Intent i = new Intent(getActivity(), CrearEditarMaquina.class);
        i.putExtras(bundle);
        startActivityForResult(i, TASK_UPDATE);

        Hacerlo en un fragment
        NavHostFragment.findNavController(getParentFragment()).navigate(R.id.action_navigation_Gestion_Maquines_to_GoogleFragment, bundle);
         */
    }

/*
    private void HacerPeticionApi() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.setMaxRetriesAndTimeout(1, 10000);
        crearHandlerParaPeticon(client);

        String url = Conexion.URL;
        url += "api/Oferta";
        client.get(url, this.handler);

    }


 */


    private void crearHandlerParaPeticon(AsyncHttpClient client) {
        handler = new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String respnse = new String(responseBody);

                try {
                    JSONArray jsonArray = new JSONArray(respnse);

                    for(int i = 0; i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        //Json de Tiempo
                        String Tipo_Tiempo = jsonObject.getString("tipo_tiempo");

                        //Json de Numero de cilindros
                        String  Numero_de_cilindors = jsonObject.getString("nummero_de_cilindros");

                        //Json de Tipo motor
                        int Tiempo = Integer.parseInt(jsonObject.getString(""));
                        int Numero_Cilindros = Integer.parseInt(jsonObject.getString(""));

                        //Json de refrigeracion
                        String Tipo_Refrigeracion = jsonObject.getString("");

                        //Json de encendio
                        String Encendido = jsonObject.getString("");

                        //Json de Cambio
                        String Tipo_Cambio = jsonObject.getString("");

                        //Json de alimentacion
                        String Alimentacion = jsonObject.getString("");

                        //Json de dimension neumatico traseo
                        String Ancho_tra = jsonObject.getString("");
                        String Perfil_tra = jsonObject.getString("");
                        int Radio_tra = Integer.parseInt(jsonObject.getString(""));

                        //Json de dimension neumatico delantero
                        String Ancho_del = jsonObject.getString("");
                        String Perfil_del = jsonObject.getString("");
                        int Radio_del = Integer.parseInt(jsonObject.getString(""));

                        //Json de motor
                        int Tipo_de_motor = Integer.parseInt(jsonObject.getString(""));
                        String Nombre_Motor = jsonObject.getString("");
                        int Refrigeracion = Integer.parseInt(jsonObject.getString(""));
                        int Encendidoo = Integer.parseInt(jsonObject.getString(""));
                        int Cambio = Integer.parseInt(jsonObject.getString(""));
                        int Alimentacionn = Integer.parseInt(jsonObject.getString(""));
                        String Cilindrada = jsonObject.getString("");
                        String Capacidad_de_aceite = jsonObject.getString("");

                        //Json de neumatico
                        int Dimension_Neumatico_trasero = Integer.parseInt(jsonObject.getString(""));
                        int Dimension_Neumatico_delantero = Integer.parseInt(jsonObject.getString(""));
                        String Marca_Neumaticos = jsonObject.getString("");
                        String Modelo_Neumaticos =jsonObject.getString("");

                        //Json de dimension neumatico
                        float Longitud_Total = Float.parseFloat(jsonObject.getString(""));
                        String  Nombre_Dimension = jsonObject.getString("");
                        float Ancho_Total = Float.parseFloat(jsonObject.getString(""));
                        float Altura_Total = Float.parseFloat(jsonObject.getString(""));
                        float Distancia_Entre_Ejes = Float.parseFloat(jsonObject.getString(""));
                        float Altura_Des_Del_Suelo = Float.parseFloat(jsonObject.getString(""));
                        float Deposito_De_gasolina = Float.parseFloat(jsonObject.getString(""));
                        float Peso = Float.parseFloat(jsonObject.getString(""));


                        //Json de Ficha tecnica
                        int Motor = Integer.parseInt(jsonObject.getString(""));
                        int Neumaticos = Integer.parseInt(jsonObject.getString(""));
                        int Dimensiones = Integer.parseInt(jsonObject.getString(""));
                        String Nombre_Ficha_tecnica = jsonObject.getString("");
                        String KM = jsonObject.getString("");
                        String Año = jsonObject.getString("");
                        String Consumo = jsonObject.getString("");
                        String Marca_Frenos = jsonObject.getString("");
                        String ABS = jsonObject.getString("");
                        String Color = jsonObject.getString("");

                        //Json de Modelo
                        int Ficha_Tecnica = Integer.parseInt(jsonObject.getString(""));
                        String Nombre_Modelo = jsonObject.getString("");
                        int Tipo_Modelo = Integer.parseInt(jsonObject.getString(""));
                        String Descripcion = jsonObject.getString("");


                        //Json de Oferta
                        String Usuario_Concesionario = jsonObject.getString("");
                        int Modelo = Integer.parseInt(jsonObject.getString(""));
                        String Foto = jsonObject.getString("");
                        String Data_Entrada = jsonObject.getString("");
                        String Data_Final = jsonObject.getString("");
                        boolean Activa = jsonObject.getBoolean("");
                        int Marca = Integer.parseInt(jsonObject.getString(""));
                        String Precio = jsonObject.getString("");
                        String Matricula =jsonObject.getString("");




                        //Añadir a la base de datos.

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        };
    }


    private void Pedir_Permisos_Llamada(){
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

