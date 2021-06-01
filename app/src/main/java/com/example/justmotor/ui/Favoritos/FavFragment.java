package com.example.justmotor.ui.Favoritos;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
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
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.example.justmotor.MainActivity;
import com.example.justmotor.R;
import com.example.justmotor.ui.BD.Datasource;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.api.SystemParameterOrBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firestore.v1.WriteResult;

import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class FavFragment extends Fragment {


    List<Map<Long, Object>> users;
    Long Guardar_Ids;
    String Imagencur;
    Cursor MirarOferta;
    SearchView searchView;
    SwipeRefreshLayout Referesh;
    String Identificador;
    String Guardar = "";
    Array[] Guardar_Idss = new Array[30];
    ArrayList<Long> Halo = new ArrayList<Long>();
    ListView lv;
    private long idActual;
    private Datasource bd;
    private adapterTodoIcon scTasks;
    private long OfertabdFav;

    ImageView FavRoto;

    String NombreGuardado;
    String GmailGuardado;
    String TelGuardado;
    String PassGuardado;

    String Activa;
    String Fotoo = "";
    String CorreoConcesio = "";
    String Data_Entradaa = "";
    String Nombre_Modeloo = "";
    String Activaa = "";
    String Marcaa = "";
    String Precioo = "";

    boolean Mirarfav = false;

    FirebaseFirestore Acceso = FirebaseFirestore.getInstance();
    private FirebaseUser Usu = FirebaseAuth.getInstance().getCurrentUser();
    private String Email;
    DocumentReference ref;


    private static String[] from = new String[]{
            Datasource.FOTOO,
            Datasource.CORREOCONCEE,
            Datasource.DATA_ENTRADAA,
            Datasource.ACTIVAA,
            Datasource.MARCAA,
            Datasource.PRECIOO,
            Datasource.MODELOO,};

    private static int[] to = new int[]{
            R.id.Imagen_moto,
            R.id.Oferta_Data_Entrada,
            R.id.Oferta_Gmail,
            R.id.Oferta_Activa,
            R.id.Oferta_Nombre_Marca,
            R.id.Oferta_Precio,
            R.id.Oferta_Nombre_Modelo,};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_fav, container, false);
        bd = new Datasource(getContext());
        lv = v.findViewById(R.id.list1);
        searchView = v.findViewById(R.id.Comp_Filt_Buscador_Fav);
        Referesh = v.findViewById(R.id.RefreshLayoutFiltroCompara);

        lv = (ListView) v.findViewById(R.id.list1);

        EliminarCamposSqlite();
        MirarPersona();


        Referesh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getContext(), "Has hecho un refresh", Toast.LENGTH_LONG).show();
                EliminarCamposSqlite();


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        loadTasks();
                    }
                }, 3000);


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

                String Aux;
                Aux = s;
                Cursor CursorFilt = bd.FiltrarNombreModelo(Aux);

                scTasks = new adapterTodoIcon(getContext(),
                        R.layout.row_oferta_fav,
                        CursorFilt,
                        from,
                        to,
                        1, FavFragment.this);

                lv.setAdapter(scTasks);
                return false;
            }
        });


        return v;
    }

    private void EliminarCamposSqlite() {
        bd.Delete_All_Oferta_Fav();
        bd.ResetearIDGNEREALFavOfert();
        loadTasks();

    }

    private void loadTasks() {
        // Demanem totes les tasques
        Cursor cursorTasks = bd.ListOfertas();

        // Now create a simple cursor adapter and set it to display
        scTasks = new adapterTodoIcon(getContext(),
                R.layout.row_oferta_fav,
                cursorTasks,
                from,
                to,
                1, FavFragment.this);


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

        NavHostFragment.findNavController(getParentFragment()).navigate(R.id.action_navigation_Fav_to_mirarFichaFragment, bundle);

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
                            ref = Acceso.collection("Usuarios").document(Identificador);
                            Halo = (ArrayList<Long>) document.getData().get("Fav");


                            if(Halo.isEmpty()){
                                Toast.makeText(getContext(), "NO tienes favoritos.", Toast.LENGTH_LONG).show();
                            }else{
                                for (int i = 0; i < Halo.size(); i++) {
                                    MirarOferta = bd.MirarOfertaFav(Halo.get(i));
                                    MirarOferta.moveToFirst();
                                    Fotoo = MirarOferta.getString(MirarOferta.getColumnIndex(Datasource.FOTO));
                                    CorreoConcesio = MirarOferta.getString(MirarOferta.getColumnIndex(Datasource.CORREOCONCE));
                                    Data_Entradaa = MirarOferta.getString(MirarOferta.getColumnIndex(Datasource.DATA_ENTRADA));
                                    Nombre_Modeloo = MirarOferta.getString(MirarOferta.getColumnIndex(Datasource.NOMBRE_MODELO));
                                    Activaa = MirarOferta.getString(MirarOferta.getColumnIndex(Datasource.ACTIVA));
                                    Marcaa = MirarOferta.getString(MirarOferta.getColumnIndex(Datasource.MARCA));
                                    Precioo = MirarOferta.getString(MirarOferta.getColumnIndex(Datasource.PRECIO));

                                    OfertabdFav = bd.FavOfertas(Fotoo, CorreoConcesio, Data_Entradaa, Nombre_Modeloo, Activaa, Marcaa, Precioo);

                                    Fotoo = "";
                                    Data_Entradaa = "";
                                    Nombre_Modeloo = "";
                                    Activaa = "";
                                    Marcaa = "";
                                    Precioo = "";

                                }
                                MirarOferta.close();
                            }


                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {

                                        loadTasks();
                                    }
                                }, 1000);


                        }
                    } else {
                        Log.d("TAG", "Error getting documents: ", task.getException());
                    }
                }
            });
        } else {
            Toast.makeText(getContext(), "NO estas logeado o registrado.", Toast.LENGTH_LONG).show();
        }
    }

    public void QuitarFav(long id) {

        long IdEliminar = id;
        BorarrFavFirebase(IdEliminar);
        bd.BorrarFav(IdEliminar);
        bd.ResetearIDGNEREALFavOfert();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                loadTasks();
            }
        }, 1000);
    }

    private void BorarrFavFirebase(long idEliminar) {
        Acceso.collection("Usuarios").whereEqualTo("Email", Email).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                        Log.d("TAG", document.getId() + " => " + document.getData());
                        Identificador = document.getId();
                        NombreGuardado = (document.getData().get("Nombre").toString());
                        GmailGuardado = (document.getData().get("Email").toString());
                        TelGuardado = (document.getData().get("Phone").toString());
                        PassGuardado = (document.getData().get("Password").toString());
                        Halo = (ArrayList<Long>) document.getData().get("Fav");

                        for (int i = 0; i < Halo.size(); i++) {
                            if (Halo.get(i) == idEliminar) {
                                Halo.remove(i);
                            }
                        }

                        Map<String, Object> user1 = new HashMap<>();

                        user1.put("Email", GmailGuardado);
                        user1.put("Nombre", NombreGuardado);
                        user1.put("Fav", Halo);
                        user1.put("Phone", TelGuardado);
                        user1.put("Password", PassGuardado);

                        Acceso.collection("Usuarios").document(Identificador).set(user1);
                        //Acceso.collection("Usuarios").document(Identificador).update("Fav", FieldValue.arrayUnion(Halo));
                        //ref.update("Fav", FieldValue.delete());
                    }
                }
            }
        });

    }


    class adapterTodoIcon extends android.widget.SimpleCursorAdapter {


        private FavFragment oTodoListIcon;

        public adapterTodoIcon(Context context, int layout, Cursor c, String[] from, int[] to, int flags, FavFragment FavFra) {
            super(context, layout, c, from, to, flags);
            oTodoListIcon = FavFra;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = super.getView(position, convertView, parent);

            // Agafem l'objecte de la view que es una LINEA DEL CURSOR
            Cursor linia = (Cursor) getItem(position);


            ImageView imagen = view.findViewById(R.id.Imagen_moto);
            Imagencur = linia.getString(linia.getColumnIndex(Datasource.FOTOO));
            Glide.with(getContext()).load(Imagencur).into(imagen);


            TextView Activaa = view.findViewById(R.id.Oferta_Activa);
            Activa = linia.getString(linia.getColumnIndex((Datasource.ACTIVAA)));
            if (Activa.equalsIgnoreCase("0")) {
                Activaa.setText("Activa");
            } else {
                Activaa.setText("Agotada");
            }


            FavRoto = view.findViewById(R.id.FavQuitar);
            FavRoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Cursor linia = (Cursor) getItem(position);
                    oTodoListIcon.QuitarFav(linia.getLong(linia.getColumnIndexOrThrow(Datasource.IDGENERAL)));
                }
            });
            /*
            ImageView Meter_Fav = view.findViewById(R.id.Fav);
            Meter_Fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Cursor linia = (Cursor) getItem(position);

                    oTodoListIcon.Coger_id(linia.getInt(linia.getColumnIndexOrThrow(Datasource.IDGENERAL)));
                }
            });


             */


            return view;
        }
    }
}