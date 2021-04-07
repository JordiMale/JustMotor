package com.example.justmotor.ui.RegistrarLogin;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.justmotor.R;
import com.example.justmotor.ui.home.HomeFragment;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;


public class RegistrarFragment extends Fragment {

    private FirebaseAuth mAuth;
    TextView TextAnarLogin;
    TextView TextAnarRegistrarConse;

    EditText NameClient;
    EditText GmailClient;
    EditText PasswordClient;

    Button Registrar;

    String GuardarName;
    String GuardarGmail;
    String GuardarPassword;


    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_registrar, container, false);
        mAuth = FirebaseAuth.getInstance();
        NameClient = v.findViewById(R.id.Nombre_Registrar);
        GmailClient = v.findViewById(R.id.gmail_Registrar);
        PasswordClient = v.findViewById(R.id.contra_Registrar);

        TextAnarLogin = v.findViewById(R.id.AnarLoginClient);


        TextAnarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(getParentFragment()).navigate(R.id.loginFragment);
            }
        });

        TextAnarRegistrarConse = v.findViewById(R.id.AnarRegistrarConse);

        TextAnarRegistrarConse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(getParentFragment()).navigate(R.id.registrarConseFragment);
            }
        });

        Registrar = v.findViewById(R.id.btnRegistrarClient);

        Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GuardarName = NameClient.getText().toString();
                GuardarGmail = GmailClient.getText().toString();
                GuardarPassword = PasswordClient.getText().toString();

                Registrar();
                NavHostFragment.findNavController(getParentFragment()).navigate(R.id.nav_home);

            }
        });


        return v;
    }

    public void Registrar(){
        mAuth.createUserWithEmailAndPassword( GuardarGmail, GuardarPassword)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Map<String, Object> user1 = new HashMap<>();

                            user1.put("Email", GuardarGmail);
                            user1.put("Password", GuardarPassword);
/*
                            db.collection("users")
                                    .add(user1)
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());



                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.w("TAG", "Error adding document", e);
                                        }
                                    });
*/
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getActivity(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });


    }
}