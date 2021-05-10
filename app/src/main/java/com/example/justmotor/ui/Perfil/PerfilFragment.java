package com.example.justmotor.ui.Perfil;

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
import android.widget.EditText;
import android.widget.Toast;

import com.example.justmotor.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;
import java.util.Objects;


public class PerfilFragment extends Fragment {

    Button AcceptarPerfil;
    Button CancelarPerfil;

    EditText EtNombreUsu, EtGmail, EtTelefono;
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

            View v = inflater.inflate(R.layout.fragment_perfil, container, false);



            EtNombreUsu = (EditText) v.findViewById(R.id.EtNombreUsu);
            EtGmail = (EditText) v.findViewById(R.id.EtGmail);
            EtTelefono = (EditText) v.findViewById(R.id.EtTelefono);


            AcceptarPerfil = (Button) v.findViewById(R.id.AcceptarPerfil);
            CancelarPerfil = (Button) v.findViewById(R.id.CancelaPerfil);


            MirarPersona();

            AcceptarPerfil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavHostFragment.findNavController(getParentFragment()).navigate(R.id.navigation_Opciones);
                }
            });

            CancelarPerfil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NavHostFragment.findNavController(getParentFragment()).navigate(R.id.navigation_Opciones);
                }
            });

        return v;

    }


    private void MirarPersona() {
        Acceso.collection("Usuarios").whereEqualTo("Email", Email).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                        Log.d("TAG", document.getId() + " => " + document.getData());
                        NombreGuardado = (document.getData().get("Nombre").toString());
                        GmailGuardado = (document.getData().get("Email").toString());
                        TelGuardado = (document.getData().get("Phone").toString());
                        PassGuardado = (document.getData().get("Password").toString());

                        EtNombreUsu.setText(NombreGuardado);
                        EtGmail.setText(GmailGuardado);
                        EtTelefono.setText(TelGuardado);


                    }
                } else {
                    Log.d("TAG", "Error getting documents: ", task.getException());
                }
            }
        });

    }



}