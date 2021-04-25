package com.example.justmotor.ui.RegistrarLogin;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.text.TextWatcher;
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
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;


public class RegistrarFragment extends Fragment {

    private FirebaseAuth mAuth;


    private TextInputLayout layoutEmail, layoutPassword, layoutConfirm, layoutName;
    private TextInputEditText txtEmail, txtPassword, txtName, txtConfirm;
    private TextView txtSignin;
    ArrayList<String> Fav = new ArrayList<>();

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

        layoutName = v.findViewById(R.id.TxtLayoutNameSignUp);
        layoutEmail = v.findViewById(R.id.TxtLayoutEmailSignUp);
        layoutPassword = v.findViewById(R.id.TxtLayoutPasswordSignUp);
        layoutConfirm = v.findViewById(R.id.TxtLayoutPassword2SignUp);
        txtName = v.findViewById(R.id.Nombre_Registrar);
        txtEmail = v.findViewById(R.id.gmail_Registrar);
        txtPassword = v.findViewById(R.id.contra_Registrar);
        txtConfirm = v.findViewById(R.id.contra2_Registrar);




        txtSignin = v.findViewById(R.id.AnarLoginClient);


        txtSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(getParentFragment()).navigate(R.id.loginFragment);
            }
        });

        Registrar = v.findViewById(R.id.btnRegistrarClient);

        Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ComprobarCampos()) {
                    Registrar();
                }

            }
        });

        txtEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!txtEmail.getText().toString().isEmpty()){
                    layoutEmail.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!txtName.getText().toString().isEmpty()){
                    layoutName.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(txtPassword.getText().toString().length() > 7){
                    layoutPassword.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        txtConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(txtPassword.getText().toString().equalsIgnoreCase(txtConfirm.getText().toString())){
                    layoutConfirm.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return v;
    }

    private boolean ComprobarCampos() {
        if(txtEmail.getText().toString().isEmpty()){
            layoutEmail.setErrorEnabled(true);
            layoutEmail.setError("Email is Required");
            return false;
        }

        if(txtName.getText().toString().isEmpty()){
            layoutName.setErrorEnabled(true);
            layoutName.setError("Name is Required");
            return false;
        }

        if(txtPassword.getText().toString().length() < 8){
            layoutPassword.setErrorEnabled(true);
            layoutPassword.setError("Required at least 8 characters");
            return false;
        }

        if(!txtPassword.getText().toString().equalsIgnoreCase(txtConfirm.getText().toString())){
            layoutConfirm.setErrorEnabled(true);
            layoutConfirm.setError("Password does not match");
            return false;
        }
        return true;
    }

    public void Registrar() {
        GuardarName = txtName.getText().toString();
        GuardarGmail = txtEmail.getText().toString();
        GuardarPassword = txtPassword.getText().toString();
        mAuth.createUserWithEmailAndPassword(GuardarGmail, GuardarPassword)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Map<String, Object> user1 = new HashMap<>();

                            user1.put("Email", GuardarGmail);
                            user1.put("Nombre", GuardarName);
                            user1.put("Fav", Fav);
                            user1.put("Password", GuardarPassword);


                            db.collection("Usuarios")
                                    .add(user1)
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            Log.d("TAG", "DocumentSnapshot added with ID: " + documentReference.getId());
                                            NavHostFragment.findNavController(getParentFragment()).navigate(R.id.nav_home);


                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.w("TAG", "Error adding document", e);
                                        }
                                    });

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