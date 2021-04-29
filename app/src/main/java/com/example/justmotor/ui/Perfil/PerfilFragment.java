package com.example.justmotor.ui.Perfil;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.justmotor.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;


public class PerfilFragment extends Fragment {

    Button AcceptarPerfil;
    Button CancelarPerfil;
    String NombreGuardado;
    String GmailGuardado;
    String TelGuardado;
    String PassGuardado;

    FirebaseFirestore Acceso = FirebaseFirestore.getInstance();
    private FirebaseUser Usu = FirebaseAuth.getInstance().getCurrentUser();
    private String Email = Usu.getEmail();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_perfil, container, false);

        //MirarPersona();

        return v;
    }

    /*
    private void MirarPersona() {
        Acceso.collection("users").whereEqualTo("Email", Email).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                        Log.d("TAG", document.getId() + " => " + document.getData());
                        NombreGuardado.add(document.getData().get("Nombre").toString());
                        GmailGuardado.add(Integer.parseInt(document.getData().get("Puntuación").toString()));
                        TelGuardado.add();
                        PassGuardado.add();
                    }
                } else {
                    Log.d("TAG", "Error getting documents: ", task.getException());
                }
            }
        });

        Aux = 1;

        Acceso.collection("users").whereNotEqualTo("Email",Email).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                        Log.d("TAG", document.getId() + " => " + document.getData());
                        NombresGuardados.add(document.getData().get("Nombre").toString());
                        NumerosGuardados.add(Integer.parseInt(document.getData().get("Puntuación").toString()));
                        Aux++;
                    }

                } else {
                    Log.d("TAG", "Error getting documents: ", task.getException());
                }
            }
        });
    }

     */
}