package com.example.justmotor.ui.home;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
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

import cz.msebera.android.httpclient.Header;

public class HomeFragment extends Fragment {




    SearchView searchView;
    ListView listView;
    private AsyncHttpResponseHandler handler;
    ArrayList<com.example.justmotor.ui.GetSet.Modelo> Modelo;
    ArrayList<com.example.justmotor.ui.GetSet.Marca> Marca;
    ArrayList<com.example.justmotor.ui.GetSet.Ficha_Tecnica_Modelo> Ficha_Tecnica_Modelo;
    ArrayList<Mix_Oferta> Total;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_home, container, false);

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

        HacerPeticionApi();

        searchView = v.findViewById(R.id.Comp_Filt_Buscador_Home);

        //searchView = v.findViewById(R.id.Comp_Filt_Buscador);
        listView = v.findViewById(R.id.list1);

        Modelo = new ArrayList<>();
        Marca = new ArrayList<>();
        Ficha_Tecnica_Modelo = new ArrayList<>();

        Total = new ArrayList<>();

        Modelo MiModelito = new Modelo(1,1, 1, "Carretera", "MT125", "Muy veloz");

        Mix_Oferta MiTotal = new Mix_Oferta("Motos", "Yamaha", MiModelito.getNombre_Modelo(), "34543564");
        Mix_Oferta MiTotal2 = new Mix_Oferta("Motos", "Motorola", MiModelito.getNombre_Modelo(), "3454");
        Mix_Oferta MiTotal3 = new Mix_Oferta("Motos", "Coche", MiModelito.getNombre_Modelo(), "3454");
        Mix_Oferta MiTotal4 = new Mix_Oferta("Motos", "Coche", MiModelito.getNombre_Modelo(), "3454");
        Mix_Oferta MiTotal5 = new Mix_Oferta("Motos", "Coche", MiModelito.getNombre_Modelo(), "3454");
        Mix_Oferta MiTotal6 = new Mix_Oferta("Motos", "Coche", MiModelito.getNombre_Modelo(), "3454");

        Total.add(MiTotal);
        Total.add(MiTotal2);
        Total.add(MiTotal3);
        Total.add(MiTotal4);
        Total.add(MiTotal5);
        //Total.add(MiTotal6);

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


    private void HacerPeticionApi() {
        AsyncHttpClient client = new AsyncHttpClient();
        client.setMaxRetriesAndTimeout(1, 10000);
        crearHandlerParaPeticon(client);

        String url = Conexion.URL;
        url += "api/";
        client.get(url, this.handler);

    }



    private void crearHandlerParaPeticon(AsyncHttpClient client) {
        handler = new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String respnse = new String(responseBody);

                try {
                    JSONArray jsonArray = new JSONArray(respnse);

                    for(int i = 0; i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String Modelo = jsonObject.getString("Modelo");

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

