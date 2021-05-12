package com.example.justmotor.ui.Settings;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.justmotor.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;

public class SetingsFragment extends Fragment {

    Button Perfil;
    Button Terminos;
    Button Politica;
    Button Ayuda;



    private FirebaseUser Usu = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_setings, container, false);


        Perfil = v.findViewById(R.id.Perfil);
        Terminos = v.findViewById(R.id.terminos);
        Politica = v.findViewById(R.id.Politica);
        Ayuda = v.findViewById(R.id.Ayuda);




        Perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Usu != null ){
                    NavHostFragment.findNavController(getParentFragment()).navigate(R.id.perfilFragment);
                }else{
                    Toast.makeText(getContext(), "NO estas registrat o logueat.", Toast.LENGTH_LONG).show();
                    return;
                }

            }
        });

        Terminos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(getParentFragment()).navigate(R.id.terminosFragment);
            }
        });

        Politica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NavHostFragment.findNavController(getParentFragment()).navigate(R.id.privacidadFragment);
            }
        });

        Ayuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(getParentFragment()).navigate(R.id.ayudaFragment);
            }
        });

        return v;
    }


}