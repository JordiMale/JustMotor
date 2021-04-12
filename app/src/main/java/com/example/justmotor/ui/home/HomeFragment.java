package com.example.justmotor.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.justmotor.R;
import com.example.justmotor.ui.Filtrar.FilterMotoFragment;
import com.example.justmotor.ui.GetSet.Mix_Oferta;
import com.example.justmotor.ui.GetSet.Modelo;
import com.example.justmotor.ui.RegistrarLogin.RegistrarFragment;

import java.util.ArrayList;

public class HomeFragment extends Fragment {


    Button btnRegistrarLogear;

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

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        btnRegistrarLogear = v.findViewById(R.id.Login_Registrar_Home);

        btnRegistrarLogear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(getParentFragment()).navigate(R.id.registrarFragment);
            }
        });

        searchView = v.findViewById(R.id.Comp_Filt_Buscador_Home);

        //searchView = v.findViewById(R.id.Comp_Filt_Buscador);
        listView = v.findViewById(R.id.list1);

        Modelo = new ArrayList<>();
        Marca = new ArrayList<>();
        Ficha_Tecnica_Modelo = new ArrayList<>();

        Total = new ArrayList<>();

        Modelo MiModelito = new Modelo(1,1, 1, "Carretera", "MT125", "Muy veloz");


        Mix_Oferta MiTotal = new Mix_Oferta("moto", "Yamaha", MiModelito.getNombre_Modelo(), "34543564");
        Mix_Oferta MiTotal2 = new Mix_Oferta("Motos", "Motorola", MiModelito.getNombre_Modelo(), "3454");

        Total.add(MiTotal);
        Total.add(MiTotal2);


        HomeFragment.AdaptadorElements OfertaTotal = new AdaptadorElements(getContext(), Total);

        listView.setAdapter(OfertaTotal);



        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
            ArrayList<Mix_Oferta>Total2 = new ArrayList<>();
                for(int i = 0; i < Total.size(); i++){
                    if(Total.get(i).getNombre_Marca().equalsIgnoreCase(s)){
                        Total2.add(Total.get(i));
                        OfertaTotal.getFilter().filter(s);
                    }
                }

                if(s.equals("")){
                    HomeFragment.AdaptadorElements OfertaTotal = new AdaptadorElements(getContext(), Total);

                    listView.setAdapter(OfertaTotal);
                }else{
                    HomeFragment.AdaptadorElements OfertaTotal2 = new AdaptadorElements(getContext(), Total2);

                    listView.setAdapter(OfertaTotal2);
                }
                return true;
            }
        });
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

