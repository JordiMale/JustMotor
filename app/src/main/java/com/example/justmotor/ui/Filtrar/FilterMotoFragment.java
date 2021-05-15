package com.example.justmotor.ui.Filtrar;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

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
        View v = inflater.inflate(R.layout.fragment_filter_moto, container, false);


        searchView = v.findViewById(R.id.Comp_Filt_Buscador);
        Referesh = v.findViewById(R.id.RefreshLayoutFiltroCompara);
        bd = new Datasource(getContext());
        lv = v.findViewById(R.id.list1);

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

                return true;
            }
        });


        return v;
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


            return view;
        }
    }
}


