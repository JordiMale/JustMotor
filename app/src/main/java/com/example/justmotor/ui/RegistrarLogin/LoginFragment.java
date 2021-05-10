package com.example.justmotor.ui.RegistrarLogin;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.justmotor.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;


public class LoginFragment extends Fragment {

    Button Log;

    String EmailS;
    String PassS;

    String Identificador;

    private TextInputLayout layoutEmail, layoutPassword;
    private TextInputEditText txtEmail, txtPassword;
    private TextView txtSignup;



    FirebaseFirestore Acceso = FirebaseFirestore.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);


        layoutEmail = v.findViewById(R.id.TxtLayoutEmailSignIn);
        layoutPassword = v.findViewById(R.id.TxtLayoutPasswordSignIn);
        txtEmail = v.findViewById(R.id.EmailLogin);
        txtPassword = v.findViewById(R.id.PasswordLogin);
        txtSignup = v.findViewById(R.id.TextAnarSignUp);

        txtSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(getParentFragment()).navigate(R.id.registrarFragment);
            }
        });




        Log = v.findViewById(R.id.btnLoginClient);

        Log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    MetodoLogin();
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
        return v;
    }

    private boolean validate() {
        if(txtEmail.getText().toString().isEmpty()){
            layoutEmail.setErrorEnabled(true);
            layoutEmail.setError("Email is Required");
            return false;
        }

        if(txtPassword.getText().toString().length() < 8){
            layoutPassword.setErrorEnabled(true);
            layoutPassword.setError("Required at least 8 characters");
            return false;
        }
        return true;
    }

    private void MetodoLogin() {
        PassS = txtPassword.getText().toString();
        EmailS = txtEmail.getText().toString();

        if(!PassS.isEmpty() && !EmailS.isEmpty()){
            FirebaseAuth.getInstance().signInWithEmailAndPassword(EmailS, PassS).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        if(user != null){
                            boolean EmailVeri = user.isEmailVerified();

                            String User =  user.getUid();
                        }

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                MirarPersona();

                            }
                        },1000);




                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                NavHostFragment.findNavController(getParentFragment()).navigate(R.id.navigation_Home);
                            }
                        },1000);

                    }else{
                        Toast.makeText(getActivity(), "No esta registrat aquest usuari ", Toast.LENGTH_LONG);
                    }
                }
            });

        }
    }

    private void MirarPersona(){
        Acceso.collection("users").whereEqualTo("Email",EmailS).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                        android.util.Log.d("TAG", document.getId() + " => " + document.getData());
                        Identificador = document.getId();
                        //usuari = document.getData().get("nom").toString();


                    }
                } else {
                    android.util.Log.d("TAG", "Error getting documents: ", task.getException());
                }
            }
        });
    }
}