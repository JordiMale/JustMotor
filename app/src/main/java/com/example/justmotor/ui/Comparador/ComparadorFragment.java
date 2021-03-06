package com.example.justmotor.ui.Comparador;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.justmotor.R;

public class ComparadorFragment extends Fragment {

    Button btnMoto1;
    Button btnMoto2;
    Button Ir_Comparador_Final;

    long Guardar_Primer_Id_Moto = 0;
    long[] Guardar_Segundo_Id_Moto = new long[2];

    long GuradarId1Final;
    long GuradarId2Final;
    boolean Verif = false;
    boolean Vefir2 = false;
    boolean IrComp = false;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_comparador, container, false);

        btnMoto1 = v.findViewById(R.id.btnMoto1);
        btnMoto2 = v.findViewById(R.id.btnMoto2);
        Ir_Comparador_Final = v.findViewById(R.id.Boton_Ir_Comparador_Final);

        btnMoto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NavHostFragment.findNavController(getParentFragment()).navigate(R.id.filterMotoFragment);
            }
        });

        if(getArguments() != null){
            Guardar_Primer_Id_Moto = getArguments().getLong("id");

            //GuradarId1Final = Guardar_Segundo_Id_Moto[0];
            //GuradarId2Final = Guardar_Segundo_Id_Moto[1];
            Vefir2 = true;

        }


            if(getArguments() != null){
                Guardar_Segundo_Id_Moto = getArguments().getLongArray("idz");
                IrComp = true;
            }






            btnMoto2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Vefir2 == true){
                    Bundle bundle = new Bundle();
                    bundle.putLong("id", Guardar_Primer_Id_Moto);

                    NavHostFragment.findNavController(getParentFragment()).navigate(R.id.action_nav_comparador_to_nav_Filter, bundle);
                    }else{
                        Toast.makeText(getContext(), "Escoje la primera moto antes", Toast.LENGTH_LONG).show();
                    }
                }
            });




            Ir_Comparador_Final.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(IrComp == true){
                    Bundle bundle = new Bundle();
                    bundle.putLongArray("idz", Guardar_Segundo_Id_Moto);


                    NavHostFragment.findNavController(getParentFragment()).navigate(R.id.action_nav_comparador_to_comparadorFinalFragment, bundle);
                    }else{
                        Toast.makeText(getContext(), "Escoje las motos para poder ir.", Toast.LENGTH_LONG).show();
                    }
                }
            });





        return v;
    }
}
