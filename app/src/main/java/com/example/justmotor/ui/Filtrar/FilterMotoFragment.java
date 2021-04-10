package com.example.justmotor.ui.Filtrar;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.justmotor.R;
import com.example.justmotor.ui.GetSet.Ficha_Tecnica_Modelo;
import com.example.justmotor.ui.GetSet.Marca;
import com.example.justmotor.ui.GetSet.Mix_Oferta;
import com.example.justmotor.ui.GetSet.Modelo;

import java.util.ArrayList;


public class FilterMotoFragment extends Fragment {


    SearchView searchView;
    ListView listView;
    ArrayList<com.example.justmotor.ui.GetSet.Modelo> Modelo;
    ArrayList<com.example.justmotor.ui.GetSet.Marca> Marca;
    ArrayList<com.example.justmotor.ui.GetSet.Ficha_Tecnica_Modelo> Ficha_Tecnica_Modelo;
    ArrayList<Mix_Oferta> Total;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_filter_moto, container, false);


        //searchView = v.findViewById(R.id.Comp_Filt_Buscador);
        listView = v.findViewById(R.id.list1);

        Modelo = new ArrayList<>();
        Marca = new ArrayList<>();
        Ficha_Tecnica_Modelo = new ArrayList<>();

        Total = new ArrayList<>();

        Modelo MiModelito = new Modelo(1,1, 1, "Carretera", "MT125", "Muy veloz");


        Mix_Oferta MiTotal = new Mix_Oferta("moto", "Hola", MiModelito.getNombre_Modelo(), "34543564");

        Total.add(MiTotal);


        FilterMotoFragment.AdaptadorElements OfertaTotal = new AdaptadorElements(getContext(), Total);

        listView.setAdapter(OfertaTotal);

        return v;
    }

    class AdaptadorElements extends ArrayAdapter<Mix_Oferta> {

        public AdaptadorElements(Context context, ArrayList<Mix_Oferta> total) {
            super(context, R.layout.row_oferta, total);
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = LayoutInflater.from(getContext());
            View item = inflater.inflate(R.layout.row_oferta, null);

            TextView Nombre = (TextView) item.findViewById(R.id.Oferta_Nombre_Marca);
            Nombre.setText(Total.get(position).getNombre_Marca());

            TextView Lletra = (TextView) item.findViewById(R.id.Oferta_Nombre_Modelo);
            Lletra.setText(Total.get(position).getNombre_Modelo());

            TextView Numero = (TextView) item.findViewById(R.id.Oferta_Precio);
            Numero.setText(Total.get(position).getPrecio());
/*
            TextView Peso = (TextView) item.findViewById(R.id.Peso);
            Peso.setText(Elements[position].getPes());

 */
            return item;
        }
    }
}