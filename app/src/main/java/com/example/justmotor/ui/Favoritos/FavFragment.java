package com.example.justmotor.ui.Favoritos;

import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;

import com.bumptech.glide.Glide;
import com.example.justmotor.R;
import com.example.justmotor.ui.BD.Datasource;
import com.example.justmotor.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;


public class FavFragment extends Fragment {


    private FirebaseUser Usu = FirebaseAuth.getInstance().getCurrentUser();
    private String Email = Usu.getEmail();
    String Identificador;

    Long Guardar_Ids;
    String Imagencur;
    SearchView searchView;
    String Guardar = "";

    ListView lv;
    private long idActual;
    private Datasource bd;
    private adapterTodoIcon scTasks;

    FirebaseFirestore Acceso = FirebaseFirestore.getInstance();


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
        View v = inflater.inflate(R.layout.fragment_fav, container, false);
        bd = new Datasource(getContext());
        lv =  v.findViewById(R.id.list1);
        searchView = v.findViewById(R.id.Comp_Filt_Buscador_Fav);
        MirarPersona();

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
                        1, FavFragment.this);

                lv.setAdapter(scTasks);
                return false;
            }
        });

        String l = String.valueOf(Guardar_Ids);
        Log.d("Id favorito: ", l);
        Log.d("Id numero: ", Guardar);
        //loadTasks(v);

        return v;
    }

    private void loadTasks(View v) {
        // Demanem totes les tasques
        Cursor cursorTasks = bd.FavOfertas(Guardar_Ids);

        // Now create a simple cursor adapter and set it to display
        scTasks = new adapterTodoIcon(getContext(),
                R.layout.row_oferta,
                cursorTasks,
                from,
                to,
                1, FavFragment.this);

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
    }

    private void MirarPersona(){
        Acceso.collection("Usuarios").whereEqualTo("Email",Email).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                        Log.d("TAG", document.getId() + " => " + document.getData());
                        Identificador = document.getId();
                        Guardar = (document.getData().get("Phone").toString());
                        Guardar_Ids = Long.valueOf(document.getData().get("Fav").toString());

                        //usuari = document.getData().get("nom").toString();
                        //Guardar_Ids = (Array[]) Guardar;
                    }
                } else {
                    Log.d("TAG", "Error getting documents: ", task.getException());
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
            Imagencur = linia.getString(linia.getColumnIndex(Datasource.FOTO));
            Glide.with(getContext()).load(Imagencur).into(imagen);

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