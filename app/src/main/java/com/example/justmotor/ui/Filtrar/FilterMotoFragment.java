package com.example.justmotor.ui.Filtrar;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.justmotor.R;
import com.example.justmotor.ui.BD.Datasource;
import com.example.justmotor.ui.GetSet.Ficha_Tecnica_Modelo;
import com.example.justmotor.ui.GetSet.Marca;
import com.example.justmotor.ui.GetSet.Mix_Oferta;
import com.example.justmotor.ui.GetSet.Modelo;
import com.example.justmotor.ui.home.HomeFragment;

import java.util.ArrayList;


public class FilterMotoFragment extends Fragment {

    private Datasource bd;
    private long idActual;
    SwipeRefreshLayout Referesh;
    private adapterTodoIcon scTasks;
    SearchView searchView;
    ListView lv;
    String Activa;
    String Imagencur;
    int Recibir_Id_Moto_1 = 0;

    private static String[] from = new String[]{
            Datasource.FOTO,
            Datasource.CORREOCONCE,
            Datasource.DATA_ENTRADA,
            Datasource.ACTIVA,
            Datasource.MARCA,
            Datasource.PRECIO,
            Datasource.NOMBRE_MODELO,};

    private static int[] to = new int[]{
            R.id.Imagen_moto_Filtro_Escoger,
            R.id.Oferta_Gmail_Filtro_Escoger,
            R.id.Oferta_Data_Entrada_Filtro_Escoger,
            R.id.Oferta_Activa_Filtro_Escoger,
            R.id.Oferta_Nombre_Marca_Filtro_Escoger,
            R.id.Oferta_Precio_Filtro_Escoger,
            R.id.Oferta_Nombre_Modelo_Filtro_Escoger,};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_filter_moto, container, false);


        searchView = v.findViewById(R.id.Comp_Filt_Buscador);
        Referesh = v.findViewById(R.id.RefreshLayoutFiltroCompara);
        bd = new Datasource(getContext());
        lv = v.findViewById(R.id.list1);


        //Recibir_Id_Moto_1 = getArguments().getInt("id");




        loadTasks(v);

        Referesh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(getContext(), "Has echo un refresh", Toast.LENGTH_LONG).show();
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
                        R.layout.row_oferta_escojer_moto,
                        CursorFilt,
                        from,
                        to,
                        1, FilterMotoFragment.this);

                lv.setAdapter(scTasks);
                return false;
            }
        });


        return v;
    }

    private void Coger_id(int id) {

        int Guardar_Primer_Id = id;

        Bundle bundle = new Bundle();
        bundle.putLong("id", Guardar_Primer_Id);

        NavHostFragment.findNavController(getParentFragment()).navigate(R.id.action_filterMotoFragment_to_nav_comparador, bundle);

    }

    private void loadTasks(View v) {
        // Demanem totes les tasques
        Cursor cursorTasks = bd.Todo_Oferta();

        // Now create a simple cursor adapter and set it to display
        scTasks = new adapterTodoIcon(getContext(),
                R.layout.row_oferta_escojer_moto,
                cursorTasks,
                from,
                to,
                1, FilterMotoFragment.this);

        lv = (ListView) v.findViewById(R.id.list1);
        lv.setAdapter(scTasks);

        lv.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> arg0, View view,
                                            int position, long id) {

                        //modifiquem el id
                        //MirarOferta(id);
                    }


                }
        );
    }


    class adapterTodoIcon extends android.widget.SimpleCursorAdapter {


        private FilterMotoFragment oTodoListIcon;

        public adapterTodoIcon(Context context, int layout, Cursor c, String[] from, int[] to, int flags, FilterMotoFragment FiltFra) {
            super(context, layout, c, from, to, flags);
            oTodoListIcon = FiltFra;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = super.getView(position, convertView, parent);

            // Agafem l'objecte de la view que es una LINEA DEL CURSOR
            Cursor linia = (Cursor) getItem(position);

            ImageView imagen = view.findViewById(R.id.Imagen_moto_Filtro_Escoger);
            Imagencur = linia.getString(linia.getColumnIndex(Datasource.FOTO));
            Glide.with(getContext()).load(Imagencur).into(imagen);


            TextView Activaa = view.findViewById(R.id. Oferta_Activa_Filtro_Escoger);
            Activa = linia.getString(linia.getColumnIndex((Datasource.ACTIVA)));
            if(Activa.equalsIgnoreCase("0")){
                Activaa.setText("Activa");
            }else{
                Activaa.setText("Agotada");
            }
            ImageView Coger_Id = view.findViewById(R.id.Cojer_Id);
            Coger_Id.setOnClickListener(new View.OnClickListener() {
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


