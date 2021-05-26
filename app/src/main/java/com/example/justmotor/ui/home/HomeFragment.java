package com.example.justmotor.ui.home;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.example.justmotor.Conexion;
import com.example.justmotor.MainActivity;
import com.example.justmotor.PantallaCargaPrincipal;
import com.example.justmotor.R;
import com.example.justmotor.ui.BD.Datasource;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Array;
import java.util.Objects;

import cz.msebera.android.httpclient.Header;

public class HomeFragment extends Fragment {

    SearchView searchView;
    ListView lv;
    String Imagen;
    String Imagencur;
    String Activa;
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

    private FirebaseUser Usu = FirebaseAuth.getInstance().getCurrentUser();
    private String Email;
    String Identificador;
    long GuardarId = 0;
    Array[] Guardar_Ids = new Array[30];
    Object Guardar;

    FirebaseFirestore Acceso = FirebaseFirestore.getInstance();

    /*
    ArrayList<Motor> motor = new ArrayList<Motor>();
    ArrayList<Ofertas> oferta = new ArrayList<Ofertas>();

     */

    private static String[] from = new String[]{
            Datasource.PRECIO,
            Datasource.FOTO,
            Datasource.DATA_ENTRADA,
            Datasource.MARCA,
            Datasource.ACTIVA,
            Datasource.NOMBRE_MODELO,};

    private static int[] to = new int[]{
            R.id.Oferta_Precio,
            R.id.Imagen_moto,
            R.id.Oferta_Data_Entrada,
            R.id.Oferta_Nombre_Marca,
            R.id.Oferta_Activa,
            R.id.Oferta_Nombre_Modelo,};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_home, container, false);
        bd = new Datasource(getContext());
        lv = v.findViewById(R.id.list1);
        searchView = v.findViewById(R.id.Comp_Filt_Buscador_Home);

        Referesh = v.findViewById(R.id.RefreshLayout);

        MirarPersona();
        Pedir_Permisos_Llamada();

        if (bd.Mirar_Si_Hay_Oferta() == true) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    loadTasks();
                }
            }, 1000);

            Referesh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    Toast.makeText(getContext(), "Has hecho un refresh", Toast.LENGTH_LONG).show();
                    EliminarCamposSqlite();

                    HacerPeticionApi();


                    Referesh.setRefreshing(false);
                }
            });

        } else {
            HacerPeticionApi();
        }


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                String Aux;
                Aux = s;
                Cursor CursorFilt = bd.FiltrarNombreModelo(Aux);

                scTasks = new adapterTodoIcon(getContext(),
                        R.layout.row_oferta,
                        CursorFilt,
                        from,
                        to,
                        1, HomeFragment.this);

                lv.setAdapter(scTasks);
                return false;
            }
        });

        return v;
    }


    private void EliminarCamposSqlite() {
        bd.Delete_All_Oferta();
        bd.Delete_All_Modelo();
        bd.Delete_All_Ficha_Tecnica();
        bd.Delete_All_Dimension();
        bd.Delete_All_Neumatico();
        bd.Delete_All_Motor();
        bd.Delete_All_Neum_Delanteros();
        bd.Delete_All_Neum_Traseros();
        bd.Delete_All_Alimentacion();
        bd.Delete_All_Cambio();
        bd.Delete_All_Encendido();
        bd.Delete_All_Refrigeracion();
        bd.Delete_All_Tipo_Motor();
        bd.Delete_All_Numero_Cilindros();
        bd.Delete_All_Tiempo();
        bd.ResetearIDGNEREAL();

    }

    private void loadTasks() {
        // Demanem totes les tasques
        Cursor cursorTasks = bd.Todo_Oferta();
        //String aux = String.valueOf(cursorTasks.getColumnIndex(Datasource.MARCA));
        // Now create a simple cursor adapter and set it to display
        scTasks = new adapterTodoIcon(getContext(),
                R.layout.row_oferta,
                cursorTasks,
                from,
                to,
                1, HomeFragment.this);


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


    private void HacerPeticionApi() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.setMaxRetriesAndTimeout(1, 10000);
        crearHandlerParaPeticon(client);

        String url = Conexion.URL;
        url += "api/ofertas";
        client.get(url, this.handler);

    }


    private void crearHandlerParaPeticon(AsyncHttpClient client) {
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
                        long id_Tiempo = Integer.parseInt(jsonObject.getString("tiempo_id"));
                        String Tipo_Tiempo = jsonObject.getString("tipotiempo");

                        //Json de Numero de cilindros
                        long id_Numero_de_cilindors = Integer.parseInt(jsonObject.getString("numerocilindro_id"));
                        String Numero_de_cilindors = jsonObject.getString("numerocilindro");

                        //Json de Tipo motor
                        long id_Tipo_Motor = Integer.parseInt(jsonObject.getString("tipomotor_id"));
                        int Tiempo = Integer.parseInt(jsonObject.getString("tiempo_id"));
                        int Numero_Cilindros = Integer.parseInt(jsonObject.getString("numerocilindro_id"));

                        //Json de refrigeracion
                        long id_Tipo_Refrigeracion = Integer.parseInt(jsonObject.getString("refrigeracion_id"));
                        String Tipo_Refrigeracion = jsonObject.getString("tiporefrigeracion");

                        //Json de encendio
                        long id_Encendido = Integer.parseInt(jsonObject.getString("encendido_id"));
                        String Tipo_Encendido = jsonObject.getString("tipoencendido");

                        //Json de Cambio
                        long id_cambio = Integer.parseInt(jsonObject.getString("cambio_id"));
                        String Tipo_Cambio = jsonObject.getString("tipocambio");

                        //Json de alimentacion
                        long id_Alimentacion = Integer.parseInt(jsonObject.getString("alimentacion_id"));
                        String Tipo_Alimentacion = jsonObject.getString("tipoalimentacion");

                        //Json de dimension neumatico traseo
                        long id_Dim_Neu_Tra = Integer.parseInt(jsonObject.getString("dimensionesneumaticotrasero_id"));
                        int Ancho_tra = Integer.parseInt(jsonObject.getString("anchotra"));
                        int Perfil_tra = Integer.parseInt(jsonObject.getString("perfiltra"));
                        int Radio_tra = Integer.parseInt(jsonObject.getString("radiotra"));

                        //Json de dimension neumatico delantero
                        long id_Dim_Neu_Del = Integer.parseInt(jsonObject.getString("dimensionesneumaticodelantero_id"));
                        int Ancho_del = Integer.parseInt(jsonObject.getString("anchodel"));
                        int Perfil_del = Integer.parseInt(jsonObject.getString("perfildel"));
                        int Radio_del = Integer.parseInt(jsonObject.getString("radiodel"));

                        //Json de motor
                        long id_Motor = Integer.parseInt(jsonObject.getString("motor_id"));
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
                        long id_neumatico = Integer.parseInt(jsonObject.getString("neumatico_id"));
                        int Dimension_Neumatico_trasero = Integer.parseInt(jsonObject.getString("dimensionesneumaticotrasero_id"));
                        int Dimension_Neumatico_delantero = Integer.parseInt(jsonObject.getString("dimensionesneumaticodelantero_id"));
                        String Marca_Neumaticos = jsonObject.getString("marcaneumatico");
                        String Modelo_Neumaticos = jsonObject.getString("modeloneumatico");

                        //Json de dimension
                        long id_Dimension = Integer.parseInt(jsonObject.getString("dimension_id"));
                        float Longitud_Total = Float.parseFloat(jsonObject.getString("longitud"));
                        String Nombre_Dimension = jsonObject.getString("nombredimension");
                        float Ancho_Total = Float.parseFloat(jsonObject.getString("anchototal"));
                        float Altura_Total = Float.parseFloat(jsonObject.getString("alturatotal"));
                        float Distancia_Entre_Ejes = Float.parseFloat(jsonObject.getString("distanciaentrejes"));
                        float Altura_Des_Del_Suelo = Float.parseFloat(jsonObject.getString("alturadesdelsuelo"));
                        float Deposito_De_gasolina = Float.parseFloat(jsonObject.getString("depositodegasolina"));
                        float Peso = Float.parseFloat(jsonObject.getString("peso"));


                        //Json de Ficha tecnica
                        long id_Ficha_Tecnia = Integer.parseInt(jsonObject.getString("fichatecnica_id"));
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
                        long id_Modelo = Integer.parseInt(jsonObject.getString("modelo_id"));
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
                        Tiempobd = bd.Crear_Tiempo(id_Tiempo, Tipo_Tiempo);

                        //Numero de cilindors
                        Numero_de_Cilindorsbd = bd.Crear_Numero_Cilindros(id_Numero_de_cilindors, Numero_de_cilindors);

                        //Tipo motor
                        Tipo_Motorbd = bd.Crear_Tipo_Motor(id_Tipo_Motor, Tiempo, Numero_Cilindros);

                        //Refrigeracion
                        Refrigeracionbd = bd.Crear_Refrigeracion(id_Tipo_Refrigeracion, Tipo_Refrigeracion);

                        //Encendido
                        Encendidobd = bd.Crear_Encendido(id_Encendido, Tipo_Encendido);

                        //Cambio
                        Cambiobd = bd.Crear_Cambio(id_cambio, Tipo_Cambio);

                        //Alimentacion
                        Alimentacionbd = bd.Crear_Alimentacion(id_Alimentacion, Tipo_Alimentacion);

                        //Dimensiones Neumatico Trasero
                        Dimension_Neumatico_Traserobd = bd.Crear_Neum_Traseros(id_Dim_Neu_Tra, Ancho_tra, Perfil_tra, Radio_tra);

                        //Dimensiones Neumatico Delantero
                        Dimension_Neumatico_Delanterobd = bd.Crear_Neum_Delanteros(id_Dim_Neu_Del, Ancho_del, Perfil_del, Radio_del);

                        //Motor
                        Motorbd = bd.Crear_Motor(id_Motor, Tipo_de_motor, Nombre_Motor, Refrigeracion, Encendido, Cambio, Alimentacion, Cilindrada, Potencia, Relacion_De_Comprersion, Capacidad_de_aceite);

                        //Neumaticos
                        Neumaticosbd = bd.Crear_Neumatico(id_neumatico, Dimension_Neumatico_trasero, Dimension_Neumatico_delantero, Marca_Neumaticos, Modelo_Neumaticos);

                        //Dimensiones
                        Dimension_Motobd = bd.Crear_Dimension(id_Dimension, Longitud_Total, Nombre_Dimension, Ancho_Total, Altura_Total, Distancia_Entre_Ejes, Altura_Des_Del_Suelo, Deposito_De_gasolina, Peso);

                        //Ficha tecnica
                        Ficha_Tecnicabd = bd.Crear_Ficha_Tecnica(id_Ficha_Tecnia, Motor, Neumaticos, Dimensiones, Nombre_Ficha_tecnica, KM, Año, Consumo, Marca_Frenos, ABS, Color);

                        //Modelo
                        Modelobd = bd.Crear_Modelo(id_Modelo, Ficha_Tecnica, Nombre_Modelo, Tipo_Modelo, Descripcion);

                        //Ofertas
                        Ofertabd = bd.Crear_Oferta(Precio, Foto, Matricula, Marca, Modelo, Data_Entrada, Data_Final, activa);

                    }

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            if (bd.Mirar_Si_Hay_Oferta() == true) {
                                loadTasks();
                            } else {
                                Toast.makeText(getContext(), "NO hay motos.", Toast.LENGTH_LONG).show();
                            }
                        }
                    }, 1000);



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


    private void Pedir_Permisos_Llamada() {
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

    private void Coger_id(long id) {
        GuardarId = id;
        if(Usu != null){
            Acceso.collection("Usuarios").document(Identificador).update("Fav", FieldValue.arrayUnion(GuardarId));
        }else{
            Toast.makeText(getContext(), "NO estas registrado.", Toast.LENGTH_LONG).show();
        }


        /*
        DocumentReference washingtonRef = Acceso.collection("Usuarios").document("Fav");

        // Atomically add a new region to the "regions" array field.
        washingtonRef.update("Fav", FieldValue.arrayUnion(GuardarId));

        // Atomically remove a region from the "regions" array field.
       // washingtonRef.update("regions", FieldValue.arrayRemove("east_coast"));

         */


    }

    private void MirarPersona() {
        if (Usu != null) {
            Email = Usu.getEmail();
            Acceso.collection("Usuarios").whereEqualTo("Email", Email).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                            Log.d("TAG", document.getId() + " => " + document.getData());
                            Identificador = document.getId();
                            //Guardar_Ids = document.getData().get("Fav");
                            //usuari = document.getData().get("nom").toString();
                            //Guardar_Ids = (Array[]) Guardar;
                        }
                    } else {
                        Log.d("TAG", "Error getting documents: ", task.getException());
                    }
                }
            });
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


            ImageView imagen = view.findViewById(R.id.Imagen_moto);
            Imagencur = linia.getString(linia.getColumnIndex(Datasource.FOTO));
            Glide.with(getContext()).load(Imagencur).into(imagen);

            TextView Activaa = view.findViewById(R.id.Oferta_Activa);
            Activa = linia.getString(linia.getColumnIndex((Datasource.ACTIVA)));
            if (Activa.equalsIgnoreCase("0")) {
                Activaa.setText("Activa");
            } else {
                Activaa.setText("Agotada");
            }

            ImageView Meter_Fav = view.findViewById(R.id.Fav);
            Meter_Fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Cursor linia = (Cursor) getItem(position);

                    oTodoListIcon.Coger_id(linia.getInt(linia.getColumnIndexOrThrow(Datasource.IDGENERAL)));
                }
            });


            return view;
        }
    }


}

